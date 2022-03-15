package ru.koleson.photousersapi.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.koleson.photousersapi.data.UserEntity;


public interface UsersRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findUserByEmail(String email);

    UserEntity findByUserId(String userId);

}
