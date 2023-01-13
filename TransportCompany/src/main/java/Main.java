import dao.CompanyDAO;
import dao.OwnerDAO;
import entity.Company;
import entity.Owner;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String args[]) {

        Company company = new Company("Company1");

        CompanyDAO.saveCompany(company);

        List<Company> companyList = Arrays
                .asList();
        CompanyDAO.saveCompanies(companyList);
        companyList = CompanyDAO.readCompanies();
        companyList.stream().forEach(System.out::println);

        Owner owner = new Owner("Ivanov");
        OwnerDAO.saveOwner(owner);
        Owner owner1 = new Owner("Petrov");
        OwnerDAO.saveOwner(owner1);
    }
}
