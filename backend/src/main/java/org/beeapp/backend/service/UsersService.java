package org.beeapp.backend.service;

import org.beeapp.backend.api.external.UsersDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsersService {
    UsersDTO getUserByEmail(String email);
    void updateUserPhoto(String email, MultipartFile file);
    void updateUserBlockedStatus(Integer userId, boolean isBlocked);
    List<UsersDTO> getAllUsers();
    Boolean getUserStatus(String email);
}