package com.internship.tool.service;

import com.internship.tool.entity.Contract;
import com.internship.tool.exception.ContractNotFoundException;
import com.internship.tool.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.internship.tool.exception.InvalidContractException;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public Contract createContract(Contract contract) {

        if (contract.getContractName() == null || contract.getContractName().isBlank()) {
            throw new InvalidContractException("Contract name is required");
        }

        if (contract.getVendorName() == null || contract.getVendorName().isBlank()) {
            throw new InvalidContractException("Vendor name is required");
        }

        return contractRepository.save(contract);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract getContractById(Long id) {

        return contractRepository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException("Contract not found with id: " + id));
    }

    public List<Contract> getContractsByStatus(String status) {
        return contractRepository.findByStatus(status);
    }

    public List<Contract> getContractsByVendor(String vendorName) {
        return contractRepository.findByVendorName(vendorName);
    }
}