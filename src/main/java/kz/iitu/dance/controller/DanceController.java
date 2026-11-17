package kz.iitu.dance.controller;

import kz.iitu.dance.model.DanceParticipant;
import kz.iitu.dance.service.DanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dance")
public class DanceController {

    private final DanceService service;

    public DanceController(DanceService service) {
        this.service = service;
    }

    // GET /api/dance (с поддержкой поиска ?search=...)
    @GetMapping
    public List<DanceParticipant> getAll(@RequestParam(required = false) String search) {
        return service.getAllParticipants(search);
    }

    // GET /api/dance/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DanceParticipant> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/dance (Создание)
    @PostMapping
    public ResponseEntity<DanceParticipant> create(@RequestBody DanceParticipant participant) {
        if (participant.getNickname() == null || participant.getNickname().isBlank() ||
                participant.getPhone() == null || participant.getPhone().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        DanceParticipant created = service.create(participant);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/dance/{id} (Обновление полей участника)
    @PutMapping("/{id}")
    public ResponseEntity<DanceParticipant> update(@PathVariable Long id, @RequestBody DanceParticipant details) {
        if (details.getNickname() == null || details.getNickname().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return service.update(id, details)
                .map(ResponseEntity::ok) // 200 OK
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    // DELETE /api/dance/{id} (Удаление)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}