package com.internship.tool;

import com.internship.tool.entity.Contract;
import com.internship.tool.exception.ContractNotFoundException;
import com.internship.tool.repository.ContractRepository;
import com.internship.tool.service.ContractService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContractServiceTest {

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private ContractService contractService;

    private Contract contract;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        contract = new Contract();

        contract.setId(1L);
        contract.setContractName("Software Agreement");
        contract.setVendorName("Infosys");
        contract.setContractType("IT");
        contract.setStatus("ACTIVE");
        contract.setContractValue(50000.0);
    }

    @Test
    void testCreateContract() {

        when(contractRepository.save(contract)).thenReturn(contract);

        Contract savedContract = contractService.createContract(contract);

        assertNotNull(savedContract);

        assertEquals("Software Agreement", savedContract.getContractName());
    }

    @Test
    void testGetAllContracts() {

        when(contractRepository.findAll())
                .thenReturn(Arrays.asList(contract));

        assertEquals(1, contractService.getAllContracts().size());
    }

    @Test
    void testGetContractById() {

        when(contractRepository.findById(1L))
                .thenReturn(Optional.of(contract));

        Contract foundContract = contractService.getContractById(1L);

        assertEquals(1L, foundContract.getId());
    }

    @Test
    void testContractNotFound() {

        when(contractRepository.findById(2L))
                .thenReturn(Optional.empty());

        assertThrows(ContractNotFoundException.class, () -> {
            contractService.getContractById(2L);
        });
    }

    @Test
    void testGetContractsByStatus() {

        when(contractRepository.findByStatus("ACTIVE"))
                .thenReturn(Arrays.asList(contract));

        assertEquals(1,
                contractService.getContractsByStatus("ACTIVE").size());
    }

    @Test
    void testGetContractsByVendor() {

        when(contractRepository.findByVendorName("Infosys"))
                .thenReturn(Arrays.asList(contract));

        assertEquals(1,
                contractService.getContractsByVendor("Infosys").size());
    }
}