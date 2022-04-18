package command.sch;

import database.EmployeeDB;
import model.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class EmployeeBirthdayFilter extends SearchFilter{

    private String column;
    private String value;
    private String option;
    EmployeeBirthdayFilter(String column, String value, String option){
        super(column, value, option);
        this.column = column;
        this.value = value;
        this.option = option;
    }

    public List<Employee> process(List<Employee> searchedEmployees) {
        switch (this.option) {
            case "-y":
                return searchByBirthdayYear(searchedEmployees);
            case "-m":
                return searchByBirthdayMonth(searchedEmployees);
            case "-d":
                return searchByBirthdayDay(searchedEmployees);
            default:
                return searchedEmployees;
        }
    }

    public List<Employee> searchByBirthdayYear(List<Employee> searchedEmployees) {

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = searchedEmployees.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrBirthday = itrEmployee.getBirthday();
            String itrBirthdayYear = itrBirthday.substring(0, 4);

            if (this.value.equals(itrBirthdayYear)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

    public List<Employee> searchByBirthdayMonth(List<Employee> searchedEmployees) {

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = searchedEmployees.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrBirthday = itrEmployee.getBirthday();
            String itrBirthdayMonth = itrBirthday.substring(4, 6);

            if (this.value.equals(itrBirthdayMonth)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

    public List<Employee> searchByBirthdayDay(List<Employee> searchedEmployees) {

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = searchedEmployees.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrBirthday = itrEmployee.getBirthday();
            String itrBirthdayDay = itrBirthday.substring(6, 8);

            if (this.value.equals(itrBirthdayDay)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

}