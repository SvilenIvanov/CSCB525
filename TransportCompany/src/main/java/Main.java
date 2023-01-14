import dao.ClientDAO;
import dao.CompanyDAO;
import dao.EmployeeDAO;
import entity.Client;
import entity.Company;
import entity.Employee;
import models.LicenseTypes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String args[]) {

        Company company = new Company("CompanyXY");

        CompanyDAO.saveCompany(company);

        List<Company> companyList = List.of();
        CompanyDAO.saveCompanies(companyList);
        companyList = CompanyDAO.readCompanies();
        companyList.forEach(System.out::println);


        Employee employee = new Employee("Pesho", "Sofia, ulitsa 12", new BigDecimal("500"), company, LicenseTypes.A);
        EmployeeDAO.saveEmployee(employee);


        Client client = new Client("Client1", company);
        ClientDAO.saveClient(client);
    }
}
