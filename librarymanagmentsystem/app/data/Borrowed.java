package data;

import models.LibraryItem;

public class Borrowed implements Comparable<Borrowed>{
    private double fineamount;
    private LibraryItem item;

    public Borrowed(double fineamount, LibraryItem item) {
        this.fineamount = fineamount;
        this.item = item;
    }
    public Borrowed(){}

    public double getFineamount() {
        return fineamount;
    }

    public void setFineamount(double fineamount) {
        this.fineamount = fineamount;
    }

    public LibraryItem getItem() {
        return item;
    }

    public void setItem(LibraryItem item) {
        this.item = item;
    }

    @Override
    public int compareTo(Borrowed o) {
        if (this.fineamount>o.fineamount) return -1;
        else if(this.fineamount<o.fineamount) return 1;
        else return 0;
    }
}
