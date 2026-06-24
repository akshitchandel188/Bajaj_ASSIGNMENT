package com.assignment.bfhl.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class BfhlRequest {

    @NotNull(message = "data must not be null")
    @NotEmpty(message = "data must not be empty")
    private List<String> data;

    public BfhlRequest() {
    }

    public BfhlRequest(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
