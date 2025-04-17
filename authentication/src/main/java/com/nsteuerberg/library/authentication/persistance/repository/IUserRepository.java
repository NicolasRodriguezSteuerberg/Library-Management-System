package com.nsteuerberg.library.authentication.persistance.repository;

import com.nsteuerberg.library.authentication.persistance.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long> {
}
