package learningapp.Repository;

import learningapp.Model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourseIdOrderBySortOrder(Long courseId);

}
