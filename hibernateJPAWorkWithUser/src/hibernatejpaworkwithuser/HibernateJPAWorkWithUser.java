
package hibernatejpaworkwithuser;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;

public class HibernateJPAWorkWithUser {

   
    public static void main(String[] args) {
        //selectUser();
        
        Scanner console = new Scanner(System.in);
//        System.out.println("Enter first name:");
//        String firstName = console.nextLine();
//        System.out.println("Enter last name:");
//        String lastName = console.nextLine();
//        insertUser(firstName, lastName);

        System.out.println("Enter user id:");
        int user_id = -1;
        try {
            user_id = Integer.parseInt(console.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        getUser(user_id);
        
        
//        System.out.println("Enter user id for delete:");
//        
//        try {
//        int user_id = Integer.parseInt(console.nextLine());
//            removeUser(user_id);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        
    }
    
    private static void insertUser(String first_name, String last_name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User(first_name, last_name);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
    
    private static void selectUser(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();
        userList.forEach(System.out :: println);
    }
    
    private static void getUser(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from User where id = '" + id + "'";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        System.out.println(users.get(0).toString());
    }
    
//    private static void removeUser(int id){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        String hql = "DELETE from User u WHERE id = :user_id";
//        Query query = session.createQuery(hql);
//        query.setParameter("user_id", id);
//        query.executeUpdate();
//    }
}
