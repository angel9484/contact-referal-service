package es.bnext.user.service;

import com.google.common.collect.Lists;
import es.bnext.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Test
    void save(@Mock User user, @Mock User.UserBuilder userBuilder) {
        when(user.toBuilder()).thenReturn(userBuilder);
        when(userBuilder.build()).thenReturn(user);
        doNothing().when(entityManager).persist(user);
        userRepository.save(user);
        verify(entityManager, times(1)).persist(user);
    }

    @Test
    void testFindByPhones_when_a_list_is_provided_should_return_proper_user(@Mock TypedQuery queryMock) {
        List<String> phones = Lists.newArrayList("1");
        List<User> userFromPhones = Lists.newArrayList(User.builder().phone("1").id(1).name("john").lastName("doe").build());

        when(entityManager.createQuery(anyString(), eq(User.class))).thenReturn(queryMock);
        when(queryMock.setParameter("phones", phones)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(userFromPhones);

        List<User> usersFound = userRepository.findByPhones(phones);
        assertEquals(1, usersFound.size());
    }
}
