package learningapp.Repository;

import learningapp.Model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByLessonId(Long lessonId);

}
