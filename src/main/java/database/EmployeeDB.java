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

    public boolean addEmployee(Employee newEmployee){

        if(IsAlreadyInDatabase(newEmployee))
            return false;

        Employee employeeToAdd = newEmployee.Copy();
        String id = employeeToAdd.getEmployeeNum();
        idHash.put(id,employeeToAdd);

        AddToNameTable(employeeToAdd);
        AddToPhoneHash(employeeToAdd);
        AddToCLHash(employeeToAdd);
        AddToBirthHash(employeeToAdd);
        AddToCertiHash(employeeToAdd);

        return true;
    }

    private void AddToNameTable(Employee employeeToAdd) {
        String name = employeeToAdd.getName();
        if(name == null || name.isEmpty())
            return;

        if(!nameHash.containsKey(name)){
            nameHash.put(name,new HashMap<>());
        }
        nameHash.get(name).put(employeeToAdd.getEmployeeNum(), employeeToAdd);
    }
    private void AddToPhoneHash(Employee employeeToAdd) {
        String phone = employeeToAdd.getPhoneNumber();
        if(phone == null || phone.isEmpty())
            return;

        if(!phoneNumberHash.containsKey(phone)){
            phoneNumberHash.put(phone,new HashMap<>());
        }
        phoneNumberHash.get(phone).put(employeeToAdd.getEmployeeNum(), employeeToAdd);
    }
    private void AddToCLHash(Employee employeeToAdd) {
        String cl = employeeToAdd.getCl().toString();
        if(cl == null || cl.isEmpty())
            return;

        if(!careerLevelHash.containsKey(cl)){
            careerLevelHash.put(cl,new HashMap<>());
        }
        careerLevelHash.get(cl).put(employeeToAdd.getEmployeeNum(), employeeToAdd);
    }

    private void AddToBirthHash(Employee employeeToAdd) {
        String birth = employeeToAdd.getBirthday();
        if(birth == null || birth.isEmpty())
            return;

        if(!birthHash.containsKey(birth)){
            birthHash.put(birth,new HashMap<>());
        }
        birthHash.get(birth).put(employeeToAdd.getEmployeeNum(), employeeToAdd);
    }

    private void AddToCertiHash(Employee employeeToAdd) {
        String certi = employeeToAdd.getCerti().toString();
        if(certi == null || certi.isEmpty())
            return;

        if(!certiHash.containsKey(certi)){
            certiHash.put(certi,new HashMap<>());
        }
        certiHash.get(certi).put(employeeToAdd.getEmployeeNum(), employeeToAdd);
    }

    private boolean IsAlreadyInDatabase(Employee newEmployee) {
        return idHash.containsKey(newEmployee.getEmployeeNum());
    }

    public Employee deleteEmployee(Employee deleteEmployee){

        String id = deleteEmployee.getEmployeeNum();
        if(!idHash.containsKey(id))
            return new Employee();

        Employee removed = idHash.remove(id);
        if(nameHash.containsKey(removed.getName())){
            nameHash.get(removed.getName()).remove(id);
        }

        return removed; //삭제된 node
    }

    //ToDo 다양한 필드의 검색을 어떻게 이해하기 쉽게 지원할지
    public Employee findEmployeeById(String employeeNum){

        if(idHash.containsKey(employeeNum)){
            return idHash.get(employeeNum);
        }
        return new Employee();
    }

    public List<Employee> findEmployeeByColumn(String columnName, String value){
        if(columnName.equals("employeeNum")){
            Employee found = findEmployeeById(value);
            return Arrays.asList(found);
        }

        List<Employee> foundList = getEmployFromIndexedColumn(columnName, value);

        return foundList;
    }

    private List<Employee> getEmployFromIndexedColumn(String columnName, String value) {
        HashMap<String, HashMap<String,Employee>> hash = getHash(columnName);

        List<HashMap<String,Employee>> employees = hash.entrySet()
                .stream()
                .filter(h -> h.getKey().contains(value))
                .map(r->r.getValue()).collect(Collectors.toList());

        List<Employee> foundList = new ArrayList<>();
        employees.forEach(h -> {
            foundList.addAll(h.values());
        });
        return foundList;
    }

    private HashMap<String, HashMap<String,Employee>> getHash(String columnName){
        if(columnName.equals("name"))
            return nameHash;
        if(columnName.equals("cl"))
            return careerLevelHash;
        if(columnName.equals("phoneNum"))
            return phoneNumberHash;
        if(columnName.equals("birthday"))
            return birthHash;
        if(columnName.equals("certi"))
            return certiHash;

        return new HashMap<>();
    }

    //ToDo 다양한 필드의 수정을 어떻게 이해하기 쉽게 구현할지
    public Employee modifyEmployee(String employeeNum, Employee modifiedEmployee){
        Employee found = findEmployeeById(employeeNum);
        Employee backup = found.Copy();
        found.Merge(modifiedEmployee);
        return backup; //수정 전 상태를 반환
    }
}
