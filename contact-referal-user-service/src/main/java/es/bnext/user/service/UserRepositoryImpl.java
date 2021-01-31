package es.bnext.user.service;


import es.bnext.user.entity.User;
import io.micronaut.transaction.annotation.ReadOnly;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public User save(User user) {
        User userToSave = user.toBuilder().build();
        entityManager.persist(userToSave);
        return userToSave;
    }

    @Override
    @ReadOnly
    public List<User> findByPhones(List<String> phones) {
        return entityManager.createQuery("SELECT u FROM User u WHERE phone IN (:phones)", User.class).setParameter("phones",
                phones).getResultList();
    }
}
