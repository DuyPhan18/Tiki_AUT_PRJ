package stepDefinitions;

import core.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.MalformedURLException;

public class ServiceHook {
    @Before
    public void initializeTest() throws MalformedURLException {
        System.out.println("Initialing driver...");
        BaseTest.setUpDriver();
    }

    @After
    public void afterHookFunction(){
        System.out.println("tear down driver");
        BaseTest.tearDown();
    }
}
