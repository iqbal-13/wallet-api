package com.dti.finalproject.group3.walletapi.transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponseDTO> createOne(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        Transaction newTransaction = transactionRequestDTO.convertToEntity();
        Transaction savedTransaction = this.transactionService.create(newTransaction);
        TransactionResponseDTO transactionResponseDTO = savedTransaction.convertToDTO();

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponseDTO);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponseDTO>> getAll(@RequestParam(value = "minAmount", defaultValue = "0", required = false) Long minAmount,
                                                    @RequestParam(value = "maxAmount", defaultValue = "999999999999", required = false) Long maxAmount,
                                                    @RequestParam(value = "fromDate", defaultValue = "01-01-0001", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fromDate,
                                                    @RequestParam(value = "toDate", defaultValue = "31-12-9999", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate toDate
                                                    // @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                                                    // @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy
                                                    ) {
        // Sort sort = Sort.by(sortBy);
        // if (orderBy.equals("desc")) {
        //     sort = Sort.by(Direction.DESC, sortBy);
        // }

        List<Transaction> transactions = this.transactionService.getAllTransactionFromCustomer(minAmount, maxAmount, fromDate, toDate);
        List<TransactionResponseDTO> transactionResponseDTOs = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionResponseDTO transactionResponseDTO = transaction.convertToDTO();
            transactionResponseDTOs.add(transactionResponseDTO);
        }

        return ResponseEntity.ok().body(transactionResponseDTOs);
    }
}
