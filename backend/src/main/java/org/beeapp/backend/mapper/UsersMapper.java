package org.beeapp.backend.mapper;

import org.beeapp.backend.api.external.UsersDTO;
import org.beeapp.backend.api.internal.Users;
import org.beeapp.backend.service.implementations.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public UsersDTO map(Users user) {
        return new UsersDTO()
                .setId(user.getId())
                .setName(user.getName())
                .setSurname(user.getSurname())
                .setEmail(user.getEmail())
                .setRole(user.getUserRole())
                .setImage(FileUtils.readFileFromLocation(user.getUserDetails().getImage()));
    }
}