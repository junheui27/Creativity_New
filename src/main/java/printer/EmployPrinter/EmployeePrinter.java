package printer.EmployPrinter;

import model.*;
import printer.DetailPrint.DetailPrint;
import printer.BriefPrint.BriefPrint;
import printer.IEmployeePrinter;
import printer.IPrintOption;
import printer.NonePrint.NonePrint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeePrinter implements IEmployeePrinter {


    public void printOption(String outputPath,List<String> strResults)throws IOException {
        for(String str: strResults){
            System.out.println(str);
        }

    }

}


