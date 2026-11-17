package kz.iitu.dance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.dance.model.DanceParticipant;
import kz.iitu.dance.repository.DanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DanceBattleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanceBattleApplication.class, args);
    }

    // Этот кусок кода читает наш participants.json и сохраняет данные в базу при старте
    @Bean
    CommandLineRunner initDatabase(DanceRepository repository) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<DanceParticipant>> typeReference = new TypeReference<>() {};
            try {
                InputStream inputStream = new ClassPathResource("participants.json").getInputStream();
                List<DanceParticipant> participants = mapper.readValue(inputStream, typeReference);
                repository.saveAll(participants);
                System.out.println(">>> Успешно загружено участников из JSON-файла: " + participants.size());
            } catch (Exception e) {
                System.out.println(">>> Не удалось загрузить JSON: " + e.getMessage());
            }
        };
    }
}