package printer.DetailPrint;

import model.COMMAND;
import model.Employee;
import printer.IPrintOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailPrint implements IPrintOption {

    private int printNum=0;
    @Override
    public void print(COMMAND command,  List<Employee> results){
        if (command.equals(COMMAND.ADD)){return;}

        List<Employee> tempList=new ArrayList<>();

        for (Employee employee:results){
            Employee copied=employee.Copy();
            String num=employee.getEmployeeNum();
            if (num.compareTo("21999999")<=0){
                copied.setEmployeeNum("2"+num);
                tempList.add(copied);
            }
            else{
                copied.setEmployeeNum("1"+num);
                tempList.add(copied);
            }

        }
        Collections.sort(tempList);


        for(Employee employee:tempList){
            if (printNum>=5)return ;
            String message=command+","
                    +employee.getEmployeeNum().substring(1)+","
                    +employee.getEmployeeNum()+","
                    +employee.getName()+","
                    +employee.getCl()+","
                    +employee.getPhoneNumber()+","
                    +employee.getCerti();
            System.out.println(message);
            printNum++;
        }

    }
}
