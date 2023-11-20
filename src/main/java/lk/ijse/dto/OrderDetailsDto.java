package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private String toolId;
    private String orderId;
    private String qty;
    private String unitprice;
    private String date;
}