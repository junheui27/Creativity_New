package printer;

import lombok.Builder;
import model.COMMAND;
import model.Employee;
import model.UserRequest;
import printer.BriefPrint.BriefPrint;
import printer.DetailPrint.DetailPrint;
import printer.NonePrint.NonePrint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface IEmployeePrinter {

    default void print(UserRequest request, List<Employee> results){
        IPrintOption printer;
        List<String> options=request.getOptions();
        List<String> strResults;

        COMMAND command=request.getCommand();
        String outputPath=request.getOutputPath();

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

        try{
            strResults=printer.print(command,filtered);
            printOption(outputPath,strResults);

        }catch (Exception e){
            System.out.println(e);
        }


    }
    void printOption(String outputPath, List<String> outputs)throws IOException;

}
