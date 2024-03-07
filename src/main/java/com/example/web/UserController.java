package com.example.web;

import com.example.repository.UserRepository;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import com.example.entity.User;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Get
    @SecurityRequirement(name = "openid", scopes = "openid")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Get("/{id}")
    @SecurityRequirement(name = "openid", scopes = "openid")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Post
    @SecurityRequirement(name = "openid", scopes = "openid")
    public User createUser(@Valid @Body User user) {
        return userRepository.save(user);
    }

    @Put("/{id}")
    @SecurityRequirement(name = "openid", scopes = "openid")
    public User updateUser(Long id, @Valid @Body User user) {
        user.setId(id);
        return userRepository.update(user);
    }

    @Delete("/{id}")
    @SecurityRequirement(name = "openid", scopes = "openid")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

