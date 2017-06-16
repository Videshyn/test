package task_21_04_2017.exceptions;

/**
 * Created by Vedia on 21.04.2017.
 */
public class LargeNumberException extends NumberFormatException{
    private String detail;

    /**
     *
     * @param a - Information entered by the user
     */
    public LargeNumberException(String a)
    {
        detail = a;
    }

    /**
     *
     * @return - info about exception
     */
    public String detailMessage()
    {
        return "LargeNumberException["+detail+"]";
    }
}
