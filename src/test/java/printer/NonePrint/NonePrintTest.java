package printer.NonePrint;


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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;


//@ExtendWith(MockitoExtension.class)
public class NonePrintTest {
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
                "LEE",
                "A",
                "010-1234-5678",
                "1234",
                "5678",
                CAREERLEVEL.CL3,
                "19920101",
                "1992",
                "01",
                "01",
                CERTI.PRO

        );
        results=new ArrayList<>();
        results.add(employee);


    }
    @Test
    @DisplayName("ADD 명령어는 출력되지 않으며 출력 옵션도 사용하지 않는다")
    void addTest(){

        IPrintOption nonePrinter=mock(NonePrint.class);
        doNothing().when(nonePrinter).print(COMMAND.ADD,results);
        nonePrinter.print(COMMAND.ADD,results);

        //호출확인. 출력안됨
        verify(nonePrinter,times(1)).print(COMMAND.ADD, results);


    }
    @Test
    @DisplayName("MOD 명령어 실행시, 데이터가 없을때 NONE 출력되는지")
    void modTest(){


        IPrintOption nonePrinter=new NonePrint();
        nonePrinter.print(COMMAND.MOD, results);
        Assertions.assertEquals("MOD,NONE", outputStreamCaptor.toString().trim());

    }

    @Test
    @DisplayName("SCH 명령어 실행시, 데이터가 없을때 NONE 출력되는지")
    void schTest(){


        IPrintOption nonePrinter=new NonePrint();
        nonePrinter.print(COMMAND.MOD, results);
        Assertions.assertEquals("SCH,NONE", outputStreamCaptor.toString().trim());

    }
    @Test
    @DisplayName("DEL 명령어 실행시, NONE 출력되는지")
    void delTest(){


        IPrintOption nonePrinter=new NonePrint();
        nonePrinter.print(COMMAND.MOD, results);
        nonePrinter.print(COMMAND.DEL, results);

    }
}
