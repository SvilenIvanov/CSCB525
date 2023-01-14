package dao;

import configuration.SessionFactoryUtil;
import entity.Employee;
import entity.Transportation;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransportationDAO {
    public static void saveTransportation(Transportation transportation) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(transportation);
            transaction.commit();
        }
    }
}
