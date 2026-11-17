package kz.iitu.dance.repository;

import kz.iitu.dance.model.DanceParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanceRepository extends JpaRepository<DanceParticipant, Long> {
    // Spring Data JPA автоматически дает нам методы save(), findAll(), findById(), deleteById()
}