import dao.CompanyDAO;
import dao.EmployeeDAO;
import entity.Company;
import entity.Employee;
import models.LicenseTypes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String args[]) {

        Company company = new Company("CompanyXX");

        CompanyDAO.saveCompany(company);

        List<Company> companyList = Arrays
                .asList();
        CompanyDAO.saveCompanies(companyList);
        companyList = CompanyDAO.readCompanies();
        companyList.stream().forEach(System.out::println);


        Employee employee = new Employee("Pesho", "Sofia, ulitsa 12", new BigDecimal("500"), company, LicenseTypes.A);
        EmployeeDAO.saveEmployee(employee);
    }
}
