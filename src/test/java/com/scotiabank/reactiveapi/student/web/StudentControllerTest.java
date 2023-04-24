package com.scotiabank.reactiveapi.student.web;

import com.scotiabank.reactiveapi.student.business.StudentService;
import com.scotiabank.reactiveapi.student.model.dto.request.CreateStudentDto;
import com.scotiabank.reactiveapi.student.model.dto.response.StudentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    private WebTestClient webClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        StudentController studentController = new StudentController(studentService);
        webClient = WebTestClient.bindToController(studentController).build();
    }

    @Test
    void testGetAllStudents() {
        when(studentService.getAllStudents()).thenReturn(Flux.just(new StudentResponse(), new StudentResponse()));

        webClient.get()
                .uri("/students")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(StudentResponse.class)
                .hasSize(2);
    }

    @Test
    void testGetStudentById() {
        when(studentService.getStudentBydId(anyString())).thenReturn(Mono.just(new StudentResponse()));

        webClient.get()
                .uri("/students/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StudentResponse.class);
    }

    @Test
    void testSaveStudent() {
        CreateStudentDto createStudentDto = new CreateStudentDto("Marco", "Cubas", 11);
        when(studentService.saveStudent(any(CreateStudentDto.class))).thenReturn(Mono.just(new StudentResponse()));

        webClient.post()
                .uri("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(createStudentDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StudentResponse.class);
    }

    @Test
    void testUpdate() {
        CreateStudentDto createStudentDto = new CreateStudentDto("Marco", "Cubas", 11);
        when(studentService.updateStudent(anyString(), any(CreateStudentDto.class))).thenReturn(Mono.just(new StudentResponse()));

        webClient.put()
                .uri("/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(createStudentDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StudentResponse.class);
    }

}

