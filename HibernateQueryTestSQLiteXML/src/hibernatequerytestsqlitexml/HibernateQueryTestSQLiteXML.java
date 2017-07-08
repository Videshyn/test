package hibernatequerytestsqlitexml;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateQueryTestSQLiteXML {

    public static void main(String[] args) {
        // создание конфигурации
        Configuration configuration = new Configuration().configure();
        //считывание настроек с файла конфигурации
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
        // получили смысловое содержание файла конфигурации
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        //Распарсили - разбили сесию на составляющие На основании настроек создается sessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        // на основании отдельных значений свойств создали фабрику соединения
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Category category = new Category("Computer");

        Product pc = new Product("DELL PC", "Quad-core PC", 1200, category);
        Product laptop = new Product("MacBook", "Apple High-end laptop", 2100, category);
        Product phone = new Product("iPhone 5", "Apple Best-selling smartphone", 499, category);
        Product tablet = new Product("iPad 3", "Apple Best-selling tablet", 1099, category);

        Set<Product> products = new HashSet<Product>();
        products.add(pc);
        products.add(laptop);
        products.add(phone);
        products.add(tablet);

        category.setProducts(products); // изменяет данные

        session.save(category);

        session.getTransaction().commit();
        session.close();

    }

}
