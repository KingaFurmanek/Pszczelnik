package org.beeapp.backend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.beeapp.backend.api.external.UsersDTO;
import org.beeapp.backend.api.internal.UserProfile;
import org.beeapp.backend.api.internal.Users;
import org.beeapp.backend.mapper.UsersMapper;
import org.beeapp.backend.repository.UserProfileRepository;
import org.beeapp.backend.repository.UsersRepository;
import org.beeapp.backend.service.FileStorageService;
import org.beeapp.backend.service.UsersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserProfileRepository userProfileRepository;
    private final UsersMapper usersMapper;
    private final FileStorageService fileStorageService;

    @Override
    public UsersDTO getUserByEmail(String email) {
        Users user = usersRepository.findUserByEmail(email).orElse(null);
        if (user != null) {
            return usersMapper.map(user);
        }
        return null;
    }

    @Override
    public void updateUserPhoto(String email, MultipartFile file) {
        Users user = usersRepository.findUserByEmail(email).orElse(null);

        if (user != null) {
            String filePath = fileStorageService.saveFile(file);

            UserProfile userProfile = user.getUserDetails();
            userProfile.setImage(filePath);
            userProfileRepository.save(userProfile);
            usersRepository.save(user);
        }
    }
}