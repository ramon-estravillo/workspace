## 🧱 Standardized Custom Exceptions for a Spring Boot Application

This project defines a set of custom exceptions to provide clear, consistent, and maintainable error handling throughout the application.

### ✅ Exception Overview

| **Exception Name**                | **HTTP Status** | **When to Use**                                                                 | **Source**                             |
|----------------------------------|-----------------|---------------------------------------------------------------------------------|--------------------------------------|
| `ValidationFailureException`     | 400 Bad Request | Input fails business rule validation or custom validator logic                  | [`ValidationFailureException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/ValidationFailureException.java) |
| `ResourceNotFoundException`      | 404 Not Found   | Resource (e.g., user, order) not found in the database                          | [`ResourceNotFoundException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/ResourceNotFoundException.java) |
| `DataIntegrityException`         | 409 Conflict    | Database constraints violated (e.g., duplicate key, foreign key violation)      | [`DataIntegrityException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/DataIntegrityException.java) |
| `AuthenticationException`        | 401 Unauthorized| Login fails due to invalid credentials                                          | [`AuthenticationException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/AuthenticationException.java) |
| `AuthorizationException`         | 403 Forbidden   | User does not have permission to access the requested resource                  | [`AuthorizationException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/AuthorizationException.java) |
| `OperationNotAllowedException`   | 403 Forbidden   | Operation disallowed in current context (e.g., canceling a shipped order)       | [`OperationNotAllowedException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/OperationNotAllowedException.java) |
| `ExternalServiceException`       | 502 Bad Gateway | External API or service call fails                                              | [`ExternalServiceException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/ExternalServiceException.java) |
| `DuplicateResourceException`     | 409 Conflict    | Resource already exists (e.g., duplicate email or username)                     | [`DuplicateResourceException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/DuplicateResourceException.java) |
| `FileStorageException`           | 500 Internal Server Error | Error during file upload/download or storage                           | [`FileStorageException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/FileStorageException.java) |
| `BusinessRuleViolationException` | 400 Bad Request | High-level domain/business logic violation (e.g., account already closed)       | [`BusinessRuleViolationException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/BusinessRuleViolationException.java) |
| `PaymentProcessingException`     | 502 Bad Gateway | Payment gateway or transaction failure                                          | [`PaymentProcessingException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/PaymentProcessingException.java) |
| `ConfigurationException`         | 500 Internal Server Error | Application misconfiguration or missing critical runtime settings        | [`ConfigurationException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/ConfigurationException.java) |
| `RateLimitExceededException`     | 429 Too Many Requests | API call throttling or too many requests                                    | [`RateLimitExceededException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/RateLimitExceededException.java) |
| `ConflictException`              | 409 Conflict    | General conflict (e.g., concurrent update conflicts)                          | [`ConflictException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/ConflictException.java) |
| `ServerErrorException`           | 500 Internal Server Error | Unexpected internal server errors (e.g., null pointer, I/O failure)       | [`ServerErrorException.java`](https://github.com/yourorg/yourrepo/blob/main/src/main/java/com/yourcompany/yourproject/exception/ServerErrorException.java) |

---

### 📁 Recommended Package Structure

Organize your exception classes in a dedicated package for better maintainability:

```
src/main/java/com/yourcompany/yourproject/exception
├── ApplicationException.java # Base class for all custom exceptions
├── ValidationFailureException.java
├── ResourceNotFoundException.java
├── DataIntegrityException.java
├── ...
├── ServerErrorException.java
├── GlobalExceptionHandler.java # @RestControllerAdvice for centralized handling
├── ErrorResponse.java # DTO returned in API error responses
```


---

### 📖 OpenAPI / Swagger Integration

You can map these exceptions in your OpenAPI specification to provide detailed API error responses. Use annotations like:

```java
@Schema(description = "Error response model")
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
    // getters, setters
}
@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    // etc.
})
