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
    public List<Employee> run(UserRequest request, EmployeeDB db) throws Exception {
    	if(request == null || db == null)
    		return new ArrayList();
    	
    	
    	//양식 : ADD,,,,18050301,KYUMOK KIM,CL2,010-9777-6055,19980906,PRO
		List<String> EmployeeInfo = request.getArguments();
		try{
			CAREERLEVEL careerLevel = null;
			if(EmployeeInfo.get(2).equals("CL1"))
				careerLevel = CAREERLEVEL.CL1;
			else if(EmployeeInfo.get(2).equals("CL2"))
				careerLevel = CAREERLEVEL.CL2;
			else if(EmployeeInfo.get(2).equals("CL3"))
				careerLevel = CAREERLEVEL.CL3;
			else if(EmployeeInfo.get(2).equals("CL4"))
				careerLevel = CAREERLEVEL.CL4;

			CERTI certi = initialiseCerti(EmployeeInfo);

			Employee employee = new Employee();
			employee.setEmployeeNum(EmployeeInfo.get(0));
			employee.setName(EmployeeInfo.get(1));
			employee.setPhoneNumber(EmployeeInfo.get(3));
			employee.setCl(careerLevel);
			employee.setBirthday(EmployeeInfo.get(4));
			employee.setCerti(certi);

			List<Employee> employeeArr = new ArrayList<Employee>();

			if(employee.getEmployeeNum().length() != 8) {
				return employeeArr;
			}

			if(!db.addEmployee(employee)) {
				return employeeArr;
			}

			employeeArr.add(employee);

			return employeeArr;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return new ArrayList<>();
    }

	private CERTI initialiseCerti(List<String> EmployeeInfo) {
		CERTI certi = CERTI.ADV;
    	if(EmployeeInfo.get(5).equals("ADV"))
    		certi = CERTI.ADV;
    	else if(EmployeeInfo.get(5).equals("PRO"))
    		certi = CERTI.PRO;
    	else if(EmployeeInfo.get(5).equals("EX"))
    		certi = CERTI.EX;
		return certi;
	}
}
