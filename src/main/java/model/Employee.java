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
    String middlePhoneNumber;
    String lastPhoneNumer;
    CAREERLEVEL cl;
    Integer birthdayYear;
    Integer birthdayMonth;
    Integer birthdayDay;
    CERTI certi;
}
