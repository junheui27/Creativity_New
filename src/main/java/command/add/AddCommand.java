package command.add;

import command.CommandExecutor;
import database.EmployeeDB;
import model.Employee;
import model.UserRequest;

import java.util.List;

public class AddCommand implements CommandExecutor {
    @Override
    public List<Employee> run(UserRequest request, EmployeeDB db) {
        return null;
    }
}
