import java.util.TimeZone.*;
import java.util.*;
import java.time.*;
public class Soundtest {
    public static void main(String[] args){
        GregorianCalendar gcalender = new GregorianCalendar();
        System.out.println(gcalender.get(Calendar.HOUR));
        System.out.println(gcalender.get(Calendar.MINUTE));
        System.out.println(gcalender.get(Calendar.SECOND));
    }
}