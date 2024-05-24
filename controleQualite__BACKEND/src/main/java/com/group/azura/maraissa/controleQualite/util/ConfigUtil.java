package com.group.azura.maraissa.controleQualite.util;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConfigUtil {
    final Environment environment;
    public String currentActivatedProfiles() {

        String activeProfiles = String.join(", ", environment.getActiveProfiles());
        return (activeProfiles.isEmpty() ? "dev" : activeProfiles);
    }
}
