package com.mvm.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class InfoController {

    private final Environment environment;

    public InfoController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        Map<String, Object> info = new HashMap<>();

        try {
            // Get hostname
            String hostname = InetAddress.getLocalHost().getHostName();
            info.put("hostname", hostname);
        } catch (UnknownHostException e) {
            info.put("hostname", "Unknown");
        }

        // Get all environment variables
        Map<String, String> envVariables = new HashMap<>();
        
        // Add system environment variables
        Map<String, String> systemEnv = System.getenv();
        envVariables.putAll(systemEnv);
        
        // Add system properties
        Map<String, String> systemProps = System.getProperties().entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
        envVariables.putAll(systemProps);

        info.put("environment", envVariables);

        return info;
    }
}
