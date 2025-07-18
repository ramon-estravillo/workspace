# 🧱 RRC Core Libraries (Multi-Module)

[![Version](https://img.shields.io/badge/version-1.0.0--SNAPSHOT-blue)](https://github.com/ramon-estravillo/workspace)
[![Build Status](https://github.com/ramon-estravillo/workspace/actions/workflows/github-ci.yml/badge.svg)](https://github.com/ramon-estravillo/workspace/actions/github-ci.yml)

The **`rrc-core`** project is a set of foundational libraries that provide **reusable**, **shared**, and **standardized** components across RRC microservices.

> 📦 Parent Module: `rrc-core`  
> 🏷️ Version: `${dynamic.version.ctrl}` (auto-generated using build timestamp)

---

## 🧱 Modules

### ⚙️ `rrc-core-utility`
Provides shared **utility methods**, helper functions, and reusable components used across multiple RRC services and modules.

### ❗ `rrc-core-exception`
Contains centralized **exception handling utilities**, including:
- Custom exceptions
- Error codes
- Standardized error response structure
- Global exception resolver

---

## 📦 How to Use

You can include any sub-module as a dependency:

### Example: Use `rrc-core-utility`

```xml
<dependency>
    <groupId>com.git.rrc.core</groupId>
    <artifactId>rrc-core-utility</artifactId>
    <version>${dynamic.version.ctrl}</version>
</dependency>
