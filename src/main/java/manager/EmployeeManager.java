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
    final private EmployeePrinterWR employeePrinter = new EmployeePrinterWR();

    public void process(UserRequest request){
        try{
            CommandExecutor executor = commandFactory.getCommand(request);
            List<Employee> results = executor.run(request,db);
            employeePrinter.print(request,results);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
