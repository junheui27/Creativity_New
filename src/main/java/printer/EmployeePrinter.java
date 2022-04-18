package printer;

import model.COMMAND;
import model.Employee;
import model.UserRequest;
import printer.DetailPrint.DetailPrint;
import printer.BriefPrint.BriefPrint;
import printer.NonePrint.NonePrint;

import java.util.List;

public class EmployeePrinter {
    public void print(UserRequest request, List<Employee> results){
        IPrintOption printer;
        List<String> options=request.getOptions();
        COMMAND command=request.getCommand();

        if(results.size()==0){
            printer=new NonePrint();
        }
        else if (options.get(0).equals("-p")){
            printer=new DetailPrint();
        }
        else{
            printer=new BriefPrint();
        }

        printer.print(command,results);

    }
}
