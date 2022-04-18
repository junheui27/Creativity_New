package command.sch;

import database.EmployeeDB;
import model.Employee;
import model.UserRequest;

import java.util.List;

public interface SearchFilterI {
    List<Employee> process(List<Employee> searchedEmployees);
}
