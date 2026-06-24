package com.assignment.bfhl.test;

import com.assignment.bfhl.controller.BfhlController;
import com.assignment.bfhl.dto.BfhlRequest;
import com.assignment.bfhl.dto.BfhlResponse;
import com.assignment.bfhl.service.BfhlService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BfhlController.class)
class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BfhlService bfhlService;

    @Test
    void shouldReturnOkForValidRequest() throws Exception {
        BfhlResponse response = new BfhlResponse(
                true,
                "student-001",
                "student@example.com",
                "BFHL-001",
                List.of("11"),
                List.of("10"),
                List.of("A"),
                List.of("#@%"),
                "21",
                "A");

        Mockito.when(bfhlService.processRequest(Mockito.any(BfhlRequest.class))).thenReturn(response);

        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"data\":[\"10\",\"11\",\"A\",\"#@%\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"is_success\":true,\"user_id\":\"student-001\",\"email\":\"student@example.com\",\"roll_number\":\"BFHL-001\",\"odd_numbers\":[\"11\"],\"even_numbers\":[\"10\"],\"alphabets\":[\"A\"],\"special_characters\":[\"#@%\"],\"sum\":\"21\",\"concat_string\":\"A\"}"));
    }
}
