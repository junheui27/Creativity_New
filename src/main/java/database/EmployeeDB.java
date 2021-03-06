package database;

import model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDB {
    final private HashMap<String, Employee> idHash = new HashMap<>();
    final private HashMap<String, HashMap<String,Employee>> nameHash = new HashMap<>();
    final private HashMap<String, HashMap<String,Employee>> phoneNumberHash = new HashMap<>();
    final private HashMap<String, HashMap<String,Employee>> careerLevelHash = new HashMap<>();
    final private HashMap<String, HashMap<String,Employee>> birthHash = new HashMap<>();
    final private HashMap<String, HashMap<String,Employee>> certiHash = new HashMap<>();

    final String columnEmployeeNum = "employeeNum";
    final String columnName = "name";
    final String columnPhoneNum = "phoneNum";
    final String columnCareerLevel = "cl";
    final String columnBirthDay = "birthday";
    final String columnCerti = "certi";

    public boolean addEmployee(Employee newEmployee) {

        if (IsAlreadyInDatabase(newEmployee))
            return false;

        Employee employeeToAdd = newEmployee.Copy();
        String id = employeeToAdd.getEmployeeNum();
        idHash.put(id, employeeToAdd);

        AddToColumnHash(columnName, employeeToAdd.getName(), employeeToAdd);
        AddToColumnHash(columnPhoneNum, employeeToAdd.getPhoneNumber(), employeeToAdd);
        AddToColumnHash(columnBirthDay, employeeToAdd.getBirthday(), employeeToAdd);
        AddToColumnHash(columnCerti, employeeToAdd.getCerti().toString(), employeeToAdd);
        AddToColumnHash(columnCareerLevel, employeeToAdd.getCl().toString(), employeeToAdd);

        return true;
    }

    public Employee deleteEmployee(Employee deleteEmployee){

        String id = deleteEmployee.getEmployeeNum();
        if(!idHash.containsKey(id))
            return new Employee();

        Employee removed = idHash.remove(id);
        deleteFromHash(columnName,removed.getName(),id);
        deleteFromHash(columnPhoneNum,removed.getPhoneNumber(),id);
        deleteFromHash(columnBirthDay,removed.getBirthday(),id);
        deleteFromHash(columnCerti,removed.getCerti().toString(),id);
        deleteFromHash(columnCareerLevel,removed.getCl().toString(),id);

        return removed; //????????? node
    }

    //ToDo ????????? ????????? ????????? ????????? ???????????? ?????? ????????????
    public Employee findEmployeeById(String employeeNum){

        if(idHash.containsKey(employeeNum)){
            return idHash.get(employeeNum).Copy();
        }
        return new Employee();
    }

    public List<Employee> findEmployeeByColumn(String columnName, String value){
        if(columnName.equals(columnEmployeeNum)){
            Employee found = findEmployeeById(value);
            return Arrays.asList(found);
        }

        return getEmployFromIndexedColumn(columnName, value);
    }

    //ToDo ????????? ????????? ????????? ????????? ???????????? ?????? ????????????
    public Employee modifyEmployee(String employeeNum, Employee modifiedEmployee){

        if(!idHash.containsKey(employeeNum)){
            return new Employee();
        }

        Employee found = idHash.get(employeeNum);
        Employee backup = found.Copy();
        this.deleteEmployee(backup);

        found.Merge(modifiedEmployee);
        this.addEmployee(found);

        return backup; //?????? ??? ????????? ??????
    }

    private void AddToColumnHash(String columnName,String value,Employee employeeToAdd){
        if(value == null || value.isEmpty())
            return;

        HashMap<String, HashMap<String,Employee>> hash = getHash(columnName);
        if(!hash.containsKey(value)){
            hash.put(value,new HashMap<>());
        }

        hash.get(value).put(employeeToAdd.getEmployeeNum(),employeeToAdd);
    }

    private boolean IsAlreadyInDatabase(Employee newEmployee) {
        return idHash.containsKey(newEmployee.getEmployeeNum());
    }

    private void deleteFromHash(String columnName, String value, String id) {
        HashMap<String, HashMap<String,Employee>> hash = getHash(columnName);
        if(!hash.containsKey(value))
            return;

        hash.get(value).remove(id);

        if(hash.get(value).size() == 0)
            hash.remove(value);
    }

    private List<Employee> getEmployFromIndexedColumn(String columnName, String value) {
        HashMap<String, HashMap<String,Employee>> hash = getHash(columnName);

        List<Employee> employees = hash.entrySet()
                .stream()
                .filter(h -> h.getKey().contains(value))
                .map(r->r.getValue())
                .flatMap(x->x.values().stream())
                .collect(Collectors.toList());

        List<Employee> foundList = new ArrayList<>();
        employees.forEach(e -> foundList.add(e.Copy()));

        return foundList;
    }

    private HashMap<String, HashMap<String,Employee>> getHash(String column){
        if(column.equals(columnName))
            return nameHash;
        if(column.equals(columnCareerLevel))
            return careerLevelHash;
        if(column.equals(columnPhoneNum))
            return phoneNumberHash;
        if(column.equals(columnBirthDay))
            return birthHash;
        if(column.equals(columnCerti))
            return certiHash;

        return new HashMap<>();
    }
}
