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

    public List<String> printOption(List<String> outputs)throws IOException {
        return outputs;
    }
}
