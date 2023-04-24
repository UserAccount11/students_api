package com.scotiabank.reactiveapi.student.web;

import com.scotiabank.reactiveapi.student.business.StudentService;
import com.scotiabank.reactiveapi.student.model.dto.request.CreateStudentDto;
import com.scotiabank.reactiveapi.student.model.dto.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.scotiabank.reactiveapi.student.util.constants.Constants.ONLY_NUMBERS_REGEX;

@Valid
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Flux<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Mono<StudentResponse> getStudentById(
            @NotNull
            @NotEmpty
            @Pattern(regexp = ONLY_NUMBERS_REGEX)
            @PathVariable
            String id) {
        return studentService.getStudentBydId(id);
    }

    @PostMapping
    public Mono<StudentResponse> saveStudent(@Valid @RequestBody CreateStudentDto request) {
        return studentService.saveStudent(request);
    }

    @PutMapping("/{id}")
    public Mono<StudentResponse> update(
            @NotNull
            @NotEmpty
            @Pattern(regexp = ONLY_NUMBERS_REGEX)
            @PathVariable
            String id,
            @Valid
            @RequestBody
            CreateStudentDto request) {
        return studentService.updateStudent(id, request);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(
            @NotNull
            @NotEmpty
            @Pattern(regexp = ONLY_NUMBERS_REGEX)
            @PathVariable
            String id) {
        return studentService.deleteStudentById(id)
                .flatMap(e -> Mono.empty());
    }

}
