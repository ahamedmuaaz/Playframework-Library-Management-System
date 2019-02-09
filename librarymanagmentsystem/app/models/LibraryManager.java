package models;

import data.Borrowed;

import java.util.List;

public interface LibraryManager {


    public List<LibraryItem> DispalyItems();

    public String Additem(LibraryItem item);

    public String DeleteItem(String ISBN);

    public void BorrowItem(LibraryItem item);

    public double ReturnItem(LibraryItem item);

    public List<Borrowed> GenerateReport();
}
