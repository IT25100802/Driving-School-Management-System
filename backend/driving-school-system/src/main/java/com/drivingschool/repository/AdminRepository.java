package com.drivingschool.repository;

import com.drivingschool.entity.Admin;
import com.drivingschool.util.FileHandler;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {

    // GET ALL
    public List<Admin> findAll() {
        return FileHandler.readAllAdmins();
    }

    // SAVE ALL (used internally)
    public void saveAll(List<Admin> admins) {
        FileHandler.writeAllAdmins(admins);
    }

    // FIND BY ID
    public Optional<Admin> findById(String id) {
        return FileHandler.readAllAdmins()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    // EXISTS BY EMAIL
    public boolean existsByEmail(String email) {
        return FileHandler.readAllAdmins()
                .stream()
                .anyMatch(a -> a.getEmail().equals(email));
    }

    // SAVE ONE ADMIN
    public void save(Admin admin) {
        List<Admin> admins = FileHandler.readAllAdmins();
        admins.add(admin);
        FileHandler.writeAllAdmins(admins);
    }

    // DELETE
    public void deleteById(String id) {
        List<Admin> admins = FileHandler.readAllAdmins();
        admins.removeIf(a -> a.getId().equals(id));
        FileHandler.writeAllAdmins(admins);
    }
}