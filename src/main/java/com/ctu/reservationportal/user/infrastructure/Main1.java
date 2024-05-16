package main.java.com.ctu.reservationportal.user.infrastructure;

import main.java.com.ctu.reservationportal.user.dbservices.InsertRecords;
import main.java.com.ctu.reservationportal.user.model.UserInfo;

import java.sql.SQLException;

public class Main1 {
    public static void main(String[] args) throws SQLException {
        InsertRecords insertDetails= new InsertRecords();
        CreateUserInfo registerUserService = new CreateUserInfo();
        UserInfo userInfo = registerUserService.registerUser();
        insertDetails.insertUserRecord(userInfo);

    }
}
