package com.scotiabank.reactiveapi.student.builder;

import com.scotiabank.reactiveapi.student.configuration.Properties;
import com.scotiabank.reactiveapi.student.model.dto.request.CreateStudentDto;
import com.scotiabank.reactiveapi.student.model.dto.response.StudentResponse;
import com.scotiabank.reactiveapi.student.model.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentBuilder {

    private final Properties properties;

    public StudentResponse buildStudentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .firstname(student.getFirstname())
                .lastname(student.getLastname())
                .status(properties.getStatus().get(student.getStatus()))
                .age(student.getAge())
                .build();
    }

    public Student buildStudentEntity(CreateStudentDto dto) {
        return Student.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .status(Boolean.TRUE)
                .age(dto.getAge())
                .build();
    }

    public Student buildStudentEntity(CreateStudentDto dto, Student entity) {
        return Student.builder()
                .id(entity.getId())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .status(entity.getStatus())
                .age(dto.getAge())
                .build();
    }

}
