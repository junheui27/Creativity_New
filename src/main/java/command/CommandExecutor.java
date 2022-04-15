package command;

import database.EmployeeDB;
import model.Employee;
import model.UserRequest;

import java.util.List;

public interface CommandExecutor {
    List<Employee> run(UserRequest request, EmployeeDB db);
}
