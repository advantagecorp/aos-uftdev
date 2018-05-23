package Web;

import com.hp.lft.report.*;
import com.hp.lft.sdk.web.*;
import org.junit.*;
import com.hp.lft.sdk.*;
import com.hp.lft.verifications.*;

import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import unittesting.*;

import java.awt.image.RenderedImage;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AdvantageSRFTest extends UnitTestClassBase {

    public static final String USERNAME = "WebUser1";
    public static final String PASSWORD = "HPEsw123";
    public static String SearchURL = "";
    public static String appURL = System.getProperty("url", "defaultvalue");
    //    public static String appURL2 = "52.32.172.3";
    public static String appURL2 = "52.38.138.5:8080";      // PRODUCTION updated
//	public static String appURL2 = "16.60.158.84";			// CI
//	public static String appURL2 = "16.59.19.163:8080";		// LOCALHOST
//	public static String appURL2 = "35.162.69.22:8080";		//
//	public static String appURL2 = "156.152.164.67:8080";	//
//	public static String appURL2 = "52.88.236.171";			// PRODUCTION
//    public static String appURL2 = "52.34.13.179:8080";     // QUALLY

    public static String browserTypeValue = System.getProperty("browser_type", "defaultvalue");
    public static String envTypeValue = System.getProperty("env_type", "local");
    //public String osValue = System.getProperty("os", "Windows");
    //public String versionValue = System.getProperty("version", "Windows");
    public BrowserType browserType;

    protected static Browser browser;

    private static long startTimeAllTests;
    private long startTimeCurrentTest;
    private static long elapsedTimeAllTests;
    private long elapsedTimeCurrentTest;

    public AdvantageStagingAppModel appModel;
    private RenderedImage img;


    public AdvantageSRFTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @Rule
    public TestName curTestName = new TestName();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        ModifiableReportConfiguration reportConfig = ReportConfigurationFactory.createDefaultReportConfiguration();
        reportConfig.setOverrideExisting(true);
//        reportConfig.setTargetDirectory("RunResults"); // The folder must exist under C:\
//        reportConfig.setReportFolder("WebTests");
        reportConfig.setTitle("WEB TESTS REPORT");
//        reportConfig.setDescription("Report Description");
        reportConfig.setSnapshotsLevel(CaptureLevel.All);

        Reporter.init(reportConfig);

        startTimeAllTests = System.currentTimeMillis();
        instance = new AdvantageSRFTest();
        globalSetup(AdvantageSRFTest.class);

        Print("browserTypeValue: " + browserTypeValue);
        Print("envTypeValue: " + envTypeValue);
        Print("appURL: " + appURL);
//        Print("appURL2: " + appURL2);

        Print("Wait for CI to be ready... 2 min");
        Thread.sleep(120000);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        //browser.navigate("./RunResults/runresults.html");
        globalTearDown();
        elapsedTimeAllTests = System.currentTimeMillis() - startTimeAllTests;
        printTimeWholeTests(elapsedTimeAllTests);
    }

    @Before
    public void setUp() throws Exception {
        startTimeCurrentTest = System.currentTimeMillis();
        printCaptionTest(curTestName.getMethodName());
        initBeforeTest();
    }

    @After
    public void tearDown() throws Exception {
        // Close the browser
        browser.close();
        printEndOfTest(curTestName.getMethodName());
//        elapsedTimeCurrentTest = System.currentTimeMillis() - startTimeCurrentTest;
//        Print(String.valueOf((elapsedTimeCurrentTest / 1000F) / 60 + " min / "
//                + String.valueOf(elapsedTimeCurrentTest / 1000F) + " sec / "
//                + String.valueOf(elapsedTimeCurrentTest) + " millisec\n"));
    }

    // This internal method checks if a user is already signed in to the web site
    public boolean isSignedIn() {
        String loggedInUserName = getUsernameFromSignOutElement();
        if (loggedInUserName.isEmpty()) {
            Print("isSignedIn FALSE");
            return false;
        }
        Print("isSignedIn TRUE");
        return true;
    }

    public static void Print(String msg) {
        System.out.println(msg);
    }

    private static void printTimeWholeTests(Long millis) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("AdvantageWebTest done in: " + String.valueOf((elapsedTimeAllTests / 1000F) / 60 + " min"));
    }

    private static void printError(Exception e, String objName) {
        System.out.println("\n##################################################");
        System.out.println("ERROR: " + objName + "\n" + e.getMessage() +  "\n");
    }

    private static void printError(String errorMessage) {
        System.out.println("\n##################################################");
        System.out.println("ERROR: " + errorMessage);
    }

    private static void printCaptionTest(String nameOfTest) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println("START " + nameOfTest);
    }

    private static void printEndOfTest(String nameOfTest) {
        System.out.println("END " + nameOfTest);
    }

    //This method is an internal function that initializes the tests
    public void initBeforeTest() throws GeneralLeanFtException {
        // Launch the browser
        switch (browserTypeValue) {
            case "Chrome":
                browserType = BrowserType.CHROME;
                break;
            case "Firefox":
                browserType = BrowserType.FIREFOX;
                break;
            case "IE":
                browserType = BrowserType.INTERNET_EXPLORER;
                break;
            default:
                browserType = BrowserType.CHROME;
                break;
        }

        if (envTypeValue.equals("SRF")) {
            System.out.println("Starting to run Tests in SRF");
            browser = SrfLab.launchBrowser(new BrowserDescription.Builder().type(browserType).set("osType", "Windows").set("osVersion", "10").build());
        } else {
            System.out.println("Starting to run Tests in local env.");
            browser = BrowserFactory.launch(browserType);
        }

        if (appURL.equals("defaultvalue"))
            appURL = appURL2;


        Reporter.addRunInformation("URL", appURL);

        // Navigate to the store site
        browserNavigate(appURL);

        // Formulate the search URL
        if (SearchURL.isEmpty()) {
            SearchURL = browser.getURL();
            if (!SearchURL.endsWith("/")) {
                SearchURL = SearchURL + "/";
            }
            SearchURL = SearchURL + "search/";
        }

        if (curTestName.getMethodName().equals("verifySearchUsingURL")) {
            Print("Without this sleep url will be without '/#/' => error in verifySearchUsingURL");
            threadSleep(5000);
            SearchURL = browser.getURL();
            if (!SearchURL.endsWith("/")) {
                SearchURL = SearchURL + "/";
            }
            SearchURL = SearchURL + "search/";
        }

        // Instantiate the application model object
        appModel = new AdvantageStagingAppModel(browser);
    }

    // This internal method gets the username from the text that appears on the SignOutMainIconWebElement object
    public String getUsernameFromSignOutElement() {
        Print("getUsernameFromSignOutElement start");
        // Get the regular expression pattern from the Sign in Out object design time description
        String pattern = null;
        try {
            pattern = appModel.AdvantageShoppingPage().SignOutMainIconWebElement().getDescription().getInnerText().toString();
        } catch (GeneralLeanFtException e) {
//            printError(e);
//            Print("\nERROR: " + e.getMessage() +  "\n");
//            fail("GeneralLeanFtException: getUsernameFromSignOutElement");
            pattern = null;
        }
        // Get the actual inner text of the Sign in Out object during runtime
        String signInOutIconElementInnerText = getWebElementInnerText(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object
        Matcher m = r.matcher(signInOutIconElementInnerText);
        m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String loggedInUserName = m.group(1).trim();
        Print("getUsernameFromSignOutElement end loggedInUserName = '" + loggedInUserName + "'");
        return loggedInUserName;
    }

    private void browserNavigate(String navigateUrl) {
        Print("browser navigate to " + navigateUrl);
        try {
            browser.navigate(navigateUrl);
        } catch (GeneralLeanFtException e) {
            printError(e, "browser navigate() ERROR");
            fail("GeneralLeanFtException: browser navigate() ERROR");
        }
    }

    private void threadSleep(long millis) {
        try {
            Print("sleep " + millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Print("\nERROR: " + e.getMessage() +  "\n");
            fail("InterruptedException: failed to sleep for " + millis + " sec");
        }
    }

    private String getWebElementInnerText(WebElement webElement) {
        String result = "";
        try {
            result = webElement.getInnerText();
        } catch (GeneralLeanFtException e) {
            printError(e, webElement.getClass().getSimpleName());
            fail("GeneralLeanFtException: getInnerText from element " + webElement.getClass().getSimpleName());
        }
        return result;
    }

    /**
     * Sign in to the store
     * @return
     */
    public boolean signIn() throws GeneralLeanFtException, ReportException {
        Print("signIn() start");
        boolean isSignedIn = false;

        if (!isSignedIn()) {
            // Click the sign-in icon
            clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
            // Fill in the user name and password
            setValueEditField(appModel.AdvantageShoppingPage().UsernameLoginEditField(), USERNAME);
            setValueEditField(appModel.AdvantageShoppingPage().PasswordLoginEditField(), PASSWORD);
            // Check the Remember Me checkbox
            setCheckBox(appModel.AdvantageShoppingPage().RememberMeCheckBox(), true);
            // Click on sign in button
            clickWebElement(appModel.AdvantageShoppingPage().SIGNINButton());
            Print("Wait for closing login popup window");
            threadSleep(2000);
            isSignedIn = isSignedIn();
        }

        Print("signIn() end (isSignedIn = " + isSignedIn + " )");
        return isSignedIn;
    }

    private void clickWebElement(WebElement webElement) {
        Print("CLICKED " + webElement.getClass().getSimpleName());
        try {
            webElement.click();
        } catch (GeneralLeanFtException e) {
            printError(e, webElement.getClass().getSimpleName());
            try {
                Reporter.reportEvent("Error clicking on element", "Could not click on element: " + webElement.getClass().getSimpleName(), Status.Failed);
                Assert.assertTrue("Could not click on element: " + webElement.getClass().getSimpleName(), false);
            } catch (ReportException e1) {
                printError(e1, webElement.getClass().getSimpleName());
            }
            fail("GeneralLeanFtException: couldn't click on element " + webElement.getClass().getSimpleName());
        }
    }

    private void setValueEditField(EditField editField, String value) {
        Print("SET VALUE '" + value + "' to " + editField.getClass().getSimpleName());
        try {
            editField.setValue(value);
        } catch (GeneralLeanFtException e) {
            printError(e, editField.getClass().getSimpleName());
            fail("GeneralLeanFtException: setValue to element " + editField.getClass().getSimpleName());
        }
    }

    // This internal method generates a random username and adds it to the site
    // - When it gets any empty value as input, it adds a random username to the site
    // - When it gets a username and password as input, it adds a new user with these values
    // - The boolean isNegativeTest specifies if to perform a negative test or not, meaning, cannot register the user when the Register form is not properly filled
    public void createNewAccountEx(String pUserName, String pPassword, boolean isNegativeTest) throws GeneralLeanFtException, ReportException {
        // In order to create a new account, we must be signed out
        signOut();

        // Click the Sign in icon
        clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

        // Click the Create New Account link
        clickWebElement(appModel.AdvantageShoppingPage().CREATENEWACCOUNTLink());

        // Set the user name and submit the new account
        // Make sure that the user name does not exist
        //int triesNumber = 5;
        //boolean newUserCreated = false;
        String username = pUserName;
        String password = pPassword;
        boolean getRandomName = true;

        // Get a random user name
        if (!pUserName.isEmpty()) {
            //triesNumber = 1;
            getRandomName = false;
        }

        if (getRandomName) {
            username = getRandomUserName();
            password = PASSWORD;
        }

        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountUsernameWebEdit(), username);

        // Fill the Create Account form

        if (!isNegativeTest) { // Do not fill the mail field in a negative test
            setValueEditField(appModel.AdvantageShoppingPage().CreateAccountEmailEditField(), "john@hpe.com");
        }

        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPasswordEditField(), password);

        if (!isNegativeTest) { // Do not confirm the password in a negative test
            setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPasswordConfirmEditField(), password);
        }

        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountFirstNameEditField(), "John");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountLastNameEditField(), "HPE");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPhoneNumberEditField(), "+97235399999");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountCityEditField(), "Yehud");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountAddressEditField(), "Shabazi 19");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPostalCodeEditField(), "56100");
        //appModel.AdvantageShoppingPage().CreateAccountReceiveOffersCheckBox().set(false);
        setCheckBox(appModel.AdvantageShoppingPage().CreateAccountAgreeToTermsCheckBox(), true);

        if (!isNegativeTest) {
            waitUntilElementExists(appModel.AdvantageShoppingPage().REGISTERButton());
            // Click the Register button
            clickWebElement(appModel.AdvantageShoppingPage().REGISTERButton());

            browserSync();
            waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

            String curGetUsernameFromSignOutElement = getUsernameFromSignOutElement();
            Print("'" + username + "' == '" + curGetUsernameFromSignOutElement + "'");

            Boolean result = username.equals(curGetUsernameFromSignOutElement);
            img = browser.getPage().getSnapshot();
            if (result) {
                Reporter.reportEvent("Verify username", "Names are the same", Status.Passed, img);
            } else {
                Reporter.reportEvent("Verify username", "Names are not the same", Status.Failed, img);
            }


            // Verify that the user name we added now appears in the inner text of the Sign In element
//            Verify.areEqual(username, curGetUsernameFromSignOutElement,
//                    "Verification - Create New Account", "  Verify that a new account was created successfully for user name: " + username + ".");
//            Assert.assertEquals("Verification - Create New Account:  Verify that a new account was created successfully for user name: " + username + ".",
//                    username, curGetUsernameFromSignOutElement);
        } else { // In a negative test, verify that the Register button is indeed disabled
            // Verify that a new account cannot be created successfully
            try {

                if (appModel.AdvantageShoppingPage().CreateAccountREGISTERNotValidWebElement().exists(2)) {
                    img = browser.getPage().getSnapshot();
                    Reporter.reportEvent("Verify Create New Account Negative test", "Verify that a new account cannot be created successfully.", Status.Passed, img);
                } else {
                    img = browser.getPage().getSnapshot();
                    Reporter.reportEvent("Verify Create New Account Negative test", "Verify that a new account cannot be created successfully.", Status.Failed, img);
                }

//                Verification(Verify.isFalse(appModel.AdvantageShoppingPage().CreateAccountREGISTERNotValidWebElement().exists(2), "Verification - Create New Account Negative test", "Verify that a new account cannot be created successfully."));
            } catch (GeneralLeanFtException e) {
                printError(e, "createNewAccountEx");
                fail("GeneralLeanFtException: createNewAccountEx");
            }
        }
    }

    //This internal method signs the user out
    public void signOut() {
        if (isSignedIn()) {
            // Click the sign-in icon
            clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
            // Click the sign out link
            clickWebElement(appModel.AdvantageShoppingPage().SignOutWebElement());
        }
    }

    //This internal method generates a random username
    public String getRandomUserName() {
        SecureRandom random = new SecureRandom();
        String randomString = new BigInteger(24, random).toString(16); // Generate a random string by hex number maximum 0xFFFFFF
        int len = 6 - randomString.length(); // Pad the string so it will contain 6 characters
        String padding = "";
        if (len > 0)
            padding = String.format("%0" + len + "d", 0);
        return "John_" + padding + randomString;
    }

    private void setCheckBox(CheckBox checkBox, Boolean value) {
        Print("SET '" + value + "' to " + checkBox.getClass().getSimpleName());
        try {
            checkBox.set(value);
        } catch (GeneralLeanFtException e) {
            printError(e, checkBox.getClass().getSimpleName());
            fail("GeneralLeanFtException: set '" + value + "' to element " + checkBox.getClass().getSimpleName());
        }
    }

    // This internal method waits until an object exists and visible
    public boolean waitUntilElementExists(WebElement webElement) {
        Print("WAIT UNTIL ELEMENT EXISTS " + webElement.getClass().getSimpleName());
        boolean result = false;
        try {
            result = WaitUntilTestObjectState.waitUntil(webElement, new WaitUntilTestObjectState.WaitUntilEvaluator<WebElement>() {
                public boolean evaluate(WebElement we) {
                    try {
                        return we.exists() && we.isVisible();
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
        } catch (GeneralLeanFtException e) {
            printError(e, webElement.getClass().getSimpleName());
            fail("GeneralLeanFtException: " + webElement.getClass().getSimpleName());
        }
        return result;
    }

    private void browserSync() {
        try {
            browser.sync();
        } catch (GeneralLeanFtException e) {
            printError(e, "browser.sync() error");
            fail("GeneralLeanFtException: browser.sync() error");
        }
    }

    /**
     * Adding main user if not exists
     * @return true - main user added, false - main user not added
     */
    @Test
    public void addMainUserIfNotExists() throws GeneralLeanFtException, ReportException {
        signIn();       // Sign in to the store

        if (!isSignedIn()) {
            clickWebElement(appModel.AdvantageShoppingPage().CloseSignInPopUpBtnWebElement());
            createNewAccountEx(USERNAME, PASSWORD, false);
        }
    }

    /**
     * This test purchases a 1000 of the first item in the Speakers category
     * Flow: Trying to purchase 1000 products. Web application shows message that maximum is 10 and continuing with 10
     */

    @Test
    public void test() throws GeneralLeanFtException {
    }

}