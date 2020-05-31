package wework.testcase;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

/**
 * TODO JUnit5不支持这种方式：@RunWith(JUnitPlatform.class)
 * https://junit.org/junit5/docs/current/user-guide/#running-tests-junit-platform-runner-test-suite
 */
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("contact suit test")
@SelectClasses({
        wework.testcase.ContactTest.class
})
public class Suit {
}
