package command.mod;

import command.CommandExecutor;
import database.EmployeeDB;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ModifyCommand implements CommandExecutor {
    final private CommandExecutor schCommand;

    public ModifyCommand(CommandExecutor schCommand) {
        this.schCommand = schCommand;
    }

    @Override
    public List<Employee> run(UserRequest request, EmployeeDB db) throws Exception {
        List<Employee> employees = searchEmployees(request, db);
        modifyEmployees(request, employees);
        return applyModifiedEmployeesToDB(db, employees);
    }

    private List<Employee> applyModifiedEmployeesToDB(EmployeeDB db, List<Employee> employees) {
        List<Employee> beforeModifiedEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            Employee beforeModified = db.modifyEmployee(employee.getEmployeeNum(),employee);
            beforeModifiedEmployees.add(beforeModified);
        }
        return beforeModifiedEmployees;
    }

    private void modifyEmployees(UserRequest request, List<Employee> employees) {
        request.setCommand(COMMAND.MOD);
        String columnToChange = request.getArguments().get(2);
        String newValue = request.getArguments().get(3);

        for (Employee employee : employees) {
            modifyFieldByColumn(employee,columnToChange,newValue);
        }
    }

    private void modifyFieldByColumn(Employee employee, String column, String newValue) {
        if(column.equals("name")){
            employee.setName(newValue);
        }
        else if(column.equals("phoneNum")){
            employee.setPhoneNumber(newValue);
        }
        else if(column.equals("birthday")){
            employee.setBirthday(newValue);
        }
        else if(column.equals("cl")){
            employee.setCl(CAREERLEVEL.valueOf(newValue));
        }
        else if(column.equals("certi")){
            employee.setCerti(CERTI.valueOf(newValue));
        }
    }

    private List<Employee> searchEmployees(UserRequest request, EmployeeDB db) throws Exception {
        request.setCommand(COMMAND.SCH);
        return this.schCommand.run(request, db);
    }
}
