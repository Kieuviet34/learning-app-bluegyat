package learningapp.Controllers;

import learningapp.Services.DockerServices;
import learningapp.dto.RunRequest;
import learningapp.dto.RunResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RunController {
    private final DockerServices docker;

    public RunController(DockerServices docker) {
        this.docker = docker;
    }
    @PostMapping ("/run")
    public ResponseEntity<RunResponse> runCode(@RequestBody RunRequest req){
        if (!("cpp".equals(req.getLanguage()) || "python".equals(req.getLanguage()))) {
            return ResponseEntity.badRequest()
                    .body(new RunResponse("Invalid language"));
        }try {
            String output = docker.runSubmission(
                    req.getLanguage(),
                    req.getCode(),
                    req.getInput() == null ? "" : req.getInput()
            );
            return ResponseEntity.ok(new RunResponse(output));
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg != null && msg.contains("time limit")) {
                return ResponseEntity.ok(new RunResponse("time limit exceeded"));
            }
            return ResponseEntity.ok(new RunResponse("Error: " + msg));
        }
    }
}
