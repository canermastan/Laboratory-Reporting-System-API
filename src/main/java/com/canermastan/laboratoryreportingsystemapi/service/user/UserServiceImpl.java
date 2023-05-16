package com.canermastan.laboratoryreportingsystemapi.service.user;

import com.canermastan.laboratoryreportingsystemapi.entity.User;
import com.canermastan.laboratoryreportingsystemapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
