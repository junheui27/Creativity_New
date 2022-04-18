package command.sch;

import database.EmployeeDB;
import model.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class EmployeePhoneNumberFilter extends SearchFilter {

    private String column;
    private String value;
    private String option;

    EmployeePhoneNumberFilter(String column, String value, String option) {
        super(column, value, option);
        this.column = column;
        this.value = value;
        this.option = option;
    }

    public List<Employee> process(List<Employee> searchedEmployees) {

        switch (this.option) {
            case "-m":
                return searchByPhoneNumberMid(searchedEmployees);
            case "-l":
                return searchByPhoneNumberLast(searchedEmployees);
            default:
                return searchedEmployees;
        }
    }

        public List<Employee> searchByPhoneNumberMid (List<Employee> searchedEmployees){

            List<Employee> resultList = new ArrayList<>();
            for (Iterator<Employee> itr = searchedEmployees.iterator(); itr.hasNext(); ) {
                Employee itrEmployee = itr.next();
                String itrPhoneNumber = itrEmployee.getPhoneNumber();
                String itrPhoneNumberMid = itrPhoneNumber.split("-")[1];

                if (this.value.equals(itrPhoneNumberMid)) {
                    resultList.add(itrEmployee);
                }
            }
            return resultList;
        }


        public List<Employee> searchByPhoneNumberLast (List<Employee> searchedEmployees){

            List<Employee> resultList = new ArrayList<>();
            for (Iterator<Employee> itr = searchedEmployees.iterator(); itr.hasNext(); ) {
                Employee itrEmployee = itr.next();
                String itrPhoneNumber = itrEmployee.getPhoneNumber();
                String itrPhoneNumberLast = itrPhoneNumber.split("-")[2];

                if (this.value.equals(itrPhoneNumberLast)) {
                    resultList.add(itrEmployee);
                }
            }
            return resultList;
        }


    }