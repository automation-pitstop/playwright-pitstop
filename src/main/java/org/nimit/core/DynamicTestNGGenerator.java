package org.nimit.core;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.Collections;

public class DynamicTestNGGenerator {
    public static void main(String[] args) {
//        if (args.length != 2) {
//            System.out.println("Usage: java DynamicTestNGGenerator <class name> <data parameter>");
//            return;
//        }

        String className = args[0];
//        String dataParameter = args[1];

        try {
            // Create TestNG suite
            XmlSuite suite = new XmlSuite();
            suite.setName("DynamicSuite");

            // Create TestNG test
            XmlTest test = new XmlTest(suite);
            test.setName("DynamicTest");

            // Create XmlClass
            XmlClass xmlClass = new XmlClass(className);
            test.setXmlClasses(Collections.singletonList(xmlClass));

            // Add parameter to test
//            test.addParameter("dataParameter", dataParameter);

            // Create TestNG instance and set suite
            TestNG testNG = new TestNG();
            testNG.setXmlSuites(Collections.singletonList(suite));

            // Run TestNG
            testNG.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
