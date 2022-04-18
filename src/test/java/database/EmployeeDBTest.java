package database;

import model.CAREERLEVEL;
import model.CERTI;
import model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeeDBTest {

    private EmployeeDB db;
    private Employee defaultEmployee;
    private Employee emp2;
    private Employee emp3;
    private Employee emp4;
    private Employee emp5;
    private Employee emp6;
    private Employee emp7;

    @BeforeEach
    public void resetDB(){
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

    }

    @Test
    @DisplayName("[normal] 추가된 employee가 잘 저장되는지")
    public void addTest(){

        boolean ret = db.addEmployee(defaultEmployee);
        Employee found = db.findEmployee("17041177");

        Assertions.assertTrue(found.equals(defaultEmployee));
    }

    @Test
    @DisplayName("[normal] 이름이 같은 employee가 잘 저장되는지")
    public void addTest3(){

        boolean ret = db.addEmployee(defaultEmployee);
        Employee sameName = defaultEmployee.Copy();
        sameName.setEmployeeNum("11111111");
        db.addEmployee(sameName);

        Employee found = db.findEmployee("17041177");

        Assertions.assertTrue(found.equals(defaultEmployee));
    }

    @Test
    @DisplayName("[exception] 이미 저장된 사번을 추가하려고 시도")
    public void addTest2(){
        db.addEmployee(defaultEmployee);
        boolean ret = db.addEmployee(defaultEmployee);

        Assertions.assertEquals(false,ret);
    }

    @Test
    @DisplayName("[normal] 삭제된 employee가 없는지")
    public void deleteTest(){

        boolean ret = db.addEmployee(defaultEmployee);
        Employee removed = db.deleteEmployee(defaultEmployee);

        Employee found = db.findEmployee("17041177");
        Assertions.assertTrue(found.equals(new Employee()));
        Assertions.assertEquals(defaultEmployee,removed);
    }

    @Test
    @DisplayName("[normal] 저장 후 이름 조회 성공")
    public void findTest1(){
        db.addEmployee(defaultEmployee);
        db.addEmployee(emp2);
        db.addEmployee(emp3);

        List<Employee> result = db.findEmployeeByColumn("name","LEE"); //이름은 항상 대문자
        Assertions.assertEquals(3,result.size());
    }

    @Test
    @DisplayName("[exception] 저장 후 없는 이름 조회 실패")
    public void findTest2(){
        db.addEmployee(defaultEmployee);
        db.addEmployee(emp2);
        db.addEmployee(emp3);

        List<Employee> result = db.findEmployeeByColumn("name","YELLOW");
        Assertions.assertEquals(0,result.size());
    }

    @Test
    @DisplayName("[exception] 없는 컬럼명 입력")
    public void findTest3(){
        db.addEmployee(defaultEmployee);
        db.addEmployee(emp2);
        db.addEmployee(emp3);

        List<Employee> result = db.findEmployeeByColumn("name2","LEE"); //에러를 띄워야하지 않을까?
        Assertions.assertEquals(0,result.size());
    }

    @Test
    @DisplayName("[normal] 전화번호 검색")
    public void findTest4(){
        db.addEmployee(defaultEmployee);
        db.addEmployee(emp2);
        db.addEmployee(emp3);
        db.addEmployee(emp4);
        db.addEmployee(emp5);
        db.addEmployee(emp6);
        db.addEmployee(emp7);

        List<Employee> result = db.findEmployeeByColumn("phoneNum","1111-1111");
        Assertions.assertEquals(6,result.size());
    }

    @Test
    @DisplayName("[normal] 생일 검색")
    public void findTest5(){
        db.addEmployee(defaultEmployee);
        db.addEmployee(emp2);
        db.addEmployee(emp3);
        db.addEmployee(emp4);
        db.addEmployee(emp5);
        db.addEmployee(emp6);
        db.addEmployee(emp7);

        List<Employee> result = db.findEmployeeByColumn("birthday","01");
        Assertions.assertEquals(6,result.size());
    }

    @Test
    @DisplayName("[normal] CL 검색")
    public void findTest6(){
        db.addEmployee(defaultEmployee);
        db.addEmployee(emp2);
        db.addEmployee(emp3);
        db.addEmployee(emp4);
        db.addEmployee(emp5);
        db.addEmployee(emp6);
        db.addEmployee(emp7);

        List<Employee> result = db.findEmployeeByColumn("cl","CL2");
        Assertions.assertEquals(7,result.size());
    }

    @Test
    @DisplayName("[normal] CERT 검색")
    public void findTest7(){
        db.addEmployee(defaultEmployee);
        db.addEmployee(emp2);
        db.addEmployee(emp3);
        db.addEmployee(emp4);
        db.addEmployee(emp5);
        db.addEmployee(emp6);
        db.addEmployee(emp7);

        List<Employee> result = db.findEmployeeByColumn("certi","PRO");
        Assertions.assertEquals(7,result.size());
    }
}
