package com.drivingschool.service;

import com.drivingschool.entity.Admin;
import com.drivingschool.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository repo;

    public AdminService(AdminRepository repo) {
        this.repo = repo;
    }

    // CREATE ADMIN
    public void addAdmin(Admin admin) {

        List<Admin> admins = repo.findAll();

        String newId = "A" + String.format("%03d", admins.size() + 1);
        admin.setId(newId);

        admin.setRegisteredDate(LocalDate.now().toString());
        admin.setStatus("ACTIVE");

        repo.save(admin);
    }

    // GET ALL ADMINS
    public List<Admin> getAllAdmins() {
        return repo.findAll();
    }

    // GET BY ID
    public Admin getAdminById(String id) {
        return repo.findById(id).orElse(null);
    }

    // SEARCH BY NAME
    public List<Admin> searchByName(String name) {

        return repo.findAll()
                .stream()
                .filter(a -> a.getName() != null)
                .filter(a -> a.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // CHECK EMAIL EXISTS
    public boolean emailExists(String email) {
        return repo.existsByEmail(email);
    }

    // UPDATE ADMIN
    public void updateAdmin(Admin updatedAdmin) {

        List<Admin> admins = repo.findAll();

        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getId().equals(updatedAdmin.getId())) {
                admins.set(i, updatedAdmin);
                repo.saveAll(admins);
                return;
            }
        }
    }

    // DELETE ADMIN
    public void deleteAdmin(String id) {
        repo.deleteById(id);
    }
}