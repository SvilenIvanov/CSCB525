package dao;

import configuration.SessionFactoryUtil;
import entity.Employee;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VehicleDAO {
    public static void saveVehicle(Vehicle Vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(Vehicle);
            transaction.commit();
        }
    }
}
