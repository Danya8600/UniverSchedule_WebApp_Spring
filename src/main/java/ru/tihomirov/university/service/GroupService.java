package ru.tihomirov.university.service;

import ru.tihomirov.university.model.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAll();
    Group getById(Long id);
    Group save(Group group);
    Group update(Long id, Group updatedGroup);
    void deleteById(Long id);
}
