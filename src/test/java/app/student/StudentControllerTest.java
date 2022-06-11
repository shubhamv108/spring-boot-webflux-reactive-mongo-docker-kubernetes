package app.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class StudentControllerTest {

    @InjectMocks
    StudentController mockStudentController;

    @Mock
    StudentRepository mockStudentRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void get() {
        String name = "student1";
        when(mockStudentRepository.findByName(name)).
                thenReturn(Mono.justOrEmpty(Student.builder().name(name).build()));
        Mono<Student> result = mockStudentController.get(name);
        verify(mockStudentRepository, times(1)).findByName(name);
        Assertions.assertEquals(name, result.block().getName());
    }

    @Test
    void put() {
        Student student = Student.builder().
                name("student1").
                age(10).
                address("a").
                subjects(Collections.emptyList()).
                build();
        when(mockStudentRepository.save(student)).
                thenReturn(Mono.justOrEmpty(student));
        Mono<Student> result = mockStudentController.put(student);
        verify(mockStudentRepository, times(1)).save(student);
        Assertions.assertEquals(student.getName(), result.block().getName());
        Assertions.assertEquals(student.getAge(), result.block().getAge());
        Assertions.assertEquals(student.getAddress(), result.block().getAddress());
    }
}