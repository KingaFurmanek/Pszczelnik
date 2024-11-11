package org.beeapp.backend.service;

import org.beeapp.backend.api.external.UsersDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UsersService {
    UsersDTO getUserByEmail(String email);
    void updateUserPhoto(String email, MultipartFile file);
}