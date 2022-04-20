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
    @DisplayName("ADD 명령어는 그대로 넘김,윗단에서처리")
    void addTest(){

        IPrintOption nonePrinter=new NonePrint();
        List<String>re=nonePrinter.print(COMMAND.ADD, results);
        Assertions.assertEquals("ADD,NONE", re.get(0));

    }
    @Test
    @DisplayName("MOD 명령어 실행시, 데이터가 없을때 NONE 출력되는지")
    void modTest(){


        IPrintOption nonePrinter=new NonePrint();
        List<String>re=nonePrinter.print(COMMAND.MOD, results);
        Assertions.assertEquals("MOD,NONE", re.get(0));

    }

    @Test
    @DisplayName("SCH 명령어 실행시, 데이터가 없을때 NONE 출력되는지")
    void schTest(){


        IPrintOption nonePrinter=new NonePrint();
        List<String>re=nonePrinter.print(COMMAND.SCH, results);
        Assertions.assertEquals("SCH,NONE", re.get(0));

    }
    @Test
    @DisplayName("DEL 명령어 실행시, NONE 출력되는지")
    void delTest(){


        IPrintOption nonePrinter=new NonePrint();
        List<String>re=nonePrinter.print(COMMAND.DEL, results);
        Assertions.assertEquals("DEL,NONE", re.get(0));

    }
}
