package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryTest {

    static int count = 0;

    @Test
    public void flakyTest() {
        count++;
        System.out.println("Execution count: " + count);
        Assert.assertTrue(count == 3, "Failing test to check retry mechanism.");
    }
}
