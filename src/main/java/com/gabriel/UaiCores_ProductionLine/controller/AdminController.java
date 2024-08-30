package com.gabriel.UaiCores_ProductionLine.controller;

import com.gabriel.UaiCores_ProductionLine.model.AdminUser;
import com.gabriel.UaiCores_ProductionLine.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping()
    public ResponseEntity<List<AdminUser>> getAllAdminUser() {
        if (adminService.getAllAdminUsers().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<AdminUser> listOfAllAdminUsers = adminService.getAllAdminUsers();
            return ResponseEntity.ok().body(listOfAllAdminUsers);
        }
    }

    @PostMapping()
    public ResponseEntity<AdminUser> postAdminUser(@RequestBody  AdminUser adminUser) {

        try {
            AdminUser adminUserObj = adminService.createAdminUser(adminUser);

            return new ResponseEntity<>(adminUserObj, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(adminUser);
        }
    }
}
