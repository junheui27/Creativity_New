package database;

import model.Employee;

import java.util.HashMap;

public class EmployeeDB {
    private HashMap<String, Employee> employeeTable = new HashMap<>();

    public boolean AddEmployee(Employee newEmployee){
        return true;
    }
    public Employee DeleteEmployee(Employee deleteEmployee){
        return null;
    }
    public Employee FindEmployee(String employeeNum){
        return null;
    }
    public Employee ModifyEmployee(String employeeNum,Employee modifiedEmployee){
        return null;
    }
}
