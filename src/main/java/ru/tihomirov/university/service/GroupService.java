package ru.tihomirov.university.service;

import ru.tihomirov.university.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    List<Group> getAll();
    Optional<Group> getById(Long id);
    Group save(Group group);
    void deleteById(Long id);
}
