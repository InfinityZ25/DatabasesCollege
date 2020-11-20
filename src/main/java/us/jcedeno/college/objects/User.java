package us.jcedeno.college.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class User {
    String username;
    int score;

    @Override
    public String toString() {
        return String.format("('%s', '%d')", username, score);
    }

}
