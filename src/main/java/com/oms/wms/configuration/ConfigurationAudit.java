package com.oms.wms.configuration;

import com.oms.wms.persistence.model.User;
import com.oms.wms.persistence.repository.RepositoryUser;
import com.oms.wms.security.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration @EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ConfigurationAudit {
    private final RepositoryUser repositoryUser;

    public ConfigurationAudit(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }
    @Bean
    public AuditorAware<User> auditorAware() {
        return new AuditorAwareImpl(repositoryUser);
    }
}