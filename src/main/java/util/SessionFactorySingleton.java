package util;

import domain.*;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {

    private static SessionFactorySingleton sessionFactorySingleton;
    private static SessionFactory INSTANCE;


    private SessionFactorySingleton() {
        var registry = new StandardServiceRegistryBuilder()
                .configure ()
                .build ();
        INSTANCE = new MetadataSources( registry )
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Expert.class)
                .addAnnotatedClass(Offer.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Service.class)
                .addAnnotatedClass(SubService.class)
                .addAnnotatedClass(SubService_Expert.class)
                .buildMetadata ()
                .buildSessionFactory ();
    }

    public static SessionFactory getInstance() {
        if (sessionFactorySingleton == null) {
            sessionFactorySingleton = new SessionFactorySingleton ();
        }
        return INSTANCE;
    }
}