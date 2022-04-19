package command.sch;

import command.CommandExecutor;
import database.EmployeeDB;
import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SearchCommand implements CommandExecutor{
    private String column = "";
    private String value = "";
    private String option = "";

    @Override
    public List<Employee> run(UserRequest request, EmployeeDB db) throws Exception {
        List<Employee> resultEmpList = new ArrayList<>();

        if (isSearchCmd(request)) {
            makeColValOpt(request);
            resultEmpList = SearchEmployeeList(column, value, option, db);
        }
        else{
            throw new Exception("SCH 명령어가 아닙니다.");
        }

        return resultEmpList;
    }

    private void makeColValOpt(UserRequest request){

        try {
            column = request.getArguments().get(0);
            value = request.getArguments().get(1);
            if(request.getOptions().size() > 1)
                option = request.getOptions().get(1);
        }
        catch (Exception e){
            System.out.println("Argument Input Error");
        }
    }

    private boolean isSearchCmd(UserRequest request) {
        return request.getCommand().equals(COMMAND.SCH);
    }

    private SearchFilter getEmployeeFilter(String column, String value, String option){

        if(column.equals("name"))
            return new EmployeeNameFilter(column, value, option);
        else if(column.equals("birthday"))
            return new EmployeeBirthdayFilter(column, value, option);
        else if(column.equals("phoneNum"))
            return new EmployeePhoneNumberFilter(column, value, option);
        else
            return new SearchFilter(column, value, option);
    }

    List<Employee> SearchEmployeeList(String column, String value, String option, EmployeeDB db) {

        List<Employee> searchedEmployees = searchByColumnValue(column, value, db);
        SearchFilter searchFilter = getEmployeeFilter(column, value, option);
        List<Employee> filterdEmployees = searchFilter.process(searchedEmployees);

        return filterdEmployees;
    }

    private List<Employee> searchByColumnValue(String column, String value, EmployeeDB db) {
        return db.findEmployeeByColumn(column, value);
    }

}
