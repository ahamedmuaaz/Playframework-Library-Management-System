package models;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import data.Borrowed;
import data.Reservation;
import org.bson.Document;
import play.libs.Json;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WestminsterLibraryManager implements LibraryManager {
    //mongodb cloud connection
    MongoClientURI connectionString = new MongoClientURI("mongodb://ahamedmuaaz:1234@cluster0-shard-00-00-7q6qk.gcp.mongodb.net:27017,cluster0-shard-00-01-7q6qk.gcp.mongodb.net:27017,cluster0-shard-00-02-7q6qk.gcp.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");

    MongoClient mongo = new MongoClient(connectionString);

    // Accessing the database
    MongoDatabase library = mongo.getDatabase("library");

    // Retrieving a collection
    MongoCollection<Document> books = library.getCollection("books");
    MongoCollection<Document> dvds = library.getCollection("dvds");
    MongoCollection<Document> reader = library.getCollection("reader");
    MongoCollection<Document> reservation = library.getCollection("reservation");

    //constructor
    public WestminsterLibraryManager() {
        //load all data to lists
        refreshList();
        refreshReader();
        refreshReservation();
    }

    //lists
    public static List<LibraryItem> listOfLibraryItems = new ArrayList<>();
    private static List<Reader> listOfReaders = new ArrayList<>();
    private static List<Borrowed> BorrowedList = new ArrayList<>();
    public static List<Reservation> ReservstionList = new ArrayList<>();

    @Override

    //Display Method
    public List<LibraryItem> DispalyItems() {
        //return a list of library item
        return listOfLibraryItems;
    }

    // ADD Method
    @Override
    public String Additem(LibraryItem item) {
        //converting the libraryitem object to json object
        String bk = Json.asciiStringify(Json.toJson(item));

        //creating documents to save data
        Document addbook = new Document("ISBN", item.getIsbn())
                .append("Book", bk);
        Document adddvd = new Document("ISBN", item.getIsbn())
                .append("Dvd", bk);
        // return variable
        String message = "";
        if (item instanceof Book) {
            if (books.count() < 100) {

                //insert into book collection in mongodb
                books.insertOne(addbook);
                //add to list
                listOfLibraryItems.add(item);
                //return message
                message = "Another " + (100 - books.count()) + "Books can be addded";


            } else {
                //return message
                message = "No Space To add your Book";
            }
        } else {
            if (dvds.count() < 50) {
                //add to list
                listOfLibraryItems.add(item);
                //insert into dvd collection
                dvds.insertOne(adddvd);
                //return message
                message = "Another " + (50 - dvds.count()) + " Dvds can be addded";


            } else {
                //return message
                message = "No Space To add your Dvd";
            }
        }

        return message;

    }

    //DeleteMethod
    @Override
    public String DeleteItem(String ISBN) {
        // check weather isbn is valid
        LibraryItem obj = ISBNcheck(ISBN);

        if (obj != null) {

            if (obj.getCurrentReader() == null) {
                if (obj instanceof Book) {
                    //delete from database collection
                    books.deleteOne(Filters.eq("ISBN", obj.getIsbn()));
                    //delete from list
                    listOfLibraryItems.remove(obj);
                    //return messsage after deletion
                    return "It Is A Book Another " + (100 - books.count()) + " Slots Left";

                } else if (obj instanceof Dvd) {
                    //delete from database collection
                    dvds.deleteOne(Filters.eq("ISBN", obj.getIsbn()));
                    //delete from list
                    listOfLibraryItems.remove(obj);
                    //return message after deletion
                    return "It Is A Dvd, Another " + (50 - dvds.count()) + " Slots Left";
                }
            } else {
                //return message if item borrowed
                return "Item Borrowed Cannot delete!";

            }
        } else {
            //return message if isbn not found
            return "!Invalid Isbn";
        }
        return null;

    }

    //Borrow Method
    @Override
    public void BorrowItem(LibraryItem item) {
        //to get current date and time for borrow
        LocalDateTime localDate = LocalDateTime.now();

        //set borrow date and time of library item
        item.setBorrowedDate(new DateTime(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear(), localDate.getMinute(), localDate.getHour(), localDate.getSecond()));

        //convert library item to json object string
        String bk = Json.asciiStringify(Json.toJson(item));

        if (item instanceof Book) {
            //update document with borrowed date
            books.updateOne(Filters.eq("ISBN", item.getIsbn()), Updates.set("Book", bk));
            // refresh list
            refreshList();
            //refresh borrowedlist
            getBorrowedList();


        } else if (item instanceof Dvd) {
            //update document with borrowed date
            dvds.updateOne(Filters.eq("ISBN", item.getIsbn()), Updates.set("Dvd", bk));
            // refresh list
            refreshList();
            //refresh borrowedlist
            getBorrowedList();
        }


    }

    //Return Method
    @Override
    public double ReturnItem(LibraryItem item) {
        //calculating fine for return item
        double fineamount = finecalculation(item);

        if (item instanceof Book) {
            //clear borrow details
            item.setBorrowedDate(null);
            item.setCurrentReader(null);
            // convert to json object string
            String bk = Json.asciiStringify(Json.toJson(item));
            //update document
            books.updateOne(Filters.eq("ISBN", item.getIsbn()), Updates.set("Book", bk));

            //reload list from database
            refreshList();
            //reload borrowed list
            getBorrowedList();

        }


        if (item instanceof Dvd) {

            //clear borrow details
            item.setBorrowedDate(null);
            item.setCurrentReader(null);

            // convert to json object string
            String bk = Json.asciiStringify(Json.toJson(item));

            //update document
            dvds.updateOne(Filters.eq("ISBN", item.getIsbn()), Updates.set("Dvd", bk));

            //reload list from database
            refreshList();
            //reload borrow list
            getBorrowedList();

        }
        //return fine amount
        return fineamount;
    }

    //Generate Report
    @Override
    public List<Borrowed> GenerateReport() {

        //return borrowed list
        getBorrowedList();
        return BorrowedList;
    }

    //method to reload data from database to list
    public void refreshList() {
        //getting all documents from collections
        FindIterable<Document> docbook = books.find();
        FindIterable<Document> docdvd = dvds.find();

        //clear preloaded data
        listOfLibraryItems.clear();

        //travers through documents and add to list
        for (Document d : docbook) {

            Book r = Json.fromJson(Json.parse(d.get("Book").toString()), Book.class);
            listOfLibraryItems.add(r);
        }
        for (Document d : docdvd) {

            Dvd r = Json.fromJson(Json.parse(d.get("Dvd").toString()), Dvd.class);
            listOfLibraryItems.add(r);
        }


    }

    //Method to add a reader
    public void addReader(Reader r) {
        //add reader to list
        this.readerList().add(r);
        //convert to json object string
        String rStr = Json.asciiStringify(Json.toJson(r));
        //create document
        Document add = new Document("ID", r.getId())
                .append("Reader", rStr);
        //save in database
        reader.insertOne(add);

    }

    //Method to View Readers
    public List<Reader> readerList() {
        //return list of readers
        return listOfReaders;
    }

    //Method which Refersh Readerlist from Database
    public void refreshReader() {
        //get all documents from reader collection
        FindIterable<Document> docreader = reader.find();
        //clear list of readers
        listOfReaders.clear();
        //traverse and add readers to list
        for (Document d : docreader) {
            Reader r = Json.fromJson(Json.parse(d.get("Reader").toString()), Reader.class);
            listOfReaders.add(r);

        }


    }

    //Method to Get Borrowed Items List
    public void getBorrowedList() {
        //clear borrowed list
        BorrowedList.clear();

        for (int i = 0; i < listOfLibraryItems.size(); i++) {
            if (listOfLibraryItems.get(i).getCurrentReader() != null) {
                double fineamount = finecalculation(listOfLibraryItems.get(i));
                Borrowed b = new Borrowed(fineamount, listOfLibraryItems.get(i));
                BorrowedList.add(b);
            }
        }
        //sort borrowed list according to longest borrow
        Collections.sort(BorrowedList);
    }

    //Method which calculates fine
    public double finecalculation(LibraryItem item) {
        //To get Current Date And Time
        LocalDateTime localDate = LocalDateTime.now();
        //Creating a DateTime object using current time
        DateTime d = new DateTime(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear(), localDate.getMinute(), localDate.getHour(), localDate.getSecond());


        int diff = 0;

        try {
            //calculate time difference in hours
            diff = d.diffDate(item.getBorrowedDate());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        //to save remaining fine hours
        int fineday = 0;
        //to save fine amount
        double fineamount = 0;


        if (item instanceof Book) {
            // check weather borrowed time greater than 7 days
            if (diff > 168) {
                //deducting the allowed seven days
                fineday = diff - 168;
                //the check weather remining hours greater than three days
                if (fineday > 72) {
                    // for first 3 days
                    fineamount += 72 * 0.2;
                    //3 days onwards
                    fineday -= 72;
                    fineamount += fineday * 0.5;

                    //normal fine applied
                } else {
                    fineamount += fineday * 0.2;
                }
            }


        }


        if (item instanceof Dvd) {
            if (diff > 72) {
                fineday = diff - 72;
                if (fineday > 72) {
                    fineamount += 72 * 0.2;
                    fineday -= 72;
                    fineamount += fineday * 0.5;

                } else {
                    fineamount += fineday * 0.2;
                }
            }


            System.out.println("Fine Amount:" + fineamount);
        }


        NumberFormat nf = NumberFormat.getInstance();

        return Double.parseDouble(nf.format(fineamount));
    }

    //Method to check ISBN Validity
    public LibraryItem ISBNcheck(String isbn) {
        //trevaers through list to fine relevent item
        LibraryItem result = null;
        for (LibraryItem each : WestminsterLibraryManager.listOfLibraryItems) {
            if (each.getIsbn().equals(isbn)) {
                result = each;
            }
        }
        return result;

    }

    // Method to check Reader Validity
    public Reader Readercheck(String id) {
        //raverse through list to find relevent reader
        Reader result = null;
        for (Reader each : listOfReaders) {
            if (each.getId().equals(id)) {
                result = each;
                System.out.println("entered");
            }
        }
        return result;
    }

    //autogeneratedid
    public int autogen() {
        int id = 0;
        //if list size is zero
        if (listOfReaders.size() == 0) {
            id = 1;
        } else {
            Reader last = null;
            for (Reader each : listOfReaders) {
                last = each;
            }
            id = Integer.valueOf(last.getId()) + 1;
        }
        return id;
    }

    //method to delete a reader
    public void deleteReader(Reader r) {
        //delete reader from list and mongo collection
        reader.deleteOne(Filters.eq("ID", r.getId()));
        listOfReaders.remove(r);


    }

    //method to add a reservation
    public String addreservation(String isbn, String id) {
        LocalDateTime t = LocalDateTime.now();

        //variable which has the Reader information if found
        Reader foundedreader = Readercheck(id);

        //variable which has the library item information if found
        LibraryItem foundeditem = ISBNcheck(isbn);

        //variable to save all messages and return
        String result = null;

        if (foundeditem != null) {

            if (foundedreader != null) {

                if (foundeditem.getCurrentReader() != null) {
                    //check weather item is passed borrowlimit if not when it will be available
                    int limit = checborrowlimit(foundeditem);
                    //list which contains all reservations for a paticular item
                    List<Reservation> allreservations = findallreservation(isbn);

                    if (limit >= 0) {
                        if (allreservations.size() > 0) {

                            Reservation lastReservation = allreservations.get(allreservations.size() - 1);

                            LocalDateTime m = t.plusDays(Reservationlimit(lastReservation));

                            DateTime ReservedDate = new DateTime(m.getDayOfMonth(), m.getMonthValue(), m.getYear(), m.getMinute(), m.getHour(), m.getSecond());

                            Reservation r = new Reservation(String.valueOf(autogenreservation()), foundedreader.getId(), ReservedDate, foundeditem.getIsbn());

                            String res = Json.asciiStringify(Json.toJson(r));

                            Document addreservation = new Document("ID", r.getReservationId()).append("reservation", res);

                            reservation.insertOne(addreservation);
                            ReservstionList.add(r);

                            result = "Your Reservation Id " + r.getReservationId();
                        } else {


                            LocalDateTime m = t.plusDays(limit);

                            DateTime ReservedDate = new DateTime(m.getDayOfMonth(), m.getMonthValue(), m.getYear(), m.getMinute(), m.getHour(), m.getSecond());

                            Reservation r = new Reservation(String.valueOf(autogenreservation()), foundedreader.getId(), ReservedDate, foundeditem.getIsbn());

                            String res = Json.asciiStringify(Json.toJson(r));

                            Document addreservation = new Document("ID", r.getReservationId()).append("reservation", res);

                            reservation.insertOne(addreservation);
                            ReservstionList.add(r);

                            result = "Your Reservation Id " + r.getReservationId();
                        }


                    } else {
                        result = "Sorry Limit Exceeded Cant make sure return date";
                    }

                } else {
                    result = "Item Not Borrowed";
                }
            } else {
                result = "Reader Not Found";
            }

        } else {
            result = "Item Not Found";
        }

        return result;

    }

    //method which returns borrow limit and balance days for a borrow to return
    public int checborrowlimit(LibraryItem item) {
        //current time and date
        LocalDateTime dt = LocalDateTime.now();
        DateTime now = new DateTime(dt.getDayOfMonth(), dt.getMonthValue(), dt.getYear(), dt.getMinute(), dt.getHour()
                , dt.getSecond());
        int dif = 0;
        int canborrow = 0;
        try {
            dif = now.diffDate(item.getBorrowedDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (item instanceof Book) {
            canborrow = 7 - (dif / 24);
        }
        if (item instanceof Dvd) {
            canborrow = 3 - (dif / 24);
        }
        return canborrow;
    }

    //method to call all data saved in reservation database
    public void refreshReservation() {
        FindIterable<Document> docreader = reservation.find();

        ReservstionList.clear();

        for (Document d : docreader) {
            Reservation r = Json.fromJson(Json.parse(d.get("reservation").toString()), Reservation.class);
            ReservstionList.add(r);
            System.out.println(r.toString());

        }


    }

    //method to find all reservations for a paticular item
    public List<Reservation> findallreservation(String isbn) {
        List<Reservation> allresrvations = new ArrayList<Reservation>();
        for (Reservation each : ReservstionList) {
            if (each.getIsbn().equals(isbn)) {
                allresrvations.add(each);
            }
        }
        return allresrvations;
    }

    //method to calculate reservation time gap between two reservations
    public int Reservationlimit(Reservation r) {
        LocalDateTime dt = LocalDateTime.now();
        LibraryItem founded = ISBNcheck(r.getIsbn());

        DateTime now = new DateTime(dt.getDayOfMonth(), dt.getMonthValue(), dt.getYear(), dt.getMinute(), dt.getHour()
                , dt.getSecond());
        int dif = 0;
        int canborrow = 0;
        try {
            dif = now.diffDate(r.getReservedDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (founded instanceof Book) {
            canborrow = 7 - (dif / 24);
        }
        if (founded instanceof Dvd) {
            canborrow = 3 - (dif / 24);
        }
        return canborrow;
    }

    //method to autogenerate reservation id
    public int autogenreservation() {
        int id = 0;
        if (ReservstionList.size() == 0) {
            id = 1;
        } else {
            Reservation last = null;
            for (Reservation each : ReservstionList) {
                last = each;
            }
            id = Integer.valueOf(last.getReservationId()) + 1;
        }
        return id;
    }

    //method to delete a given reservation
    public void deleteReservation(Reservation r) {
        reservation.deleteOne(Filters.eq("ID", r.getReservationId()));
        ReservstionList.remove(r);
    }

    //find a given reservation
    public Reservation findReservation(String id) {
        refreshReservation();
        for (Reservation each : ReservstionList) {
            if (each.getReservationId().equals(id)) {
                return each;
            }
        }
        return null;
    }


}
