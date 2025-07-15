package com.git.rrc.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Should serialize error response from dto to json")
    void shouldSerializeErrorResponseToJson() throws Exception {
        Error error = new Error("email", "not-an-email");
        ErrorResponse response = new ErrorResponse(
                "https://example.com/errors/validation",
                "Validation Error",
                400,
                "Invalid Input",
                "/api/register",
                "RC-111"
        );
        response.getErrors().add(error);
        String json = mapper.writeValueAsString(response);

        assertTrue(json.contains("\"title\":\"Validation Error\""));
        assertTrue(json.contains("\"field\":\"email\""));
        assertTrue(json.contains("\"rejectedValue\":\"not-an-email\""));
        assertTrue(json.contains("\"status\":400"));
    }

    @Test
    @DisplayName("Should deserialize error response from json to dto")
    void shouldDeserializeErrorResponseFromJson() throws Exception {
        String json = """
            {
              "type": "https://example.com/errors/validation",
              "title": "Validation Error",
              "status": 400,
              "detail": "Invalid input",
              "instance": "/api/register",
              "errorCode": "RC-111",
              "timestamp": "2025-07-08T12:00:00Z",
              "errors": [
                {
                  "field": "email",
                  "message": "must be a valid email",
                  "rejectedValue": "not-an-email"
                }
              ]
            }
        """;

        ErrorResponse response = mapper.readValue(json, ErrorResponse.class);

        assertEquals(400, response.getStatus());
        assertEquals("Validation Error", response.getTitle());
        assertEquals(1, response.getErrors().size());
        assertEquals("email", response.getErrors().get(0).getField());
    }
}