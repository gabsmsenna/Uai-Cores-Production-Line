package com.gabriel.UaiCores_ProductionLine.controller;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.User.CreateUserDTO;
import com.gabriel.UaiCores_ProductionLine.model.Role;
import com.gabriel.UaiCores_ProductionLine.repository.RoleRepository;
import com.gabriel.UaiCores_ProductionLine.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDTO createUserDTO) {

        var externalOfficerRole = roleRepository.findByName(Role.RoleType.EXTERNAL_OFFICER.name());
        

        return ResponseEntity.ok().build();
    }
}
