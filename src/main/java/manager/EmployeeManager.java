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

    public void Process(UserRequest request){
        CommandExecutor executor = commandFactory.GetCommand(request);
        List<Employee> results = executor.Execute(request,db);
        employeePrinter.Print(request,results);
    }
}
