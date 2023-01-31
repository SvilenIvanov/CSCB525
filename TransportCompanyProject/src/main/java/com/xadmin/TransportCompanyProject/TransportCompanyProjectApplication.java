package com.xadmin.TransportCompanyProject;

import com.xadmin.TransportCompanyProject.entity.Client;
import com.xadmin.TransportCompanyProject.entity.Company;
import com.xadmin.TransportCompanyProject.services.ClientService;
import com.xadmin.TransportCompanyProject.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TransportCompanyProjectApplication {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ClientService clientService;


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TransportCompanyProjectApplication.class, args);
		TransportCompanyProjectApplication app = context.getBean(TransportCompanyProjectApplication.class);


		app.run();

	}
	private void run() {
		Client ZdravkoClient = new Client("Zdravko", "Sofia");
		Client Client2 = new Client("Pesho", "Plovdiv");

		clientService.createClient(ZdravkoClient);
		clientService.createClient(Client2);

		Company company = new Company("NewCompany");
		Company company2 = new Company("XYZCompany2");
		companyService.createCompany(company);
		companyService.createCompany(company2);
		System.out.println("Zdravko:" + ZdravkoClient.getId() + " and client2:" + Client2.getId());
		companyService.addClientToCompany(company, ZdravkoClient);
		companyService.addClientToCompany(company, Client2);
		companyService.saveCompany(company);

		//companyService.addClient(company, ZdravkoClient);



	}
}
