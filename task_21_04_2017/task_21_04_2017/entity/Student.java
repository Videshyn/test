package task_21_04_2017.entity;


import task_21_04_2017.exceptions.InvalidDateException;
import task_21_04_2017.exceptions.LargeNumberException;
import task_21_04_2017.exceptions.SmallNumberException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Student implements Cloneable{
    private final int MIN_YEAR = 1880;
    private final int NOW_YEAR = 2017;
    private String name;
    private double[] marks;
    private double mark;
    private LocalDate birthday;

    public Student() {
        this.name = "default_Student";
        this.mark = 0.0;
        this.birthday = LocalDate.of(1970, 1, 1);
    }

    /**
     *
     * @param console - fill Student fields from console
     */
    public Student(Scanner console) {
        System.out.println("Enter name:");
        this.name = console.nextLine();
        int countMarks = -1;
            while (true){
                try {
                    System.out.println("How many marks?");
                    countMarks = Integer.parseInt(console.nextLine());
                    if (countMarks <=0) throw new SmallNumberException("small number");
                    break;
                }catch (NumberFormatException ex){
                    System.out.println(ex.getMessage());
                }catch (SmallNumberException ex){
                    System.out.println(ex.detailMessage());
                }
            }
        getAverageMark(console, countMarks);
        int[] date = getDayOfBirth(console);
        this.birthday = LocalDate.of(date[0], date[1], date[2]);
    }

    /**
     *
     * @param name - Student's name
     * @param marks - Student's array of marks
     * @param mark - Student's average mark
     * @param birthday - Student's date of born
     */
    public Student(String name, double[] marks, double mark, LocalDate birthday) {
        this.name = name;
        this.marks = marks;
        this.mark = mark;
        this.birthday = birthday;
    }

    /**
     *
     * @return copy of the object Student
     * @throws CloneNotSupportedException - available exception
     */
    public Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }

    /**
     *
     * @param console - Method for obtaining information about the student
     * @return - day of born
     */
    private int[] getDayOfBirth(Scanner console){
        int[] date = new int[3];
        int day = - 1;
        int month = getMonth(console);
        int year = getYear(console);
        boolean flag = false;
        if (year % 4 == 0){
            if (year % 100 != 0){
                flag = true;
            }else if (year % 100 == 0){
                if (year % 400 == 0) flag = true;
            }
        }
            while (true){
                 try {
                    System.out.println("Enter day of birth:");
                    day = Integer.parseInt(console.nextLine());
                    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                        if (day <= 0 || day > 31) throw new InvalidDateException("wrong date");
                    }
                    if (month == 2 && flag == false){
                        if (day <= 0 || day > 28) throw new InvalidDateException("wrong date");
                    }
                    if (month == 2 && flag == true){
                        if (day <= 0 || day > 29) throw new InvalidDateException("wrong date");
                    }
                    if (month == 4 || month == 6 || month == 9 || month == 11){
                        if (day <= 0 || day > 30) throw new InvalidDateException("wrong date");
                    }
                    break;
                }catch (NumberFormatException ex){
                    System.out.println(ex.getMessage());
                }catch (InvalidDateException ex){
                    System.out.println(ex.detailMessage());
                }
            }
        date[0] = year;
        date[1] = month;
        date[2] = day;
        return date;
    }

    /**
     *
     * @param console - Method for obtaining information about the student
     * @return - year of born
     */
    private int getYear(Scanner console){
        int year = -1;
            while (true){
                try {
                    System.out.println("Enter year of born:");
                    year = Integer.parseInt(console.nextLine());
                    if (year <= MIN_YEAR) throw new InvalidDateException("this student must already be dead");
                    if (year > NOW_YEAR) throw new InvalidDateException("this year of birth is not real");
                    break;
                }catch (NumberFormatException ex){
                    System.out.println(ex.getMessage());
                }catch (InvalidDateException ex){
                    System.out.println(ex.detailMessage());
                }
            }
        return year;
    }

    /**
     *
     * @param console - Method for obtaining information about the student
     * @return - month of born
     */
    private int getMonth(Scanner console){
        int month = -1;
        while (true){
            try {
                System.out.println("Enter month of birth:");
                month = Integer.parseInt(console.nextLine());
                if (month <= 0) throw new InvalidDateException("wrong date");
                if (month > 12) throw new InvalidDateException("wrong date");
                break;
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
            }catch (InvalidDateException ex){
                System.out.println(ex.detailMessage());
            }
        }
        return month;
    }

    /**
     *
     * @param console - Method for obtaining information about the student
     * @param size - array's length
     */
    private void getAverageMark(Scanner console, int size){
        this.marks = new double[size];
        while (true){
            try {
                for (int i = 1; i <= size; i ++){
                    System.out.printf("Enter %d - s mark: \n", i);
                    double tmp = Double.parseDouble(console.nextLine());
                    if (tmp <= 0) throw new SmallNumberException("small number");
                    if (tmp > 5) throw new LargeNumberException("large number");
                    marks[i - 1] = tmp;
                }
                break;
            }catch (SmallNumberException ex){
                System.out.println(ex.detailMessage());
            }catch (LargeNumberException ex){
                System.out.println(ex.detailMessage());
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
            }
        }
        roundingAverageMark(marks);
    }

    /**
     *
     * @param marks - array's with Students marks
     */
    private void roundingAverageMark(double[] marks){
        double sum = 0.0;
        for (int i = 0, size = marks.length; i < size; i ++){
            sum += marks[i];
        }
        sum /= marks.length;
        BigDecimal sumDec = new BigDecimal(sum);
        this.mark = sumDec.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getMark() {
        return mark;
    }
    public void setMark(double mark) {
        this.mark = mark;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return - Output information about the student on the screen
     */
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", mark=" + mark +
                ", birthday=" + birthday +
                '}';
    }
    public String toString(int i) {
        return "Student{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
