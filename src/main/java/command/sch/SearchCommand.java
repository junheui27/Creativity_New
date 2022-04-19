package command.sch;

import command.CommandExecutor;
import database.EmployeeDB;
import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class RequestObj {
    public String column;
    public String value;
    public String option;

    public RequestObj(String column, String value, String option) {
        this.column = column;
        this.value = value;
        this.option = option;
    }
}

public class SearchCommand implements CommandExecutor{

    @Override
    public List<Employee> run(UserRequest request, EmployeeDB db) throws Exception {
        List<Employee> resultEmpList = new ArrayList<>();

        if (isSearchCmd(request)) {
            RequestObj reqObj = makeReqObj(request);
            resultEmpList = SearchEmployeeList(reqObj, db);
        }
        else{
            throw new Exception("SCH 명령어가 아닙니다.");
        }

        return resultEmpList;
    }

    private RequestObj makeReqObj(UserRequest request) {
        String column = request.getArguments().get(0);
        String value = request.getArguments().get(1);
        String option = request.getOptions().get(1);

        return new RequestObj(column, value, option);
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

    List<Employee> SearchEmployeeList(RequestObj reqObj, EmployeeDB db) {

        List<Employee> searchedEmployees = searchByColumnValue(reqObj.column, reqObj.value, db);
        SearchFilter searchFilter = getEmployeeFilter(reqObj.column, reqObj.value, reqObj.option);
        List<Employee> filterdEmployees = searchFilter.process(searchedEmployees);

        return filterdEmployees;
    }

    private List<Employee> searchByColumnValue(String column, String value, EmployeeDB db) {
        return db.findEmployeeByColumn(column, value);
    }

}
