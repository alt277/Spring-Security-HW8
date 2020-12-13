package com.example.SpringSecurityHW8.repositories;

import com.example.SpringSecurityHW8.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
