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
    String phoneNumber;
    CAREERLEVEL cl;
    String birthday;
    CERTI certi;

    public Employee Copy(){
        return new Employee().builder()
                .employeeNum(this.employeeNum)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .cl(this.cl)
                .birthday(this.birthday)
                .certi(this.certi)
                .build();
    }

    public void Merge(Employee employee){
        setBirthday(employee.getBirthday());
        setCerti(employee.getCerti());
        setCl(employee.getCl());
        setName(employee.getName());
        setPhoneNumber(employee.getPhoneNumber());
    }
    @Override
    public int compareTo(Employee o) {
        return getEmployeeNum().compareTo(o.getEmployeeNum());

    }
}
