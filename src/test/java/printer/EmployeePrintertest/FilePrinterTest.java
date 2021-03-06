package printer.EmployeePrintertest;


import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import printer.EmployPrinter.FilePrinter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilePrinterTest {

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;
    private Employee employee5;
    private Employee employee6;
    private UserRequest request;
    private List<Employee> results;
    private FilePrinter filePrinter;


    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @BeforeEach
    public void setting(){

        filePrinter = new FilePrinter();

        request=new UserRequest();

        //db 데이터 생성
        employee1 = new Employee(
                "99000101",
                "A LEE",
                "010-1234-5678",
                CAREERLEVEL.CL3,
                "19920101",
                CERTI.PRO

        );
        employee2 = new Employee(
                "00000101",
                "A LEE",
                "010-1234-5678",
                CAREERLEVEL.CL3,
                "19920101",
                CERTI.PRO

        );
        employee3 = new Employee(
                "99999999",
                "A LEE",
                "010-1234-5678",
                CAREERLEVEL.CL3,
                "19920101",
                CERTI.PRO

        );
        employee4 = new Employee(
                "00000000",
                "A LEE",
                "010-1234-5678",
                CAREERLEVEL.CL3,
                "19920101",
                CERTI.PRO

        );
        employee5 = new Employee(
                "69999999",
                "A LEE",
                "010-1234-5678",
                CAREERLEVEL.CL3,
                "19920101",
                CERTI.PRO

        );
        employee6 = new Employee(
                "21999999",
                "A LEE",
                "010-1234-5678",
                CAREERLEVEL.CL3,
                "19920101",
                CERTI.PRO

        );




    }

    @Test
    @DisplayName("결과row가_0개일때")
    void resultsZero(){

        //db내 데이터 없음
        results=new ArrayList<>();

        //command , args 설정
        request.setCommand(COMMAND.SCH);
        //request.setOutputPath("output.txt");
        request.setOptions(new ArrayList<>(Arrays.asList("-p", " "," ")));

        List<String>re=filePrinter.print(request,results);

        Assertions.assertEquals(re.get(0),"");

    }


    @Test
    @DisplayName("결과row가 5개이상일때 P옵션 사용시")
    void resultsMoreThanFive(){

        results=new ArrayList<>();
        results.add(employee1);
        results.add(employee2);
        results.add(employee3);
        results.add(employee4);
        results.add(employee5);
        results.add(employee6);



        //command , args 설정
        request.setCommand(COMMAND.SCH);
        //request.setOutputPath("output.txt");
        request.setOptions(new ArrayList<>(Arrays.asList("-p", " "," ")));

        List<String>re=filePrinter.print(request,results);
        Assertions.assertEquals(re.get(0),"SCH,69999999,A LEE,CL3,010-1234-5678,19920101,PRO");
        Assertions.assertEquals(re.get(1),"SCH,99000101,A LEE,CL3,010-1234-5678,19920101,PRO");
        Assertions.assertEquals(re.get(2),"SCH,99999999,A LEE,CL3,010-1234-5678,19920101,PRO");
        Assertions.assertEquals(re.get(3),"SCH,00000000,A LEE,CL3,010-1234-5678,19920101,PRO");
        Assertions.assertEquals(re.get(4),"SCH,00000101,A LEE,CL3,010-1234-5678,19920101,PRO");


    }

    @Test
    @DisplayName("결과row가 5개이상일때 p옵션없을시")
    void resultsExceptPOption(){

        results=new ArrayList<>();
        results.add(employee1);
        results.add(employee2);
        results.add(employee3);
        results.add(employee4);
        results.add(employee5);
        results.add(employee6);

        //command , args 설정
        request.setCommand(COMMAND.SCH);
        //request.setOutputPath("output.txt");
        request.setOptions(new ArrayList<>(Arrays.asList(" ", " "," ")));

        List<String>re=filePrinter.print(request,results);
        Assertions.assertEquals(re.get(0),"SCH,6");
    }

}
