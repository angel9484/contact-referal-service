package es.bnext.user.service;

import es.bnext.user.dto.UserCreationDTO;
import es.bnext.user.dto.UserDTO;
import es.bnext.user.dto.UserPhonesSearchDTO;
import es.bnext.user.entity.User;
import es.bnext.user.error.ApplicationException;
import es.bnext.user.error.ErrorDTO;
import es.bnext.user.error.Errors;
import io.micronaut.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import javax.inject.Singleton;
import java.util.List;

@RequiredArgsConstructor
@Singleton
public class UserService {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final UserRepository userRepository;

    public UserDTO createUser(UserCreationDTO userCreationDTO) {
        User user = userMapper.toEntity(userCreationDTO);
        User createdUser = userRepository.save(user);
        return userMapper.toDTO(createdUser);
    }

    public List<UserDTO> findByPhones(UserPhonesSearchDTO userPhonesSearchDTO) {
        List<User> usersRetrieved = userRepository.findByPhones(userPhonesSearchDTO.getPhones());
        if (usersRetrieved.size() != userPhonesSearchDTO.getPhones().size()) {
            throw new ApplicationException(HttpStatus.PARTIAL_CONTENT,
                    ErrorDTO.builder().code(Errors.INCOMPLETE_RESULT_ERROR).details(usersRetrieved).description(
                            "There are some users that are not registered.").build());
        }
        return userMapper.toDTOs(usersRetrieved);
    }
}
