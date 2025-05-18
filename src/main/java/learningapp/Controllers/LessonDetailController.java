package learningapp.Controllers;

import learningapp.Model.Challenge;
import learningapp.Model.Lesson;
import learningapp.Services.ChallengeService;
import learningapp.Services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LessonDetailController {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private ChallengeService challengeService;

    @GetMapping("/lesson/lesson-detail")
    public String lessonDetail(
            @PathVariable("lang") String lang,
            @PathVariable("lessonId") Long lessonId,
            Model model
    ) {
        Lesson lesson = lessonService.findById(lessonId);
        Challenge challenge = challengeService .listByLesson(lessonId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No challenge for lesson " + lessonId));
        model.addAttribute("lesson", lesson);
        model.addAttribute("challenge", challenge);
        model.addAttribute("language", lang.toLowerCase());
        // stubCode và testSuite lấy từ challenge
        model.addAttribute("stubCode", challenge.getStubCode());
        model.addAttribute("stubInput", challenge.getTestSuite());
        return "Client/lesson-detail";
    }
}
