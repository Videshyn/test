package sqlhibernatetest;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class SqlHibernateTest {

   
    public static void main(String[] args) {
       //testInsertEmployee("test fname", "testLname");
       //testUser();
       //testSelectEmployee();
       testSearchQueryForEmployee("test name");
       
       
    }
    
     private static void testInsertEmployee(String userLName, String userFname) throws ExceptionInInitializerError, HibernateException {
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
        Integer userId = null;
        String fname =userFname;
        String lname = userLName;
        try{
            // заполняются значения
            tx = session.beginTransaction();
            User user = new User(fname, lname);
            //Employee employee = new Employee(fname);
            //employeeID = (Long) session.save(employee);
            userId = (Integer) session.save(user);
            //
            tx.commit();
        }catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
     
     private static void testSelectEmployee(){
         //анализируем hibernate.cfg, открывает сессию
         Session session = HibernateUtil.getSessionFactory().openSession();
         //обращение к классу обертке, универсальный запрос хибернейта(регистрочувствительный)
         String hql = "from Employee";
         //Query - должен преобразовываться в какую-то коллекцию
         Query query = session.createQuery(hql); // запрос выполняется, получаем результат
         List<Employee> listEmployee = query.list();//
         for(Employee aEmployee : listEmployee){
             System.out.println(aEmployee.getName());
         }
         
     }
     private static void testSearchQueryForEmployee(String emplName){
         
         Session session = HibernateUtil.getSessionFactory().openSession();
         
         String hql = "from Employee where  name = '" + emplName + "'";
         
         Query query = session.createQuery(hql); // запрос выполняется, получаем результат
         List<Employee> listEmployee = query.list();//
         for(Employee aEmployee : listEmployee){
             System.out.println(aEmployee.getName());
         }
     }
     
     
     private static void testUser() throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User();
        user.setFirst_name("Alexander");
        user.setLast_name("Barchuk");
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
