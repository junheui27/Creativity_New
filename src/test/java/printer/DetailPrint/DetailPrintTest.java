package printer.DetailPrint;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import printer.IPrintOption;
import printer.NonePrint.NonePrint;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DetailPrintTest {

    private Employee employee;
    public List<Employee> results;
    private final PrintStream standardOut = System.out;



    @BeforeEach
    public void setting(){

        employee = new Employee(
                "99000101",
                "A LEE",
                "010-1234-5678",
                CAREERLEVEL.CL3,
                "19920101",
                CERTI.PRO

        );
        results=new ArrayList<>();
        results.add(employee);


    }
    @Test
    @DisplayName("ADD 명령어 실행시, 그대로 넘김,윗단에서처리")
    void addTest(){

        IPrintOption Printer=new DetailPrint();
        List<String>re=Printer.print(COMMAND.ADD, results);
        Assertions.assertEquals("ADD,99000101,A LEE,CL3,010-1234-5678,19920101,PRO", re.get(0));


    }
    @Test
    @DisplayName("MOD 명령어 실행시, detail 출력확인")
    void modTest(){


        IPrintOption Printer=new DetailPrint();
        List<String>re=Printer.print(COMMAND.MOD, results);
        Assertions.assertEquals("MOD,99000101,A LEE,CL3,010-1234-5678,19920101,PRO",  re.get(0));

    }

    @Test
    @DisplayName("SCH 명령어 실행시, detail 출력확인")
    void schTest(){

        IPrintOption Printer=new DetailPrint();
        List<String>re=Printer.print(COMMAND.SCH, results);
        Assertions.assertEquals("SCH,99000101,A LEE,CL3,010-1234-5678,19920101,PRO",  re.get(0));

    }
    @Test
    @DisplayName("DEL 명령어 실행시, detail 출력확인")
    void delTest(){

        IPrintOption Printer=new DetailPrint();
        List<String>re=Printer.print(COMMAND.DEL, results);
        Assertions.assertEquals("DEL,99000101,A LEE,CL3,010-1234-5678,19920101,PRO",  re.get(0));

    }
}
