package com.assignment.bfhl.service;

import com.assignment.bfhl.dto.BfhlRequest;
import com.assignment.bfhl.dto.BfhlResponse;

public interface BfhlService {

    BfhlResponse processRequest(BfhlRequest request);
}
