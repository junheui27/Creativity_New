package command;

import database.EmployeeDB;
import model.Employee;
import model.UserRequest;

import java.util.List;

public interface CommandExecutor {
    List<Employee> Execute(UserRequest request, EmployeeDB db);
}
