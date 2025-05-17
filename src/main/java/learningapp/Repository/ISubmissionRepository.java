package learningapp.Repository;

import learningapp.Model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByUserId(Long userId);
    List<Submission> findByChallengeId(Long challengeId);   
}
