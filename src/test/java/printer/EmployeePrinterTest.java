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

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;
    private Employee employee5;
    private Employee employee6;
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

        //db 데이터 생성
        employee1 = new Employee(
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
        employee2 = new Employee(
                "00000101",
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
        employee3 = new Employee(
                "99999999",
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
        employee4 = new Employee(
                "00000000",
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
        employee5 = new Employee(
                "69999999",
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
        employee6 = new Employee(
                "21999999",
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

        results=new ArrayList<>();
        results.add(employee1);
        results.add(employee2);
        results.add(employee3);
        results.add(employee4);
        results.add(employee5);
        results.add(employee6);



        //command , args 설정
        request.setCommand(COMMAND.SCH);
        request.setOptions(new ArrayList<>(Arrays.asList("-p", " "," ")));

        employeePrinter.print(request,results);
        Assertions.assertEquals("SCH,69999999,169999999,A LEE,CL3,010-1234-5678,PRO\r\n" +
                "SCH,99000101,199000101,A LEE,CL3,010-1234-5678,PRO\r\n" +
                "SCH,99999999,199999999,A LEE,CL3,010-1234-5678,PRO\r\n" +
                "SCH,00000000,200000000,A LEE,CL3,010-1234-5678,PRO\r\n" +
                "SCH,00000101,200000101,A LEE,CL3,010-1234-5678,PRO", outputStreamCaptor.toString().trim());


    }

    @Test
    void 결과row가_1개이상일때_p옵션없을시_출력테스트(){

        results=new ArrayList<>();
        results.add(employee1);
        results.add(employee2);
        results.add(employee3);
        results.add(employee4);
        results.add(employee5);
        results.add(employee6);

        //command , args 설정
        request.setCommand(COMMAND.SCH);
        request.setOptions(new ArrayList<>(Arrays.asList(" ", " "," ")));

        employeePrinter.print(request,results);
        Assertions.assertEquals("SCH,6", outputStreamCaptor.toString().trim());
    }

}
