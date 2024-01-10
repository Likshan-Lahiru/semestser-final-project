package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {
    private String CustomerId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerNic;
    private String CustomerContactNumber;
    private String CustomerEmail;
}
