package util;

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
                .addAnnotatedClass(Object.class)
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