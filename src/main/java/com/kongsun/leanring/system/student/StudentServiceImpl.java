package com.kongsun.leanring.system.student;

import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Student> getAll(Map<String , String> params) {
        Specification<Student> spec = Specification.where(null);

        if(params.containsKey("name")){
            spec = spec.and(StudentSpec.containLastname(params.get("name")))
                    .or(StudentSpec.containFirstname(params.get("name")));
        }
        if(params.containsKey("phone")){
           spec = spec.and(StudentSpec.containPhone(params.get("phone")));
        }
        if(params.containsKey("gender")){
            spec = spec.and(StudentSpec.containGender(params.get("gender")));
        }

        return studentRepository.findAll(spec);
    }

    @Override
    public List<Student> getByIds(List<Long> studentIds) {
        return studentRepository.findAllById(studentIds);
    }

}
