package org.beeapp.backend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.beeapp.backend.api.external.AuthenticationRequest;
import org.beeapp.backend.api.external.AuthenticationResponse;
import org.beeapp.backend.api.external.RegisterRequest;
import org.beeapp.backend.api.external.UsersDTO;
import org.beeapp.backend.api.internal.UserProfile;
import org.beeapp.backend.api.internal.Users;
import org.beeapp.backend.mapper.UsersMapper;
import org.beeapp.backend.repository.UserProfileRepository;
import org.beeapp.backend.repository.UsersRepository;
import org.beeapp.backend.service.AuthenticationService;
import org.beeapp.backend.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsersRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsersMapper userMapper;
    private final UserProfileRepository userProfileRepository;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists!");
        }

        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setSurname(request.getSurname());
        user.setName(request.getName());
        user.setUserRole("user");

        userRepository.save(user);

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);

        userProfileRepository.save(userProfile);

        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    @Override
    public UsersDTO verify() {
        var user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userMapper.map(user);
    }
}