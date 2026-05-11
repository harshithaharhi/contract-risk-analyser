package com.internship.tool.service;

import com.internship.tool.entity.Contract;
import com.internship.tool.exception.ContractNotFoundException;
import com.internship.tool.exception.InvalidContractException;
import com.internship.tool.repository.ContractRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @CacheEvict(value = "contracts", allEntries = true)
    public Contract createContract(Contract contract) {

        if (contract.getContractName() == null || contract.getContractName().isBlank()) {
            throw new InvalidContractException("Contract name is required");
        }

        if (contract.getVendorName() == null || contract.getVendorName().isBlank()) {
            throw new InvalidContractException("Vendor name is required");
        }

        if (contract.getContractValue() == null || contract.getContractValue() <= 0) {
            throw new InvalidContractException("Contract value must be greater than zero");
        }

        return contractRepository.save(contract);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Cacheable(value = "contracts")
    public Page<Contract> getAllContractsPaginated(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return contractRepository.findAll(pageable);
    }

    @Cacheable(value = "contract", key = "#id")
    public Contract getContractById(Long id) {

        return contractRepository.findById(id)
                .orElseThrow(() ->
                        new ContractNotFoundException("Contract not found with id: " + id));
    }

    public List<Contract> getContractsByStatus(String status) {
        return contractRepository.findByStatus(status);
    }

    public List<Contract> getContractsByVendor(String vendorName) {
        return contractRepository.findByVendorName(vendorName);
    }
}