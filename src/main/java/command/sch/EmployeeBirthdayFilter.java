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

    @Override
    public String findValueByOption(String str) {

        switch (this.option) {
            case "-y":
                return str.substring(0,4);
            case "-m":
                return str.substring(4,6);
            case "-d":
                return str.substring(6,8);
            default:
                return str;
        }
    }

    @Override
    public List<Employee> process(List<Employee> searchedEmployees) {
        if(this.option.equals(" ") || this.option.equals("")){
            return searchedEmployees;
        }
        return searchByValue(searchedEmployees);
    }

    @Override
    public List<Employee> searchByValue(List<Employee> searchedEmployees) {

        List<Employee> resultList = new ArrayList<>();

        for(Employee itrEmployee: searchedEmployees){
            String itrBirthdayYear = findValueByOption(itrEmployee.getBirthday());

            if (this.value.equals(itrBirthdayYear)) {
                resultList.add(itrEmployee);
            }
        }

        return resultList;
    }
}