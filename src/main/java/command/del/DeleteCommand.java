package command.del;

import command.CommandExecutor;
import database.EmployeeDB;
import model.Employee;
import model.UserRequest;

import java.util.List;

public class DeleteCommand implements CommandExecutor {
    final private CommandExecutor schCommand;
    public DeleteCommand(CommandExecutor schCommand) {
        this.schCommand = schCommand;
    }

    @Override
    public List<Employee> run(UserRequest request, EmployeeDB db) {
        return null;
    }
}
