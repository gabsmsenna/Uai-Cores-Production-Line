package com.gabriel.UaiCores_ProductionLine.config;

import com.gabriel.UaiCores_ProductionLine.model.Role;
import com.gabriel.UaiCores_ProductionLine.model.User;
import com.gabriel.UaiCores_ProductionLine.repository.RoleRepository;
import com.gabriel.UaiCores_ProductionLine.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.RoleType.ADMIN.name());

        var userAdmin = userRepository.findByUserName("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin já existee");
                },
                () -> {
                    var user = new User();
                    user.setUserName("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("admin"));
                    user.setRole(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        );
    }
}
