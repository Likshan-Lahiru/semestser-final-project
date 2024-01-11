package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;


public class BOFactory {
    private static BOFactory boFactory ;
    private BOFactory(){}
    public static BOFactory getDaoFactory(){

        return (boFactory==null)?boFactory=new BOFactory():boFactory;
    }
    public enum BOTypes{
        CUSTOMER,EMPLOYEE,ATTANDANCE,LOGIN,ORDER,ORDERPLACE,ORDERDETAILS,SIGNUP,SUPPLIER,TOOL,VEHICLE,INVOICE
    }
    public SuperBO getDAO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ATTANDANCE:
                return new AttandanceBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDERPLACE:
                return new PlaceOrderBOImpl();
            case ORDERDETAILS:
                return new OrderDeatilBOImpl();
            case SIGNUP:
                return new SignUpBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case TOOL:
                return new ToolBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case INVOICE:
                return null;
            default:
                return null;
        }
    }
}
