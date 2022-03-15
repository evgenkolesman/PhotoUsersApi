package ru.koleson.photousersapi.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.koleson.photousersapi.dto.UserDto;
import ru.koleson.photousersapi.model.UserModel;

@Service
public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);

    UserModel findUserByEmail(String userEmail);

    UserDto getUserByEmail(String userName);

    UserDto getUserById(String userId);
}
