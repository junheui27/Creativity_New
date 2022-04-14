package command.mod;

import command.CommandExecutor;
import database.EmployeeDB;
import model.Employee;
import model.UserRequest;

import java.util.List;

public class ModifyCommand implements CommandExecutor {
    final private CommandExecutor schCommand;

    public ModifyCommand(CommandExecutor schCommand) {
        this.schCommand = schCommand;
    }

    @Override
    public List<Employee> Execute(UserRequest request, EmployeeDB db) {
        return null;
    }
}
