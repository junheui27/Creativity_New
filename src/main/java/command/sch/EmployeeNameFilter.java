package command.sch;

import database.EmployeeDB;
import model.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class EmployeeNameFilter extends SearchFilter{

    private String column;
    private String value;
    private String option;

    EmployeeNameFilter(String column, String value, String option){
        super(column, value, option);
        this.column = column;
        this.value = value;
        this.option = option;
    }

    public List<Employee> process(List<Employee> searchedEmployees) {
        switch (option) {
            case "-f":
                return searchByFirstName(searchedEmployees);
            case "-l":
                return searchByLastName(searchedEmployees);
            default:
                return searchedEmployees;
        }
    }

    public List<Employee> searchByFirstName(List<Employee> searchedEmployees) {

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = searchedEmployees.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrFullName = itrEmployee.getName();
            String itrFirstName = itrFullName.substring(0, itrFullName.indexOf(" "));

            if (this.value.equals(itrFirstName)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

    public List<Employee> searchByLastName(List<Employee> searchedEmployees) {

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = searchedEmployees.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrFullName = itrEmployee.getName();
            String itrLasttName = itrFullName.substring(itrFullName.lastIndexOf(" ") + 1);

            if (this.value.equals(itrLasttName)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }




}