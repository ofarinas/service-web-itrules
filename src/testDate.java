import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by osvaldo on 1/19/15.
 */
public class testDate {
    public static void main(String[] args) throws ParseException {
        String ds1 = "November 1, 2012";
        String ds = "01/01/2014";
        DateFormat df = DateFormat.getDateInstance();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2014,1,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(gregorianCalendar.getGregorianChange());
    }

    private static Date getDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
}
