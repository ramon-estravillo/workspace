# 🧱 RRC Standardized Custom Exceptions for Spring Boot

[![Version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-blue)](https://github.com/ramon-estravillo/workspace)
[![Build Status](https://github.com/ramon-estravillo/workspace/actions/workflows/github-ci.yml/badge.svg)](https://github.com/ramon-estravillo/workspace/actions/github-ci.yml)

This module provides a structured set of custom exceptions for **consistent**, **meaningful**, and **reusable** error handling across all RRC microservices.

> 📦 Maven Module: `rrc-core-exception`  
> 🏷️ Version: `${dynamic.version.ctrl}` (generated using Maven timestamp)

---

## ✅ Exception Overview

| **Exception Name**                | **HTTP Status**            | **Purpose**                                                                 |
|----------------------------------|-----------------------------|------------------------------------------------------------------------------|
| `ValidationFailureException`     | `400 Bad Request`           | Business rule or custom validator violations                                |
| `ResourceNotFoundException`      | `404 Not Found`             | Resource (user, order, etc.) not found                                      |
| `DataIntegrityException`         | `409 Conflict`              | Database constraint violations                                               |
| `AuthenticationException`        | `401 Unauthorized`          | Invalid login credentials                                                    |
| `AuthorizationException`         | `403 Forbidden`             | User lacks access to the requested resource                                 |
| `OperationNotAllowedException`   | `403 Forbidden`             | Logical disallowance of an operation (e.g., cancel shipped order)           |
| `ExternalServiceException`       | `502 Bad Gateway`           | Downstream service/API failure                                               |
| `DuplicateResourceException`     | `409 Conflict`              | Duplicate key/resource exists (e.g., email or username)                      |
| `FileStorageException`           | `500 Internal Server Error` | Error during file upload/download or disk storage                            |
| `BusinessRuleViolationException` | `400 Bad Request`           | Domain-specific business logic violations                                    |
| `PaymentProcessingException`     | `502 Bad Gateway`           | Payment provider or gateway failure                                          |
| `ConfigurationException`         | `500 Internal Server Error` | Runtime misconfiguration or missing env setup                               |
| `RateLimitExceededException`     | `429 Too Many Requests`     | API throttling or request flooding                                           |
| `ConflictException`              | `409 Conflict`              | General purpose conflict (e.g., concurrent modifications)                    |
| `ServerErrorException`           | `500 Internal Server Error` | Fallback for unexpected internal errors                                      |

---

## 📁 Package Structure

```
rrc-core-exception
└── src
    └── main
        └── java
            └── com
                └── git
                    └── rrc
                        └── core
                            └── exception
                                ├── ApplicationException.java
                                ├── ValidationFailureException.java
                                ├── ResourceNotFoundException.java
                                ├── ...
                                ├── ServerErrorException.java
                                ├── GlobalExceptionHandler.java
                                └── ErrorResponse.java

```

- `ApplicationException.java`: Abstract base class for all custom exceptions.

- `GlobalExceptionHandler.java`: Centralized @RestControllerAdvice that maps exceptions to HTTP responses.

- `ErrorResponse.java`: DTO that standardizes the API error response payload.

---

## 📦 Maven Dependency

To use in another RRC module:
```xml
<dependency>
    <groupId>com.git.rrc.core</groupId>
    <artifactId>rrc-core-exception</artifactId>
    <version>${dynamic.version.ctrl}</version>
</dependency>
```

---

## 🎯 OpenAPI / Swagger Support

Document your exceptions in your service OpenAPI spec:

```java
@Schema(description = "Standard error response")
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}

@ApiResponses({
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
})
```

---

## 📌 Notes
- This module is not meant to be executable or deployable.
- It's shared via the parent project ``rrc-core`` to all other microservices.
- Exception classes extend a common base and can be caught globally or at the controller level.
