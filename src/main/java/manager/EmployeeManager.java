package manager;

import command.CommandExecutor;
import command.factory.CommandFactory;
import database.EmployeeDB;
import model.Employee;
import model.UserRequest;
import printer.EmployeePrinter;

import java.util.List;

public class EmployeeManager {
    final private EmployeeDB db = new EmployeeDB();
    final private CommandFactory commandFactory = new CommandFactory();
    final private EmployeePrinter employeePrinter = new EmployeePrinter();

    public void process(UserRequest request) throws Exception{
        CommandExecutor executor = commandFactory.getCommand(request);
        List<Employee> results = executor.run(request,db);
        employeePrinter.print(request,results);
    }
}
