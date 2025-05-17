package learningapp.Model;


import java.io.Serializable;
import java.util.Objects;

public class ProgressId implements Serializable {
    private Long userId;
    private Long lessonId;

    public ProgressId() {}
    public ProgressId(Long u, Long l) { this.userId=u; this.lessonId=l; }

    @Override public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof ProgressId)) return false;
        ProgressId p=(ProgressId)o;
        return Objects.equals(userId,p.userId)&&Objects.equals(lessonId,p.lessonId);
    }

    @Override public int hashCode(){
        return Objects.hash(userId,lessonId);
    }

}