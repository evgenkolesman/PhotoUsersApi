package ru.koleson.photousersapi.service.implimentation;

import com.appsdeveloperblog.photoapp.api.albums.ui.model.AlbumResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.koleson.photousersapi.data.UserEntity;
import ru.koleson.photousersapi.data.repository.UsersRepository;
import ru.koleson.photousersapi.dto.UserDto;
import ru.koleson.photousersapi.model.UserModel;
import ru.koleson.photousersapi.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RestTemplate restTemplate;
    private final Environment env;

    @Override
    public UserDto createUser(UserDto user) {
        user.setUserId(UUID.randomUUID().toString());
        UserEntity userEntity = UserEntity.of(user);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(userEntity);
        UserDto returnValue = UserDto.of(userEntity);
        return returnValue;
    }

    @Override
    public UserModel findUserByEmail(String userEmail) {
        return UserModel.of(usersRepository.findUserByEmail(userEmail));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findUserByEmail(username);
        if (userEntity == null) {
            throw new NullPointerException();
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
                true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDto getUserByEmail(String userName) {
        UserEntity userEntity = usersRepository.findUserByEmail(userName);
        if (userEntity == null) {
            throw new NullPointerException();
        }
        return UserDto.of(userEntity);
    }

    @Override
    public UserDto getUserById(String userId) {
        UserEntity userEntity = usersRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new NullPointerException();
        }
        String albumsUrl = String.format(Objects.requireNonNull(env.getProperty("album.api.endpoint")), userId);
/**
 * exchange consist of
 * URL
 * HTTPMethod
 * RequestEntity
 * ParameterizedTypeReference with any model or list of models you`re import (JSON Array)
 */
        ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(
                albumsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlbumResponseModel>>() {
                });
        List<AlbumResponseModel> albumsList = albumsListResponse.getBody();
        UserDto userDto = UserDto.of(userEntity);
        userDto.setAlbumResponseModelList(albumsList);
        return userDto;
    }
}
