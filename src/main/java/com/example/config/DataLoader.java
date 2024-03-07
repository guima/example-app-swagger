package com.example.config;

import com.example.entity.User;
import com.example.repository.UserRepository;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;

import java.util.stream.Stream;

@Singleton
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        Stream.of("Darth Vader", "Luke Skywalker", "Leia Organa",
                "Obi-Wan Kenobi", "Han Solo", "Yoda").forEach(name -> {
            User user = new User(name,
                    name.toLowerCase()
                            .replace(" ", ".")
                            .replace("-", ".") + "@domain.com");
            userRepository.save(user);
        });
    }
}
