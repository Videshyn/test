/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapphibernatejpatagmapsqlitetest;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.Query;
import java.util.List;
/**
 *
 * @author Artik
 */
public class JavaAppHibernateJpaTagMapSqliteTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Nbeans + Hibernate + SQLite3 + JPA-mapping");
        //testEmployee();
        //testEmployees();
        //testDepartment();
        //testDepartmentEx();
        testEmployeesEx();
    }
    private static void testEmployee() throws ExceptionInInitializerError, HibernateException {
        SessionFactory mFctory;
        try{
            mFctory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = mFctory.openSession();
        Transaction tx = null;
        Long employeeID = null;
        String fname ="test fname 13";
        try{
            tx = session.beginTransaction();
            Employee employee = new Employee(fname);
            employeeID = (Long) session.save(employee);
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
        Query query = session.createQuery("SELECT e FROM Employee e");
	List<Employee> employees = query.list();
	for (Employee foundEmployee : employees) 
        {
	System.out.println(String.format("Found: %s\n", foundEmployee));
        }
        session.close();
        //
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
    private static void testDepartment() throws ExceptionInInitializerError, HibernateException {
        SessionFactory mFctory;
        try{
            mFctory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = mFctory.openSession();
        Transaction tx = null;
        Long departmentID = null;
        String name ="Dep#1";
        try{
            tx = session.beginTransaction();
            Department department = new Department(name);
            departmentID = (Long) session.save(department);
            tx.commit();
            System.out.println(String.format("Inserted: %s\n", departmentID));
        }catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        //
        session = mFctory.openSession();
        System.out.println("--- Find all Departments ---");
        Query query = session.createQuery("SELECT d FROM Department d");
	List<Department> departments = query.list();
	for (Department foundDepartment : departments) 
        {
	System.out.println(String.format("Found: %s\n", foundDepartment));
        }
        session.close();
        //
    }
    private static void testDepartmentEx() throws ExceptionInInitializerError, HibernateException {
        SessionFactory mFctory;
        try{
            mFctory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session=null;
        session = mFctory.openSession();
        Transaction tx = null;
        Long departmentID = null;
        String name ="Dep#3";
        try{
            tx = session.beginTransaction();
            Department department = new Department(name);
            //
            List<Employees> empls =  new ArrayList<Employees>(2);
            Employees e1 = new Employees("Иванов",-1);
            session.save(e1);
            Employees e2 = new Employees("Иванов 1",-1);
            session.save(e2);
            empls.add(e1);
            empls.add(e2);
            department.setEmployees(empls);
            //
            departmentID = (Long) session.save(department);
            tx.commit();
            System.out.println(String.format("Inserted: %s\n", departmentID));
        }catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        session = mFctory.openSession();
        System.out.println("--- Find all Departments && Employees ---");
        Query query = session.createQuery("SELECT d FROM Department d");
	List<Department> departments = query.list();
	       for (Department foundDepartment : departments) {
            System.out.println(String.format("Found: %s\n", foundDepartment));
            //
            for (Employees employee : foundDepartment.getEmployees()) {
                System.out.println(String.format("departments employees: %s", employee));
            }
        }
        //
        session.close();
        //
    }
    private static void testEmployeesEx() throws ExceptionInInitializerError, HibernateException {
        SessionFactory mFctory;
        try{
            mFctory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = null;
        session = mFctory.openSession();
        Transaction tx = null;
        Long employeesID = null;
        String name ="test name 13";
        int idCode = 1; 
        try{
            tx = session.beginTransaction();
            Employees employee = new Employees(name, idCode);
            employeesID = (Long) session.save(employee);
            DocsEntity doc1 = new DocsEntity("passport", employee);
            session.save(doc1);
            DocsEntity doc2 = new DocsEntity("drive lic.", employee);
            session.save(doc2);
            List<DocsEntity> docs = new ArrayList<DocsEntity>();
            docs.add(doc1);
            docs.add(doc2);
            employee.setDocs(docs);
            session.save(employee);
            
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
        //
        for (DocsEntity d : foundEmployee.getDocs()) 
        {
	System.out.println(String.format("docs info: %s\n", d));
        }
        //
        for (Department dep : foundEmployee.getDepartments()) 
        {
	System.out.println(String.format("deps info: %s\n", dep));
        }
        //
        }
        session.close();
        //
    }
}
