package models;


import java.util.List;


public class Book extends LibraryItem {


    private List<String> authors;
    private String publisher;
    private int numberOfPages;

    public Book( String isbn, String title, String sector, DateTime publicationDate,
                 List<String> authors, String publisher, int numberOfPages) {
        super(isbn, title, sector, publicationDate);
        this.authors = authors;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;


    }

    public Book(){}

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }


    @Override
    public String toString() {
        return "Book{" +
                "authors=" + authors +
                ", publisher=" + publisher +
                ", numberOfPages=" + numberOfPages +
                '}';
    }

}
