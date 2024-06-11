package com.kongsun.leanring.system.teacher;

import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", id));
    }

    @Override
    public Teacher update(Long id, Teacher teacher) {
        Teacher byId = getById(id);
        byId.setFirstName(teacher.getFirstName());
        byId.setLastName(teacher.getLastName());
        byId.setGender(teacher.getGender());
        byId.setSalary(teacher.getSalary());
        byId.setHireDate(teacher.getHireDate());
        return teacherRepository.save(byId);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

}
