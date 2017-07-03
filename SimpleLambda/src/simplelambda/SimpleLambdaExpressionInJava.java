
package simplelambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SimpleLambdaExpressionInJava {

    
    public static void main(String[] args) {
        
      List<Integer> listInt = Arrays.asList(1,4,6,2,4,7, -4, 22, -6, 0, -3);
        //printIntegers(listInt, pred -> (pred % 2) == 0);
        //System.out.println("");
        //printIntegers(listInt, pred -> (pred < 0));
        //System.out.println("");
        //printIntegers(listInt, pred -> (pred >= 0));
        
//        listInt.forEach((Integer value) -> System.out.println(value));
//        listInt.forEach(value -> System.out.println(value));
//        listInt.forEach(System.out::println);
        
        printIntegers(listInt, pred -> (pred < 0));
        printIntegers(listInt, pred -> true);
        
        
    }

    public static void printIntegers(List<Integer> numbers, Predicate<Integer> pred){
        int sum = 0;
        for(int list : numbers){
            if (pred.test(list)){
                sum += list;
            }
        }
        System.out.println(sum);
    }

    
}
