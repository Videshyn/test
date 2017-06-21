package printf_testing;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Vedia on 26.03.2017.
 */
public class WorkWithDateInJava {
    public static void main(String[] args) {
        Date currentDate = new Date();
        System.out.println("Date = " + currentDate);

        //Задаем дату с помощью класса Date()
        currentDate = new Date();
        Long time = currentDate.getTime();
        long anotherTime = -1;
        time = time + (400* 60 * 50 * 3600 * anotherTime);
        currentDate = new Date(time);
        System.out.println("currentTime = " + currentDate);

        // Получаем дату с помощью GregorianCalendar
        Calendar calendar = new GregorianCalendar(2017, 11, 26); // 00 & 12 - January; 11 - December;
        System.out.println("GregorianCalendar = " + calendar.getTime());

        // DateFormat
        Locale locale = new Locale("ru","RU"); // формируем русскую локаль
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, locale);
        currentDate = new Date();
        System.out.println("df = " + df.format(currentDate));

        DateFormat df2 = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
        currentDate = new Date();
        System.out.println("df Time = " + df2.format(currentDate));







    }
}
