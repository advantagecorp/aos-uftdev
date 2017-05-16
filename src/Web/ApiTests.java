package Web;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.verifications.*;
import com.hp.lft.sdk.apitesting.uft.APITestResult;
import com.hp.lft.sdk.apitesting.uft.APITestRunner;

import unittesting.*;

public class ApiTests extends UnitTestClassBase {

    public ApiTests() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new ApiTests();
        globalSetup(ApiTests.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws GeneralLeanFtException {

        APITestResult result = APITestRunner.run("52.88.236.171:8080/catalog/api/v1/categories");
       //Map outParams = result.getOutParams();
        boolean status = result.getStatus();
    }

}