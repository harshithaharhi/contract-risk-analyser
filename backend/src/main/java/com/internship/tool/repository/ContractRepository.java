package com.internship.tool.repository;

import com.internship.tool.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByStatus(String status);

    List<Contract> findByVendorName(String vendorName);

    List<Contract> findByContractType(String contractType);
}