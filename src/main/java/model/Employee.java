package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    String employeeNum;
    String name;
    String firstName;
    String lastName;
    String phoneNumber;
    String middlePhoneNumber;
    String lastPhoneNumer;
    CAREERLEVEL cl;
    String birthday;
    String birthdayYear;
    String birthdayMonth;
    String birthdayDay;
    CERTI certi;
}
