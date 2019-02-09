package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import data.Reservation;
import models.*;
import org.bson.Document;
import play.libs.Json;
import play.mvc.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    MongoClientURI connectionString = new MongoClientURI("mongodb://ahamedmuaaz:1234@cluster0-shard-00-00-7q6qk.gcp.mongodb.net:27017,cluster0-shard-00-01-7q6qk.gcp.mongodb.net:27017,cluster0-shard-00-02-7q6qk.gcp.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");

    MongoClient mongo = new MongoClient(connectionString);

    // Accessing the database
    MongoDatabase fmis = mongo.getDatabase("library");

    WestminsterLibraryManager manager = new WestminsterLibraryManager();

    // Retrieving a collection
    MongoCollection<Document> staffdetail = fmis.getCollection("users");

    public Result index() {

        return ok(Json.toJson(manager.DispalyItems()));
    }

    public Result login() {

        String h = request().body().asText();
        String[] userdetail = h.split("\\.");

        String usern = null;
        String passw = null;
        Document myDoc = null;
        try {
            myDoc = staffdetail.find(Filters.and(Filters.eq("Name", userdetail[0]), Filters.eq("password", userdetail[1])))
                    .first();
            usern = myDoc.getString("Name");
            passw = myDoc.getString("password");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String result = null;
        if (!(usern == null || passw == null)) {
            result = "ok";
        } else {
            result = "notok";
        }

        return ok(Json.toJson(result));
    }

    public Result additem() {

        JsonNode body = request().body().asJson();



        String receivedisbn = body.get("isbn").asText();

        String message = "";

        if (manager.ISBNcheck(receivedisbn) == null) {
            if (body.has("authors")) {
                Book b = null;
                try {
                    b = Json.fromJson(body, Book.class);

                    message = manager.Additem(b);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return ok(Json.toJson(message));
            } else {
                Dvd b = null;
                try {
                    b = Json.fromJson(body, Dvd.class);

                    message = manager.Additem(b);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return ok(Json.toJson(message));

            }
        } else {
            return ok(Json.toJson("Notok"));
        }

    }

    public Result DeletItem(String isbn) {

        String message = manager.DeleteItem(isbn);
        return ok(Json.toJson(message));
    }

    public Result addReader() {
        JsonNode body = request().body().asJson();
        System.out.println(body);
        Reader r = null;
        String id = String.valueOf(manager.autogen());
        try {
            r = Json.fromJson(body, Reader.class);
            r.setId(id);
            System.out.println(r.getId());
            System.out.println("****" + r);
            manager.addReader(r);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return ok(Json.toJson(id));
    }

    public Result readerList() {

        return ok(Json.toJson(manager.readerList()));
    }

    public Result generate() {

        return ok(Json.toJson(manager.GenerateReport()));
    }

    public Result borrow() {
        //getting the object from body as json
        JsonNode body = request().body().asJson();
        //getting the isbn to borrow
        String isbn = body.get("isbn").asText();
        //getting id to borrow
        String id = body.get("id").asText();
        //get the library object if found
        LibraryItem founded = manager.ISBNcheck(isbn);
        //get the reader object if found
        Reader foundedreader = manager.Readercheck(id);

        if (founded != null) {
            if (foundedreader != null) {
                //to find its borrowed or not
                if (founded.getCurrentReader() == null) {
                    //passing the currunt reader object
                    founded.setCurrentReader(foundedreader);
                    //call library manager borrow method
                    manager.BorrowItem(founded);
                    return ok(Json.toJson("done"));
                }
                //if already borrowed
                else {
                    //geeting the return time of object
                    int borrowtime = manager.checborrowlimit(founded);
                    //if return time is positive send estimated return time
                    if (borrowtime >= 0) {
                        return ok(Json.toJson(borrowtime));
                    }
                    // time limit exceeded do cannot make sure return period
                    else {
                        return ok(Json.toJson("exceeded"));
                    }

                }
            } else {
                return ok(Json.toJson("noreader"));
            }
        }


        return ok(Json.toJson("isbnerror"));
    }

    public Result returnItem(String isbn) {

        //getting the relevent libraryitem if found
        LibraryItem founded = manager.ISBNcheck(isbn);

        //if found
        if (founded != null) {
            //check weather its borrowed
            if (founded.getCurrentReader() != null) {
                //call return method from westminster library manager
                // which returns a double value
                double fine = manager.ReturnItem(founded);

                return ok(Json.toJson(fine));
            } else {
                //if item not borrowed
                return ok(Json.toJson("notborrowed"));
            }

        }
        //if item not found
        return ok(Json.toJson("isbnerror"));
    }

    public Result deleteReader(String id) {
        //getting reader if found
        Reader r = manager.Readercheck(id);
        if (r != null) {
            //call delete method from manager
            manager.deleteReader(r);
            // return readers name
            return ok(Json.toJson(r.getName()));
        }
        // if reader not found
        return ok(Json.toJson("notfound"));
    }

    public Result reserve() {
        // get data from body as json node
        JsonNode body = request().body().asJson();
        //get isbn
        String isbn = body.get("isbn").asText();
        //get id
        String id = body.get("id").asText();


        String result = manager.addreservation(isbn, id);
        return ok(Json.toJson(result));

    }

    public Result displayReservations() {

        return ok(Json.toJson(WestminsterLibraryManager.ReservstionList));
    }

    public Result deleteReservation(String id) {
        //to check weather reservation is available
        Reservation founded = manager.findReservation(id);
        // reservation found
        if (founded != null) {
            //call delete method from manager
            manager.deleteReservation(founded);
            return ok(Json.toJson("Reservation Deleted"));
        } else {
            return ok(Json.toJson("ID Not Found"));
        }

    }


}
