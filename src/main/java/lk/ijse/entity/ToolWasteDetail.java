package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ToolWasteDetail {
    private String toolId;
    private String toolName;
    private int qtyOnhand;
    private String wasteCount;
    private  String lastupdatedDate;
}
