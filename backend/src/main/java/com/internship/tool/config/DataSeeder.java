package com.internship.tool.config;

import com.internship.tool.entity.Contract;
import com.internship.tool.repository.ContractRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ContractRepository contractRepository;

    public DataSeeder(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public void run(String... args) {

        if (contractRepository.count() == 0) {

            for (int i = 1; i <= 30; i++) {

                Contract contract = new Contract();

                contract.setContractName("Contract " + i);
                contract.setVendorName("Vendor " + i);
                contract.setContractType("IT");
                contract.setStatus("ACTIVE");
                contract.setContractValue(1000.0 + i);
                contract.setStartDate(LocalDateTime.now());
                contract.setEndDate(LocalDateTime.now().plusDays(30));
                contract.setDescription("Demo contract " + i);

                contractRepository.save(contract);
            }
        }
    }
}