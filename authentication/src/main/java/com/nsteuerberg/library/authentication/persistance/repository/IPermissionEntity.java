package com.nsteuerberg.library.authentication.persistance.repository;

import com.nsteuerberg.library.authentication.persistance.entity.PermissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPermissionEntity extends CrudRepository<PermissionEntity, Integer> {
}
