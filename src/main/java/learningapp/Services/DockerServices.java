package learningapp.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Service
public class DockerServices {
    @Value("${app.docker.cpp-image:submission_cpp_img}")
    private String cppImage;
    @Value("${app.docker.py-image:submission_py_img}")
    private String pyImage;
    public String runSubmission(String language, String code, String input) throws IOException, InterruptedException{
        String id = UUID.randomUUID().toString();
        Path workDir = Paths.get("tmp", id);
        Path outputDir = workDir.resolve("output");
        Files.createDirectories(outputDir);

        Path codeFile = (language.equals("cpp") ? workDir.resolve("main.cpp")
                                                : workDir.resolve("main.py"));

        Files.writeString(codeFile, code, StandardOpenOption.CREATE);
        Files.writeString(workDir.resolve("input.txt"), input, StandardOpenOption.CREATE);


        String img = (language.equals("cpp") ? cppImage : pyImage);
        String cmd = String.join(" ",
                "docker run --rm",
                "--network none",
                "--memory", language.equals("cpp") ? "256m" : "128m",
                "--cpus",  language.equals("cpp") ? "0.5"  : "0.2",
                "-v", workDir.toAbsolutePath()+":/home/runner/code:ro",
                "-v", outputDir.toAbsolutePath()+":/home/runner/output:rw",
                "--tmpfs /tmp:exec",
                "--cap-drop ALL",
                img
        );
        Process build = new ProcessBuilder("sh", "-c", cmd.replace('\\','/'))
                .directory(new File("."))
                .start();
        int codeExit = build.waitFor();
        Path outFile = outputDir.resolve("output.txt");
        if(Files.exists(outFile)){
            return Files.readString(outFile);
        }
        return "No output (exit code" + codeExit + ")";
    }
}
