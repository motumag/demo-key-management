# 🔐 Spring Boot Integration Guide for Key Management Library

This guide provides step-by-step instructions to integrate the `key-management-library` into your Spring Boot project using PostgreSQL.

---

## ✅ Step 1: Create a Spring Boot Project

Create a new Spring Boot project using [Spring Initializr](https://start.spring.io) with the following dependencies:

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Lombok

You can use your favorite IDE (IntelliJ IDEA, VS Code, Eclipse) or CLI tools like `curl` or `Spring CLI` to bootstrap the project.

---

## ✅ Step 2: Add Key Management Library Dependency

Open your `pom.xml` and add the following dependency to include the key management library into your project:

```xml
<dependency>
  <groupId>com.motumag</groupId>
  <artifactId>key-management-library</artifactId>
  <version>1.0.1</version>
</dependency>
```

---

## ✅ Step 3: Configure PostgreSQL Database

Add the following configuration into your `application.properties` or `application.yml` file to set up the database and logging settings:

```properties
spring.application.name=demoKeyManagement

# ===============================
# = DATABASE CONFIGURATION
# ===============================
spring.datasource.url=jdbc:postgresql://localhost:5432/demo_key_management
spring.datasource.username=postgres
spring.datasource.password=momo@123
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA & Hibernate
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Connection Pool (HikariCP)
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.idle-timeout=10000
spring.datasource.hikari.max-lifetime=30000
spring.datasource.hikari.minimum-idle=5

# Logging
logging.level.root=INFO
logging.level.org.springframework.web=INFO
logging.level.com.motumag=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n

server.port=8082
```

---

## ✅ Step 4: Implement Key Management Service

Create a service class to demonstrate key generation and validation using the Key Management Library:

```java
package com.motumag.demokeymanagement.demo;

import com.motumag.keymanagement.dto.KeyGenerationRequest;
import com.motumag.keymanagement.dto.KeyRotationResponse;
import com.motumag.keymanagement.dto.KeyValidationResponse;
import com.motumag.keymanagement.enums.Environment;
import com.motumag.keymanagement.service.KeyRotationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service responsible for demonstrating key generation and validation
 * using the Key Management Library developed by Motuma Gishu.
 * Author: Motuma Gishu, Senior Software Engineer
 * Date: 05/18/2025
 */
@Service
public class MyService {

    private final KeyRotationService keyRotationService;

    public MyService(KeyRotationService keyRotationService) {
        this.keyRotationService = keyRotationService;
    }

    public Map<String, Object> demoKeyManagement() {
        // Build key generation request with sample metadata
        KeyGenerationRequest request = new KeyGenerationRequest();
        request.setApiKeyName("motuma-Test-Key-1");
        request.setOwnerName("Nova Team");
        request.setOwnerType("INTERNAL");
        request.setEnvironment(Environment.TEST); // Change to Environment.PROD as needed
        request.setCreatedBy("user@example.com");

        // Generate the API key using the key management service
        KeyRotationResponse response = keyRotationService.generateApiKey(request);

        // Validate the generated key within the specified environment
        KeyValidationResponse validation = keyRotationService.validateApiKey(response.getCurrentKey(), Environment.TEST);

        // Compose response payload
        Map<String, Object> result = new HashMap<>();
        result.put("apiKeyName", response.getApiKeyName());
        result.put("currentKey", response.getCurrentKey());
        result.put("valid", validation.isValid());
        result.put("validationMessage", validation.getMessage());

        return result;
    }
}
```

With this setup, your Spring Boot application is now integrated with the Key Management Library. You can build on top of this structure to implement full key lifecycle management including revocation, rotation, and audit logging.

---

✅ Now you’re ready to manage your API keys in a secure, auditable, and scalable way using this library.