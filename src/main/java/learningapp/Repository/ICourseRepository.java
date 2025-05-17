package learningapp.Repository;

import learningapp.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Long> {
    
}
