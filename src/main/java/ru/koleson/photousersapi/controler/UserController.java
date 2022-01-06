package ru.koleson.photousersapi.controler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.koleson.photousersapi.data.UserEntity;
import ru.koleson.photousersapi.dto.UserDto;
import ru.koleson.photousersapi.model.UserModel;
import ru.koleson.photousersapi.model.UserResponseModel;
import ru.koleson.photousersapi.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/")
public class UserController {

    private final Environment env;
    private final UserService service;

//    @GetMapping("/status/check")
//    public Image status() throws IOException {
//        return ImageIO.read(new URL("https://ya.cc/t/a6Ih_UVx37iLP5"));
//    }

    @GetMapping("/status/check")
    public String status() {
        return "Api is working " + env.getProperty("local.server.port");
    }


    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserModel userModel) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponseModel.of(service.createUser(UserDto.of(userModel))));
    }


}