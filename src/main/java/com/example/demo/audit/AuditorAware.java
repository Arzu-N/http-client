package com.example.demo.audit;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditAware")
public class AuditorAware implements org.springframework.data.domain.AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("admin");
    }
}
