package es.bnext.user.service;

import es.bnext.user.dto.UserCreationDTO;
import es.bnext.user.dto.UserDTO;
import es.bnext.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(UserCreationDTO userCreationDTO);

    UserDTO toDTO(User user);

    List<UserDTO> toDTOs(List<User> users);
}
