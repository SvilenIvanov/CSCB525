import dao.ClientDAO;
import dao.CompanyDAO;
import dao.EmployeeDAO;
import entity.Client;
import entity.Company;
import entity.Employee;
import models.LicenseType;

import java.math.BigDecimal;

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
    }
}
