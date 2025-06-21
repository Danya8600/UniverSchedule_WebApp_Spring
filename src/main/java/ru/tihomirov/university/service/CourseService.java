package ru.tihomirov.university.service;

import ru.tihomirov.university.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> getAll();
    Optional<Course> getById(Long id);
    Course save(Course course);
    void deleteById(Long id);
}
