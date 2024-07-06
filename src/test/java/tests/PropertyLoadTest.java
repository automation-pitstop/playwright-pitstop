package tests;

import org.testng.annotations.Test;

public class PropertyLoadTest extends TestBase{

    @Test
    public void verifyGlobalPropertyData(){
        testProperties.forEach((k,v)->{
            System.out.println(k + " : " + v);
        });
    }
}
