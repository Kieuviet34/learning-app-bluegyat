package learningapp.Model;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@IdClass(ProgressId.class)
public class Progress {
    @Setter
    @Id
    @Column(name="user_id")
    private Long userId;

    @Setter
    @Id
    @Column(name="lesson_id")
    private Long lessonId;

    @Setter
    @Column(name="completed_at", nullable=false)
    private LocalDateTime completedAt = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    // getters/setters

    public Long getUserId() {
        return userId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            this.userId = user.getId();
        }
    }
}