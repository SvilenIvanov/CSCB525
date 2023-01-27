package com.example.demo;

import com.example.demo.services.CompanyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final CompanyService companyService;

    public Runner(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override

    public void run(String... args) throws Exception {
        companyService.createCompany();
    }
}
