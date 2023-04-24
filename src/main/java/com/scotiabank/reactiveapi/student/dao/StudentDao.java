package com.scotiabank.reactiveapi.student.dao;

import com.scotiabank.reactiveapi.student.model.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentDao {

    Mono<Student> getById(Long id);

    Flux<Student> getAll();

    Mono<Student> save(Student student);

}
