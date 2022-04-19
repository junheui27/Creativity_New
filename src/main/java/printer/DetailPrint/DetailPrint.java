package printer.DetailPrint;

import model.*;

import printer.IPrintOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



public class DetailPrint implements IPrintOption {

    @Override
    public List<String> print(COMMAND command,  List<Employee> results){
        List <String> strResults=new ArrayList<>();

        List<TempEmployee> tempList=new ArrayList<>();

        for (Employee employee:results){
            if(employee.getEmployeeNum() == null)
                continue;

            TempEmployee tempEmployee=new TempEmployee(employee);
            tempList.add(tempEmployee);
        }
        Collections.sort(tempList);

        //limit 5
        List<TempEmployee> limitList=tempList.stream().limit(5).collect(Collectors.toList());

        //print
        for(TempEmployee tempEmployee:limitList){

            String message=command+","
                    +tempEmployee.getEmployeeNum()+","
                    +tempEmployee.getName()+","
                    +tempEmployee.getCl()+","
                    +tempEmployee.getPhoneNumber()+","
                    +tempEmployee.getBirthday()+","
                    +tempEmployee.getCerti();
            strResults.add(message);

        }
        return strResults;

    }
}
