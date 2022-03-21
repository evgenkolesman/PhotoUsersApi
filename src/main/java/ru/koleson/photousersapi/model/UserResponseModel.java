package ru.koleson.photousersapi.model;

import com.appsdeveloperblog.photoapp.api.albums.ui.model.AlbumResponseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import ru.koleson.photousersapi.dto.UserDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel {

    private String firstname;
    private String lastname;
    private String email;
    private String userId;
    private List<AlbumResponseModel> albumResponseModelList;

//    public static UserResponseModel of(UserModel userModel) {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        return modelMapper.map(userModel, UserResponseModel.class);
//    }

    public static UserResponseModel of(UserDto userModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(userModel, UserResponseModel.class);
    }

}
