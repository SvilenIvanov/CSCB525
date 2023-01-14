import dao.ClientDAO;
import dao.CompanyDAO;
import dao.EmployeeDAO;
import dao.VehicleDAO;
import entity.Client;
import entity.Company;
import entity.Employee;
import entity.Vehicle;
import models.FuelType;
import models.LicenseType;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String args[]) {

        Company company = new Company("CompanyXY");
        Company company2 = new Company("Company2");

        CompanyDAO.saveCompany(company);
        CompanyDAO.saveCompany(company2);



        Employee employee = new Employee("Pesho", "Sofia, ulitsa 12", new BigDecimal("500"), company, LicenseType.A);
        EmployeeDAO.saveEmployee(employee);


        Client client = new Client("Client1", company2);
        ClientDAO.saveClient(client);

        Vehicle vehicle = new Vehicle("BMW", "750i", FuelType.PETROL, 5, 500, LicenseType.B, company);
        VehicleDAO.saveVehicle(vehicle);
    }
}
