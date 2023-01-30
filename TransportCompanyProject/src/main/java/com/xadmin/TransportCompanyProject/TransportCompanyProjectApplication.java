package com.xadmin.TransportCompanyProject;

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
		companyService.createCompany("XYZCompany2");



	}
}
