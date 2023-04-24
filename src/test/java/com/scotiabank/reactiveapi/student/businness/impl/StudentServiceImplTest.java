package com.scotiabank.reactiveapi.student.businness.impl;

import com.scotiabank.reactiveapi.student.builder.StudentBuilder;
import com.scotiabank.reactiveapi.student.business.impl.StudentServiceImpl;
import com.scotiabank.reactiveapi.student.dao.StudentDao;
import com.scotiabank.reactiveapi.student.model.dto.request.CreateStudentDto;
import com.scotiabank.reactiveapi.student.model.dto.response.StudentResponse;
import com.scotiabank.reactiveapi.student.model.entity.Student;
import com.scotiabank.reactiveapi.student.util.exceptions.StudentNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentBuilder studentBuilder;

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void getStudentBydId_ReturnsStudentResponse_WhenStudentExists() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstname("John");
        student.setLastname("Doe");

        StudentResponse expectedResponse = new StudentResponse();
        expectedResponse.setId(1L);
        expectedResponse.setFirstname("John");
        expectedResponse.setLastname("Doe");

        when(studentDao.getById(anyLong())).thenReturn(Mono.just(student));
        when(studentBuilder.buildStudentResponse(any(Student.class))).thenReturn(expectedResponse);

        Mono<StudentResponse> actualResponseMono = studentService.getStudentBydId("1");

        StepVerifier.create(actualResponseMono)
                .expectNext(expectedResponse)
                .verifyComplete();

        verify(studentDao).getById(anyLong());
        verify(studentBuilder).buildStudentResponse(any(Student.class));
    }

    @Test
    void getStudentBydId_ThrowsStudentNotFoundException_WhenStudentDoesNotExist() {
        when(studentDao.getById(anyLong())).thenReturn(Mono.empty());

        Mono<StudentResponse> actualResponseMono = studentService.getStudentBydId("1");

        StepVerifier.create(actualResponseMono)
                .expectError(StudentNotFoundException.class)
                .verify();

        verify(studentDao).getById(anyLong());
        verifyNoInteractions(studentBuilder);
    }

    @Test
    void getAllStudents_ReturnsListOfStudentResponses_WhenThereAreStudents() {
        // Arrange
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstname("John");
        student1.setLastname("Doe");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstname("Jane");
        student2.setLastname("Doe");

        StudentResponse expectedResponse1 = new StudentResponse();
        expectedResponse1.setId(1L);
        expectedResponse1.setFirstname("John");
        expectedResponse1.setLastname("Doe");

        StudentResponse expectedResponse2 = new StudentResponse();
        expectedResponse2.setId(2L);
        expectedResponse2.setFirstname("Jane");
        expectedResponse2.setLastname("Doe");

        List<Student> students = Arrays.asList(student1, student2);
        List<StudentResponse> expectedResponses = Arrays.asList(expectedResponse1, expectedResponse2);

        when(studentDao.getAll()).thenReturn(Flux.fromIterable(students));
        when(studentBuilder.buildStudentResponse(any(Student.class))).thenReturn(expectedResponse1, expectedResponse2);

        // Act
        Flux<StudentResponse> actualResponsesFlux = studentService.getAllStudents();

        // Assert
        StepVerifier.create(actualResponsesFlux)
                .expectNext(expectedResponse1)
                .expectNext(expectedResponse2)
                .verifyComplete();

        verify(studentDao).getAll();
        verify(studentBuilder, times(2)).buildStudentResponse(any(Student.class));
    }

    @Test
    void getStudentByIdNotFound() {
        // Arrange
        String id = "1";
        when(studentDao.getById(anyLong())).thenReturn(Mono.empty());

        // Act and Assert
        StepVerifier.create(studentService.getStudentBydId(id))
                .expectError(StudentNotFoundException.class)
                .verify();
    }

    @Test
    void updateStudentNotFound() {
        String id = "1";
        CreateStudentDto createStudentDto = new CreateStudentDto();

        when(studentDao.getById(anyLong())).thenReturn(Mono.empty());

        StepVerifier.create(studentService.updateStudent(id, createStudentDto))
                .expectError(StudentNotFoundException.class)
                .verify();
    }

}
