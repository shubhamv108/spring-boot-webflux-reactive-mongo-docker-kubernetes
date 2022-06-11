package app.common.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/healthz")
public class HealthController {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    private static final ResponseEntity
        ALIVE_RESPONSE = ResponseEntity.ok().
                    header("Content-Type", "application/json").
                    body("{\"alive\": true}"),
        NOT_ALIVE_RESPONSE = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).
                    header("Content-Type", "application/json").
                    body("{\"alive\": false}");
    private final String HEALTH_CHECK_QUERY = "{}";

    @Autowired
    public HealthController(final ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @GetMapping
    public ResponseEntity<?> health() {
        try {
            this.reactiveMongoTemplate.executeCommand(HEALTH_CHECK_QUERY).share().block();
            return ALIVE_RESPONSE;
        } catch (final NoSuchElementException noSuchElementException) {
            return ALIVE_RESPONSE;
        } catch (final Exception exception) {
            log.error("Mongo database is down", exception);
            return NOT_ALIVE_RESPONSE;
        }
    }
}
