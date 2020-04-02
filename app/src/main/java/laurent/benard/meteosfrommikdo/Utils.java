package laurent.benard.meteosfrommikdo;

public class Utils {

    public static String getMonth (int month) {

        String nameOfMonth = null;

        switch (month) {
            case 0:
                nameOfMonth = "Janvier";
                break;
            case 1:
                nameOfMonth = "Fevrier";
                break;
            case 2:
                nameOfMonth = "Mars";
                break;
            case 3:
                nameOfMonth = "Avril";
                break;
            case 4:
                nameOfMonth = "Mai";
                break;
            case 5:
                nameOfMonth = "Juin";
                break;
            case 6:
                nameOfMonth = "Juillet";
                break;
            case 7:
                nameOfMonth = "Aout";
                break;
            case 8:
                nameOfMonth = "Septembre";
                break;
            case 9:
                nameOfMonth = "Octobre";
                break;
            case 10:
                nameOfMonth = "Novembre";
                break;
            case 11:
                nameOfMonth = "Decembre";
                break;


        }

        return nameOfMonth;
    }

    public static String getDay(int day){

        String nameOfDay = null;

        switch (day) {

            case 1:
                nameOfDay = "Dimanche";
                break;
            case 2:
                nameOfDay = "Lundi";
                break;
            case 3:
                nameOfDay = "Mardi";
                break;
            case 4:
                nameOfDay = "Mercredi";
                break;
            case 5:
                nameOfDay = "Jeudi";
                break;
            case 6:
                nameOfDay = "Vendredi";
                break;
            case 7:
                nameOfDay = "Samedi";
                break;
        }
        return nameOfDay;
    }
}
