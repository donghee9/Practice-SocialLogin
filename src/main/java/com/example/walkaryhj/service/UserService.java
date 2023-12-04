package com.example.walkaryhj.service;

import com.example.walkaryhj.models.Users;
import com.example.walkaryhj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void processOAuthPostLogin(String username, String provider) {
        Users existUsers = repo.findByUsername(username);

        if (existUsers == null) {
            Users newUsers = new Users();
            newUsers.setUsername(username);
            newUsers.setProvider(provider);
            newUsers.setEnabled(true);

            repo.save(newUsers);
        }
    }
}
