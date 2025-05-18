package learningapp.Services;

import learningapp.Model.Lesson;
import learningapp.Repository.ILessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    private final ILessonRepository lessonRepository;

    public LessonService(ILessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }
    public List<Lesson> findByCourseId(Long courseId){
        return lessonRepository.findByCourseIdOrderBySortOrder(courseId);
    }
    public Lesson findById(Long lessonId){
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy Lesson với id " + lessonId));
    }
}
