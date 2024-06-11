package base.exception;

import org.hibernate.HibernateException;

public class DatabaseOperationException extends Throwable {
    public DatabaseOperationException(String s, HibernateException e) {
        super(s, e);
    }
}
