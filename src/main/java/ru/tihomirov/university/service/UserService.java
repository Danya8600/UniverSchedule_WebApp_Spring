package ru.tihomirov.university.service;

import ru.tihomirov.university.dto.RegisterRequest;
import ru.tihomirov.university.dto.RegisterResponse;

public interface UserService {
    RegisterResponse registerUser(RegisterRequest request);
}
