package com.scotiabank.reactiveapi.student.repository;

import com.scotiabank.reactiveapi.student.model.entity.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends R2dbcRepository<Student, Long> {

    @Query("INSERT INTO students (id, firstname, lastname, status, age) " +
            "VALUES (NEXT VALUE FOR students_seq, :firstname, :lastname, :status, :age)")
    Mono<Student> save(String firstname, String lastname, Boolean status, Integer age);

    Mono<Student> findByIdAndStatus(Long id, Boolean status);

    Flux<Student> findByStatus(Boolean status);

}
