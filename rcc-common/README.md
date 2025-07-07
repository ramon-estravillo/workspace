# RCC Common Library (Multi-Module)

The **`rcc-common`** project is a shared library that contains reusable components across RCC services. It is structured as a multi-module Maven project, allowing you to separate domain objects, business logic, and shared service utilities.

---

## 🧱 Modules

### 📦 `rcc-common-bo`
Contains shared **business/domain objects** (DTOs, enums, constants, error types) used across RCC services.

### ⚙️ `rrc-common-svc`
Provides **shared service-level utilities**, such as helper methods, common validators, exception handlers, etc.

---

## 🔧 Usage

Include any submodule as a dependency in your service:

### Example: Use `rcc-common-bo`

```xml
<dependency>
    <groupId>com.git.rcc</groupId>
    <artifactId>rcc-common-bo</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
