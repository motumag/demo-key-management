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