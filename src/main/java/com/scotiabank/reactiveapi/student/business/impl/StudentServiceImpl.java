package com.scotiabank.reactiveapi.student.business.impl;

import com.scotiabank.reactiveapi.student.builder.StudentBuilder;
import com.scotiabank.reactiveapi.student.business.StudentService;
import com.scotiabank.reactiveapi.student.dao.StudentDao;
import com.scotiabank.reactiveapi.student.model.dto.request.CreateStudentDto;
import com.scotiabank.reactiveapi.student.model.dto.response.StudentResponse;
import com.scotiabank.reactiveapi.student.model.entity.Student;
import com.scotiabank.reactiveapi.student.util.exceptions.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentBuilder studentBuilder;
    private final StudentDao studentDao;

    @Override
    public Mono<StudentResponse> getStudentBydId(String id) {
        return studentDao.getById(Long.parseLong(id))
                .map(studentBuilder::buildStudentResponse)
                .switchIfEmpty(Mono.error(StudentNotFoundException::new))
                .doOnError(e -> log.error("Error in called to getStudentBydId() method: {}", e.getMessage()));
    }

    @Override
    public Flux<StudentResponse> getAllStudents() {
        return studentDao.getAll()
                .map(studentBuilder::buildStudentResponse)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<StudentResponse> saveStudent(CreateStudentDto request) {
        return studentDao.save(studentBuilder.buildStudentEntity(request))
                .map(studentBuilder::buildStudentResponse)
                .doOnError(e -> log.error("Error in called to saveStudent() method: {}", e.getMessage()));
    }

    @Override
    public Mono<StudentResponse> updateStudent(String id, CreateStudentDto request) {
        return studentDao.getById(Long.parseLong(id))
                .flatMap(entity -> studentDao.save(studentBuilder.buildStudentEntity(request, entity)))
                .map(studentBuilder::buildStudentResponse)
                .switchIfEmpty(Mono.error(StudentNotFoundException::new))
                .doOnError(e -> log.error("Error in called to updateStudent() method: {}", e.getMessage()));
    }

    @Override
    public Mono<Student> deleteStudentById(String id) {
        return studentDao.getById(Long.parseLong(id))
                .flatMap(entity -> {
                    entity.setStatus(Boolean.FALSE);
                    return studentDao.save(entity);
                })
                .switchIfEmpty(Mono.error(StudentNotFoundException::new))
                .doOnError(e -> log.error("Error in called to deleteStudentById() method: {}", e.getMessage()));
    }

}
