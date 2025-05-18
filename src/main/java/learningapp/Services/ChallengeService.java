package learningapp.Services;

import learningapp.Model.Challenge;
import learningapp.Repository.IChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {
    private final IChallengeRepository challengeRepo;
    @Autowired

    public ChallengeService(IChallengeRepository challengeRepo) {
        this.challengeRepo = challengeRepo;
    }
    public List<Challenge> listByLesson(Long lessonId){
        return challengeRepo.findByLessonId(lessonId);
    }
    public Challenge findById(Long challengeId){
        return challengeRepo.findById(challengeId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Không tìm thấy Challenge với id " + challengeId));
    }
}
