package ru.tihomirov.university.service;

import ru.tihomirov.university.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAll();
    Optional<Teacher> getById(Long id);
    Teacher save(Teacher teacher);
    void deleteById(Long id);
}
