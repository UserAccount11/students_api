package com.scotiabank.reactiveapi.student.business;

import com.scotiabank.reactiveapi.student.model.dto.request.CreateStudentDto;
import com.scotiabank.reactiveapi.student.model.dto.response.StudentResponse;
import com.scotiabank.reactiveapi.student.model.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    Mono<StudentResponse> getStudentBydId(String id);

    Flux<StudentResponse> getAllStudents();

    Mono<StudentResponse> saveStudent(CreateStudentDto request);

    Mono<StudentResponse> updateStudent(String id, CreateStudentDto request);

    Mono<Student> deleteStudentById(String id);

}
