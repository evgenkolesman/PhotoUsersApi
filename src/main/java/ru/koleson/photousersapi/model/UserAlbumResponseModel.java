package ru.koleson.photousersapi.model;

import com.appsdeveloperblog.photoapp.api.albums.ui.model.AlbumResponseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import ru.koleson.photousersapi.dto.UserDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAlbumResponseModel {

    private String firstname;
    private String lastname;
    private String email;
    private String userId;
    private List<AlbumResponseModel> albumResponseModels;

    public static UserAlbumResponseModel of(UserDto userModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(userModel, UserAlbumResponseModel.class);
    }

}
