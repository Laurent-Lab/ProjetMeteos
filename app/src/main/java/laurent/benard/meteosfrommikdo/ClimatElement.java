package laurent.benard.meteosfrommikdo;

class ClimatElement {

    String ville;
    String pays;
    String location;
    String minTemp;
    String maxTemp;
    int timeStamp;
    String nameOfMonth;
    String nameOfDay;

    int day;
    int month;
    int year;

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

    public ClimatElement(String ville, String pays, String location, String minTemp, String maxTemp, int timeStamp, String nameOfMonth, String nameOfDay, int day, int month, int year)
    {
        this.ville = ville;
        this.pays = pays;
        this.location = location;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.timeStamp = timeStamp;
        this.nameOfMonth = nameOfMonth;
        this.nameOfDay = nameOfDay;

        this.day = day;
        this.month = month;
        this.year = year;


    }


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
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
