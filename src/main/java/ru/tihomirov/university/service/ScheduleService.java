package ru.tihomirov.university.service;

import ru.tihomirov.university.model.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    Schedule save(Schedule schedule);
    Schedule update(Long id, Schedule updatedSchedule);
    void delete(Long id);
    Schedule getById(Long id);
    List<Schedule> getAll();
    List<Schedule> getByGroupId(Long groupId);
    List<Schedule> getByTeacherId(Long teacherId);
    List<Schedule> getByDate(LocalDate date);
}
