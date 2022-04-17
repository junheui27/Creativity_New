package printer.NonePrint;

import model.COMMAND;
import model.Employee;
import printer.IPrintOption;

import java.util.List;

public class NonePrint implements IPrintOption {

    //None 방식의 경우 "<command>,NONE" 으로출력
    public void print(COMMAND command,  List<Employee> results){
        System.out.println(command+",NONE");
    }
}
