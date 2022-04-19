package printer.BriefPrint;

import model.COMMAND;
import model.Employee;
import printer.IPrintOption;

import java.util.ArrayList;
import java.util.List;

public class BriefPrint implements IPrintOption {

    @Override
    public List<String> print(COMMAND command,  List<Employee> results){
        List <String> strResults=new ArrayList<>();

        strResults.add(command+","+results.size());

        return strResults;

    }
}
