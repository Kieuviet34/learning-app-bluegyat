package learningapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseDetailController {
    @GetMapping("/course/course-detail")
    public String courseDetail() {
        return "Client/courses-detail";
    }
}
