package ru.tihomirov.university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tihomirov.university.model.Schedule;
import ru.tihomirov.university.service.ScheduleService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> create(@RequestBody Schedule schedule) {
        return ResponseEntity.ok(scheduleService.save(schedule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> update(@PathVariable Long id, @RequestBody Schedule schedule) {
        return ResponseEntity.ok(scheduleService.update(id, schedule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> getAll() {
        return ResponseEntity.ok(scheduleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getById(id));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Schedule>> getByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(scheduleService.getByGroupId(groupId));
    }

    @GetMapping("/group/{groupId}/page")
    public ResponseEntity<Page<Schedule>> getByGroupPaged(@PathVariable Long groupId, Pageable pageable) {
        return ResponseEntity.ok(scheduleService.getByGroupIdPaged(groupId, pageable));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Schedule>> getByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(scheduleService.getByTeacherId(teacherId));
    }

    @GetMapping("/teacher/{teacherId}/page")
    public ResponseEntity<Page<Schedule>> getByTeacherPaged(@PathVariable Long teacherId, Pageable pageable) {
        return ResponseEntity.ok(scheduleService.getByTeacherIdPaged(teacherId, pageable));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Schedule>> getByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(scheduleService.getByDate(date));
    }
}
