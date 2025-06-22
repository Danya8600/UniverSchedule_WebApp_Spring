package ru.tihomirov.university.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.tihomirov.university.model.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Для студентов (по группе)
    List<Schedule> findByGroupId(Long groupId);
    Page<Schedule> findByGroupId(Long groupId, Pageable pageable);

    // Для преподавателя
    List<Schedule> findByTeacherId(Long teacherId);
    Page<Schedule> findByTeacherId(Long teacherId, Pageable pageable);

    // По дате
    List<Schedule> findByDate(LocalDate date);

    // Все по страницам
    Page<Schedule> findAll(Pageable pageable);
}
