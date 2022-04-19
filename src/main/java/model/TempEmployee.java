package model;


public class TempEmployee implements Comparable<TempEmployee>{
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
    public CERTI getCerti(){
        return originalEmployee.getCerti();
    }

    @Override
    public int compareTo(TempEmployee o) {
        return getTempEmployeeNum().compareTo(o.getTempEmployeeNum());
    }

}