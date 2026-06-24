package com.assignment.bfhl.controller;

import com.assignment.bfhl.dto.BfhlRequest;
import com.assignment.bfhl.dto.BfhlResponse;
import com.assignment.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    public ResponseEntity<BfhlResponse> processData(@Valid @RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processRequest(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
