package command.sch;

import model.Employee;
import java.util.List;


public class SearchFilter implements SearchFilterI{

    protected String column;
    protected String value;
    protected String option;

     SearchFilter(String column, String value, String option){
        this.column = column;
        this.value = value;
        this.option = option;
    }

    public List<Employee> process(List<Employee> searchedEmployees) {
        return searchedEmployees;
    }

    @Override
    public String findValueByOption(String str) {
        return str;
    }

    @Override
    public List<Employee> searchByValue(List<Employee> searchedEmployees) {
        return searchedEmployees;
    }

}
