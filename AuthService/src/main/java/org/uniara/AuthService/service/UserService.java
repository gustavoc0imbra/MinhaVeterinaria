package org.uniara.AuthService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uniara.AuthService.model.User;
import org.uniara.AuthService.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
