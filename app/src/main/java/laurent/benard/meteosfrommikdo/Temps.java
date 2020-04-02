package laurent.benard.meteosfrommikdo;

import java.util.Calendar;

public class Temps {

    int dt_unix;
    int year;
    int month;
    int day;
    String dt_text;
    String nameOfYear;
    String nameOfMonth;
    String nameOfDay;

    public Temps(int dt_unix, String dt_text) {
        this.dt_unix = dt_unix;
        this.dt_text = dt_text;

        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis((long)dt_unix*1000);

        this.year = mydate.get(Calendar.YEAR);
        this.month = mydate.get(Calendar.MONTH);
        this.day = mydate.get(Calendar.DAY_OF_WEEK);

        this.nameOfMonth =  Utils.getMonth(mydate.get(Calendar.MONTH));
        this.nameOfDay = Utils.getDay(mydate.get(Calendar.DAY_OF_WEEK));
    }

    public int getDt_unix() {
        return dt_unix;
    }

    public void setDt_unix(int dt_unix) {
        this.dt_unix = dt_unix;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDt_text() {
        return dt_text;
    }

    public void setDt_text(String dt_text) {
        this.dt_text = dt_text;
    }

    public String getNameOfYear() {
        return nameOfYear;
    }

    public void setNameOfYear(String nameOfYear) {
        this.nameOfYear = nameOfYear;
    }

    public String getNameOfMonth() {
        return nameOfMonth;
    }

    public void setNameOfMonth(String nameOfMonth) {
        this.nameOfMonth = nameOfMonth;
    }

    public String getNameOfDay() {
        return nameOfDay;
    }

    public void setNameOfDay(String nameOfDay) {
        this.nameOfDay = nameOfDay;
    }
}
