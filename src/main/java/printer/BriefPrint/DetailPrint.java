package printer.BriefPrint;

import model.COMMAND;
import model.Employee;
import printer.IPrintOption;

import java.util.List;

public class DetailPrint implements IPrintOption {

    @Override
    public void print(COMMAND command,  List<Employee> results){
        if (command.equals(COMMAND.ADD)){return;}

        for(Employee employee:results){
            String message=command+","
                    +employee.getEmployeeNum()+","
                    +employee.getName()+","
                    +employee.getCl()+","
                    +employee.getPhoneNumber()+","
                    +employee.getCerti();
            System.out.println(message);
        }

    }
}
