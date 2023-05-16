package com.canermastan.laboratoryreportingsystemapi;

import com.canermastan.laboratoryreportingsystemapi.entity.Role;
import com.canermastan.laboratoryreportingsystemapi.entity.User;
import com.canermastan.laboratoryreportingsystemapi.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User userAdmin = new User();
        userAdmin.setFirstName("Admin");
        userAdmin.setLastName("LastName");
        userAdmin.setEmail("admin@test.com");
        userAdmin.setPassword(passwordEncoder.encode("123456"));
        userAdmin.setHospitalIdentityNumber("123456A");
        userAdmin.setRole(Role.ADMIN);

        User userUser = new User();
        userUser.setFirstName("User");
        userUser.setLastName("LastName");
        userUser.setEmail("user@test.com");
        userUser.setPassword(passwordEncoder.encode("123456"));
        userUser.setHospitalIdentityNumber("123456B");
        userUser.setRole(Role.USER);

        userRepository.save(userAdmin);
        userRepository.save(userUser);

    }
}
