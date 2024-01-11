package lk.ijse.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Tool {
    private String toolId;
    private String toolName;
    private int qtyOnhand;
    private  double rentPerDay;


    public Tool(String searchIdText) {
        this.toolId = searchIdText;
    }



}