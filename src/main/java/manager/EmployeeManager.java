package manager;

import command.CommandExecutor;
import command.factory.CommandFactory;
import database.EmployeeDB;
import model.Employee;
import model.UserRequest;
import printer.EmployPrinter.*;


import java.util.List;

public class EmployeeManager {
    final private EmployeeDB db = new EmployeeDB();
    final private CommandFactory commandFactory = new CommandFactory();
    final private FilePrinter printer = new FilePrinter();
    final private ConsolePrinter consolePrinter = new ConsolePrinter();

    public void process(UserRequest request){
        try{
            CommandExecutor executor = commandFactory.getCommand(request);
            List<Employee> results = executor.run(request,db);
            printer.print(request,results);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
