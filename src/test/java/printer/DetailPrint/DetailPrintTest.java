package printer.DetailPrint;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import printer.IPrintOption;


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
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


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
    @DisplayName("ADD 명령어 실행시, 출력안됨")
    void addTest(){

        IPrintOption detailPrinter=mock(DetailPrint.class);
        doNothing().when(detailPrinter).print(COMMAND.ADD,results);
        detailPrinter.print(COMMAND.ADD,results);

        //호출확인. 출력안됨
        verify(detailPrinter,times(1)).print(COMMAND.ADD, results);


    }
    @Test
    @DisplayName("MOD 명령어 실행시, detail 출력확인")
    void modTest(){


        IPrintOption detailPrinter=new DetailPrint();
        detailPrinter.print(COMMAND.MOD, results);
        Assertions.assertEquals("MOD,99000101,A LEE,CL3,010-1234-5678,PRO", outputStreamCaptor.toString().trim());

    }

    @Test
    @DisplayName("SCH 명령어 실행시, detail 출력확인")
    void schTest(){

        IPrintOption detailPrinter=new DetailPrint();
        detailPrinter.print(COMMAND.SCH, results);
        Assertions.assertEquals("SCH,99000101,A LEE,CL3,010-1234-5678,PRO", outputStreamCaptor.toString().trim());

    }
    @Test
    @DisplayName("DEL 명령어 실행시, detail 출력확인")
    void delTest(){

        IPrintOption detailPrinter=new DetailPrint();
        detailPrinter.print(COMMAND.DEL, results);
        Assertions.assertEquals("DEL,99000101,A LEE,CL3,010-1234-5678,PRO", outputStreamCaptor.toString().trim());

    }
}
