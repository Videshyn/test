
package sqlitehibernatexmlconnection;

import org.hibernate.Session;


public class SQLiteHibernateXMLConnection {

    
    public static void main(String[] args) {
        testInsertIntoDepartments("Kharkov", "The best region in Ukraine");
    }
    
    private static void testInsertIntoDepartments(String departmnets_name, String region_info){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Departments departments = new Departments();
        departments.setDepartments_name(departmnets_name);
        departments.setRegion_info(region_info);
        session.save(departments);
        session.getTransaction().commit();
        session.close();
    }
}
