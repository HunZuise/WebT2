package kz.iitu.dance.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import kz.iitu.dance.model.DanceParticipant;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DanceService {

    // Файл сохраняется прямо в корень проекта (рядом с build.gradle и папкой src)
    private final File jsonFile = new File("participants.json");
    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private List<DanceParticipant> participants = new ArrayList<>();
    private long nextId = 1;

    public DanceService() {
        loadFromFile();
    }

    // Чтение данных из participants.json при старте
    private void loadFromFile() {
        try {
            if (jsonFile.exists() && jsonFile.length() > 0) {
                participants = mapper.readValue(jsonFile, new TypeReference<List<DanceParticipant>>() {});
                nextId = participants.stream()
                        .mapToLong(p -> p.getId() != null ? p.getId() : 0)
                        .max()
                        .orElse(0) + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Запись актуального массива обратно в participants.json
    private void saveToFile() {
        try {
            mapper.writeValue(jsonFile, participants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Получить всех + опциональный поиск (?search=...)
    public List<DanceParticipant> getAllParticipants(String search) {
        if (search == null || search.isBlank()) {
            return participants;
        }
        String lowerQuery = search.toLowerCase();
        return participants.stream()
                .filter(p -> (p.getNickname() != null && p.getNickname().toLowerCase().contains(lowerQuery)) ||
                        (p.getDanceStyle() != null && p.getDanceStyle().toLowerCase().contains(lowerQuery)))
                .collect(Collectors.toList());
    }

    public Optional<DanceParticipant> getById(Long id) {
        return participants.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    // Создание (POST)
    public DanceParticipant create(DanceParticipant participant) {
        participant.setId(nextId++);
        participants.add(participant);
        saveToFile(); // Обновляет JSON-файл
        return participant;
    }

    // Обновление (PUT) — меняет ник, телефон и стиль
    public Optional<DanceParticipant> update(Long id, DanceParticipant newDetails) {
        return getById(id).map(existing -> {
            existing.setNickname(newDetails.getNickname());
            existing.setPhone(newDetails.getPhone());
            existing.setDanceStyle(newDetails.getDanceStyle());
            saveToFile(); // Обновляет JSON-файл
            return existing;
        });
    }

    // Удаление (DELETE)
    public boolean delete(Long id) {
        boolean removed = participants.removeIf(p -> p.getId().equals(id));
        if (removed) {
            saveToFile(); // Обновляет JSON-файл
        }
        return removed;
    }
}