package com.kreitek.Kreitekfy.user.domain.persistence;


import com.kreitek.Kreitekfy.user.domain.entity.User;

import java.util.Optional;

public interface UserPersistence {
    User save(User user);
    Optional<User> find(String username);
}
