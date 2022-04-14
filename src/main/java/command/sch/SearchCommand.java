package command.sch;

import command.CommandExecutor;
import database.EmployeeDB;
import model.Employee;
import model.UserRequest;

import java.util.List;

public class SearchCommand implements CommandExecutor {
    @Override
    public List<Employee> Execute(UserRequest request, EmployeeDB db) {
        return null;
    }
}
