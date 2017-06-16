package task_21_04_2017.exceptions;

import java.time.DateTimeException;

/**
 * Created by Vedia on 21.04.2017.
 */
public class InvalidDateException extends Exception {

    private String detail;

    /**
     *
     * @param a - Information entered by the user
     */
    public  InvalidDateException(String a)
    {
        detail = a;
    }

    /**
     *
     * @return - info about exception
     */
    public String detailMessage()
    {
        return " InvalidDateException["+detail+"]";
    }

}
