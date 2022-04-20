package command.sch;

import database.EmployeeDB;
import model.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class EmployeePhoneNumberFilter extends SearchFilter {

    EmployeePhoneNumberFilter(String column, String value, String option) {
        super(column, value, option);
    }

    @Override
    public List<Employee> process(List<Employee> searchedEmployees) {
        return searchByValue(searchedEmployees);
    }

    @Override
    public String findValueByOption(String str) {

        switch (option) {
            case "-m":
                return str.split("-")[1];
            case "-l":
                return str.split("-")[2];
            default:
                return str;
        }
    }

    @Override
    public List<Employee> searchByValue(List<Employee> searchedEmployees) {

        List<Employee> resultList = new ArrayList<>();
        for(Employee itrEmployee: searchedEmployees){

            String itrPhoneNumber = findValueByOption(itrEmployee.getPhoneNumber());

            if (value.equals(itrPhoneNumber)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

}