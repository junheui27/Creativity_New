package command.sch;

import database.EmployeeDB;
import model.CAREERLEVEL;
import model.CERTI;
import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class SearchCommandTest {
    private EmployeeDB db;
    private Employee defaultEmployee;
    private Employee emp2;
    private Employee emp3;
    private Employee emp4;
    private Employee emp5;
    private Employee emp6;
    private Employee emp7;
    private Employee emp8;
    private Employee emp9;
    private Employee emp10;
    static SearchCommand sc = new SearchCommand();

    private List<Employee> list = new ArrayList<>();

    @BeforeEach
    public void resetDB(){
        db = new EmployeeDB();
        defaultEmployee = new Employee().builder()
                .employeeNum("11111111")
                .birthday("11111110")
                .cl(CAREERLEVEL.CL2)
                .certi(CERTI.PRO)
                .phoneNumber("010-1111-2222")
                .name("WHITEDEV LEE")
                .build();

        emp2 = defaultEmployee.Copy();
        emp2.setEmployeeNum("22222222");
        emp2.setName("BLACKDEV LEE");
        emp2.setPhoneNumber("010-2222-3333");
        emp2.setBirthday("22222220");
        emp2.setCerti(CERTI.ADV);

        emp3 = emp2.Copy();
        emp3.setEmployeeNum("33333333");
        emp3.setName("LEE WHITEDEV");
        emp3.setPhoneNumber("010-3333-4444");
        emp3.setBirthday("33333330");


        emp4 = emp3.Copy();
        emp4.setEmployeeNum("44444444");
        emp4.setName("SW SMILE");
        emp4.setPhoneNumber("010-4444-5555");
        emp4.setBirthday("88884440");


        emp5 = emp4.Copy();
        emp5.setEmployeeNum("55555555");
        emp5.setName("JUNHEUI LEE");
        emp5.setPhoneNumber("010-4444-5555");
        emp5.setBirthday("55554440");

        emp6 = emp5.Copy();
        emp6.setEmployeeNum("66666666");
        emp6.setName("DOWON LEE");
        emp6.setPhoneNumber("010-1111-2222");
        emp6.setBirthday("33333330");
        emp6.setCl(CAREERLEVEL.CL3);

        emp7 = emp6.Copy();
        emp7.setEmployeeNum("77777777");
        emp7.setName("JIWON SEO");
        emp7.setPhoneNumber("010-7777-8588");
        emp7.setBirthday("88888882");
        emp7.setCl(CAREERLEVEL.CL4);

        emp8 = emp7.Copy();
        emp8.setEmployeeNum("88888888");
        emp8.setName("SOOWON SEO");
        emp8.setPhoneNumber("010-7777-8588");
        emp8.setBirthday("88888882");

        emp9 = emp8.Copy();
        emp9.setEmployeeNum("99999999");
        emp9.setName("SEO JIOWN");
        emp9.setPhoneNumber("010-7777-8588");
        emp9.setBirthday("88888882");

        emp10 = emp9.Copy();
        emp10.setEmployeeNum("90000009");
        emp10.setName("SW SMIL");
        emp10.setPhoneNumber("010-7777-8588");
        emp10.setBirthday("88888882");
        emp10.setCerti(CERTI.EX);

        db.addEmployee(defaultEmployee);
        db.addEmployee(emp2);
        db.addEmployee(emp3);
        db.addEmployee(emp4);
        db.addEmployee(emp5);
        db.addEmployee(emp6);
        db.addEmployee(emp7);
        db.addEmployee(emp8);
        db.addEmployee(emp9);
        db.addEmployee(emp10);

    }

    @Test
    void searchByEmpNumTest() {

        list = sc.SearchEmployeeList("employeeNum","77777777", "",db);
        assertEquals(1,list.size());
        assertEquals("JIWON SEO",list.get(0).getName());

        list = sc.SearchEmployeeList("employeeNum","66666666", "",db);
        assertEquals(1,list.size());
        assertEquals("DOWON LEE",list.get(0).getName());
    }

    @Test
    void searchByNameTest(){

        list = sc.SearchEmployeeList("name","BLACKDEV LEE", "",db);
        assertEquals(1,list.size());
        assertEquals("22222220",list.get(0).getBirthday());

        list = sc.SearchEmployeeList("name","SW SMILE", "",db);
        assertEquals(1,list.size());
        assertEquals("010-4444-5555",list.get(0).getPhoneNumber());
    }

    @Test
    void searchByFirstNameTest() {

        list = sc.SearchEmployeeList("name","SW", "-f",db);
        assertEquals(2,list.size());
        assertEquals("SW SMILE_SW SMIL",list.get(0).getName()+"_"+list.get(1).getName());

        list = sc.SearchEmployeeList("name","SEO", "-f",db);
        assertEquals(1,list.size());
        assertEquals("SEO JIOWN",list.get(0).getName());

    }
    @Test
    void searchByLastNameTest() {

        list = sc.SearchEmployeeList("name","LEE", "-l",db);
        assertEquals(4,list.size());
        int ans = 0;
        for(Employee emp: list){
            ans += Integer.parseInt(emp.getEmployeeNum());
        }
        assertEquals(155555554,ans);
    }

    @Test
    void searchByPhoneNumber(){

        list = sc.SearchEmployeeList("phoneNum","010-2222-3333", "",db);
        assertEquals(1,list.size());
        assertEquals("BLACKDEV LEE",list.get(0).getName());

        list = sc.SearchEmployeeList("phoneNum","010-7777-8588", "",db);
        assertEquals(4,list.size());
    }


    @Test
    void searchByPhoneNumberMidTest() {

        list = sc.SearchEmployeeList("phoneNum","4444", "-m",db);
        assertEquals(2,list.size());
        assertEquals("SW SMILE JUNHEUI LEE",list.get(0).getName() + " " + list.get(1).getName());

        list = sc.SearchEmployeeList("phoneNum","3333", "-m",db);
        assertEquals(1,list.size());
        assertEquals("LEE WHITEDEV",list.get(0).getName() );
    }

    @Test
    void searchByPhoneNumberLastTest() {

        list = sc.SearchEmployeeList("phoneNum","4444", "-l",db);
        assertEquals(1,list.size());
        assertEquals("LEE WHITEDEV",list.get(0).getName());

        list = sc.SearchEmployeeList("phoneNum","8588", "-l",db);
        assertEquals(4,list.size());
    }

    @Test
    void searchByClTest() {

        list = sc.SearchEmployeeList("cl","CL3", "",db);
        assertEquals(1,list.size());
        assertEquals("DOWON LEE",list.get(0).getName());

        list = sc.SearchEmployeeList("cl","CL4", "",db);
        assertEquals(4,list.size());

        list = sc.SearchEmployeeList("cl","CL2", "",db);
        assertEquals(5,list.size());
    }

    @Test
    void searchByBirthdayTest(){

        list = sc.SearchEmployeeList("birthday","22222220", "",db);
        assertEquals(1,list.size());
        assertEquals("22222222",list.get(0).getEmployeeNum());

        list = sc.SearchEmployeeList("birthday","22222221", "",db);
        assertEquals(0,list.size());
    }

    @Test
    void searchByBirthdayYearTest() {

        list = sc.SearchEmployeeList("birthday","8888", "-y",db);
        assertEquals(5,list.size());

        list = sc.SearchEmployeeList("birthday","2222", "-y",db);
        assertEquals(1,list.size());
        assertEquals(CAREERLEVEL.CL2,list.get(0).getCl());

    }

    @Test
    void searchByBirthdayMonthTest() {

        list = sc.SearchEmployeeList("birthday","44", "-m",db);
        assertEquals(2,list.size());

        list = sc.SearchEmployeeList("birthday","22", "-m",db);
        assertEquals(1,list.size());
        assertEquals("22222222",list.get(0).getEmployeeNum());
    }

    @Test
    void searchByBirthdayDayTest() {

        list = sc.SearchEmployeeList("birthday","44", "-m",db);
        assertEquals(2,list.size());

        list = sc.SearchEmployeeList("birthday","22", "-m",db);
        assertEquals(1,list.size());
        assertEquals("22222222",list.get(0).getEmployeeNum());
    }

    @Test
    void searchByCertiTest() {

        list = sc.SearchEmployeeList("certi","EX", "",db);
        assertEquals(1,list.size());
        assertEquals("SW SMIL",list.get(0).getName());

        list = sc.SearchEmployeeList("certi","ADV", "",db);
        assertEquals(8,list.size());

        list = sc.SearchEmployeeList("certi","PRO", " ",db);
        assertEquals(1,list.size());
        assertEquals("11111111",list.get(0).getEmployeeNum());
    }

}