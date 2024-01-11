package lk.ijse.dao.factory;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dao.custom.impl.*;


public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){

        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        CUSTOMER,EMPLOYEE,ATTANDANCE,LOGIN,ORDER,ORDERDETAILS,SIGNUP,SUPPLIER,TOOL,VEHICLE,INVOICE
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ATTANDANCE:
                return new AttadanceDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDeatilDAOImpl();
            case SIGNUP:
                return new SignUpDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case TOOL:
                return new ToolDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case INVOICE:
                return new InvoiceDAOImpl();
            default:
                return null;
        }
    }
}

