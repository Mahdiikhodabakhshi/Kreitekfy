package com.kreitek.Kreitekfy.user.infraestructure.persistence;


import com.kreitek.Kreitekfy.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String>{

}
