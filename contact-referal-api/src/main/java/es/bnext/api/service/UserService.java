package es.bnext.api.service;

import es.bnext.api.client.UserClient;
import es.bnext.api.dto.user.UserCreationDTO;
import es.bnext.api.dto.user.UserDTO;
import es.bnext.api.dto.user.UserPhonesSearchDTO;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.List;

@RequiredArgsConstructor
@Singleton
public class UserService {
    private final UserClient userClient;

    public UserDTO createUser(UserCreationDTO userCreationDTO) {
        return userClient.createUser(userCreationDTO);
    }

    public List<UserDTO> findByPhones(UserPhonesSearchDTO userPhonesSearchDTO) {
        return userClient.findByPhones(userPhonesSearchDTO);
    }
}
