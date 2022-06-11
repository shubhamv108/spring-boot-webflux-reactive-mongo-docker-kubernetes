package app.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/StudentDetails")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/getData.htm")
    public Mono<Student> get(@RequestParam("studentName") String name) {
        log.info("Fetching student by name: {}", name);
        return this.studentRepository.findByName(name);
    }

    @PutMapping
    public Mono<Student> put(@RequestBody Student student) {
        log.info("Writing student by name: {}", student.getName());
        return this.studentRepository.save(student);
    }
}
