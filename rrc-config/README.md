# 🛠 RRC Build Configuration (Parent POM)

The **`rrc-build-config`** project is a centralized Maven parent POM that standardizes build configurations, dependency versions, testing tools, code coverage, and project reporting across all RRC Java-based modules.

It simplifies and unifies how RRC microservices and shared libraries are built, tested, and released — making your development process consistent and maintainable.

---

## 📦 Purpose

- ✅ Centralize plugin and dependency versions
- ✅ Enforce consistent Java, Spring Boot, and testing configurations
- ✅ Enable out-of-the-box support for code coverage (JaCoCo), unit/integration tests, and Maven site generation
- ✅ Define common reporting and CI-ready build setups

---

## 🧱 Included Modules

The parent POM references the following sibling modules in a multi-module setup:

- `rrc-core`: Core utilities and exception handling logic for RRC services

> These modules are defined under `<modules>` and are expected to be located in parallel to `rrc-build-config`.

---

## ⚙️ Key Features

### ✅ Java & Spring Boot

- Java version: `17`
- Spring Boot BOM version: `3.5.3` (via `spring-boot-dependencies` import)

### ✅ Unified Testing Stack

Includes test dependency versions and plugin configuration:

- **JUnit 4 & 5**
- **Mockito (inline & JUnit Jupiter)**
- **PowerMock for legacy mocking scenarios**
- **Maven Surefire & Failsafe plugins** for unit and integration test execution
- **Tree-based test reporting** via `maven-surefire-junit5-tree-reporter`

### ✅ Code Coverage & Reporting

- **JaCoCo**: Unit and integration test coverage, merged and separate reports
- **Maven Site Plugin**: Generates a complete site including dependencies, plugins, and test coverage
- **Maven Project Info Reports Plugin**: Dependency, plugin, and summary reports

### 🧪 Example Reporting Outputs:
```
target/site/
├── index.html (Maven site homepage)
├── jacoco-ut/ (Unit test coverage)
├── jacoco-it/ (Integration test coverage)
└── jacoco-merged/ (Merged report)
```

### ✅ CI-Friendly Configuration

- Dynamic versioning support via timestamp (e.g., `1.0.0-20250717.1450.22`)
- Preconfigured plugins for **site deployment**, **SCM tagging**, and **SonarQube analysis**

---

## 📝 Project Info

- **Group ID**: `com.git.rrc`
- **Artifact ID**: `rrc-build-config`
- **Version**: `1.0.0`
- **Packaging**: `pom`

---

## 🧩 How to Use

Add this POM as the parent in your module's `pom.xml`:

```xml
<parent>
    <groupId>com.git.rrc</groupId>
    <artifactId>rrc-build-config</artifactId>
    <version>1.0.0</version>
    <relativePath>../rrc-config/pom.xml</relativePath>
</parent>
