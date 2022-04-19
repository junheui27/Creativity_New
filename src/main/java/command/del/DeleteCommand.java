package command.del;

import command.CommandExecutor;
import command.sch.SearchCommand;
import database.EmployeeDB;
import model.COMMAND;
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
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			UserRequest requestTemp = new UserRequest();
			requestTemp.setArguments(request.getArguments());
			requestTemp.setCommand(COMMAND.SCH);
			requestTemp.setOptions(request.getOptions());
			employeeList = schCommand.run(requestTemp, db);
		} catch (Exception e) {
			System.out.println("DeleteCommand.run() Exception 발생");
			e.printStackTrace();
		}

		for (int i = 0; i < employeeList.size(); i++) {
			db.deleteEmployee(employeeList.get(i));
		}

		return employeeList;
	}
}
