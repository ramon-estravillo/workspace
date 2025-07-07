# RCC Common Library

The `rcc-common` module provides shared utilities, constants, and domain objects that are reused across RCC services and modules. It helps reduce code duplication and promotes consistency across the platform.

---

## 📦 Contents

This module typically includes:

- 📚 **Common utility classes** – String manipulation, date/time helpers, etc.
- 📐 **Shared domain models** – DTOs or value objects used across modules.
- 🔁 **Enum and constant definitions** – Standardized values for use across services.
- ⚙️ **Exception classes** – Common error structures and base exception types.

---

## 🧩 Usage

To use this module in another Maven project:

```xml
<dependency>
    <groupId>com.git.rcc</groupId>
    <artifactId>rcc-common</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
