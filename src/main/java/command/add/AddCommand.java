package command.add;

import command.CommandExecutor;
import database.EmployeeDB;
import model.CAREERLEVEL;
import model.CERTI;
import model.Employee;
import model.UserRequest;

import java.util.ArrayList;
import java.util.List;

public class AddCommand implements CommandExecutor {
    @Override
    public List<Employee> run(UserRequest request, EmployeeDB db) {
    	if(request == null || db == null)
    		return new ArrayList();
    	
    	
    	//형식 : ADD,,,,18050301,KYUMOK KIM,CL2,010-9777-6055,19980906,PRO
    	List<String> EmployeeInfo = request.getArguments();
    	String EmployeeNum = EmployeeInfo.get(0);
    	String EmployeeName = EmployeeInfo.get(1);
    	String[] EmployeeFirstLastName = EmployeeName.split(" ");
    	String EmployeeLevel = EmployeeInfo.get(2);
    	String EmployeePhoneNum = EmployeeInfo.get(3);
    	String[] EmployeePhoneNums = EmployeePhoneNum.split("-");
    	String EmployeeBirth = EmployeeInfo.get(4);
    	String EmployeeBirthYear = EmployeeBirth.substring(0,4);
    	String EmployeeBirthMonth = EmployeeBirth.substring(4, 6);
    	String EmployeeBirthDay = EmployeeBirth.substring(6, 8);
    	String EmployeeCert = EmployeeInfo.get(5);
    	
    	CAREERLEVEL carrierLevel = null;
    	if(EmployeeLevel.equals("CL1"))
    		carrierLevel = CAREERLEVEL.CL1;
    	else if(EmployeeLevel.equals("CL2"))
    		carrierLevel = CAREERLEVEL.CL2;
    	else if(EmployeeLevel.equals("CL3"))
    		carrierLevel = CAREERLEVEL.CL3;
    	else if(EmployeeLevel.equals("CL4"))
    		carrierLevel = CAREERLEVEL.CL4;
    	
    	CERTI certi = null;
    	if(EmployeeCert == "ADV")
    		certi = CERTI.ADV;
    	else if(EmployeeCert == "PRO")
    		certi = CERTI.PRO;
    	else if(EmployeeCert == "EX")
    		certi = CERTI.EX;
    	
    	Employee employee = new Employee();
    	employee.setEmployeeNum(EmployeeNum);
    	employee.setName(EmployeeName);
        employee.setFirstName(EmployeeFirstLastName[0]);
        employee.setLastName(EmployeeFirstLastName[1]);
        employee.setMiddlePhoneNumber(EmployeePhoneNums[1]);
        employee.setLastPhoneNumer(EmployeePhoneNums[2]);
        employee.setCl(carrierLevel);
        employee.setBirthdayYear(EmployeeBirthYear);
        employee.setBirthdayMonth(EmployeeBirthMonth);
        employee.setBirthdayDay(EmployeeBirthDay);
        employee.setCerti(certi);
    	
        
        db.addEmployee(employee);
        
        List<Employee> temp = new ArrayList<>();
        temp.add(employee);
        
        return temp;
    }
}
