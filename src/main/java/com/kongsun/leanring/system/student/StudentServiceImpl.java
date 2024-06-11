package com.kongsun.leanring.system.student;

import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", id));
    }

    @Override
    public Student update(Long id, Student student) {
        Student byId = getById(id);
        byId.setFirstName(student.getFirstName());
        byId.setLastName(student.getLastName());
        byId.setGender(student.getGender());
        byId.setPhone(student.getPhone());
        return studentRepository.save(byId);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAll() {
        //TODO search student by their name
        return studentRepository.findAll();
    }

}
