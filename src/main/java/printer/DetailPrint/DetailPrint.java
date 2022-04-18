package printer.DetailPrint;

import model.CAREERLEVEL;
import model.CERTI;
import model.COMMAND;
import model.Employee;
import printer.IPrintOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class TempEmployee implements Comparable<TempEmployee>{
    private String tempEmployeeNum;
    private Employee originalEmployee;

    public TempEmployee(Employee employee){

        String num=employee.getEmployeeNum();
        if (num.compareTo("21999999")<=0){
            this.tempEmployeeNum = "2"+num;
        }
        else{
            this.tempEmployeeNum = "1"+num;
        }
        this.originalEmployee = employee;
    }

    //field별 원본객체의 field get 함수 추가
    public String getEmployeeNum(){
        return  originalEmployee.getEmployeeNum();
    }
    public String getTempEmployeeNum(){
        return  tempEmployeeNum;
    }
    public String getName(){
        return  originalEmployee.getName();
    }
    public String getPhoneNumber(){
        return originalEmployee.getPhoneNumber();
    }
    public CAREERLEVEL getCl(){
        return originalEmployee.getCl();
    }
    public String getBirthday(){
        return originalEmployee.getBirthday();
    }
    public  CERTI getCerti(){
        return originalEmployee.getCerti();
    }

    @Override
    public int compareTo(TempEmployee o) {
        return getTempEmployeeNum().compareTo(o.getTempEmployeeNum());
    }

}


public class DetailPrint implements IPrintOption {

    @Override
    public void print(COMMAND command,  List<Employee> results){
        if (command.equals(COMMAND.ADD)){return;}

        List<TempEmployee> tempList=new ArrayList<>();

        for (Employee employee:results){
            TempEmployee tempEmployee=new TempEmployee(employee);
            tempList.add(tempEmployee);
        }
        Collections.sort(tempList);

        //limit 5
        List<TempEmployee> limitList=tempList.stream().limit(5).collect(Collectors.toList());

        //print
        for(TempEmployee tempEmployee:limitList){

            String message=command+","
                    +tempEmployee.getEmployeeNum()+","
                    +tempEmployee.getName()+","
                    +tempEmployee.getCl()+","
                    +tempEmployee.getPhoneNumber()+","
                    +tempEmployee.getBirthday()+","
                    +tempEmployee.getCerti();
            System.out.println(message);

        }

    }
}
