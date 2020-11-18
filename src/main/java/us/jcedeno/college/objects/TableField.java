package us.jcedeno.college.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(staticName = "of")
@Data
public class TableField {
    String fieldName;
    Object type;
}
