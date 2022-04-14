package command.factory;

import command.CommandExecutor;
import database.EmployeeDB;
import model.Employee;
import model.UserRequest;

import java.util.List;

public class NoneCommand implements CommandExecutor {
    @Override
    public List<Employee> run(UserRequest request, EmployeeDB db) {
        return null;
    }
}
