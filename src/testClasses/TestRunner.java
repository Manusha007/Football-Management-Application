package testClasses;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args){
        Result result_1 = JUnitCore.runClasses(PremierLeagueManagerTest.class);
        for (Failure failure_1 : result_1.getFailures()) {
            System.out.println(failure_1.toString());
        }
    }
}
