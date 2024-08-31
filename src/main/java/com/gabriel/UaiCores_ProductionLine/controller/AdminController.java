package com.gabriel.UaiCores_ProductionLine.controller;

import com.gabriel.UaiCores_ProductionLine.model.AdminUser;
import com.gabriel.UaiCores_ProductionLine.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        Optional<AdminUser> adminUserById = adminService.getAdminUserById(id);
        
        if (adminUserById.isPresent()) {
            return new ResponseEntity<>(adminUserById.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + id + " não encontrado na aplicação");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdminUser> updateAdmin(@PathVariable Long id, @RequestBody AdminUser adminUser) {

        try {
            AdminUser adminUserToBeUpdated = adminService.updateAdminUser(id, adminUser);
            return new ResponseEntity<>(adminUserToBeUpdated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {

        if (adminService.getAdminUserById(id).isPresent()) {
            adminService.deleteAdminUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
