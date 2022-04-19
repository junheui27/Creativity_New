package printer.EmployPrinter;

import model.COMMAND;
import model.Employee;
import model.UserRequest;
import printer.BriefPrint.BriefPrint;
import printer.DetailPrint.DetailPrint;
import printer.IEmployeePrinter;
import printer.IPrintOption;
import printer.NonePrint.NonePrint;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilePrinter implements IEmployeePrinter {

    public void printOption(String outputPath, List<String> outputs)throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("./" + outputPath,true));

        for(String str2 : outputs){
            bw.write(str2 + "\n");
        }
        bw.flush();
        bw.close();

    }

}
