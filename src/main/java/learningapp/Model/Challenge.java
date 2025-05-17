package learningapp.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "challenges")
public class Challenge {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="lesson_id", nullable=false)
    private Lesson lesson;

    @Column(nullable=false, length=255)
    private String title;

    @Column(name="stub_code", columnDefinition="TEXT")
    private String stubCode;

    @Column(name="test_suite", columnDefinition="TEXT")
    private String testSuite;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=10)
    private Language language;

    public enum Language { CPP, PY }

    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStubCode() {
        return stubCode;
    }

    public void setStubCode(String stubCode) {
        this.stubCode = stubCode;
    }

    public String getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(String testSuite) {
        this.testSuite = testSuite;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}