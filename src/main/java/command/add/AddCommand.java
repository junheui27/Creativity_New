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
    	CAREERLEVEL careerLevel = null;
    	if(EmployeeInfo.get(2).equals("CL1"))
    		careerLevel = CAREERLEVEL.CL1;
    	else if(EmployeeInfo.get(2).equals("CL2"))
    		careerLevel = CAREERLEVEL.CL2;
    	else if(EmployeeInfo.get(2).equals("CL3"))
    		careerLevel = CAREERLEVEL.CL3;
    	else if(EmployeeInfo.get(2).equals("CL4"))
    		careerLevel = CAREERLEVEL.CL4;
    	
    	CERTI certi = null;
    	if(EmployeeInfo.get(5) == "ADV")
    		certi = CERTI.ADV;
    	else if(EmployeeInfo.get(5) == "PRO")
    		certi = CERTI.PRO;
    	else if(EmployeeInfo.get(5) == "EX")
    		certi = CERTI.EX;
    	
    	Employee employee = new Employee();
    	employee.setEmployeeNum(EmployeeInfo.get(0));
    	employee.setName(EmployeeInfo.get(1));
        employee.setFirstName(EmployeeInfo.get(1).split(" ")[0]);
        employee.setLastName(EmployeeInfo.get(1).split(" ")[1]);
        employee.setMiddlePhoneNumber(EmployeeInfo.get(3).split("-")[1]);
        employee.setLastPhoneNumer(EmployeeInfo.get(3).split("-")[2]);
        employee.setCl(careerLevel);
        employee.setBirthdayYear(EmployeeInfo.get(4).substring(0,4));
        employee.setBirthdayMonth(EmployeeInfo.get(4).substring(4, 6));
        employee.setBirthdayDay(EmployeeInfo.get(4).substring(6, 8));
        employee.setCerti(certi);
    	
        
        db.addEmployee(employee);
        
        List<Employee> employeeArr = new ArrayList<>();
        employeeArr.add(employee);
        
        return employeeArr;
    }
}
