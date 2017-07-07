
package hibernatejpamappingsqlitetest;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateJPAMappingSQLiteTest {

    public static void main(String[] args) {
        //testSelectEmployee();
        //testEmployeeInsert("Alex");
        //testSelectEmployees();
        //testEmployeesInsert("New name", 666);
        testEmployees();
    }
    
    
    
    private static void testEmployees() throws ExceptionInInitializerError, HibernateException {
        SessionFactory mFctory;
        try{
            mFctory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = mFctory.openSession();
        Transaction tx = null;
        Long employeesID = null;
        String name ="test name 1";
        int idCode = 1; 
        try{
            tx = session.beginTransaction();
            Employees employee = new Employees(name,idCode);
            employeesID = (Long) session.save(employee);
            tx.commit();
        }catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        //
        session = mFctory.openSession();
        System.out.println("--- Find all Employees ---");
        Query query = session.createQuery("SELECT e FROM Employees e");
	List<Employees> employees = query.list();
	for (Employees foundEmployee : employees) 
        {
	System.out.println(String.format("Found: %s\n", foundEmployee));
        }
        session.close();
        //
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
     
      private static void testEmployeeInsert(String name) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee empl = new Employee();
        empl.setName(name);
        session.save(empl);
        session.getTransaction().commit();
        session.close();
    }
      
       private static void testEmployeesInsert(String name, Integer idCode) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employees empls = new Employees();
        empls.setEmployees_Name(name);
        empls.setIdCode(idCode);
        session.save(empls);
        session.getTransaction().commit();
        session.close();
    }   
       
       
       private static void testSelectEmployees(){
         //анализируем hibernate.cfg, открывает сессию
         Session session = HibernateUtil.getSessionFactory().openSession();
         //обращение к классу обертке, универсальный запрос хибернейта(регистрочувствительный)
         String hql = "from Employees";
         //Query - должен преобразовываться в какую-то коллекцию
         Query query = session.createQuery(hql); // запрос выполняется, получаем результат
         List<Employees> listEmployee = query.list();//
         for(Employees aEmployee : listEmployee){
             System.out.println(aEmployee.toString());
         }
         
     }
}
