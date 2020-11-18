package us.jcedeno.college.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(staticName = "of")
@Data
public class Person {
    String firstName;
    String lastName;
    int age;
    long ssn;
    long creditCard;

}
