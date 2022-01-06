package ru.koleson.photousersapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import ru.koleson.photousersapi.data.UserEntity;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @NotNull(message = "cannot be null")
    @Size(min = 2, message = "firstname should be more than 2 characters")
    private String firstname;

    @NotNull(message = "cannot be null")
    @Size(min = 2, message = "Lastname should be more than 2 characters")
    private String lastname;

    @NotNull(message = "cannot be null")
    @Email
    private String email;

    @NotNull(message = "cannot be null")
    @Size(min = 8, max = 100, message = "Password should be more than 8 characters" +
            " and less than 100")
    private String password;

    public static UserModel of(UserEntity userEntity) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(userEntity, UserModel.class);
    }

}
