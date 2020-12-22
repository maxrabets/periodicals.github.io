package com.maxrabets.periodicals.repository;

import com.maxrabets.periodicals.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}