package Web;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.WaitUntilTestObjectState.WaitUntilEvaluator;
import com.hp.lft.sdk.web.*;
import com.hp.lft.verifications.Verify;

import unittesting.*;

import static org.junit.Assert.fail;

// Make sure the tests run at the ascending alphabet name order (JUnit 4.11 and above)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AdvantageWebTest extends UnitTestClassBase {

    public static final String USERNAME = "johnhpe1";
    public static final String PASSWORD = "HPEsw123";
    public static String SearchURL = "";
    public static String appURL = System.getProperty("url", "defaultvalue");
//    public static String appURL2 = "52.32.172.3";
	public static String appURL2 = "16.60.158.84";			// CI
//	public static String appURL2 = "16.59.19.163:8080";		// LOCALHOST
//	public static String appURL2 = "35.162.69.22:8080";		//
//	public static String appURL2 = "156.152.164.67:8080";	//
//	public static String appURL2 = "52.88.236.171";			// PRODUCTION
//    public static String appURL2 = "52.34.13.179:8080";     // QUALLY

    public String browserTypeValue = System.getProperty("browser_type", "defaultvalue");
    public String envTypeValue = System.getProperty("env_type", "local");
    //public String osValue = System.getProperty("os", "Windows");
    //public String versionValue = System.getProperty("version", "Windows");
    public BrowserType browserType;

    protected static Browser browser;

    private static long startTimeAllTests;
    private long startTimeCurrentTest;
    private static long elapsedTimeAllTests;
    private long elapsedTimeCurrentTest;

    public AdvantageStagingAppModel appModel;

    public AdvantageWebTest() {

    }

    @Rule
    public TestName curTestName = new TestName();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        startTimeAllTests = System.currentTimeMillis();
        instance = new AdvantageWebTest();
        globalSetup(AdvantageWebTest.class);
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
        waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
        String loggedInUserName = getUsernameFromSignOutElement();
        if (loggedInUserName.isEmpty())
            return false;
        return true;
    }

    public static void Print(String msg) {
        System.out.println(msg);
    }

    private String getWebElementInnerText(WebElement webElement) {
        String result = "";
        try {
            result = webElement.getInnerText();
        } catch (GeneralLeanFtException e) {
            fail("GeneralLeanFtException: " + webElement);
            e.printStackTrace();
        }
        return result;
    }

    // This internal method gets the username from the text that appears on the SignOutMainIconWebElement object
    public String getUsernameFromSignOutElement() {
        Print("getUsernameFromSignOutElement start");
        // Get the regular expression pattern from the Sign in Out object design time description
        String pattern = null;
        try {
            pattern = appModel.AdvantageShoppingPage().SignOutMainIconWebElement().getDescription().getInnerText().toString();
        } catch (GeneralLeanFtException e) {
            fail("GeneralLeanFtException: SignOutMainIconWebElement");
            e.printStackTrace();
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

    // This internal method gets the shipping costs from the text that appears on the SHIPPINGCostWebElement object
    public double getShippingCostFromShippingWebElement() throws GeneralLeanFtException {
        Print("getShippingCostFromShippingWebElement start");
        // Get the regular expression pattern from the SHIPPINGCostWebElement object design time description
        String pattern = appModel.AdvantageShoppingPage().SHIPPINGCostWebElement().getDescription().getInnerText().toString();
        // Get the actual inner text of the SHIPPINGCostWebElement object during runtime
        String SHIPPINGCostWebElementInnerText = appModel.AdvantageShoppingPage().SHIPPINGCostWebElement().getInnerText();

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object
        Matcher m = r.matcher(SHIPPINGCostWebElementInnerText);
        m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String shippingCostString = m.group(1).trim();

        // TODO: remove if test is working
//        double shippingCost = Double.parseDouble(shippingCostString);
//        return shippingCost;
        Print("getShippingCostFromShippingWebElement end");
        return Double.parseDouble(shippingCostString);
    }

    //This internal method signs the user in - a user must be in the system:
    //username - johnhpe1, password - HPEsw123

    /**
     * Sign in to the store
     * @return
     * @throws GeneralLeanFtException
     */
    public boolean signIn() {
        Print("signIn() start");
        boolean isSignedIn = true;

        if (!isSignedIn()) {
            // Click the sign-in icon
            clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
            waitUntilElementExists(appModel.AdvantageShoppingPage().SIGNINButton());
            // Fill in the user name and password
            setValueEditField(appModel.AdvantageShoppingPage().UsernameLoginEditField(), USERNAME);
            setValueEditField(appModel.AdvantageShoppingPage().PasswordLoginEditField(), PASSWORD);
            // Check the Remember Me checkbox
            setCheckBox(appModel.AdvantageShoppingPage().RememberMeCheckBox(), true);
            // Click on sign in button
            clickWebElement(appModel.AdvantageShoppingPage().SIGNINButton());

            threadSleep(2000);

            isSignedIn = isSignedIn();
            Verify.isTrue(isSignedIn, "Verification - Sign In", "Verify that the user " + USERNAME + " signed in properly.");
        }
        Print("signIn() end (isSignedIn = " + isSignedIn + " )");
        return isSignedIn;
    }

    private void setCheckBox(CheckBox checkBox, Boolean value) {
        Print("SET '" + value + "' to " + checkBox.getClass().getSimpleName());
        try {
            checkBox.set(value);
        } catch (GeneralLeanFtException e) {
            fail("GeneralLeanFtException: set '" + value + "' to element " + checkBox.getClass().getSimpleName());
            e.printStackTrace();
        }
    }

    private void setValueEditField(EditField editField, String value) {
        Print("SET VALUE '" + value + "' to " + editField.getClass().getSimpleName());
        try {
            editField.setValue(value);
        } catch (GeneralLeanFtException e) {
            fail("GeneralLeanFtException: setValue to element " + editField.getClass().getSimpleName());
            e.printStackTrace();
        }
    }

    private void clickWebElement(WebElement webElement) {
        Print("CLICKED " + webElement.getClass().getSimpleName());
        try {
            webElement.click();
        } catch (GeneralLeanFtException e) {
            fail("GeneralLeanFtException: click on element " + webElement.getClass().getSimpleName());
            e.printStackTrace();
        }
    }

    private void threadSleep(long millis) {
        try {
            Print("sleep " + millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    // This internal method gets a products category object and a product object and adds them to the cart
    // WebElementNodeBase productsCategory - 	the category object
    // ImageNodeBase product - 					the specific product object
    public void selectItemToPurchase(WebElement productsCategory, WebElement product, int productQuantity) {
        Print("selectItemToPurchase() start");
        // Pick the product's category
        threadSleep(2000);
        Print("productsCategory click");
        clickWebElement(productsCategory);
        threadSleep(2000);

        // Pick the specific product
        Print("product click");
        clickWebElement(product);
        threadSleep(1000);

        // Select the first non-selected available color for the product
        Print("ColorSelectorFirstWebElement click");
        clickWebElement(appModel.AdvantageShoppingPage().ColorSelectorFirstWebElement());
        threadSleep(1000);

        // If the quantity is more than 1, set this value in the quantity edit-field
        if (productQuantity != 1) {
            Print("QuantityOfProductWebEdit setValue" + Integer.toString(productQuantity));
            setValueEditField(appModel.AdvantageShoppingPage().QuantityOfProductWebEdit(), Integer.toString(productQuantity));
        }

        // Add it to the cart
        Print("ADDTOCARTButton click");
        clickWebElement(appModel.AdvantageShoppingPage().ADDTOCARTButton());
        threadSleep(2000);
        Print("selectItemToPurchase() end");
    }

    public void selectItemToPurchase(WebElement productsCategory, WebElement product) throws GeneralLeanFtException {
        selectItemToPurchase(productsCategory, product, 1); // The default product quantity is 1
    }

    // This method will checkout to the cart and pay for the cart content
    // The boolean fillCredentials specifies if to fill the credentials in the form or not
    public void checkOutAndPay(boolean fillCredentials) {
        Print("checkOutAndPay start");
        // Checkout the cart for purchase
        // Click the cart icon
        Print("CartIcon click");
        clickWebElement(appModel.AdvantageShoppingPage().CartIcon());
        // Click the checkout button
        Print("CHECKOUTHoverButton click");
        clickWebElement(appModel.AdvantageShoppingPage().CHECKOUTHoverButton());
        // Click Next to continue the purchase wizard
        Print("NEXTButton click");
        clickWebElement(appModel.AdvantageShoppingPage().NEXTButton());
        // Select the payment method
        Print("SafepayImage click");
        clickWebElement(appModel.AdvantageShoppingPage().SafepayImage());

        if (fillCredentials) {
            // Set the payment method user name
            Print("SafePayUsernameEditField setValue HPE123");
            setValueEditField(appModel.AdvantageShoppingPage().SafePayUsernameEditField(), "HPE123");
            // Set the payment method password
            Print("SafePayPasswordEditField setValue Aaaa1");
            setValueEditField(appModel.AdvantageShoppingPage().SafePayPasswordEditField(), "Aaaa1");
            // Set the Remember Me checkbox to true or false
            setCheckBox(appModel.AdvantageShoppingPage().SaveChangesInProfileForFutureUse(), true);
        }

        // Click the "Pay Now" button
        Print("PAYNOWButton click");
        clickWebElement(appModel.AdvantageShoppingPage().PAYNOWButton());

        waitUntilElementExists(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement());

        try {
            // Verify that the product was purchased
            if (fillCredentials)
                    Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2), "Verification - Product Purchase:", " Verify that the product was purchased successfully"));
            else
                Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2), "Verification - Product Purchase:", " Verify that the product was purchased successfully"));
        } catch (GeneralLeanFtException e) {
            e.printStackTrace();
        }
        Print("checkOutAndPay end");
    }

    public void checkOutAndPay() {
        checkOutAndPay(true); // Default value is true - fill credentials
    }

    public void checkOutAndPayMasterCredit(String cardnum, String CVV, String holdername, boolean save) {
        Print("checkOutAndPayMasterCredit start");
        // Checkout the cart for purchase
        // Click the cart icon
        Print("CartIcon().click()");
        clickWebElement(appModel.AdvantageShoppingPage().CartIcon());
        // Click the checkout button
        Print("CHECKOUTHoverButton().click()");
        clickWebElement(appModel.AdvantageShoppingPage().CHECKOUTHoverButton());
        // Click Next to continue the purchase wizard
        Print("NEXTButton().click()");
        clickWebElement(appModel.AdvantageShoppingPage().NEXTButton());
        // Select the payment method
        Print("MasterCreditImage().click()");
        clickWebElement(appModel.AdvantageShoppingPage().MasterCreditImage());

        // Set the card number
        Print("CardNumberEditField().setValue(" + cardnum);
        setValueEditField(appModel.AdvantageShoppingPage().CardNumberEditField(), cardnum);
        // Set the CVV number
        Print("CvvNumberEditField().setValue(" + CVV);
        setValueEditField(appModel.AdvantageShoppingPage().CvvNumberEditField(), CVV);
        // Set the card holder name
        Print("CardholderNameEditField().setValue(" + holdername);
        setValueEditField(appModel.AdvantageShoppingPage().CardholderNameEditField(), holdername);
        if (!save) {
            // Set the Remember Me checkbox to true or false
            Print("SaveMasterCreditCheckBox().set(false)");
            setCheckBox(appModel.AdvantageShoppingPage().SaveMasterCreditCheckBox(), false);
        }

        //appModel.AdvantageShoppingPage().NEXTButton().click();
        // Click the "Pay Now" button
        Print("PAYNOWButtonManualPayment().click()");
        clickWebElement(appModel.AdvantageShoppingPage().PAYNOWButtonManualPayment());

        Print("ThankYouForBuyingWithAdvantageWebElement()");
        waitUntilElementExists(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement(), 5000);

        // Verify that the product was purchased
        try {
            Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2), "Verification - Product Purchase MasterCredit:", " Verify that the product was purchased successfully with MasterCredit "));
        } catch (GeneralLeanFtException e) {
            fail("GeneralLeanFtException: appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2)");
            e.printStackTrace();
        }
        Print("checkOutAndPayMasterCredit end");
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

        if (envTypeValue == "SRF")
            browser = SrfLab.launchBrowser(new BrowserDescription.Builder().type(browserType).set("osType", "Windows").set("osVersion", "10").build());
        else
            browser = BrowserFactory.launch(browserType);

        if (appURL.equals("defaultvalue"))
            appURL = appURL2;

        Reporter.addRunInformation("URL", appURL);

        // Navigate to the store site
        browser.navigate(appURL);
        browser.sync();

        // Formulate the search URL
        if (SearchURL.isEmpty()) {
            SearchURL = browser.getURL();
            if (!SearchURL.endsWith("/")) {
                SearchURL = SearchURL + "/";
            }
            SearchURL = SearchURL + "search/";
        }

        // Instantiate the application model object
        appModel = new AdvantageStagingAppModel(browser);
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

    // This internal method generates a random username and adds it to the site
    // - When it gets any empty value as input, it adds a random username to the site
    // - When it gets a username and password as input, it adds a new user with these values
    // - The boolean isNegativeTest specifies if to perform a negative test or not, meaning, cannot register the user when the Register form is not properly filled
    public void createNewAccountEx(String pUserName, String pPassword, boolean isNegativeTest) {
        // In order to create a new account, we must be signed out
        signOut();

        // Click the Sign in icon
        Print("click SignOutMainIconWebElement()");
        clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

        // Click the Create New Account link
        Print("click CREATENEWACCOUNTLink");
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

        waitUntilElementExists(appModel.AdvantageShoppingPage().CreateAccountUsernameWebEdit());
        threadSleep(2000);
        Print("CreateAccountUsernameWebEdit setValue: " + username);
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountUsernameWebEdit(), username);

        // Fill the Create Account form

        if (!isNegativeTest) { // Do not fill the mail field in a negative test
            Print("CreateAccountEmailEditField setValue: john@hpe.com ");
            setValueEditField(appModel.AdvantageShoppingPage().CreateAccountEmailEditField(), "john@hpe.com");
        }

        Print("CreateAccountPasswordEditField setValue: " + password);
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPasswordEditField(), password);

        if (!isNegativeTest) { // Do not confirm the password in a negative test
            Print("CreateAccountPasswordConfirmEditField setValue: " + password);
            setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPasswordConfirmEditField(), password);
        }

        Print("CreateAccountFirstNameEditField setValue: " + "John");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountFirstNameEditField(), "John");
        Print("CreateAccountLastNameEditField setValue: " + "HPE");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountLastNameEditField(), "HPE");
        Print("CreateAccountPhoneNumberEditField setValue: " + "+97235399999");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPhoneNumberEditField(), "+97235399999");
        Print("CreateAccountCityEditField setValue: " + "Yehud");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountCityEditField(), "Yehud");
        Print("CreateAccountAddressEditField setValue: " + "Shabazi 19");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountAddressEditField(), "Shabazi 19");
        Print("CreateAccountPostalCodeEditField setValue: " + "56100");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPostalCodeEditField(), "56100");
        //appModel.AdvantageShoppingPage().CreateAccountReceiveOffersCheckBox().set(false);
        Print("CreateAccountAgreeToTermsCheckBox set: " + "true");
        setCheckBox(appModel.AdvantageShoppingPage().CreateAccountAgreeToTermsCheckBox(), true);

        if (!isNegativeTest) {
            waitUntilElementExists(appModel.AdvantageShoppingPage().REGISTERButton());
            // Click the Register button
            Print("REGISTERButton click");
            clickWebElement(appModel.AdvantageShoppingPage().REGISTERButton());

            threadSleep(2000);

            browserSync();
            waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

            // Verify that the user name we added now appears in the inner text of the Sign In element
            Verify.areEqual(username, getUsernameFromSignOutElement(), "Verification - Create New Account", "  Verify that a new account was created successfully for user name: " + username + ".");

            Assert.assertEquals("Verification - Create New Account:  Verify that a new account was created successfully for user name: " + username + ".", username, getUsernameFromSignOutElement());
        } else { // In a negative test, verify that the Register button is indeed disabled
            // Verify that a new account cannot be created successfully
            try {
                Verification(Verify.isFalse(appModel.AdvantageShoppingPage().CreateAccountREGISTERNotValidWebElement().exists(2), "Verification - Create New Account Negative test", "Verify that a new account cannot be created successfully."));
            } catch (GeneralLeanFtException e) {
                fail("GeneralLeanFtException: createNewAccountEx");
                e.printStackTrace();
            }
        }
    }

    // This internal method waits until an object exists and visible
    public boolean waitUntilElementExists(WebElement webElem) {
        boolean result = false;
        try {
            result = WaitUntilTestObjectState.waitUntil(webElem, new WaitUntilEvaluator<WebElement>() {
                public boolean evaluate(WebElement we) {
                    try {
                        return we.exists() && we.isVisible();
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
        } catch (GeneralLeanFtException e) {
            fail("GeneralLeanFtException: " + webElem.toString());
            e.printStackTrace();
        }
        return result;
    }

    public boolean waitUntilElementExists(WebElement webElem, long time) {
        boolean result = false;
        try {
            result = WaitUntilTestObjectState.waitUntil(webElem, new WaitUntilEvaluator<WebElement>() {
                public boolean evaluate(WebElement we) {
                    try {
                        return we.exists() && we.isVisible();
                    } catch (Exception e) {
                        return false;
                    }
                }
            }, time);
        } catch (GeneralLeanFtException e) {
            fail("GeneralLeanFtException: " + webElem.toString());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method empties the shopping cart
     * @throws GeneralLeanFtException
     */
    public void emptyTheShoppingCart() {
        if (!isCartEmpty()) {
            threadSleep(2000);

            Print("Empty the cart....");
            // Navigate to the cart
            Print("CartIcon click");
            clickWebElement(appModel.AdvantageShoppingPage().CartIcon());
            browserSync();

            // Get the rows number from the cart table
            int numberOfRowsInCart = 0;
            try {
                numberOfRowsInCart = appModel.AdvantageShoppingPage().CartTable().getRows().size();
            } catch (GeneralLeanFtException e) {
                fail("GeneralLeanFtException: emptyTheShoppingCart appModel.AdvantageShoppingPage().CartTable().getRows().size()");
                e.printStackTrace();
            }
            Print("nubmerOfRowsInCart = " + numberOfRowsInCart);
            int numberOfRelevantProductRowsInCart = numberOfRowsInCart - 3; // Removing the non-relevant rows number from our counter. These are the title etc.. and rows that do not represent actual products
            Print("numberOfRelevantProductRowsInCart = " + numberOfRelevantProductRowsInCart);

            // Iterate and click the "Remove" link for all products
            for (; numberOfRelevantProductRowsInCart > 0; numberOfRelevantProductRowsInCart--) {
//                waitUntilElementExists(appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement(), 10000);

                threadSleep(2000);

                Print("FirstRemoveItemFromCartLinkWebElement click");
                clickWebElement(appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement());
                threadSleep(2000);// Remove the top product from the cart
            }

            Print("cart is empty");
            //browser.refresh();
        }
    }

    // This internal method returns the number of items in the shopping cart by the inner text of the cart icon object
    public int getCartProductsNumberFromCartObjectInnerText() {
        Print("getCartProductsNumberFromCartObjectInnerText start");
        int productsNunberInCart = 0;

        // Get the regular expression pattern from the Cart icon object design time description
        //String pattern = appModel.AdvantageShoppingPage().LinkCartIcon().getInnerText();

        // Get the actual inner text of the Cart icon object during runtime
        String advantageCartIcontInnerText = getWebElementInnerText(appModel.AdvantageShoppingPage().LinkCartIcon());

        // Create a Pattern object
        //Pattern r = Pattern.compile(pattern);

        // Now create matcher object
        //Matcher m = r.matcher(advantageCartIcontInnerText);
        //m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String productsNunberInCartString = advantageCartIcontInnerText.split("[ ]+")[0];//m.group(1).trim();
        if (!productsNunberInCartString.isEmpty())
            productsNunberInCart = Integer.parseInt(productsNunberInCartString);

        Print("Product number in cart: " + productsNunberInCartString);
        Print("getCartProductsNumberFromCartObjectInnerText end");
        return productsNunberInCart;
    }

    // This internal method checks if the shopping cart is empty or not
    public boolean isCartEmpty() {
        if (getCartProductsNumberFromCartObjectInnerText() == 0)
            return true;
        return false;
    }

//    @Test
    public void testCISerer() throws GeneralLeanFtException, ReportException {
        addMainUserIfNotExists();
        negativeLoginTest();
        purchaseLaptopTest();
    }

    //This test method creates the default user to be used in the tests, if it already does not exist - a user must be in the system:
    //username - johnhpe1, password - HPEsw123

    /**
     * Adding main user if not exists
     * @return true - main user added, false - main user not added
     * @throws GeneralLeanFtException
     */
    @Test
    public void addMainUserIfNotExists() {
        // Sign in to the store
        signIn();

        if (!isSignedIn()) {
            Print("CloseSignInPopUpBtnWebElement click");
            clickWebElement(appModel.AdvantageShoppingPage().CloseSignInPopUpBtnWebElement());
            createNewAccountEx(USERNAME, PASSWORD, false);
        }
    }

    // This test purchases the first item in the Speakers category
    @Test
    public void purchaseSpeakersTest() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        threadSleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(); // Verification inside
    }

    private void browserSync() {
        try {
            browser.sync();
        } catch (GeneralLeanFtException e) {
            fail("GeneralLeanFtException: browser.sync() error");
            e.printStackTrace();
        }
    }

    /**
     * This test purchases a 1000 of the first item in the Speakers category
     * Flow: Trying to purchase 1000 products. Web application shows message that maximum is 10 and continuing with 10
     * @throws GeneralLeanFtException
     */
    @Test
    public void purchase1000SpeakersNegativeTest() {
        addMainUserIfNotExists();
        if (!isSignedIn())
            signIn();

        threadSleep(2000);

        // Empty the shopping cart
        emptyTheShoppingCart();

        threadSleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click: ");
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage(), 1000);
        } catch (Exception e) {
            fail("Exception when selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage(), 1000)");
            e.printStackTrace();
        }

        browserSync();

        threadSleep(2000);

        // Verify that the quantity that was actually added to the cart is 10 and not a 1000
        int productsQuantityInCart = getCartProductsNumberFromCartObjectInnerText();
        try {
            Verification(Verify.isTrue(productsQuantityInCart == 10, "Verification - Purchase 1000 Speakers negative test", " Verify that you cannot buy a 1000 items. You can only buy 10 max."));
        } catch (GeneralLeanFtException e) {
            fail("GeneralLeanFtException Verify.isTrue");
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(); // Verification inside
    }

    // This test verifies that the shipping costs is free when purchasing 1 item
    // 	and that the shipping cost for 4 items is not free
    @Test
    public void verifyShippingCostsTest() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        double shippingCost = 0;

        // Purchase 1 item

        // Empty the shopping cart
        emptyTheShoppingCart();

        threadSleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Navigate to the shopping cart table
        Print("LinkCartIcon click");
        appModel.AdvantageShoppingPage().LinkCartIcon().click();
        // Navigate to the check-out page
        Print("CHECKOUTHoverButton click");
        appModel.AdvantageShoppingPage().CHECKOUTHoverButton().click();

        shippingCost = getShippingCostFromShippingWebElement();

        Print("shippingCost = " + shippingCost);
        Print("shippingCost == 0.0 ?");
        // Verify that the shipping costs are for free
        Verification(Verify.isTrue(shippingCost == 0.0, "Verification - shipping costs", " Verify that the shipping costs for 1 item are free."));

        // Purchase 4 items

        // Empty the shopping cart
        emptyTheShoppingCart();

        threadSleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage(), 4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Navigate to the shopping cart table
        Print("LinkCartIcon click");
        appModel.AdvantageShoppingPage().LinkCartIcon().click();
        // Navigate to the check-out page
        Print("CHECKOUTHoverButton click");
        appModel.AdvantageShoppingPage().CHECKOUTHoverButton().click();

        shippingCost = getShippingCostFromShippingWebElement();

        Print("shippingCost = " + shippingCost);
        Print("shippingCost > 0.0 ?");

        // Verify that the shipping costs are for free
        Verification(Verify.isTrue(shippingCost > 0.0, "Verification - shipping costs", " Verify that the shipping costs for 4 item are NOT free."));
    }

    // This test purchases the first item in the Speakers category
    // It does the purchase twice:
    // 1st time - it fills all the details in the payment form and marks the Save Changes In Profile For Future Use to be true
    // 2nd time - tries to do the payment without filling the credentials. If succeeded, it means that the fields values were remembered and used correctly
    @Test
    public void verifySaveChangesInProfilePaymentTest() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        // Empty the cart
        emptyTheShoppingCart();

        // 1st time purchase

        threadSleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(); // Fill credentials. Verification inside

        // 2nd time purchase

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(false); // Do not fill credentials. Verification inside
    }

    // This test purchases the first item in the Tablets category
    @Test
    public void purchaseTabletTest() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        threadSleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().TabletsImgWebElement(), appModel.AdvantageShoppingPage().HPProTablet608G1());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(); // Verification inside
    }

    // This test purchases the first item in the Laptops category
    @Test
    public void purchaseLaptopTest() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        threadSleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().LAPTOPSShopNowWebElement(), appModel.AdvantageShoppingPage().HPENVY17tTouchLaptop());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPayMasterCredit("123412341234", "774", USERNAME, false); // Verification inside
    }

    // This test purchases the first item in the Mice category
    @Test
    public void purchaseMouseTest() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        threadSleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().MICEShopNowWebElement(), appModel.AdvantageShoppingPage().LogitechG502ProteusCore7());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPayMasterCredit("123412341234", "774", USERNAME, false); // Verification inside
    }

    // This test purchases the first item in the Headphones category
    @Test
    public void purchaseHeadphonesTest() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        threadSleep(2000);

        // Go to home page
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().HEADPHONESShopNowWebElement(), appModel.AdvantageShoppingPage().HPH2310InEarHeadset());
            // Pay for the item
            checkOutAndPay(); // Verification inside
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This test verifies that the Contact Us form filling and sending works
    //@Test todo: an error accrues when trying to execute  'select'
    public void contactUsTest() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        // Go to home page
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Fill in the Contact Us form
        appModel.AdvantageShoppingPage().CONTACTUSMainWebElement().click();
        appModel.AdvantageShoppingPage().SelectProductLineContactUsListBox().select(4);
        appModel.AdvantageShoppingPage().SelectProductListBox2().select(2);
        appModel.AdvantageShoppingPage().EmailContactUsWebElement().setValue("john@hpe.com");
        appModel.AdvantageShoppingPage().ContactUsSubject().setValue("Thank you");

        // Submit the form by sending it
        appModel.AdvantageShoppingPage().SENDContactUsButton().click();

        waitUntilElementExists(appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement());
        // Verify that the support request was sent successfully
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement().exists(2), "Verification - Contact Us", " Verify that the support request was sent successfully"));
    }

    // This test purchases the first item in the popular items list
    @Test
    public void popularItemPurchaseFirst() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        threadSleep(2000);

        // Go to home page
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        try {
            //selectItemToPurchase(appModel.AdvantageShoppingPage().POPULARITEMSMainWebElement(), appModel.AdvantageShoppingPage().SpecialOfferViewDetailsItem1Link());
            appModel.AdvantageShoppingPage().SpecialOfferViewDetailsItem1Link().click();
        } catch (GeneralLeanFtException e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(); // Verification inside
    }

    // This test purchases the first item in the special offer items list
    @Test
    public void specialOfferPurchase() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        threadSleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        selectItemToPurchase(appModel.AdvantageShoppingPage().SPECIALOFFERMainWebElement(), appModel.AdvantageShoppingPage().SEEOFFERButton());

        // Pay for the item
        checkOutAndPay(); // Verification inside
    }

    // This test method creates a random user and adds it to the site
    @Test
    public void createNewAccount() throws GeneralLeanFtException, ReportException {
        createNewAccountEx("", "", false);
    }

    // This test makes a negative test for registering a new user
    @Test
    public void createNewAccountNegative() throws GeneralLeanFtException, ReportException {
        createNewAccountEx("", "", true);
    }

    // This test starts a chat with the support of the site
    //TODO: check why this test pass successfully on local LeanFT but crash on CI
    //@Test
    public void contactUsChatTest() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        threadSleep(4000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        threadSleep(5000);

        // Click the Contact Us link
        Print("CONTACTUSMainWebElement click");
        appModel.AdvantageShoppingPage().CONTACTUSMainWebElement().click();

        threadSleep(5000);

        // Click the Chat With Us link
        Print("ChatLogoImage().click()");
        appModel.AdvantageShoppingPage().ChatLogoImage().click();

        threadSleep(5000);
        // IMPORTANT: Make sure to enable pop-up messages from this site in BROWSER

        // Verify that the chat window has opened
        // Close the pop up message browser
        Browser chatBrowser;
        BrowserDescription chatBrowserDescription = new BrowserDescription();
        chatBrowserDescription.setTitle("Advantage Online Shopping Demo Support Chat");

        try {
            chatBrowser = BrowserFactory.attach(chatBrowserDescription);
            String brURL = chatBrowser.getURL();
            threadSleep(1000);
            Verification(Verify.isTrue(brURL.matches(".*/chat\\.html.*"), "Verification - Contact Us Chat", " Verify that the browser navigated to the chat URL"));
            chatBrowser.close();
        } catch (Exception e) {
            Reporter.reportEvent("contact Us Chat", "Could not locate the pop up chat browser", Status.Failed);
            Assert.assertTrue("Verification - Contact Us Chat: The chat window was not created", false);
        }
    }

    // This internal method gets a regular expression pattern and tries to match it to any title of the current open browsers
    // The browsers are from the type defined in the test
    // Returns the actual title of the located browser
    public String getBrowserRegExTitleFromBrowsersList(String regExTitlePattern) throws GeneralLeanFtException {
        BrowserDescription desc = new BrowserDescription();
        desc.setType(browserType);
        Browser[] allOpenBrowsers = BrowserFactory.getAllOpenBrowsers(desc);

        // Create a Pattern object
        Pattern r = Pattern.compile(regExTitlePattern);

        int length = allOpenBrowsers.length;
        for (int index = length - 1; index >= 0; index--) {
            String curTitle = allOpenBrowsers[index].getTitle();

            if (curTitle != null) {
                // Now create matcher object
                Matcher m = r.matcher(curTitle);
                //m.matches();

                if (m.find())
                    return curTitle;
            }
        }
        return "";
    }

    // This test verifies that the social media links work properly by clicking them
    @Test
    public void verifySocialMedia() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        threadSleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        String brURL = " ";
        String socialLink = " ";

        String Facebooktitle = "HP Application Lifecycle Management | Facebook";
        String Twittertitle = "HPE ALM (@HPE_ALM) | Twitter";
        String Linkedintitle = "HPE Software | LinkedIn";

        try {
            // Verify the Facebook link

            // Clicking te link opens a new browser tab
            // We attach to it and verify its title and URL are ads expected, then close it
            Print("FacebookImage click");
            appModel.AdvantageShoppingPage().FacebookImage().click();
            browser.sync();
            Thread.sleep(4000);
            socialLink = "facebook";

            Browser fbBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title(Facebooktitle).build());
            fbBrowser.sync();
            brURL = fbBrowser.getURL();
            Assert.assertTrue("Verification - Verify Social Media: Verify that the Facebook site was launched properly.", brURL.matches(".*facebook\\.com.*"));
            fbBrowser.close();

            // Verify the Twitter link
            Print("TwitterImage click");
            appModel.AdvantageShoppingPage().TwitterImage().click();
            browser.sync();
            threadSleep(4000);
            socialLink = "twiiter";

            Browser tweetBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title(Twittertitle).build());
            tweetBrowser.sync();
            brURL = tweetBrowser.getURL();
            Assert.assertTrue("Verification - Verify Social Media: Verify that the Twitter site was launched properly.", brURL.matches(".*twitter\\.com.*"));
            tweetBrowser.close();

            // Verify the LinkedIn link
            Print("LinkedInImage click");
            appModel.AdvantageShoppingPage().LinkedInImage().click();
            browser.sync();
            threadSleep(4000);
            socialLink = "linkedin";

            Browser linkedinBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title(Linkedintitle).build());
            linkedinBrowser.sync();
            brURL = linkedinBrowser.getURL();
            Assert.assertTrue("Verification - Verify Social Media: Verify that the LinkedIn site was launched properly.", brURL.matches(".*linkedin\\.com.*"));
            linkedinBrowser.close();
        } catch (Exception e) {
            Reporter.reportEvent("verify Social Media ERROR", "Could not locate the browser with the matching URL of : " + socialLink, Status.Failed);
            Assert.assertTrue("Verification - Verify Social Media: Could not locate the browser with the  matching URL of the social media: " + socialLink, false);
        }
    }

    // This test gets the site version from the site UI and prints it to the console
   /* @Test
    public void verifyAdvantageVersionNumber() throws GeneralLeanFtException, ReportException
    {
    	// Get the regular expression pattern from the Copyright object design time description
    	String pattern = appModel.AdvantageShoppingPage().AdvantageIncCopyrightVersionWebElement().getDescription().getInnerText().toString();
    	waitUntilElementExists(appModel.AdvantageShoppingPage().AdvantageIncCopyrightVersionWebElement());
    	// Get the actual inner text of the Copyright object during runtime
    	String advantageIncCopyrightVersionElementInnerText = appModel.AdvantageShoppingPage().AdvantageIncCopyrightVersionWebElement().getInnerText();

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object
        Matcher m = r.matcher(advantageIncCopyrightVersionElementInnerText);
        m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String copyrightVersion = m.group(1).trim();

        Reporter.reportEvent("verifyAdvantageVersionNumber", "The page copyright version is: " + copyrightVersion, Status.Passed);
        //Verification(Verify.isTrue(!copyrightVersion.isEmpty(),"Verification - Verify Advantage Site Version Number","Verify that the site version: " + copyrightVersion + " was located correctly."));
		Verify.isTrue(!copyrightVersion.isEmpty(),"Verification - Verify Advantage Site Version Number","Verify that the site version: " + copyrightVersion + " was located correctly.");

	}*/

    // This test verifies that the main user links work
    @Test
    public void verifyUserLinks() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        threadSleep(3000);

        // Go to home page
        Print("AdvantageDEMOHomeLink().click()");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        Print("SignOutMainIconWebElement().click()");
        appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
        Print("MyAccountWebElement().click()");
        appModel.AdvantageShoppingPage().MyAccountWebElement().click();
        browser.sync();
        Print("waitUntilElementExists MyAccountHeaderLabelWebElement()");
        waitUntilElementExists(appModel.AdvantageShoppingPage().MyAccountHeaderLabelWebElement());
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().MyAccountHeaderLabelWebElement().exists(2), "Verification - Verify User Links", " Verify that the user links navigations work - My Account."));

        Print("SignOutMainIconWebElement().click()");
        appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
        Print("MyOrdersWebElement().click()");
        appModel.AdvantageShoppingPage().MyOrdersWebElement().click();
        browser.sync();
        Print("waitUntilElementExists MyOrdersHeaderLabelWebElement()");
        waitUntilElementExists(appModel.AdvantageShoppingPage().MyOrdersHeaderLabelWebElement());
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().MyOrdersHeaderLabelWebElement().exists(2), "Verification - Verify User Links", " Verify that the user links navigations work - My Orders."));

        Print("SignOutMainIconWebElement().click()");
        appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
        Print("SignOutWebElement().click()");
        appModel.AdvantageShoppingPage().SignOutWebElement().click();

        browser.refresh();
        browser.sync();

        Verification(Verify.isTrue(!isSignedIn(), "Verification - Verify User Links", " Verify that the user links navigations work - Sign Out."));
    }

    // This internal method strips the search parameter from the Search result page title and returns it
    public String getSearchParameterFromSearchResultsTitle() throws GeneralLeanFtException {
        // Get the regular expression pattern from the Search Result Title object design time description
        String pattern = appModel.AdvantageShoppingPage().SearchResultTitleWebElement().getDescription().getInnerText().toString();

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Get the actual inner text of the Search Result Title object during runtime
        String searchResultTitleElementInnerText = appModel.AdvantageShoppingPage().SearchResultTitleWebElement().getInnerText();
        // Now create the matcher object
        Matcher m = r.matcher(searchResultTitleElementInnerText);
        m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String searchParameterFromTitle = m.group(1).trim();

        // The title encapsulates the search parameter with quotes, so we need to remove them by trimming
        searchParameterFromTitle = searchParameterFromTitle.substring(1, searchParameterFromTitle.length() - 1);

        return searchParameterFromTitle;
    }

    // This test verifies that the search page works, using the Search URL
    @Test
    public void verifySearchUsingURL() throws GeneralLeanFtException {
        // Sign in to the store
        signIn();

        String searchParameter;
        searchParameter = "Laptops";

        // Go to the Search page as a workaround - search for Laptops
        browser.navigate(SearchURL + "?viewAll=" + searchParameter);
        waitUntilElementExists(appModel.AdvantageShoppingPage().LaptopFilterSearchCheckbox());
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().LaptopFilterSearchCheckbox().exists(), "Verification - Verify Search using URL", " Verify that the Laptops checkbox element exists."));

        // Get the actual inner text of the Search Result Title object during runtime
        Verification(Verify.isTrue(getSearchParameterFromSearchResultsTitle().equals(searchParameter), "Verification - Verify Search using URL", " Verify that the title reflects the search parameter: " + searchParameter + "."));

        searchParameter = "Speakers";
        // Go to the Search page as a workaround - search for Speakers
        browser.navigate(SearchURL + "?viewAll=" + searchParameter);
        waitUntilElementExists(appModel.AdvantageShoppingPage().SpeakersFilterSearchCheckbox());
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().SpeakersFilterSearchCheckbox().exists(), "Verification - Verify Search using URL", " Verify that the Speakers checkbox element exists."));

        // Get the actual inner text of the Search Result Title object during runtime
        Verification(Verify.isTrue(getSearchParameterFromSearchResultsTitle().equals("Speakers"), "Verification - Verify Search using URL", " Verify that the title reflects the search parameter: " + searchParameter + "."));
    }

    @Test
    public void verifyDownloadPageTest() throws GeneralLeanFtException {
        threadSleep(4000);

        waitUntilElementExists(appModel.AdvantageShoppingPage().MICEShopNowWebElement());

        browser.navigate(appURL + "/downloads");
        threadSleep(4000);
        Verification(Verify.isTrue(appModel.DownloadPage().DownloadAndroidAppWebElement().exists(), "Download Verification : Android", "verift that the android link works"));
        Verification(Verify.isTrue(appModel.DownloadPage().DownloadIosAppWebElement().exists(), "Download Verification : IOS", "verift that the IOS link works"));
        //appModel.DownloadPage().DownloadIosAppWebElement().click();
        //threadSleep(2000);
    }

    ////////////////////////////////////////////////// moti gadian Code added on  27/3/17 /////////////////////////////////////////////////////////////

    @Test
    public void orderServiceTest() throws GeneralLeanFtException {
   /*
    * login
		purchase�a product - remember it's name
		goto orders history page:
		Search by name�- look for the number you just created
		=> the search result shall show all relevant entries

	Delete an order =>delete the order you just created, when the user clicks delete validate that the application informs the user�that his order will be cancelled, and ask him to approve.�

	Validate that the following was added to the order grid:
		Order time*/

        signIn();

        Print("AdvantageDEMOHomeLink().click()");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();
        Print("LAPTOPSWebElement().click()");
        appModel.AdvantageShoppingPage().LAPTOPSWebElement().click();

        // Select an item to purchase and add it to the cart
        try {
            //selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage());
            Print("laptopFororderService().click()");
            appModel.AdvantageShoppingPage().laptopFororderService().click();
            String ProductName = appModel.AdvantageShoppingPage().LaptopName().getInnerText();
            Print("ORDER:" + ProductName);

            Print("ADDTOCARTButton().click()");
            appModel.AdvantageShoppingPage().ADDTOCARTButton().click();

            Checkout();

            Print("SignOutMainIconWebElement().click()");
            appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
            Print("MyOrdersWebElement().click()");
            appModel.AdvantageShoppingPage().MyOrdersWebElement().click();
            Print("OrderSearchWebElement().click()");
            appModel.AdvantageShoppingPage().OrderSearchWebElement().click();
            Print("SearchOrderEditField().setValue(" + ProductName);
            appModel.AdvantageShoppingPage().SearchOrderEditField().setValue(ProductName);
            Print("FirstRemoveItemFromCartLinkWebElement().click()");
            appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement().click();


            Verify.isTrue(appModel.AdvantageShoppingPage().RemoveFromOrderValidate().exists(), "Verification - Verify Search orders", "Verify that the alert window element exists.");
            Assert.assertTrue("Verification - Verify Search orders: Verify that the alert window element exists.", appModel.AdvantageShoppingPage().YesNoButtonsRemoveOrderSearch().exists());
            Print("YesCANCELButton().click()");
            appModel.AdvantageShoppingPage().YesCANCELButton().click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /*@Test
    public void outOfStockTest() throws GeneralLeanFtException {

    	/*
    	 * Missing (Quantity=0 in all colors)- validate that the UI is marked correctly and that the user can watch all its details
    	 * - change color to see the different pictures,
    	 * read the 'more info' section (click and open it in mobile) but,
    	 * that the user can't change the quantity or add it to cart
    	 *

    	browser.sync();
    	appModel.AdvantageShoppingPage().HEADPHONESShopNowWebElement().click();
    	appModel.AdvantageShoppingPage().SoldOutHeadphonesWebElement().click();
    	appModel.AdvantageShoppingPage().ColorSelectorFirstWebElement().click();


    	Verify.isFalse(appModel.AdvantageShoppingPage().ADDTOCARTButton().isEnabled(),"Verification - Verify sold out item","Verify that the ADD TO CART button not enabled.");
    	Verify.isTrue(appModel.AdvantageShoppingPage().QuantityOfProductWebEdit().isReadOnly(),"Verification - Verify sold out item","Verify that the Quantity field not enabled to edit.");

    }*/

    @Test
    public void payButtonRegExTest() throws GeneralLeanFtException {
        //The button text always starts with Pay to allow for adding regular expressions in object identification.

        //In  web the button calls "CHECKOUT ({{RegEx}})"

        String pattern = "CHECKOUT(.*)(\\d+).(\\d+)[')']";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        signIn();
        appModel.AdvantageShoppingPage().LAPTOPSWebElement().click();

        // Select an item to purchase and add it to the cart
        try {
            appModel.AdvantageShoppingPage().laptopFororderService().click();
            appModel.AdvantageShoppingPage().ADDTOCARTButton().click();


            String checkOutTXT = appModel.AdvantageShoppingPage().CHECKOUTHoverButton().getInnerText();
            Matcher m = r.matcher(checkOutTXT);
            boolean match = m.find();
            System.out.println(checkOutTXT + " :: " + match);

            Verification(Verify.isTrue(match, "Verification - Verify CHECKOUT RegEx", " Verify that the text in CHECKOUT button start with 'CHECKOUT' ."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void negativeLoginTest() throws GeneralLeanFtException {
        //Try to login with non valid credentials and verify the message "invalid user name or password"
        waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
        appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
        waitUntilElementExists(appModel.AdvantageShoppingPage().SIGNINButton());
        // Fill in the user name and password
        appModel.AdvantageShoppingPage().UsernameLoginEditField().setValue("bla bla");
        appModel.AdvantageShoppingPage().PasswordLoginEditField().setValue("bla pss");
        // Check the Remember Me checkbox
        appModel.AdvantageShoppingPage().RememberMeCheckBox().set(true);
        // Click on sign in button
        appModel.AdvantageShoppingPage().SIGNINButton().click();

        threadSleep(2000);
        boolean invalid = appModel.AdvantageShoppingPage().InvalidUserMessageWebElement().exists();

        Verification(Verify.isTrue(invalid, "Verification - Negative Sign In", "Verify that the we can't login with non valid credentials"));
    }

    @Test
    public void logOutTest() throws GeneralLeanFtException {
        //perform logout and make sure you are not logged in
        signIn();
        browser.sync();
        signOut();
        browser.refresh();
        Verification(Verify.isFalse(isSignedIn(), "Verification - Verify logout", " Verify that the is realy logout from the site ."));
    }


   /* @Test
    public void chatSupportTest() throws GeneralLeanFtException, ReportException {

     *todo: the test runs until he needs to set value in the chat support edit field
     * the app model can't recognize the field although the spy find one single match.
     *

    	// check if the Chat option for support are work fine and send a respond to user msg.


    	// Make sure to enable pop-up messages from this site


    	Browser chatBrowser;

    	try{
    		appModel.AdvantageShoppingPage().ChatLogoImage().click();
        	browser.sync();
        	Verify.isTrue(appModel.AdvantageOnlineShoppingDemoSupportChatPage().exists(),"Verification - Contact Us Chat","The chat window was created");
        	//waitUntilElementExists(appModel.AdvantageOnlineShoppingDemoSupportChatPage().ServerConnectmsg());

			chatBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title("Advantage Online Shopping Demo Support Chat").build());
			String brURL = chatBrowser.getURL();
			threadSleep(2000);
	    	Verify.isTrue(brURL.matches(".*//*chat\\.html.*"),"Verification - Contact Us Chat","Verify that the browser navigated to the chat URL");
            Verify.isTrue(appModel.AdvantageOnlineShoppingDemoSupportChatPage().ServerConnectmsg().exists() ,"Verification - Contact Us Chat","The 'server concted' massege show up");

    	}
    	catch (Exception e)
    	{
			Reporter.reportEvent("verify ContactUS  ERROR", "Could not locate the browser with the matching URL"  , Status.Failed);
			Verify.isTrue(false,"Verification - Contact Us Chat","The chat window was not created");
    	}


    	appModel.AdvantageOnlineShoppingDemoSupportChatPage().TypeAMessageEditField().setValue("Hello I need Help.");
    	appModel.AdvantageOnlineShoppingDemoSupportChatPage().ChatSendImage().click();

    	browser.sync();

    	Verify.isTrue(appModel.AdvantageOnlineShoppingDemoSupportChatPage().RespondChatWebElement().exists(),"Verification - Verify chat respond","Verify that we get respond to our massege.");
    	assertNotNull(appModel.AdvantageOnlineShoppingDemoSupportChatPage().RespondChatWebElement().getInnerText());
    }*/

    @Test
    public void contactSupportTest() throws GeneralLeanFtException {
    	/*
    	 * validate all the following fields
			- email address (mandatory)
			- select category (optional)
			- select product  from the selected category - one product only can be selected (optional)
			- free text of the issue (mandatory)
				"Send" option

			after clicking OK,  the user will receive a message saying "thank you for contacting Advantage support"
			play with this - select something, go back and try to change it - try to break this feature
    	 */

        //try to send request with just txt in the email field
        Print("EmailContactUsWebElement().setValue(\"fffff\")");
        appModel.AdvantageShoppingPage().EmailContactUsWebElement().setValue("fffff");
        Verification(Verify.isFalse(appModel.AdvantageShoppingPage().SENDContactUsButton().isEnabled(), "Verification - Verify contact Us request", "Verify that we cant send request with unproper Email."));

        //try to send request with just email  in the email field
        Print("EmailContactUsWebElement().setValue(\"user@demo.com\")");
        appModel.AdvantageShoppingPage().EmailContactUsWebElement().setValue("user@demo.com");
        Verification(Verify.isFalse(appModel.AdvantageShoppingPage().SENDContactUsButton().isEnabled(), "Verification - Verify contact Us request", "Verify that we cant send request with Email without Subject."));

        //try to send request with just txt in the email field and subject (not should be working)
        Print("EmailContactUsWebElement().setValue(\"sometxt\")");
        appModel.AdvantageShoppingPage().EmailContactUsWebElement().setValue("sometxt");
        Print("ContactUsSubject().setValue(\"I have Problem..\")");
        appModel.AdvantageShoppingPage().ContactUsSubject().setValue("I have Problem..");

        Print("SENDContactUsButton().click()");
        appModel.AdvantageShoppingPage().SENDContactUsButton().click();

        // Verify that the support request was not sent successfully
        Verify.isFalse(appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement().exists(2), "Verification - Verify contact Us request", "Verify that we cant send request with unproper Email and Subject.");
    }

    public void Checkout() throws GeneralLeanFtException {
        Print("ADDTOCARTButton().click()");
        clickWebElement(appModel.AdvantageShoppingPage().ADDTOCARTButton());
        Print("CHECKOUTHoverButton().click()");
        clickWebElement(appModel.AdvantageShoppingPage().CHECKOUTHoverButton());
        Print("NEXTButton().click()");
        clickWebElement(appModel.AdvantageShoppingPage().NEXTButton());
        Print("PAYNOWButton().click()");
        clickWebElement(appModel.AdvantageShoppingPage().PAYNOWButton());
//        appModel.AdvantageShoppingPage().PAYNOWButtonManualPayment().click();
        browser.sync();
    }

    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException {
        if (!VerifyMethod)
            throw new GeneralLeanFtException("varfication ERORR - verification of test fails! check runresults.html");
    }

    public void Close() throws GeneralLeanFtException {
        browser.close();
    }

    private static void printCaptionTest(String nameOfTest) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("START " + nameOfTest);
    }

    private static void printEndOfTest(String nameOfTest) {
        System.out.println("END " + nameOfTest);
    }

    private static void printTimeWholeTests(Long millis) {
        System.out.println("\n--------------------------------------------------");
        Print("AdvantageWebTest done in: " + String.valueOf((elapsedTimeAllTests / 1000F) / 60 + " min"));
    }

}