package com.thoughtworks.dps.restapirefimpl.services;

import com.thoughtworks.dps.restapirefimpl.entities.User;
import com.thoughtworks.dps.restapirefimpl.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    public Optional<User> getUser(int userId) {
        if (userId >= User.USERS.size() || userId < 0) {
            return Optional.empty();
        }
        return Optional.of(User.USERS.get(userId));
    }

    public User getUserByName(String username) {
        return User.USERS.stream().filter(u -> u.getUsername().equals(username))
                .findAny().orElseThrow(() -> new BadRequestException("no such user"));
    }
}
