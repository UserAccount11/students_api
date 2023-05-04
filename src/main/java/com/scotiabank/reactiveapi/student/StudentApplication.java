package com.scotiabank.reactiveapi.student;

import com.scotiabank.reactiveapi.student.model.entity.Student;
import com.scotiabank.reactiveapi.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class StudentApplication implements CommandLineRunner {

	private final StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentRepository.saveAll(Arrays.asList(
				new Student(null, "Carlos", "Vásquez", true, 30),
				new Student(null, "Juan", "Gonzales", true, 30),
				new Student(null, "Teresa", "Gomez", true, 30),
				new Student(null, "Franco", "Alayo", true, 30),
				new Student(null, "Daniel", "Mendoza", true, 30),
				new Student(null, "Julian", "Alvarez", true, 30)
		)).subscribe(student -> System.out.println("Saved student: " + student.getFirstname()));
	}

}
