package com.gabriel.UaiCores_ProductionLine.service;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.AdminUser.CreateAdminDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.AdminUser.GetAdminUserDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.AdminUser.UpdateAdminDTO;
import com.gabriel.UaiCores_ProductionLine.model.AdminUser;
import com.gabriel.UaiCores_ProductionLine.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminUserRepository adminUserRepository;

    public Long createAdminUser(CreateAdminDTO adminUser) {
        var entity = new AdminUser(adminUser.name(), adminUser.login(), adminUser.password());
        var userSaved = adminUserRepository.save(entity);

        return userSaved.getId();
    }

    public List<AdminUser> getAllAdminUsers() {
        return adminUserRepository.findAll();
    }

    public Optional<GetAdminUserDTO> getAdminUserDTOById(Long id) {
       var adminUserEntity = adminUserRepository.findById(id);
       return adminUserEntity.map(adminUser -> new GetAdminUserDTO(
               adminUser.getName(),
               adminUser.getLogin()
       ));
    }

    public void updateAdminUser(Long id, UpdateAdminDTO updateAdminDTO) {
        var adminExists = adminUserRepository.findById(id);

        try {
            if (adminExists.isPresent()) {
                var admin = adminExists.get();

                if (updateAdminDTO.password() != null) {
                    admin.setPassword(updateAdminDTO.password());
                }
                adminUserRepository.save(admin);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + "Não foi possível atualizar o usuário");
        }
    }

    public void deleteAdminUser(Long id) {
        adminUserRepository.deleteById(id);
    }
}
