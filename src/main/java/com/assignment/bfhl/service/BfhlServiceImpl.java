package com.assignment.bfhl.service;

import com.assignment.bfhl.dto.BfhlRequest;
import com.assignment.bfhl.dto.BfhlResponse;
import com.assignment.bfhl.exception.InvalidRequestException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[+-]?\\d+$");
    private static final Pattern ALPHABET_PATTERN = Pattern.compile("^[A-Za-z]+$");
    private static final BigInteger TWO = BigInteger.valueOf(2);

    @Override
    public BfhlResponse processRequest(BfhlRequest request) {
        validateRequest(request);

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        StringBuilder alphabeticBuilder = new StringBuilder();
        BigInteger total = BigInteger.ZERO;

        for (String rawValue : request.getData()) {
            if (rawValue == null) {
                throw new InvalidRequestException("data list contains a null element");
            }

            String value = rawValue.trim();
            if (value.isEmpty()) {
                throw new InvalidRequestException("data list contains an empty string");
            }

            if (NUMBER_PATTERN.matcher(value).matches()) {
                BigInteger number = new BigInteger(value);
                total = total.add(number);
                if (number.mod(TWO).abs().equals(BigInteger.ONE)) {
                    oddNumbers.add(value);
                } else {
                    evenNumbers.add(value);
                }
            } else if (ALPHABET_PATTERN.matcher(value).matches()) {
                String uppercased = value.toUpperCase();
                alphabets.add(uppercased);
                alphabeticBuilder.append(uppercased);
            } else {
                specialCharacters.add(value);
            }
        }

        String concatString = buildConcatString(alphabeticBuilder.toString());

        return new BfhlResponse(
                true,
                "student-001",
                "student@example.com",
                "BFHL-001",
                oddNumbers,
                evenNumbers,
                alphabets,
                specialCharacters,
                total.toString(),
                concatString);
    }

    private void validateRequest(BfhlRequest request) {
        if (request == null) {
            throw new InvalidRequestException("request body must not be null");
        }

        if (request.getData() == null || request.getData().isEmpty()) {
            throw new InvalidRequestException("data must not be empty");
        }
    }

    private String buildConcatString(String combinedAlphabetic) {
        if (combinedAlphabetic.isEmpty()) {
            return "";
        }

        String reversed = new StringBuilder(combinedAlphabetic).reverse().toString();
        StringBuilder formatted = new StringBuilder(reversed.length());

        boolean upper = true;
        for (char character : reversed.toCharArray()) {
            if (upper) {
                formatted.append(Character.toUpperCase(character));
            } else {
                formatted.append(Character.toLowerCase(character));
            }
            upper = !upper;
        }

        return formatted.toString();
    }
}
