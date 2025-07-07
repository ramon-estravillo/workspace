# RRC Common Library (Multi-Module)

The **`rrc-common`** project is a shared library that contains reusable components across RCC services. It is structured as a multi-module Maven project, allowing you to separate domain objects, business logic, and shared service utilities.

---

## 🧱 Modules

### 📦 `rrc-common-bo`
Contains shared **business/domain objects** (DTOs, enums, constants, error types) used across RCC services.

### ⚙️ `rrc-common-svc`
Provides **shared service-level utilities**, such as helper methods, common validators, exception handlers, etc.

---

## 🔧 Usage

Include any submodule as a dependency in your service:

### Example: Use `rrc-common-bo`

```xml
<dependency>
    <groupId>com.git.rcc</groupId>
    <artifactId>rrc-common-bo</artifactId>
    <version>1.0.0</version>
</dependency>
