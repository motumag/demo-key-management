package com.motumag.demokeymanagement.controller;

import com.motumag.demokeymanagement.demo.MyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Author: Motuma Gishu, Senior Software Engineer
 * Date: 5/18/25
 * Description: KeyController
 */
@RestController
@RequestMapping("/api/keys")
public class KeyController {
    private final MyService myService;

    public KeyController(MyService myService) {
        this.myService = myService;
    }

    @PostMapping("/test")
    public Map<String, Object> testKeyGeneration() {
        return myService.demoKeyManagement();
    }
}
