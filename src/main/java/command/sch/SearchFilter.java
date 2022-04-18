package command.sch;

import model.Employee;
import java.util.List;


public class SearchFilter implements SearchFilterI{

    private String column;
    private String value;
    private String option;

     SearchFilter(String column, String value, String option){
        this.column = column;
        this.value = value;
        this.option = option;
    }

    @Override
    public List<Employee> process(List<Employee> searchedEmployees) {
        return searchedEmployees;
    }
}
