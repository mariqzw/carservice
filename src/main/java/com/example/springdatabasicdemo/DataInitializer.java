package com.example.springdatabasicdemo;

import com.example.springdatabasicdemo.enums.RoleEnum;
import com.example.springdatabasicdemo.models.Role;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.repositories.RoleRepository;
import com.example.springdatabasicdemo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (roleRepository.count() == 0) {
            var adminRole = new Role(RoleEnum.Admin);
            var normalUserRole = new Role(RoleEnum.User);
            roleRepository.save(adminRole);
            roleRepository.save(normalUserRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initBasicUser();
        }
    }

    private void initAdmin(){
        var adminRole = roleRepository.
                findRoleByName(RoleEnum.Admin).orElseThrow();

        var adminUser = new User("admin", passwordEncoder.encode(defaultPassword), "Admin", "Adminovich", "admin");
        adminUser.setRole(adminRole);

        userRepository.save(adminUser);
    }

    private void initBasicUser(){
        var userRole = roleRepository.
                findRoleByName(RoleEnum.User).orElseThrow();

        var basicUser = new User("user", passwordEncoder.encode(defaultPassword), "User", "Userovich", "user");
        basicUser.setRole(userRole);

        userRepository.save(basicUser);
    }

}
