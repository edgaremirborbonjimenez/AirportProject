package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Utils {


    public static boolean isValidDateFormat(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); //Doesnt allow invalid dates
        try{
            sdf.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }

    public static Date parseStringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); //Doesnt allow invalid dates
        try{
            return sdf.parse(date);
        }catch (ParseException e){
            return null;
        }
    }

}
