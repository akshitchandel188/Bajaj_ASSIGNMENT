package com.assignment.bfhl.test;

import com.assignment.bfhl.dto.BfhlRequest;
import com.assignment.bfhl.dto.BfhlResponse;
import com.assignment.bfhl.service.BfhlServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BfhlServiceImplTest {

    private final BfhlServiceImpl service = new BfhlServiceImpl();

    @Test
    void processRequestShouldClassifyDataCorrectly() {
        BfhlRequest request = new BfhlRequest(List.of("A", "ABCD", "DOE", "10", "11", "#@%"));

        BfhlResponse response = service.processRequest(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(List.of("11"), response.getOddNumbers());
        Assertions.assertEquals(List.of("10"), response.getEvenNumbers());
        Assertions.assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        Assertions.assertEquals(List.of("#@%"), response.getSpecialCharacters());
        Assertions.assertEquals("21", response.getSum());
        Assertions.assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    void processRequestShouldReturnEmptyConcatWhenNoAlphabeticValues() {
        BfhlRequest request = new BfhlRequest(List.of("10", "20", "123"));

        BfhlResponse response = service.processRequest(request);

        Assertions.assertEquals("153", response.getSum());
        Assertions.assertTrue(response.getAlphabets().isEmpty());
        Assertions.assertEquals("", response.getConcatString());
    }
}
