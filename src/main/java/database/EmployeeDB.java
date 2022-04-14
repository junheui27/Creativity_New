package database;

import model.Employee;

import java.util.HashMap;

public class EmployeeDB {
    private HashMap<String, Employee> employeeTable = new HashMap<>();

    public boolean addEmployee(Employee newEmployee){
        return true;
    }
    public Employee deleteEmployee(Employee deleteEmployee){
        return null;
    }
    public Employee findEmployee(String employeeNum){
        return null;
    }
    public Employee modifyEmployee(String employeeNum, Employee modifiedEmployee){
        return null;
    }
}
