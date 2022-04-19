import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

public class BulkDataTest {

    private List<String> employeeNum;
    private List<String> name;
    private List<String> cl;
    private List<String> phone;
    private List<String> birth;
    private List<String> certi;
    private static List<String> inputs;
    private int MAX_DATA_COUNT = 100000;
    private File outputFile;


    @BeforeAll
    public static void initTest() {
        try{
            inputs = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("./bulkinput.txt"));
            String str2 = "";
            while((str2 = br.readLine())!=null){
                inputs.add(str2);
            }
            br.close();
        }
        catch(IOException e){

        }
    }

    @BeforeEach
    public void InitOutputFile(){
        String filePath = "output1.txt";
        outputFile = new File(filePath);
    }

    @AfterEach
    public void CloseOutputFile(){
        outputFile.delete();
    }
    @Test
    @DisplayName("add만 했을 때 출력 없음")
    public void bulkTest0() throws IOException {

        List<String> testInput = new ArrayList<>(inputs);
        List<String> inputResults = Main.run(testInput);
        Main.writeOutput(outputFile.getPath(), inputResults);

        List<String> results = Main.readInput(outputFile.getPath());
        Assertions.assertTrue(results.size()==0);
    }


    @Test
    @DisplayName("CL2를 검색하여 전화번호를 변경해 봄")
    public void bulkTest1() throws IOException {

        List<String> testInput = new ArrayList<>(inputs);

        testInput.add("SCH,,,,phoneNum,010-0000-0000");
        testInput.add("SCH,,,,cl,CL2");
        testInput.add("MOD,,,,cl,CL2,phoneNum,010-0000-0000");
        testInput.add("SCH,,,,phoneNum,010-0000-0000");

        List<String> inputResults = Main.run(testInput);
        Main.writeOutput(outputFile.getPath(), inputResults);

        List<String> results = Main.readInput(outputFile.getPath());
        Assertions.assertTrue(results.get(0).contains("SCH,None"));
        Assertions.assertTrue(results.get(1).contains("SCH,25165"));
        Assertions.assertTrue(results.get(2).contains("MOD,25165"));
        Assertions.assertTrue(results.get(3).contains("SCH,25165"));
    }

    @Test
    @DisplayName("name -f을 검색하여 변경 후 삭제")
    public void bulkTest2() throws IOException {

        List<String> testInput = new ArrayList<>(inputs);

        testInput.add("SCH,,-f,,name,WORH");
        testInput.add("SCH,,,,cl,CL4");
        testInput.add("MOD,-p,-f,,name,WORH,cl,CL4");
        testInput.add("SCH,,,,cl,CL4");
        testInput.add("DEL,-p,,,cl,CL4");
        testInput.add("SCH,,,,cl,CL4");

        List<String> inputResults = Main.run(testInput);
        Main.writeOutput(outputFile.getPath(), inputResults);

        List<String> results = Main.readInput(outputFile.getPath());
        Assertions.assertTrue(results.get(0).contains("SCH,1"));
        Assertions.assertTrue(results.get(1).contains("SCH,24887"));
        Assertions.assertTrue(results.get(3).contains("SCH,24888"));
        Assertions.assertTrue(results.get(9).contains("SCH,None"));
    }

    @Test
    @DisplayName("name -l을 검색하여 변경 후 삭제")
    public void bulkTest9() throws IOException {

        List<String> testInput = new ArrayList<>(inputs);

        testInput.add("SCH,,-l,,name,WORH");
        testInput.add("SCH,,,,certi,EX");
        testInput.add("MOD,-p,-l,,name,WORH,certi,EX");
        testInput.add("SCH,,,,certi,EX");
        testInput.add("DEL,-p,-l,,name,WORH");
        testInput.add("SCH,,-l,,name,WORH");

        List<String> inputResults = Main.run(testInput);
        Main.writeOutput(outputFile.getPath(), inputResults);

        List<String> results = Main.readInput(outputFile.getPath());
        Assertions.assertTrue(results.get(0).contains("SCH,None"));
        Assertions.assertTrue(results.get(1).contains("SCH,33262"));
        Assertions.assertTrue(results.get(2).contains("MOD,None"));
        Assertions.assertTrue(results.get(3).contains("SCH,33262"));
        Assertions.assertTrue(results.get(4).contains("DEL,None"));
        Assertions.assertTrue(results.get(5).contains("SCH,None"));
    }

    @Test
    @DisplayName("phoneNum -m 검색하여 변경 및 삭제")
    void bulkTest3() throws IOException {

        List<String> testInput = new ArrayList<>(inputs);

        testInput.add("SCH,,-m,,phoneNum,8423");
        testInput.add("MOD,,-m,,phoneNum,8423,name,CHOSEN PEOPLE");
        testInput.add("SCH,,-m,,phoneNum,8423");
        testInput.add("SCH,,,,name,CHOSEN PEOPLE");

        List<String> inputResults = Main.run(testInput);
        Main.writeOutput(outputFile.getPath(), inputResults);

        List<String> results = Main.readInput(outputFile.getPath());
        Assertions.assertTrue(results.get(0).contains("SCH,13"));
        Assertions.assertTrue(results.get(1).contains("MOD,13"));
        Assertions.assertTrue(results.get(2).contains("SCH,13"));
        Assertions.assertTrue(results.get(3).contains("SCH,13"));
    }

    @Test
    @DisplayName("존재하는 사번을 입력하려고 함")
    void bulkTest4() throws IOException {

        List<String> testInput = new ArrayList<>(inputs);

        testInput.add("ADD,,,,69358690,NNAI DOKR,CL3,010-5842-3944,19200910,ADV");
        testInput.add("ADD,,,,69358700,BQJO XMGM,CL3,010-1636-0817,19501018,PRO");
        testInput.add("ADD,,,,69358710,RVXE ACHJ,CL2,010-0378-7754,19890528,ADV");
        testInput.add("ADD,,,,69358720,WEWO JCEE,CL1,010-0814-2891,19710627,ADV");
        testInput.add("ADD,,,,69358730,HFYR XYSL,CL2,010-2410-9613,19311115,EX");

        testInput.add("SCH,,,,employeeNum,69358690");
        testInput.add("SCH,,,,employeeNum,69358700");
        testInput.add("SCH,,,,employeeNum,69358710");
        testInput.add("SCH,,,,employeeNum,69358720");
        testInput.add("SCH,,,,employeeNum,69358730");

        List<String> inputResults = Main.run(testInput);
        Main.writeOutput(outputFile.getPath(), inputResults);

        List<String> results = Main.readInput(outputFile.getPath());
        Assertions.assertTrue(results.get(0).contains("SCH,1"));
        Assertions.assertTrue(results.get(1).contains("SCH,1"));
        Assertions.assertTrue(results.get(2).contains("SCH,1"));
        Assertions.assertTrue(results.get(3).contains("SCH,1"));
        Assertions.assertTrue(results.get(4).contains("SCH,1"));
    }



    @Test
    @DisplayName("phoneNum -l 으로 검색하여 변경하고 삭제")
    void bulkTest8() throws IOException {

        List<String> testInput = new ArrayList<>(inputs);

        testInput.add("SCH,,-l,,phoneNum,5294");
        testInput.add("SCH,-p,-l,,name,RZLJ");
        testInput.add("MOD,,-l,,phoneNum,5294,name,RZLJ RZLJ");
        testInput.add("SCH,,,,name,RZLJ RZLJ");
        testInput.add("SCH,,-l,,name,RZLJ");
        testInput.add("SCH,,-f,,name,RZLJ");

        List<String> inputResults = Main.run(testInput);
        Main.writeOutput(outputFile.getPath(), inputResults);

        List<String> results = Main.readInput(outputFile.getPath());
        Assertions.assertTrue(results.get(0).contains("SCH,9"));
        Assertions.assertTrue(results.get(2).contains("MOD,9"));
        Assertions.assertTrue(results.get(3).contains("SCH,9"));
        Assertions.assertTrue(results.get(4).contains("SCH,10"));
    }

    @Test
    @DisplayName("birth -m으로 검색하여 변경하고 삭제")
    void bulkTest5() throws IOException {

        List<String> testInput = new ArrayList<>(inputs);

        testInput.add("SCH,,-m,,birthday,06");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("MOD,,-m,,birthday,06,phoneNum,010-1234-5678");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("DEL,,,,phoneNum,010-1234-5678");
        testInput.add("ADD,,,,69000200,BEKV IKKL,CL4,010-1739-8142,19600628,ADV");
        testInput.add("ADD,,,,69000270,QPLC REVN,CL4,010-7539-8559,19560601,EX");
        testInput.add("ADD,,,,69000410,MVZD NAFE,CL4,010-8713-1928,19190613,ADV");
        testInput.add("ADD,,,,69000460,EIBW HWBJ,CL4,010-6497-8206,19060620,EX");
        testInput.add("ADD,,,,69000520,DMGD FVWT,CL4,010-4775-3553,19310602,PRO");
        testInput.add("SCH,,-m,,birthday,06");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("MOD,,-m,,birthday,06,phoneNum,010-1234-5678");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("DEL,,,,phoneNum,010-1234-5678");
        testInput.add("ADD,,,,69000200,BEKV IKKL,CL4,010-1739-8142,19600628,ADV");
        testInput.add("ADD,,,,69000270,QPLC REVN,CL4,010-7539-8559,19560601,EX");
        testInput.add("ADD,,,,69000410,MVZD NAFE,CL4,010-8713-1928,19190613,ADV");
        testInput.add("ADD,,,,69000460,EIBW HWBJ,CL4,010-6497-8206,19060620,EX");
        testInput.add("ADD,,,,69000520,DMGD FVWT,CL4,010-4775-3553,19310602,PRO");
        testInput.add("SCH,,-m,,birthday,06");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("MOD,,-m,,birthday,06,phoneNum,010-1234-5678");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("DEL,,,,phoneNum,010-1234-5678");
        testInput.add("ADD,,,,69000200,BEKV IKKL,CL4,010-1739-8142,19600628,ADV");
        testInput.add("ADD,,,,69000270,QPLC REVN,CL4,010-7539-8559,19560601,EX");
        testInput.add("ADD,,,,69000410,MVZD NAFE,CL4,010-8713-1928,19190613,ADV");
        testInput.add("ADD,,,,69000460,EIBW HWBJ,CL4,010-6497-8206,19060620,EX");
        testInput.add("ADD,,,,69000520,DMGD FVWT,CL4,010-4775-3553,19310602,PRO");
        testInput.add("SCH,,-m,,birthday,06");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("MOD,,-m,,birthday,06,phoneNum,010-1234-5678");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("DEL,,,,phoneNum,010-1234-5678");
        testInput.add("ADD,,,,69000200,BEKV IKKL,CL4,010-1739-8142,19600628,ADV");
        testInput.add("ADD,,,,69000270,QPLC REVN,CL4,010-7539-8559,19560601,EX");
        testInput.add("ADD,,,,69000410,MVZD NAFE,CL4,010-8713-1928,19190613,ADV");
        testInput.add("ADD,,,,69000460,EIBW HWBJ,CL4,010-6497-8206,19060620,EX");
        testInput.add("ADD,,,,69000520,DMGD FVWT,CL4,010-4775-3553,19310602,PRO");

        List<String> inputResults = Main.run(testInput);
        Main.writeOutput(outputFile.getPath(), inputResults);

        List<String> results = Main.readInput(outputFile.getPath());
        Assertions.assertTrue(results.get(0).contains("SCH,8304"));
        Assertions.assertTrue(results.get(1).contains("SCH,None"));
        Assertions.assertTrue(results.get(2).contains("MOD,8304"));
        Assertions.assertTrue(results.get(3).contains("SCH,8304"));
        Assertions.assertTrue(results.get(4).contains("DEL,8304"));
        Assertions.assertTrue(results.get(5).contains("SCH,5"));
        Assertions.assertTrue(results.get(6).contains("SCH,None"));

    }

    @Test
    @DisplayName("birth -d 검색하여 변경하고 삭제")
    void bulkTest6() throws IOException {

        List<String> testInput = new ArrayList<>(inputs);

        testInput.add("SCH,,-d,,birthday,06");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("MOD,,-d,,birthday,06,phoneNum,010-1234-5678");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("DEL,,,,phoneNum,010-1234-5678");


        List<String> inputResults = Main.run(testInput);
        Main.writeOutput(outputFile.getPath(), inputResults);

        List<String> results = Main.readInput(outputFile.getPath());
        Assertions.assertTrue(results.get(0).contains("SCH,3163"));
        Assertions.assertTrue(results.get(1).contains("SCH,None"));
        Assertions.assertTrue(results.get(2).contains("MOD,3163"));
        Assertions.assertTrue(results.get(3).contains("SCH,3163"));
        Assertions.assertTrue(results.get(4).contains("DEL,3163"));
    }

    @Test
    @DisplayName("birth -y 검색하여 변경하고 삭제")
    void bulkTest7() throws IOException {

        List<String> testInput = new ArrayList<>(inputs);

        testInput.add("SCH,,-y,,birthday,1990");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("MOD,,-y,,birthday,1990,phoneNum,010-1234-5678");
        testInput.add("SCH,,,,phoneNum,010-1234-5678");
        testInput.add("DEL,,,,phoneNum,010-1234-5678");


        List<String> inputResults = Main.run(testInput);
        Main.writeOutput(outputFile.getPath(), inputResults);

        List<String> results = Main.readInput(outputFile.getPath());
        Assertions.assertTrue(results.get(0).contains("SCH,972"));
        Assertions.assertTrue(results.get(1).contains("SCH,None"));
        Assertions.assertTrue(results.get(2).contains("MOD,972"));
        Assertions.assertTrue(results.get(3).contains("SCH,972"));
        Assertions.assertTrue(results.get(4).contains("DEL,972"));
    }

    @Disabled
    @Test
    public void initTestData() throws IOException {
        Random random = new Random();
        generateEmployNum(random);
        generateName(random);
        generateCL(random);
        generatePhone(random);
        generateBirth(random);
        generateCerti(random);

        List<String> addCommands = new ArrayList<>();

        for (int i = 0; i < MAX_DATA_COUNT; i++) {
            StringJoiner joiner = new StringJoiner(",");
            joiner.add("ADD");
            joiner.add("");
            joiner.add("");
            joiner.add("");
            joiner.add(employeeNum.get(i));
            joiner.add(name.get(i));
            joiner.add(cl.get(i));
            joiner.add(phone.get(i));
            joiner.add(birth.get(i));
            joiner.add(certi.get(i));

            addCommands.add(joiner.toString());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("./" + "output.txt",true));

        for(String str2 : addCommands){
            bw.write(str2 + "\n");
        }
        bw.flush();
        bw.close();
    }

    private void generateCerti(Random random) {
        certi = new ArrayList<>();
        List<String> certiString = Arrays.asList("ADV","PRO","EX");
        for (int i = 0; i < MAX_DATA_COUNT; i++) {
            certi.add(certiString.get(random.nextInt(3)));
        }
    }

    private void generateBirth(Random random) {
        birth = new ArrayList<>();
        for (int i = 0; i < MAX_DATA_COUNT; i++) {
            int year = 1900 + random.nextInt(100);
            int month = random.nextInt(12)+1;
            int day = random.nextInt(31)+1;
            String monthString = month < 10 ? "0"+month : Integer.toString(month);
            String dayString = day < 10 ? "0"+day : Integer.toString(day);
            birth.add(year+monthString+dayString);
        }
    }

    private void generatePhone(Random random) {
        phone = new ArrayList<>();
        for (int i = 0; i < MAX_DATA_COUNT; i++) {
            String middle = getRandomNumberString(random,4);
            String last = getRandomNumberString(random,4);
            phone.add("010-"+middle+"-"+last);
        }
    }

    private void generateCL(Random random) {
        cl = new ArrayList<>();
        for (int i = 0; i < MAX_DATA_COUNT; i++) {
            cl.add("CL" + (random.nextInt(4)+1));
        }
    }

    private void generateName(Random random) {
        name = new ArrayList<>();
        for (int i = 0; i < MAX_DATA_COUNT; i++) {
            String firstName = getRandomString(random,4);
            String lastName = getRandomString(random,4);
            name.add(firstName + " " + lastName);
        }
    }

    private void generateEmployNum(Random random) {
        employeeNum = new ArrayList<>();
        int startEmployeeNum = 69000000;
        for (int i = 0; i < MAX_DATA_COUNT; i++) {
            employeeNum.add(Integer.toString(startEmployeeNum + + (i*10)) );
        }
    }

    private String getRandomNumberString(Random random, int length){
        return random.ints(48, 57 + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private String getRandomString(Random random, int length){
        return random.ints(65, 90 + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
