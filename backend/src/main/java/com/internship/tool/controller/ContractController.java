package com.internship.tool.controller;

import com.internship.tool.entity.Contract;
import com.internship.tool.exception.ContractNotFoundException;
import com.internship.tool.service.ContractService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PreAuthorize("permitAll()")
    @PostMapping("/create")
    public ResponseEntity<Contract> createContract(@Valid @RequestBody Contract contract) {

        Contract savedContract = contractService.createContract(contract);

        return new ResponseEntity<>(savedContract, HttpStatus.CREATED);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public ResponseEntity<Page<Contract>> getAllContracts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                contractService.getAllContractsPaginated(page, size)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long id) {

        Contract contract = contractService.getContractById(id);

        return ResponseEntity.ok(contract);
    }

    @ExceptionHandler(ContractNotFoundException.class)
    public ResponseEntity<String> handleContractNotFound(ContractNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}