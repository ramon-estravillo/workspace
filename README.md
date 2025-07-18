# 🧱 RRC Workspace

[![Version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-blue)](https://github.com/ramon-estravillo/workspace)
[![Build Status](https://github.com/ramon-estravillo/workspace/actions/workflows/github-ci.yml/badge.svg)](https://github.com/ramon-estravillo/workspace/actions/github-ci.yml)
[![License](https://img.shields.io/github/license/ramon-estravillo/workspace?style=flat-square)](LICENSE)

> Central repository for foundational modules, configurations, and shared libraries powering the **RRC microservices platform**.

---

## 📦 Modules

| Module | Description |
|--------|-------------|
| [![RRC Config](https://img.shields.io/badge/Module-rrc--config-blue)](./rrc-config) | Centralized **build configuration**, dependency management, and plugin versions |
| [![RRC Core](https://img.shields.io/badge/Module-rrc--core-green)](./rrc-core) | Core utilities and standardized exception handling |
| [![RRC Common](https://img.shields.io/badge/Module-rrc--common-yellow)](./rrc-common) | Domain models, constants, and shared system infrastructure |

---

## 🛠 Build Instructions

Standard build:

```bash
mvn clean install
```

With dynamic timestamp versioning:

```bash
mvn clean install -Dmaven.build.timestamp=$(date +%Y%m%d.%H%M.%S)
```

---

## 📁 Project Structure

```bash
workspace/
├── .gitignore
├── rrc-config/       # Centralized POM configuration
├── rrc-core/         # Utilities & exception handling
├── rrc-common/       # Shared models & cross-cutting logic
└── README.md
```

---

## 🔗 Related Docs

- [`rrc-core`](./rrc-core/README.md) — Utility & exception libraries
- [`rrc-common`](./rrc-common/README.md) — Domain models & helpers
- [`rrc-config`](./rrc-config/README.md) — Maven parent config

---

## 🏷 Versioning Strategy

```xml
<version>${dynamic.version.ctrl}</version>
```

Where dynamic.version.ctrl is computed using:

```xml
<project.version.base>1.0.0</project.version.base>
<maven.build.timestamp.format>yyyyMMdd.HHmm.ss</maven.build.timestamp.format>
```

To generate a dynamic version like 1.0.0-20250718.1042.31, use:

```bash
mvn clean install -Dmaven.build.timestamp=$(date +%Y%m%d.%H%M.%S)
```