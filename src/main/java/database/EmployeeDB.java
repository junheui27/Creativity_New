package database;

import model.CAREERLEVEL;
import model.CERTI;
import model.Employee;

import java.util.HashMap;

public class EmployeeDB {
    private HashMap<String, Employee> employeeTableById = new HashMap<>();
    private HashMap<String, HashMap<String,Employee>> tableByName = new HashMap<>();
    private HashMap<String, HashMap<String,Employee>> tableByFirstName = new HashMap<>();
    private HashMap<String, HashMap<String,Employee>> tableByLastName = new HashMap<>();
    private HashMap<String, HashMap<String,Employee>> tableByPhoneNumber = new HashMap<>();
    private HashMap<String, HashMap<String,Employee>> tableByMidlePhoneNumber = new HashMap<>();
    private HashMap<String, HashMap<String,Employee>> tableByLastPhoneNumber = new HashMap<>();
    private HashMap<CAREERLEVEL, HashMap<String,Employee>> tableByCL = new HashMap<>();
    private HashMap<String, HashMap<String,Employee>> tableByBirth = new HashMap<>();
    private HashMap<String, HashMap<String,Employee>> tableByBirthYear = new HashMap<>();
    private HashMap<String, HashMap<String,Employee>> tableByBirthMonth = new HashMap<>();
    private HashMap<String, HashMap<String,Employee>> tableByBirthDay = new HashMap<>();
    private HashMap<CERTI, HashMap<String,Employee>> tableByCERTI = new HashMap<>();


    public boolean addEmployee(Employee newEmployee){

        if(IsAlreadyInDatabase(newEmployee))
            return false;

        Employee employeeToAdd = newEmployee.Copy();
        String id = employeeToAdd.getEmployeeNum();
        employeeTableById.put(id,employeeToAdd);

        AddToNameTable(employeeToAdd, id);


        return true;
    }

    private void AddToNameTable(Employee employeeToAdd, String id) {
        String name = employeeToAdd.getName();
        if(!tableByName.containsKey(name)){
            tableByName.put(name,new HashMap<>());
        }
        tableByName.get(name).put(id, employeeToAdd);
    }

    private boolean IsAlreadyInDatabase(Employee newEmployee) {
        return employeeTableById.containsKey(newEmployee.getEmployeeNum());
    }

    public Employee deleteEmployee(Employee deleteEmployee){

        String id = deleteEmployee.getEmployeeNum();
        if(!employeeTableById.containsKey(id))
            return new Employee();

        Employee removed = employeeTableById.remove(id);
        if(tableByName.containsKey(removed.getName())){
            tableByName.get(removed.getName()).remove(id);
        }

        return removed; //삭제된 node
    }

    //ToDo 다양한 필드의 검색을 어떻게 이해하기 쉽게 지원할지
    public Employee findEmployee(String employeeNum){

        if(employeeTableById.containsKey(employeeNum)){
            return employeeTableById.get(employeeNum);
        }
        return new Employee();
    }

    //ToDo 다양한 필드의 수정을 어떻게 이해하기 쉽게 구현할지
    public Employee modifyEmployee(String employeeNum, Employee modifiedEmployee){
        Employee found = findEmployee(employeeNum);
        Employee backup = found.Copy();
        found.Merge(modifiedEmployee);
        return backup; //수정 전 상태를 반환
    }
}
