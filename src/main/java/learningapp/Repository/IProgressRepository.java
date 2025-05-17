package learningapp.Repository;

import learningapp.Model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByUserId(Long userId);
    
}
