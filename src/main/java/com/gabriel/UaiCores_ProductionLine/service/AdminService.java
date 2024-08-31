package com.gabriel.UaiCores_ProductionLine.service;

import com.gabriel.UaiCores_ProductionLine.model.AdminUser;
import com.gabriel.UaiCores_ProductionLine.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminUserRepository adminUserRepository;

    public AdminUser createAdminUser(AdminUser adminUser) {
        var encoder = new BCryptPasswordEncoder();
        var hashedPassword = encoder.encode(adminUser.getPassword());

        var adminUserObj = new AdminUser(adminUser.getName(), adminUser.getLogin(), hashedPassword);
        return adminUserRepository.save(adminUserObj);
    }

    public List<AdminUser> getAllAdminUsers() {
        return adminUserRepository.findAll();
    }

    public Optional<AdminUser> getAdminUserById(Long id) {
        return adminUserRepository.findById(id);
    }

    public AdminUser updateAdminUser(Long id, AdminUser adminUser) {
        Optional<AdminUser> adminUserToBeUpdated = adminUserRepository.findById(id);

        if (adminUserToBeUpdated.isPresent()) {
            AdminUser adminUserUpdated = adminUserToBeUpdated.get();

            adminUserUpdated.setName(adminUser.getName());
            adminUserUpdated.setLogin(adminUser.getLogin());
            adminUserUpdated.setPassword(adminUser.getPassword());

            return adminUserRepository.save(adminUserUpdated);
        } else {
            throw new RuntimeException("Não foi possível atualizar esse funcionário | ID (" + id + ") não encontrado");
        }
    }

    public void deleteAdminUser(Long id) {
        adminUserRepository.deleteById(id);
    }
}
