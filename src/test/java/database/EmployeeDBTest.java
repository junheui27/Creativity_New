package database;

import model.CAREERLEVEL;
import model.CERTI;
import model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmployeeDBTest {

    private EmployeeDB db;
    private Employee defaultEmployee;

    @BeforeEach
    public void resetDB(){
        db = new EmployeeDB();
        defaultEmployee = new Employee().builder()
                .employeeNum("17041177")
                .birthdayDay("01")
                .birthdayMonth("01")
                .birthdayYear("2000")
                .cl(CAREERLEVEL.CL2)
                .certi(CERTI.PRO)
                .firstName("whiteDev")
                .lastName("lee")
                .lastPhoneNumer("1111")
                .middlePhoneNumber("1111")
                .name("whiteDev lee")
                .build();
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
    @DisplayName("[normal] name 수정이 잘 되는지")
    public void modifyTest(){

        boolean ret = db.addEmployee(defaultEmployee);

        Employee employeeModified = new Employee().builder()
                .employeeNum("17041177")
                .birthdayDay("01")
                .birthdayMonth("01")
                .birthdayYear("2000")
                .cl(CAREERLEVEL.CL2)
                .certi(CERTI.PRO)
                .firstName("blackDev")
                .lastName("lee")
                .lastPhoneNumer("1111")
                .middlePhoneNumber("1111")
                .name("whiteDev lee")
                .build();

        db.modifyEmployee("17041177",employeeModified);

        Employee found = db.findEmployee("17041177");
        Assertions.assertEquals("blackDev",found.getFirstName());
    }
}
