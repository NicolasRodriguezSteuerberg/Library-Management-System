package com.nsteuerberg.library.authentication.persistance.repository;

import com.nsteuerberg.library.authentication.persistance.entity.RoleEntity;
import com.nsteuerberg.library.authentication.util.constants.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findRoleEntityByRole(Roles role);
}
