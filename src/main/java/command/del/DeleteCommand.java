package command.del;

import command.CommandExecutor;
import command.sch.SearchCommand;
import database.EmployeeDB;
import model.Employee;
import model.UserRequest;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand implements CommandExecutor {
	final private CommandExecutor schCommand;

	public DeleteCommand(CommandExecutor schCommand) {
		this.schCommand = schCommand;
	}

	@Override
    public List<Employee> run(UserRequest request, EmployeeDB db) {
		/*        
         DEL, , , ,employeeNum,18115040
		 DEL,-p,-l, ,name,MPOSXU 
         
		 * 
		 * String printOption = options.get(0); // -p String nameOption =
		 * options.get(1); // -f : 성명의 이름으로, -l : 성명의 성, -m : 전화번호 중간자리 -l 전화번호 뒷자리
		 */
		
		
    	List<Employee> employeeList = new ArrayList<Employee>();
		try {
			employeeList = schCommand.run(request, db);
		} catch (Exception e) {
			System.out.println("DeleteCommand.run() Exception 발생");
			e.printStackTrace();
		}
        
    	for(int i = 0 ; i < employeeList.size(); i++) {
    		db.deleteEmployee(employeeList.get(i));
    	}
    
    	
    	return employeeList;
    }
}
