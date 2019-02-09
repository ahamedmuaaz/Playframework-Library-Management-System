package data;

import models.DateTime;

public class Reservation{
    private String reservationId;
    private String  id;
    private DateTime reservedDate;
    private String isbn;

    public Reservation(String reservationId, String id, DateTime reservedDate, String isbn) {
        this.reservationId = reservationId;
        this.id = id;
        this.reservedDate = reservedDate;
        this.isbn = isbn;
    }
    public Reservation(){}

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(DateTime reservedDate) {
        this.reservedDate = reservedDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", id='" + id + '\'' +
                ", reservedDate=" + reservedDate.viewDate() +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
