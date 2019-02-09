package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTime {

    private int day;

    private int month;

    private int year;

    private int hour;

    private int minute;

    private int second;

    public DateTime(int day, int month, int year,int minute,int hour,int second) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public DateTime(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public DateTime(){}

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String viewDate() {
        String date;
        if (day < 10 && month < 10) {
            date = "0" + day + "//0" + month + "//" + year;
            return date;
        } else if (day < 10) {
            date = "0" + day + "//" + month + "//" + year;
            return date;

        } else if (month < 10) {
            date = day + "//0" + month + "//" + year;
            return date;

        } else {
            date = day + "//" + month + "//" + year;
            return date;

        }

    }

    public String viewTime() {
        return this.hour+":"+this.minute+":"+this.second;
    }

    public int diffDate(DateTime o) throws ParseException {

        SimpleDateFormat myFormat = new SimpleDateFormat("dd//MM//yyyy");
        Date date1 = myFormat.parse(this.viewDate());
        Date date2 = myFormat.parse(o.viewDate());
        long diff = date1.getTime()-date2.getTime();

        System.out.println("dd"+this.viewTime());
        System.out.println("nn"+o.viewTime());

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date11 = format.parse(this.viewTime());
        Date date22 = format.parse(o.viewTime());

        long difference = date11.getTime()-date22.getTime();
        //System.out.println("EXACT"+(difference/1000));

        diff+=difference;

        int days = (int) TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);

        System.out.println("hours"+days);

        return days;

    }
}
