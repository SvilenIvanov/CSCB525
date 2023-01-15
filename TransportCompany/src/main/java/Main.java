import dao.*;
import entity.*;
import models.FuelType;
import models.LicenseType;
import models.TransportationType;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Company company = new Company("CompanyXY");
        //Company company2 = new Company("Company2");

        CompanyDAO.saveCompany(company);
        //CompanyDAO.saveCompany(company2);



        Employee employee = new Employee("Pesho", "Sofia, ulitsa 12", new BigDecimal("500"), company, LicenseType.A);
        EmployeeDAO.saveEmployee(employee);


        Client client = new Client("Client1", company);
        ClientDAO.saveClient(client);

        Vehicle vehicle = new Vehicle("BMW", "750i", FuelType.PETROL, 5, 500, LicenseType.B, company);
        VehicleDAO.saveVehicle(vehicle);
        Transportation transportation = new Transportation("Sofia", "Plovdiv", new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()), new BigDecimal("500"), TransportationType.CARGO_TRIP, 5, employee, company, vehicle, client);
        TransportationDAO.saveTransportation(transportation);


    }
}
