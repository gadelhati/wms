package com.oms.wms.security;

import com.oms.wms.persistence.model.Privilege;
import com.oms.wms.persistence.model.Role;
import com.oms.wms.persistence.model.User;
import com.oms.wms.persistence.repository.RepositoryPrivilege;
import com.oms.wms.persistence.repository.RepositoryRole;
import com.oms.wms.persistence.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryRole repositoryRole;
    @Autowired
    private RepositoryPrivilege repositoryPrivilege;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound("ADMIN", adminPrivileges);
        createRoleIfNotFound("USER", Arrays.asList(readPrivilege));

        Role adminRole = repositoryRole.findByName("ADMIN");
        User user = new User();
        user.setUsername("Test."+Math.random());
        user.setPassword(passwordEncoder.encode("test1"));
        user.setEmail("test1@test.com");
        user.setRole(Collections.singletonList(adminRole));
        user.setActive(true);
        repositoryUser.save(user);
        alreadySetup = true;
    }
    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = repositoryPrivilege.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            repositoryPrivilege.save(privilege);
        }
        return privilege;
    }
    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = repositoryRole.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPrivilege(privileges);
            repositoryRole.save(role);
        }
        return role;
    }
}