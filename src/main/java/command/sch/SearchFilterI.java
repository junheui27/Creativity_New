package command.sch;

import database.EmployeeDB;
import model.Employee;
import model.UserRequest;

import java.util.List;

public interface SearchFilterI {
    String findValueByOption(String str);
    List<Employee> searchByValue(List<Employee> searchedEmployees);
}
