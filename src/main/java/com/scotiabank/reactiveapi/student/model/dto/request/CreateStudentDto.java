package com.scotiabank.reactiveapi.student.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.scotiabank.reactiveapi.student.util.constants.Constants.ONLY_LETTERS_REGEX;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {

    @NotNull(message = "The value of field 'firstname' cannot be null.")
    @NotBlank(message = "The value of field 'firstname' cannot be empty.")
    @Size(max = 50, message = "The value of field 'firstname' must have a maximum of 50 characters.")
    @Pattern(regexp = ONLY_LETTERS_REGEX, message = "The value of field 'firstname' should only contain letters.")
    private String firstname;

    @NotNull(message = "The value of field 'firstname' cannot be null.")
    @NotBlank(message = "The value of field 'lastname' cannot be empty.")
    @Size(max = 50, message = "The value of field 'lastname' must have a maximum of 50 characters.")
    @Pattern(regexp = ONLY_LETTERS_REGEX, message = "The value of field 'lastname' should only contain letters.")
    private String lastname;

    @NotNull(message = "The value of field 'age' cannot be null.")
    @Min(value = 0, message = "The value of the age field must be greater than zero.")
    private Integer age;

}
