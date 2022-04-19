package printer;

import model.COMMAND;
import model.Employee;
import model.UserRequest;
import printer.DetailPrint.DetailPrint;
import printer.BriefPrint.BriefPrint;
import printer.NonePrint.NonePrint;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeePrinter {
    public void print(UserRequest request, List<Employee> results){
        IPrintOption printer;
        List<String> options=request.getOptions();
        COMMAND command=request.getCommand();
        List<Employee> filtered = results.stream().filter(e -> e.getEmployeeNum() != null).collect(Collectors.toList());

        if(command.equals(COMMAND.ADD))
            return;
        else if(filtered.size()==0){
            printer=new NonePrint();
        }
        else if (options.contains("-p")){
            printer=new DetailPrint();
        }
        else{
            printer=new BriefPrint();
        }

        printer.print(command,filtered);

    }
}
