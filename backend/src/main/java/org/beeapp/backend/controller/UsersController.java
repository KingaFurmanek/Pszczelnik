package org.beeapp.backend.controller;

import lombok.RequiredArgsConstructor;
import org.beeapp.backend.api.external.*;
import org.beeapp.backend.api.internal.Users;
import org.beeapp.backend.service.JwtService;
import org.beeapp.backend.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/info")
    public ResponseEntity<UsersDTO> getUserInfo(Authentication authentication) {
        Users currentUser = (Users) authentication.getPrincipal();
        String userEmail = currentUser.getEmail();
        UsersDTO userDTO = usersService.getUserByEmail(userEmail);
        if (userDTO != null) {
            return ResponseEntity.ok(usersService.getUserByEmail(userDTO.getEmail()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/photo")
    public ResponseEntity<String> uploadPhoto(Authentication authentication, @RequestParam("file") MultipartFile file) {
        Users currentUser = (Users) authentication.getPrincipal();
        String userEmail = currentUser.getEmail();
        UsersDTO userDTO = usersService.getUserByEmail(userEmail);
        if (userDTO != null) {
            usersService.updateUserPhoto(userDTO.getEmail(), file);
            return ResponseEntity.ok("Photo uploaded successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}