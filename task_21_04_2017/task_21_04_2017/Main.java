package task_21_04_2017;

import task_21_04_2017.entity.Student;
import task_21_04_2017.journal.Journal;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Vedia on 21.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        Journal journal = new Journal();
        List<Student> journalList = journal.getJournal();
        List<Student> cloneForSort = journal.getClone(journalList);
        List<Student> cloneBirthdays = journal.getClone(journalList);
        System.out.println("\n original journal \n");
        journal.printJournal(journalList);
        System.out.println("\n sorted journal\n");
        journal.printJournal(journal.sortJournal(cloneForSort));
        System.out.println("\n birthdays journal \n");
        journal.printJournal(journal.sortBirthdayJournal(cloneBirthdays));
    }
}
