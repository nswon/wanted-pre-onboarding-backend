package com.wanted.wanted.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    default User get() {
        List<User> users = findAll();

        if(users.isEmpty()) {
            User user = User.createUser();
            save(user);
            return user;
        }

        return users.get(0);
    }
}
