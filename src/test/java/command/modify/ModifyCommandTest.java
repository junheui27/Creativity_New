package command.modify;

import command.CommandExecutor;
import command.factory.CommandFactory;
import command.mod.ModifyCommand;
import command.sch.SearchCommand;
import database.EmployeeDB;
import model.*;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ModifyCommandTest {
    private CommandExecutor modifyCommand;
    private CommandExecutor searchCommand;
    private EmployeeDB db;
    private Employee defaultEmployee;
    private Employee emp2;
    private Employee emp3;
    private Employee emp4;
    private Employee emp5;
    private Employee emp6;
    private Employee emp7;

    @BeforeEach
    void initTest(){
        searchCommand = new SearchCommand();
        modifyCommand = new ModifyCommand(searchCommand);

        db = new EmployeeDB();
        defaultEmployee = new Employee().builder()
                .employeeNum("17041177")
                .birthday("20000101")
                .cl(CAREERLEVEL.CL2)
                .certi(CERTI.PRO)
                .phoneNumber("1111-1111")
                .name("WHITEDEV LEE")
                .build();

        emp2 = defaultEmployee.Copy();
        emp2.setEmployeeNum("12345678");
        emp2.setName("BLACKDEV LEE");

        emp3 = emp2.Copy();
        emp3.setEmployeeNum("22345678");
        emp3.setName("LEE WHITEDEV");

        Employee emp8 = emp3.Copy();
        emp8.setEmployeeNum("72345678");

        emp4 = emp3.Copy();
        emp4.setEmployeeNum("32345678");
        emp4.setName("SW SMILE");

        emp5 = emp4.Copy();
        emp5.setEmployeeNum("42345678");
        emp5.setName("JUNHEUI LEE");

        emp6 = emp5.Copy();
        emp6.setEmployeeNum("52345678");
        emp6.setName("DOWON CHAE");

        emp7 = emp6.Copy();
        emp7.setEmployeeNum("62345678");
        emp7.setName("JIWON SEO");
        emp7.setPhoneNumber("2222-2222");
        emp7.setBirthday("20000202");

        db.addEmployee(defaultEmployee);
        db.addEmployee(emp2);
        db.addEmployee(emp3);
        db.addEmployee(emp4);
        db.addEmployee(emp5);
        db.addEmployee(emp6);
        db.addEmployee(emp7);
        db.addEmployee(emp8);
    }

    @Test
    @DisplayName("[normal] modify name")
    public void modifyTest1() throws Exception {

        UserRequest request = new UserRequest();
        request.setCommand(COMMAND.MOD);
        request.setArguments(Arrays.asList("name","LEE WHITEDEV","name","BLACKDEV KIM"));
        request.setOptions(Arrays.asList(" "," "," "));
        List<Employee> whiteDevEmployees = db.findEmployeeByColumn("name","LEE WHITEDEV");

        List<Employee> beforeModified = modifyCommand.run(request,db);

        Employee firstEmployee = db.findEmployeeById(beforeModified.get(0).getEmployeeNum());
        Employee secondEmployee = db.findEmployeeById(beforeModified.get(1).getEmployeeNum());
        List<Employee> shouldNotExistsEmployees = db.findEmployeeByColumn("name","LEE WHITEDEV");

        Assertions.assertAll(
                ()-> Assertions.assertEquals(2,beforeModified.size()),
                ()-> Assertions.assertEquals(whiteDevEmployees.size(),beforeModified.size()),
                ()-> Assertions.assertEquals("BLACKDEV KIM",firstEmployee.getName()),
                ()-> Assertions.assertEquals("BLACKDEV KIM",secondEmployee.getName()),
                ()-> Assertions.assertEquals(0,shouldNotExistsEmployees.size())
                );
    }

    @Test
    @DisplayName("[normal] modify phoneNum")
    public void modifyTest2() throws Exception {

        UserRequest request = new UserRequest();
        request.setCommand(COMMAND.MOD);
        request.setArguments(Arrays.asList("cl","CL2","phoneNum","3333-3333"));
        request.setOptions(Arrays.asList(" "," "," "));
        List<Employee> cl2Employees = db.findEmployeeByColumn("cl","CL2");

        List<Employee> beforeModified = modifyCommand.run(request,db);

        List<Employee> afterModified = db.findEmployeeByColumn("phoneNum","3333-3333");
        List<Employee> shouldNotExistsEmployees = db.findEmployeeByColumn("phoneNum","1111-1111");

        Assertions.assertAll(
                ()-> Assertions.assertEquals(8,beforeModified.size()),
                ()-> Assertions.assertEquals(cl2Employees.size(),beforeModified.size()),
                ()-> Assertions.assertEquals(8,afterModified.size()),
                ()-> Assertions.assertEquals(0,shouldNotExistsEmployees.size())
        );
    }

    @Test
    @DisplayName("[normal] modify birthday")
    public void modifyTest3() throws Exception {

        UserRequest request = new UserRequest();
        request.setCommand(COMMAND.MOD);
        request.setArguments(Arrays.asList("cl","CL2","birthday","20220418"));
        request.setOptions(Arrays.asList(" "," "," "));
        List<Employee> cl2Employees = db.findEmployeeByColumn("cl","CL2");

        List<Employee> beforeModified = modifyCommand.run(request,db);

        List<Employee> afterModified = db.findEmployeeByColumn("birthday","20220418");
        List<Employee> shouldNotExistsEmployees = db.findEmployeeByColumn("birthday","20000101");

        Assertions.assertAll(
                ()-> Assertions.assertEquals(8,beforeModified.size()),
                ()-> Assertions.assertEquals(cl2Employees.size(),beforeModified.size()),
                ()-> Assertions.assertEquals(8,afterModified.size()),
                ()-> Assertions.assertEquals(0,shouldNotExistsEmployees.size())
        );
    }

    @Test
    @DisplayName("[normal] modify CL")
    public void modifyTest4() throws Exception {

        UserRequest request = new UserRequest();
        request.setCommand(COMMAND.MOD);
        request.setArguments(Arrays.asList("cl","CL2","cl","CL4"));
        request.setOptions(Arrays.asList(" "," "," "));
        List<Employee> cl2Employees = db.findEmployeeByColumn("cl","CL2");

        List<Employee> beforeModified = modifyCommand.run(request,db);

        List<Employee> afterModified = db.findEmployeeByColumn("cl","CL4");
        List<Employee> shouldNotExistsEmployees = db.findEmployeeByColumn("cl","CL2");

        Assertions.assertAll(
                ()-> Assertions.assertEquals(8,beforeModified.size()),
                ()-> Assertions.assertEquals(cl2Employees.size(),beforeModified.size()),
                ()-> Assertions.assertEquals(8,afterModified.size()),
                ()-> Assertions.assertEquals(0,shouldNotExistsEmployees.size())
        );
    }

    @Test
    @DisplayName("[normal] modify certi")
    public void modifyTest5() throws Exception {

        UserRequest request = new UserRequest();
        request.setCommand(COMMAND.MOD);
        request.setArguments(Arrays.asList("cl","CL2","certi","EX"));
        request.setOptions(Arrays.asList(" "," "," "));
        List<Employee> cl2Employees = db.findEmployeeByColumn("cl","CL2");

        List<Employee> beforeModified = modifyCommand.run(request,db);

        List<Employee> afterModified = db.findEmployeeByColumn("certi","EX");
        List<Employee> shouldNotExistsEmployees = db.findEmployeeByColumn("certi","PRO");

        Assertions.assertAll(
                ()-> Assertions.assertEquals(8,beforeModified.size()),
                ()-> Assertions.assertEquals(cl2Employees.size(),beforeModified.size()),
                ()-> Assertions.assertEquals(8,afterModified.size()),
                ()-> Assertions.assertEquals(0,shouldNotExistsEmployees.size())
        );
    }

    @Test
    @DisplayName("[normal] modify name with last name option")
    public void modifyTest6() throws Exception {

        UserRequest request = new UserRequest();
        request.setCommand(COMMAND.MOD);
        request.setArguments(Arrays.asList("name","LEE","phoneNum","4444-4444"));
        request.setOptions(Arrays.asList("-l"," "," "));

        List<Employee> beforeModified = modifyCommand.run(request,db);
        List<Employee> afterModified = db.findEmployeeByColumn("phoneNum","4444-4444");

        Assertions.assertAll(
                ()-> Assertions.assertEquals(3,beforeModified.size()),
                ()-> Assertions.assertEquals(3,afterModified.size()),
                ()-> Assertions.assertTrue(afterModified.stream().allMatch(m -> m.getName().contains("LEE")))
        );
    }

    @Test
    @DisplayName("[normal] 존재하지 않는 컬럼이 수정대상으로 지정됨")
    public void modifyTest7() throws Exception {

        UserRequest request = new UserRequest();
        request.setCommand(COMMAND.MOD);
        request.setArguments(Arrays.asList("name","LEE","notExist","4444-4444"));
        request.setOptions(Arrays.asList(" "," "," "));

        List<Employee> beforeModified = modifyCommand.run(request,db);
        Assertions.assertTrue(beforeModified.stream().allMatch(h -> db.findEmployeeById(h.getEmployeeNum()).equals(h)));
    }
}
