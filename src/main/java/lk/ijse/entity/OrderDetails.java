package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private String toolId;
    private String orderId;
    private String qty;
    private String unitprice;
    private String date;
    private String status;


    public OrderDetails(String orderId, String toolId, int qty) {
        this.orderId = orderId;
        this.toolId = toolId;
        this.qty = String.valueOf(qty);
    }

    public OrderDetails(String lblReOrderIdText, String lblReToolIdText, String lblReQtyText, String txtReStatusText, String date) {
        this.orderId = lblReOrderIdText;
        this.toolId = lblReToolIdText;
        this.qty = lblReQtyText;
        this.status = txtReStatusText;
        this.date = date;
    }
}