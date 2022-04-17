package printer;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeePrinterTest {

    private Employee employee;
    private UserRequest request;
    private List<Employee> results;
    private EmployeePrinter employeePrinter;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @BeforeEach
    public void setting(){

        employeePrinter = new EmployeePrinter();
        request=new UserRequest();




    }

    @Test
    void 결과row가_0개일때_출력테스트(){

        //db내 데이터 없음
        results=new ArrayList<>();

        //command , args 설정
        request.setCommand(COMMAND.SCH);
        request.setOptions(new ArrayList<>(Arrays.asList("-p", " "," ")));

        employeePrinter.print(request,results);
        Assertions.assertEquals("SCH,NONE", outputStreamCaptor.toString().trim());
    }


    @Test
    void 결과row가_1개이상일때_p옵션시_출력테스트(){
        //db 데이터 생성
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

        //command , args 설정
        request.setCommand(COMMAND.SCH);
        request.setOptions(new ArrayList<>(Arrays.asList("-p", " "," ")));

        employeePrinter.print(request,results);
        Assertions.assertEquals("SCH,99000101,A LEE,CL3,010-1234-5678,PRO", outputStreamCaptor.toString().trim());
    }

    @Test
    void 결과row가_1개이상일때_p옵션없을시_출력테스트(){
        //db 데이터 생성
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

        //command , args 설정
        request.setCommand(COMMAND.SCH);
        request.setOptions(new ArrayList<>(Arrays.asList(" ", " "," ")));

        employeePrinter.print(request,results);
        Assertions.assertEquals("SCH,1", outputStreamCaptor.toString().trim());
    }

}
