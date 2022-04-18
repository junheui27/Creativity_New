package model;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Employee implements Comparable<Employee>{
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

    public Employee Copy(){
        Employee copied = new Employee().builder()
                .employeeNum(this.employeeNum)
                .name(this.name)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phoneNumber(this.phoneNumber)
                .middlePhoneNumber(this.middlePhoneNumber)
                .lastPhoneNumer(this.lastPhoneNumer)
                .cl(this.cl)
                .birthday(this.birthday)
                .birthdayYear(this.birthdayYear)
                .birthdayMonth(this.birthdayMonth)
                .birthdayDay(this.birthdayDay)
                .certi(this.certi)
                .build();

        return copied;
    }

    public void Merge(Employee employee){
        setBirthday(employee.getBirthday());
        setBirthdayDay(employee.getBirthdayDay());
        setBirthdayMonth(employee.getBirthdayMonth());
        setBirthdayYear(employee.getBirthdayYear());
        setCerti(employee.getCerti());
        setCl(employee.getCl());
        setName(employee.getName());
        setFirstName(employee.getFirstName());
        setLastName(employee.getLastName());
        setPhoneNumber(employee.getPhoneNumber());
        setLastPhoneNumer(employee.getLastPhoneNumer());
        setMiddlePhoneNumber(employee.getMiddlePhoneNumber());
    }
    @Override
    public int compareTo(Employee o) {
        //return o.getEmployeeNum().compareTo(getEmployeeNum());
        return getEmployeeNum().compareTo(o.getEmployeeNum());

    }
}
