package ru.tihomirov.university.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tihomirov.university.dto.RegisterRequest;
import ru.tihomirov.university.dto.RegisterResponse;
import ru.tihomirov.university.exception.EntityNotFoundException;
import ru.tihomirov.university.model.*;
import ru.tihomirov.university.repository.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse registerUser(RegisterRequest request) {
        Role role = roleRepository.findByName(request.getRole().toUpperCase())
                .orElseThrow(() -> new EntityNotFoundException("Role not found: " + request.getRole()));

        User user = new User()
                .setUsername(request.getUsername())
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setRole(role);

        if ("STUDENT".equalsIgnoreCase(request.getRole())) {
            Student student = studentRepository.findById(request.getRelatedId())
                    .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + request.getRelatedId()));
            user.setStudent(student);
        } else if ("TEACHER".equalsIgnoreCase(request.getRole())) {
            Teacher teacher = teacherRepository.findById(request.getRelatedId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + request.getRelatedId()));
            user.setTeacher(teacher);
        }

        userRepository.save(user);

        return new RegisterResponse("User registered successfully", user.getUsername());
    }
}
