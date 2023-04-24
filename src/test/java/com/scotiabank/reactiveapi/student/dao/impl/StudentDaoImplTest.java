package com.scotiabank.reactiveapi.student.dao.impl;
import com.scotiabank.reactiveapi.student.model.entity.Student;
import com.scotiabank.reactiveapi.student.repository.StudentRepository;
import com.scotiabank.reactiveapi.student.util.exceptions.StudentNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentDaoImplTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentDaoImpl dao;

    @Test
    void getByIdReturnsMonoStudent() {
        Long id = 1L;
        Student student = new Student();
        student.setId(id);
        student.setFirstname("John");
        student.setLastname("Doe");

        when(repository.findByIdAndStatus(id, Boolean.TRUE)).thenReturn(Mono.just(student));

        StepVerifier.create(dao.getById(id))
                .expectNext(student)
                .verifyComplete();
    }

    @Test
    void getAllReturnsFluxStudent() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstname("John");
        student1.setLastname("Doe");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstname("Jane");
        student2.setLastname("Doe");

        when(repository.findByStatus(Boolean.TRUE)).thenReturn(Flux.fromIterable(Arrays.asList(student1, student2)));

        StepVerifier.create(dao.getAll())
                .expectNext(student1)
                .expectNext(student2)
                .verifyComplete();
    }

    @Test
    void saveReturnsMonoStudent() {
        Student student = new Student();
        student.setFirstname("John");
        student.setLastname("Doe");

        when(repository.save(student)).thenReturn(Mono.just(student));

        StepVerifier.create(dao.save(student))
                .expectNext(student)
                .verifyComplete();
    }

    @Test
    void getByIdNotFound() {
        Long id = 1L;

        when(repository.findByIdAndStatus(id, Boolean.TRUE)).thenReturn(Mono.empty());

        StepVerifier.create(dao.getById(id))
                .expectComplete()
                .verify();
    }

    @Test
    void getAllNoData() {
        when(repository.findByStatus(Boolean.TRUE)).thenReturn(Flux.empty());

        StepVerifier.create(dao.getAll())
                .expectNextCount(0)
                .verifyComplete();
    }

}
