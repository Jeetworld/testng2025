package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataParam {

    @DataProvider(name = "dataProviderName")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"data1", "data2", "id1"},
                {"data3", "data4", "id3"}
        };
    }



    @Test(dataProvider = "dataProviderName", groups = "SmokeTesting")
    public void testMethod(String param1, String param2, String param3) {
        System.out.println("Parameter 1: " + param1 + ", Parameter 2: " + param2 + "Param 3 "+param3);
    }

    @Test
    @Parameters({"username", "password"})
    public void loginTest(String username, String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }


}
