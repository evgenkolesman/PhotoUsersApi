package ru.koleson.photousersapi.dto;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import ru.koleson.photousersapi.data.UserEntity;
import ru.koleson.photousersapi.model.UserModel;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private static final  long serialVersionUID = -1414159521297129939L;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String userId;

    private String encryptedpassword;

    public static UserDto of(UserModel userModel) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(userModel, UserDto.class);
    }

    public static UserDto of(UserEntity userModel) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(userModel, UserDto.class);
    }
}
