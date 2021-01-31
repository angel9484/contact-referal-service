package es.bnext.user.service;


import es.bnext.user.entity.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    List<User> findByPhones(List<String> phones);
}
