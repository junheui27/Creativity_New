package command.sch;

import database.EmployeeDB;
import model.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class EmployeeNameFilter extends SearchFilter {

    private String column;
    private String value;
    private String option;

    EmployeeNameFilter(String column, String value, String option) {
        super(column, value, option);
        this.column = column;
        this.value = value;
        this.option = option;
    }

    @Override
    public List<Employee> process(List<Employee> searchedEmployees) {
        if(this.option.equals(" ") || this.option.equals("")){
            return searchedEmployees;
        }
        return searchByValue(searchedEmployees);
    }

    @Override
    public String findValueByOption(String str) {

        switch (this.option) {
            case "-f":
                return str.substring(0, str.indexOf(" "));
            case "-l":
                return str.substring(str.lastIndexOf(" ") + 1);
            default:
                return str;
        }
    }

    @Override
    public List<Employee> searchByValue(List<Employee> searchedEmployees) {

        List<Employee> resultList = new ArrayList<>();
        for (Employee itrEmployee : searchedEmployees) {
            String itrtName = findValueByOption(itrEmployee.getName());

            if (this.value.equals(itrtName)) {
                resultList.add(itrEmployee);
            }
        }

        return resultList;
    }



}