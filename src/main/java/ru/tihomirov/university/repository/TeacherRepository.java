package ru.tihomirov.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tihomirov.university.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
