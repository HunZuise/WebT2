package kz.iitu.dance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DanceParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String phone;
    private String danceStyle;

    public DanceParticipant() {}

    public DanceParticipant(String nickname, String phone, String danceStyle) {
        this.nickname = nickname;
        this.phone = phone;
        this.danceStyle = danceStyle;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDanceStyle() { return danceStyle; }
    public void setDanceStyle(String danceStyle) { this.danceStyle = danceStyle; }
}