package ru.tihomirov.university.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tihomirov.university.exception.EntityNotFoundException;
import ru.tihomirov.university.model.Schedule;
import ru.tihomirov.university.repository.*;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final ClassTypeRepository classTypeRepository;

    @Override
    public Schedule save(Schedule schedule) {
        schedule.setGroup(groupRepository.findById(schedule.getGroup().getId())
                .orElseThrow(() -> new EntityNotFoundException("Group not found")));
        schedule.setCourse(courseRepository.findById(schedule.getCourse().getId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found")));
        schedule.setTeacher(teacherRepository.findById(schedule.getTeacher().getId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found")));
        schedule.setClassType(classTypeRepository.findById(schedule.getClassType().getId())
                .orElseThrow(() -> new EntityNotFoundException("ClassType not found")));

        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule update(Long id, Schedule updatedSchedule) {
        if (!scheduleRepository.existsById(id)) {
            throw new EntityNotFoundException("Schedule not found with id: " + id);
        }
        updatedSchedule.setId(id);
        return save(updatedSchedule); // переиспользуем save для подгрузки сущностей
    }

    @Override
    public void delete(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new EntityNotFoundException("Schedule not found with id: " + id);
        }
        scheduleRepository.deleteById(id);
    }

    @Override
    public Schedule getById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + id));
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getByGroupId(Long groupId) {
        return scheduleRepository.findByGroupId(groupId);
    }

    @Override
    public Page<Schedule> getByGroupIdPaged(Long groupId, Pageable pageable) {
        return scheduleRepository.findByGroupId(groupId, pageable);
    }

    @Override
    public List<Schedule> getByTeacherId(Long teacherId) {
        return scheduleRepository.findByTeacherId(teacherId);
    }

    @Override
    public Page<Schedule> getByTeacherIdPaged(Long teacherId, Pageable pageable) {
        return scheduleRepository.findByTeacherId(teacherId, pageable);
    }

    @Override
    public List<Schedule> getByDate(LocalDate date) {
        return scheduleRepository.findByDate(date);
    }
}
