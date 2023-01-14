package dao;

import configuration.SessionFactoryUtil;
import entity.Client;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDAO {
    public static void saveClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        }
    }
}
