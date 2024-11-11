package org.beeapp.backend.service;

import org.beeapp.backend.api.external.AuthenticationRequest;
import org.beeapp.backend.api.external.AuthenticationResponse;
import org.beeapp.backend.api.external.RegisterRequest;
import org.beeapp.backend.api.external.UsersDTO;


public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(AuthenticationRequest request);

    UsersDTO verify();
}