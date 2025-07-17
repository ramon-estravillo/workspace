# RRC Core Libraries (Multi-Module)

The **`rrc-core`** project is a set of foundational libraries that provide reusable, shared components across RRC microservices. Structured as a **multi-module Maven project**, it includes utility functions, standardized exception handling, and other core building blocks.

---

## 🧱 Modules

### ⚙️ `rrc-core-utility`
Provides shared **utility methods**, helper functions, and reusable components used across multiple RRC services and modules.

### ❗ `rrc-core-exception`
Contains centralized **exception handling utilities**, including custom exceptions, error codes, and standardized error response structures.

---

## 🔧 Usage

You can include any module as a dependency in your service or application as needed.

### Example: Use `rrc-core-utility`

```xml
<dependency>
    <groupId>com.git.rrc.core</groupId>
    <artifactId>rrc-core-utility</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
