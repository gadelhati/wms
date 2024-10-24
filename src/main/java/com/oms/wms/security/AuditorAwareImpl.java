package com.oms.wms.security;

import com.oms.wms.persistence.model.User;
import com.oms.wms.persistence.repository.RepositoryUser;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditorAwareImpl implements AuditorAware<User> {

    private final RepositoryUser repositoryUser;

    public AuditorAwareImpl(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }
    @Override @NonNull
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof UserDetails)) {
            return Optional.empty();
        }
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return repositoryUser.findByUsername(username);
    }
}