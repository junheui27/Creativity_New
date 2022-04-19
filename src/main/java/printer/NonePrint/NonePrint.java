package printer.NonePrint;

import model.COMMAND;
import model.Employee;
import printer.IPrintOption;

import java.util.ArrayList;
import java.util.List;

public class NonePrint implements IPrintOption {

    //None 방식의 경우 "<command>,NONE" 으로출력
    public List<String> print(COMMAND command,  List<Employee> results){
        List <String> strResults=new ArrayList<>();

        strResults.add(command+",None");

        return strResults;
    }
}
