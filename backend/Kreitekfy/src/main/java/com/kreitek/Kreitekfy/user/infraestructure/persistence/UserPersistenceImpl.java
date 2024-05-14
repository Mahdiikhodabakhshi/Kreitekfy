package com.kreitek.Kreitekfy.user.infraestructure.persistence;


import com.kreitek.Kreitekfy.user.domain.entity.User;
import com.kreitek.Kreitekfy.user.domain.persistence.UserPersistence;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPersistenceImpl implements UserPersistence {

    private final UserJpaRepository userJpaRepository;

    public UserPersistenceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override public Optional<User> find(String username) {
        return userJpaRepository.findById(username);
    }

}
