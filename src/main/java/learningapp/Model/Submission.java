package learningapp.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="challenge_id", nullable=false)
    private Challenge challenge;

    @Column(columnDefinition="TEXT", nullable=false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=10)
    private Challenge.Language language;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=10)
    private Status status;

    @Column(nullable=false)
    private Integer score = 0;

    @Column(name="submitted_at", nullable=false, updatable=false)
    private LocalDateTime submittedAt = LocalDateTime.now();

    public enum Status { QUEUED, RUNNING, SUCCESS, FAIL, TLE }

    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Challenge.Language getLanguage() {
        return language;
    }

    public void setLanguage(Challenge.Language language) {
        this.language = language;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}