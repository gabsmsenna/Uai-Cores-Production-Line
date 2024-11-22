package com.gabriel.UaiCores_ProductionLine.controller;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.AdminUser.CreateAdminDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.AdminUser.UpdateAdminDTO;
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
    public ResponseEntity<AdminUser> postAdminUser(@RequestBody CreateAdminDTO adminUser) {
        var adminId = adminService.createAdminUser(adminUser);

        return ResponseEntity.created(URI.create("/v1/admin " + adminId)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        var adminUserById = adminService.getAdminUserDTOById(id);
        
        if (adminUserById.isPresent()) {
            return new ResponseEntity<>(adminUserById.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + id + " não encontrado na aplicação");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAdmin(@PathVariable Long id, @RequestBody UpdateAdminDTO adminUser) {

        adminService.updateAdminUser(id, adminUser);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {

        if (adminService.getAdminUserDTOById(id).isPresent()) {
            adminService.deleteAdminUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
