
package hibernatesqlconnection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateSQLConnection {

   
    public static void main(String[] args) {
        //testInsertClients("Vova", "555-555-35", "vova@mail.ru");
        testInsertClientsWithHibernateUtil("Ivan", "938-38-38", "ivan@mail.ru");
    }
 
    private static void testInsertClients(String clientsName, String clientsPhone, String clientsMail) throws ExceptionInInitializerError, HibernateException {
        SessionFactory mFctory;
        try{
            //Обращение к конфиг файлу, буде твыполняться xml атрибуты
            mFctory = new Configuration().configure().buildSessionFactory();
            
        }catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = mFctory.openSession();
        Transaction tx = null;
        //Long employeeID = null;
        Integer clientsId = null;
        try{
            // заполняются значения
            tx = session.beginTransaction();
            Clients clients = new Clients(clientsName, clientsPhone, clientsMail);
            clientsId = (Integer) session.save(clients);
            //
            tx.commit();
        }catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    
    private static void testInsertClientsWithHibernateUtil(String cientsName, String clientsPhone, String clientsMail){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Clients clients = new Clients();
        clients.setClients_name(cientsName);
        clients.setClients_phone(clientsPhone);
        clients.setClients_mail(clientsMail);
        session.save(clients);
        session.getTransaction().commit();
        session.close();
        
    } 
    
}
