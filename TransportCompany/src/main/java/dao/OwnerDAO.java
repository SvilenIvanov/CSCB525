package dao;

import configuration.SessionFactoryUtil;
import entity.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OwnerDAO {
    public static void saveOwner(Owner owner) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(owner);
            transaction.commit();
        }
    }
}
