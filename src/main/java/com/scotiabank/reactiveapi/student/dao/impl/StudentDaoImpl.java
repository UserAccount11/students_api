package com.scotiabank.reactiveapi.student.dao.impl;

import com.scotiabank.reactiveapi.student.dao.StudentDao;
import com.scotiabank.reactiveapi.student.model.entity.Student;
import com.scotiabank.reactiveapi.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentDaoImpl implements StudentDao {

    private final StudentRepository repository;

    @Override
    public Mono<Student> getById(Long id) {
        return repository.findByIdAndStatus(id, Boolean.TRUE);
    }

    @Override
    public Flux<Student> getAll() {
        return repository.findByStatus(Boolean.TRUE);
    }

    @Override
    public Mono<Student> save(Student student) {
        return repository.save(student);
    }

}
