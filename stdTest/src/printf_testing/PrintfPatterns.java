package printf_testing;

import java.util.Date;

/**
 * Created by Vedia on 25.03.2017.
 */
public class PrintfPatterns {
    public static void main(String[] args) {
        double k =  111.35565;
        double minusK = - 111.344355;
        System.out.printf("k = %+.4f \n", k);
        System.out.printf("-k = %,(.4f \n", minusK);
        System.out.printf("%tc Полная дата и время\n", new Date());
        System.out.printf("%tF Дата в формате ISO8601\n", new Date());
        System.out.printf("%tD Дата как в США\n", new Date());
        System.out.printf("%tT Время в 24-х часовом формате\n", new Date());
        System.out.printf("%tr Время в 12-ти часовом формате\n", new Date());
        System.out.printf("%tR Время в 24-х часовом формате без секунд\n", new Date());
        System.out.printf("%tY год в 4-х цифрах\n", new Date());
        System.out.printf("%ty год в виде 2 последних цифр\n", new Date());
        System.out.printf("%tC год в виде первых 2-х цифр\n", new Date());
        System.out.printf("%tB полное название месяца\n", new Date());
        System.out.printf("%tb скоращение название месяца\n", new Date());
        System.out.printf("%th тоже что и %%tb\n", new Date());
        System.out.printf("%tm месяц в виде 2-х цифр\n", new Date());
        System.out.printf("%td день в виде 2 цифр\n", new Date());
        System.out.printf("%te день в виде 2 или 1 цифры без нулей\n", new Date());
        System.out.printf("%tA полное название дня недели\n", new Date());
        System.out.printf("%ta скоращенное название дня недели\n", new Date());
        System.out.printf("%tj день в году от 001 до 366 с нулями в начале\n", new Date());
        System.out.printf("%tH час в виде 1 или 2 цифр от 00 до 23\n", new Date());
        System.out.printf("%tk час в виде 1 или 2 цифр без нулей\n", new Date());
        System.out.printf("%tI час в виде 2 цифр от 1 до 12\n", new Date());
        System.out.printf("%tl час в виде 1-2 цифр от 01 до 12 без нулей\n", new Date());
        System.out.printf("%tM минуты в виде 2 цифр(с нулями)\n", new Date());
        System.out.printf("%tS секунды в иде 2 цифр(с нулями)\n", new Date());
        System.out.printf("%tL милисекунды в виде трех цифр(с нулями)\n", new Date());
        System.out.printf("%tN наносекунды в виде 9 цифр(с нулями)\n", new Date());
        System.out.printf("%tp метка времени до и после полудня\n", new Date());
        System.out.printf("%tz смещение относительно времени по гринвичу\n", new Date());
        System.out.printf("%tZ часовой пояс\n", new Date());
        System.out.printf("%ts к-во секунд от начала отсчета в 1970г.\n", new Date());
        System.out.printf("%tQ к-во милисекунд от начала отсчета в 1970г.\n", new Date());
    }
}
