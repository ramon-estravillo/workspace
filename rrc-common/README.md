# RRC Shared Libraries (Multi-Module)

The **`rrc-common`** project is a set of shared libraries that provide reusable, foundational components across RRC services. Structured as a multi-module Maven project, it separates core domain models, infrastructure utilities, and service-level functionality for better modularity and reusability.

---

## 🧱 Modules

### 📦 `rrc-common-model`
Contains shared **domain models**, including DTOs, enums, constants, and value objects used across RRC applications and services.

### ⚙️ `rrc-common-system`
Provides **service infrastructure utilities**, such as helper methods, configuration tools, exception handling, and cross-cutting concerns common to RRC modules.

---

## 🔧 Usage

You can include any module as a dependency in your service or application.

### Example: Use `rrc-common-model`

```xml
<dependency>
    <groupId>com.git.rrc</groupId>
    <artifactId>rrc-common-model</artifactId>
    <version>1.0.0</version>
</dependency>
