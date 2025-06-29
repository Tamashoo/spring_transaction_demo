package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public Users addUser(String username) {
        if (usersRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists.");
        }
        Users user = new Users();
        user.setUsername(username);
        return usersRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
