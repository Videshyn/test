package printf_testing;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Vedia on 26.03.2017.
 */
public class WorkWithFile {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {


    }


    public static void write(String fileName, String text){
        File file = new File(fileName);
        try {
            if (!file.exists()){
                file.createNewFile();
            }
        }catch (IOException ex){
            System.out.printf(ex.getMessage());
        }
    }

}
