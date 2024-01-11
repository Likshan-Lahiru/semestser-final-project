package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Vehicle {

    private String vehicleId;
    private String vehicleStatus;
    private String lastServiceDate;
    private String numberPlateNo;


}
