package printer.BriefPrint;

import model.COMMAND;
import model.Employee;
import printer.IPrintOption;

import java.util.List;

public class BriefPrint implements IPrintOption {

    @Override
    public void print(COMMAND command,  List<Employee> results){

        //ADD 커맨드시 출력없음
        if (command.equals(COMMAND.ADD)){return;}

        //MOD, SCH, DEL 커맨드 -p옵션없으면 number 출력
        String message=command+","+results.size();
        System.out.println(message);



    }
}
