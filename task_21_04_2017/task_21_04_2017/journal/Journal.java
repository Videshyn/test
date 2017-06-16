package task_21_04_2017.journal;

import task_21_04_2017.entity.Student;
import task_21_04_2017.exceptions.SmallNumberException;

import java.util.*;

/**
 * Created by Vedia on 21.04.2017.
 */
public class Journal {
    /**
     *
     * @return - original List with Students
     */
    public List<Student> getJournal(){
        int journalSize = 0;
        Scanner console = new Scanner(System.in);
        while (true){
            try {
                    System.out.println("How many students?");
                    journalSize = Integer.parseInt(console.nextLine());
                    if (journalSize <= 0) throw new SmallNumberException("Negative number");
                    break;
            }catch (SmallNumberException ex){
                System.out.println(ex.detailMessage());
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
            }
        }
        List<Student> journal = new ArrayList<>(journalSize);
        for (int i = 0; i < journalSize; i ++){
            journal.add(new Student(console));
        }
        return journal;
    }

    /**
     *
     * @param journalClone - support list for sorting
     * @return - sorted List
     */
    public List<Student> sortJournal(List<Student> journalClone){
        Collections.sort(journalClone, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int checkDate = o1.getBirthday().compareTo(o2.getBirthday());
                if (checkDate != 0) return checkDate;
                int checkName = o1.getName().compareTo(o2.getName());
                if (checkName != 0) return checkName;
                return Double.compare(o1.getMark(), o2.getMark());
            }
        });
        return journalClone;
    }

    /**
     *
     * @param sortedJournal - some List for printing
     */
    public void printJournal(List<Student> sortedJournal){
        Scanner console = new Scanner(System.in);
        int check = -1;
        while (true){
            try {
                System.out.println("What info do you want? 1 = with marks, 2 = without marks");
                check = Integer.parseInt(console.nextLine());
                if (check == 1){
                    for (Student elem : sortedJournal){
                        System.out.println(elem.toString());
                    }
                }else if ( check == 2){
                    for (Student elem : sortedJournal){
                        System.out.println(elem.toString(1));
                    }
                }else throw new NumberFormatException();
                break;
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     *
     * @param journalClone - supportList for sorting by birthday
     * @return - sorted List by birthday
     */
    public List<Student> sortBirthdayJournal(List<Student> journalClone){
        Collections.sort(journalClone, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int checkMonth = Integer.valueOf(o1.getBirthday().getMonthValue()).
                        compareTo(Integer.valueOf(o2.getBirthday().getMonthValue()));
                if (checkMonth != 0) return checkMonth;
                int checkDay = Integer.valueOf(o1.getBirthday().getDayOfMonth()).
                        compareTo(Integer.valueOf(o2.getBirthday().getDayOfMonth()));
                if (checkDay != 0) return checkDay;
                return o1.getName().compareTo(o2.getName());
            }
        });
        return journalClone;
    }

    /**
     *
     * @param journal - original List with Students
     * @return - clone of the original List
     */
    public List<Student> getClone(List<Student> journal){
        List<Student> journalClone = new ArrayList<>();
        for (int i = 0, n = journal.size(); i < n; i ++){
            try {
                journalClone.add(journal.get(i).clone());
            }catch (CloneNotSupportedException ex){
                System.out.println(ex.getMessage());
            }
        }
        return journalClone;
    }
}
