package old;


import org.hibernate.SessionFactory;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/*
* This class follows the singleton pattern.
*/

public class HibernateUtilSingleton

{

private static final SessionFactory sessionFactory;
//private static final ServiceRegistry serviceRegistry;

    static{
        try{
            AnnotationConfiguration config = new AnnotationConfiguration();

            config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

            config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");

            //change the next line of code to match your MySQL url

            config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:8889/test");

            //change the next two lines of code to match your MySQL user name and password.

            config.setProperty("hibernate.connection.username", "root");

            config.setProperty("hibernate.connection.password", "root");

            //change the pool size to reflect how many users you expect your application to have initially

            config.setProperty("hibernate.connection.pool_size", "1");

            config.setProperty("hibernate.connection.autocommit", "true");

            config.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");

            /*
            * un-comment the next line of code if you want to be able to drop and recreate tables for your data classes listed below.  This is generally a bad idea for security reasons.
            */

            //config.setProperty("hibernate.hbm2ddl.auto", "create-drop");

            config.setProperty("hibernate.show_sql", "true");

            config.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");

            config.setProperty("hibernate.current_session_context_class", "thread");

            /*
            *  Add your classes here that you want to match your database tables
            *  The example has a User and a PhoneNumber class.
            */

            config.addAnnotatedClass(Picture.class);

            sessionFactory = config.buildSessionFactory();
            //this was to replace the above code:
//            Configuration configuration = new Configuration();
//            configuration.configure();
//            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        }

        catch (Throwable ex)

        {

            System.err.println("Initial SessionFactory creation failed." + ex);

            throw new ExceptionInInitializerError(ex);

        }

    }

    public static SessionFactory getSessionFactory()

    {

    return sessionFactory;

    }

    //make a private default constructor so that no other HibernateUtil can be created.

    private HibernateUtilSingleton(){



    }

}