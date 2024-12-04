package testcases;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsClass {

    @Test(groups = {"smoke"} , description = "verify the user profile",
    priority = 0, invocationCount = 1, timeOut = 9000, expectedExceptions = {ArithmeticException.class})
    public void test1() {
        System.out.println("Executing Smoke Test 1");
    }

    @Test(groups = {"smoke", "regression"})
    public void test2() {
        System.out.println("Executing Smoke and Regression Test 2");
    }

    @Test(groups = {"regression"})
    public void test3() {
        System.out.println("Executing Regression Test 3");
    }

    @Test(groups = {"sanity"})
    public void test4() {
        System.out.println("Executing Sanity Test 4");
    }


    @BeforeGroups("smoke")
    public void beforeSmokeGroup() {
        System.out.println("Setting up for Smoke tests");
    }

    @AfterGroups("smoke")
    public void afterSmokeGroup() {
        System.out.println("Tearing down Smoke tests");
    }
}
