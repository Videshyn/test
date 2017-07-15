package hibernatequerytestsqlitejpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateQueryTestSQliteJPA {
//

    private Session session;
    private ServiceRegistry serviceRegistry;

    void openSession() {
        System.out.println("openSession()");
        // loads configuration and mappings
        Configuration configuration = new Configuration().configure();
        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        // builds a session factory from the service registry
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        // obtains the session
        session = sessionFactory.openSession();
        session.beginTransaction();

    }

    void closeSession() {
        System.out.println("closeSession()");
        session.getTransaction().commit();
        session.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

    void testListQuery() {
        System.out.println("testListQuery()");
        String hql = "from Category";
        Query query = session.createQuery(hql);
        List<Category> listCategories = query.list();

        for (Category aCategory : listCategories) {
            System.out.println(aCategory.getName());
        }
    }

    void testSearchQuery() {
        System.out.println("testSearchQuery()");
        String hql = "???";
        Query query = session.createQuery(hql);
        List<Product> listProducts = query.list();

        for (Product aProduct : listProducts) {
            System.out.println(aProduct.getName());
        }

    }

    void testCountQuery() {
        System.out.println("testCountQuery()");
        String hql = "???";
        Query query = session.createQuery(hql);
        List listResult = query.list();
        Number number = (Number) listResult.get(0);
        System.out.println(number.intValue());
    }

    void testInsertQuery() {
        System.out.println("testInsertQuery()");

        String hql = "insert ";
        Query query = session.createQuery(hql);
        int rowsAffected = query.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(rowsAffected + "(s) were inserted");
        }
    }

    void testQueryWithNamedParameters() {
        System.out.println("testQueryWithNamedParameters()");
        String hql = "???";

        String keyword = "New";
        Query query = session.createQuery(hql);
        //query.setParameter(???);

        List<Product> listProducts = query.list();

        for (Product aProduct : listProducts) {
            System.out.println(aProduct.getName());
        }
    }

    void testUpdateQuery() {
        System.out.println("testUpdateQuery()");
        String hql = "???";
        Query query = session.createQuery(hql);
        query.setParameter("price", 488.0f);
        query.setParameter("id", 2l);//С‚СѓС‚ С‚РёРї long

        int rowsAffected = query.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Updated " + rowsAffected + " rows.");
        }
    }

    void testDeleteQuery() {
        System.out.println("testDeleteQuery()");
        String hql = "???";

        Query query = session.createQuery(hql);
        query.setParameter("catId", new Long(1));

        int rowsAffected = query.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Deleted " + rowsAffected + " rows.");
        }
    }

    void testJoinQuery() {
        System.out.println("testJoinQuery()");
        String hql = "???";
        Query query = session.createQuery(hql);
        List<Object[]> listResult = query.list();

        for (Object[] aRow : listResult) {
            Product product = (Product) aRow[0];
            Category category = (Category) aRow[1];
            System.out.println(product.getName() + " - " + product.getPrice()
                    + " - " + category.getName());
        }
    }

    void testOrderByQuery() {
        System.out.println("testOrderByQuery()");
        String hql = "???";
        Query query = session.createQuery(hql);
        List<Product> listProducts = query.list();

        for (Product aProduct : listProducts) {
            System.out.println(aProduct.getName() + "\t - " + aProduct.getPrice());
        }
    }

    void testGroupByQuery() {
        System.out.println("testGroupByQuery()");
        String hql = "???";

        Query query = session.createQuery(hql);
        List<Object[]> listResult = query.list();

        for (Object[] aRow : listResult) {
            Double sum = (Double) aRow[0];
            String category = (String) aRow[1];
            System.out.println(category + " - " + sum);
        }
    }

    void testDateRangeQuery() throws ParseException {
        System.out.println("testDateRangeQuery()");
        String hql = "from Order where purchaseDate >= :beginDate and purchaseDate <= :endDate";
        Query query = session.createQuery(hql);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = dateFormatter.parse("2014-11-01");

        query.setParameter("beginDate", beginDate);

        Date endDate = dateFormatter.parse("2017-11-22");
        query.setParameter("endDate", endDate);

        List<Order> listOrders = query.list();

        for (Order anOrder : listOrders) {
            System.out.println(anOrder.getProduct().getName() + " - "
                    + anOrder.getAmount() + " - "
                    + anOrder.getPurchaseDate());
        }
    }

    void testArithmeticExpression() {
        System.out.println("testArithmeticExpression()");
        String hql = "???";
        Query query = session.createQuery(hql);
        List<Product> listProducts = query.list();

        for (Product aProduct : listProducts) {
            System.out.println(aProduct.getName() + "\t - " + aProduct.getPrice());
        }
    }

    public static void main(String[] args) {
        HibernateQueryTestSQliteJPA tester = new HibernateQueryTestSQliteJPA();
        try {
            tester.openSession();

            //tester.testListQuery();
//            tester.testSearchQuery();
//            tester.testQueryWithNamedParameters();
//            tester.testCountQuery();

            tester.testDateRangeQuery();

            tester.testInsertQuery();

        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally {
            tester.closeSession();
        }
//    
    }
}