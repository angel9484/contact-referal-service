package es.bnext.user.service;

import com.google.common.collect.Lists;
import es.bnext.user.dto.UserCreationDTO;
import es.bnext.user.dto.UserDTO;
import es.bnext.user.dto.UserPhonesSearchDTO;
import es.bnext.user.entity.User;
import es.bnext.user.error.ApplicationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void createUser_when_user_is_requested_to_create_should_create_user_and_return_the_new_user(@Mock UserCreationDTO userCreationDTO) {
        when(userCreationDTO.getName()).thenReturn("john");
        when(userCreationDTO.getLastName()).thenReturn("doe");
        when(userCreationDTO.getPhone()).thenReturn("1234");

        User user = userMapper.toEntity(userCreationDTO);
        User userAfterCreation = userMapper.toEntity(userCreationDTO);
        userAfterCreation.setId(1);
        when(userRepository.save(user)).thenReturn(userAfterCreation);


        UserDTO userMocked = userMapper.toDTO(userAfterCreation);
        UserDTO userCreated = userService.createUser(userCreationDTO);

        assertEquals(userMocked, userCreated);
        assertEquals("john", userCreated.getName());
        assertEquals("doe", userCreated.getLastName());
        assertEquals("1234", userCreated.getPhone());
        assertEquals(1, userCreated.getId());
    }

    @Test
    void findByPhones_when_a_list_is_retrieved_should_return_same_size_list_returned(@Mock UserPhonesSearchDTO userPhonesSearchDTO) {
        List<String> phoneList = Lists.newArrayList("1234", "5678");
        when(userPhonesSearchDTO.getPhones()).thenReturn(phoneList);
        when(userRepository.findByPhones(phoneList)).thenReturn(Lists.newArrayList(
                User.builder().phone("1234").name("john").lastName("doe").id(1).build(),
                User.builder().phone("5678").name("lorem").lastName("ipsum").id(2).build()
        ));
        List<UserDTO> usersReturned = userService.findByPhones(userPhonesSearchDTO);
        assertEquals(2, usersReturned.size());
    }

    @Test
    void findByPhones_when_a_list_is_retrieved_from_users_not_in_db_should_throw_error(@Mock UserPhonesSearchDTO userPhonesSearchDTO) {
        List<String> phoneList = Lists.newArrayList("1234", "5678");
        when(userPhonesSearchDTO.getPhones()).thenReturn(phoneList);
        when(userRepository.findByPhones(phoneList)).thenReturn(Lists.newArrayList(
                User.builder().phone("1234").name("john").lastName("doe").id(1).build()
        ));
        assertThrows(ApplicationException.class, () -> userService.findByPhones(userPhonesSearchDTO));
    }
}
