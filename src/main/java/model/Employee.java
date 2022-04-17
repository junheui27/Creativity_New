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

    public Employee(
            String employeeNum,
            String name,
            String firstName,
            String lastName,
            String phoneNumber,
            String middlePhoneNumber,
            String lastPhoneNumer,
            CAREERLEVEL cl,
            String birthday,
            String birthdayYear,
            String birthdayMonth,
            String birthdayDay,
            CERTI certi) {
        this.employeeNum = employeeNum;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.middlePhoneNumber = middlePhoneNumber;
        this.lastPhoneNumer = lastPhoneNumer;
        this.cl = cl;
        this.birthday = birthday;
        this.birthdayYear = birthdayYear;
        this.birthdayMonth = birthdayMonth;
        this.birthdayDay = birthdayDay;
        this.certi = certi;
    }
}


