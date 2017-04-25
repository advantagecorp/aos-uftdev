package Mobile;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.WaitUntilTestObjectState.WaitUntilEvaluator;
import com.hp.lft.sdk.web.*;
import com.hp.lft.sdk.mobile.*;
import com.hp.lft.sdk.internal.mobile.*;
import com.hp.lft.verifications.*;



import com.hp.lft.unittesting.*;

import unittesting.*;




//Make sure the tests run at the ascending alphabet name order (JUnit 4.11 and above)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class androidTests extends UnitTestClassBase {
	
	static AdvantageAndroidApp appModel;
	protected static Device device;
	protected static Application app;
	
	static String UNAME = "AndroidUser";
	String         PASS = "Userpass1";
	
    static String appURL = "52.32.172.3:8080";

	
	
	

    public androidTests() {
        //Change this constructor to private if you supply your own public constructor
    	
    
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        instance = new androidTests();
        globalSetup(androidTests.class);

		//device = MobileLab.lockDeviceById("QHC0216114003497");// ID For Hawawii
		//device = MobileLab.lockDeviceById("04cbab13");// ID For Nexus 7
		//device = MobileLab.lockDeviceById("140882a2");// ID For GT-19515
		device = MobileLab.lockDeviceById("05157df581dae805");// ID For galaxy S6


		// Describe the AUT.
		app = device.describe(Application.class, new ApplicationDescription.Builder()
				.identifier("com.Advantage.aShopping").build());

		//connect between the appModel and the device
		appModel = new AdvantageAndroidApp(device);



	}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
       
    	device.unlock();
    	globalTearDown();
     
    }

    @Before
    public void setUp() throws Exception {
    	app.restart();
    }

    @After
    public void tearDown() throws Exception {
    	
    	//SignOut();
    
    }



    public  void InitSetUP() throws GeneralLeanFtException, InterruptedException {


		//change the setting of the server
		//setting();
		//create a new user for testing if not exists

		CreateNewUser();

	}

    public void setting() throws GeneralLeanFtException, InterruptedException {
		waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());

		appModel.AdvantageShoppingApplication().MainMenu().tap();
		appModel.AdvantageShoppingApplication().SETTINGSLabel().tap();

		String server = appModel.AdvantageShoppingApplication().EditTextServer().getText();

		if(!server.equals(appURL)){ // check if the setting already set up

			appModel.AdvantageShoppingApplication().EditTextServer().setText(appURL);

			appModel.AdvantageShoppingApplication().ConnectButton().tap();
			Thread.sleep(2000);

			waitUntilElementExists(appModel.AdvantageShoppingApplication().ButtonPanelSettingUiObject());

			appModel.AdvantageShoppingApplication().OKButton().tap();

		}


       app.restart();


	}

    public void  CreateNewUser() throws GeneralLeanFtException , InterruptedException{
    	// create new user if not exists to run all tests

		if(!SignIn(false)){


			appModel.AdvantageShoppingApplication().SignUp().tap();

			waitUntilElementExists(appModel.AdvantageShoppingApplication().SignUpObject());

			appModel.AdvantageShoppingApplication().UserNameSignUp().setText(UNAME);
			appModel.AdvantageShoppingApplication().EmailSignUp().setText(UNAME + "@default.com");
			appModel.AdvantageShoppingApplication().PasswordSignUp().setText(PASS);
			appModel.AdvantageShoppingApplication().ConfirmPassSignUp().setText(PASS);



			appModel.AdvantageShoppingApplication().REGISTERButton().tap();

			Verification(Verify.isTrue(SignIn(true) , "New User creation" , "verify that the creation of new user for testing  succeeds"));



		}


	}

	@Test
	public void AddNewUserAndCheckInitials() throws GeneralLeanFtException, InterruptedException {

    	InitSetUP();
	}


    @Test
    public void UpdateCartTest() throws GeneralLeanFtException, InterruptedException {
    	
    	/*
    	 *			1.���Open app
					2.���Login if not logged in
					3.���Select Tablets tile
					4.���Select a Tablet
					5.���Add +1 to quantity
					6.���Click on Add to Cart
					7.���Click on Cart
					8.���swipe to Edit
					9.���Change amount and color
					10.� click on add to cart
					11.� Open cart menu and validate that the cart was updated correctly (the old color is not there�..)
					12.� click checkout
					13.� Select SafePay as Payment method
					14.� Click Pay Now
					15.� Verify receipt 
    	 * 
    	 * 
    	 * 
    	 */
    	
    	
    	//System.out.println(appModel.AdvantageShoppingApplication().SIGNOUTLabel().exists());
    	   SignIn(false);
    	
    	//buy a leptop item
    	BuyLeptop();
    	//goes to cart and edit details
    	
    	 waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess());
    	appModel.AdvantageShoppingApplication().CartAccess().tap();
    	appModel.AdvantageShoppingApplication().FirstCartItem().tap();
    	
    	//change color and amount
    	 waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor());
    	
    	appModel.AdvantageShoppingApplication().ProductColor().tap();
    	appModel.AdvantageShoppingApplication().colorObject().tap();
    	 
    	appModel.AdvantageShoppingApplication().ProductQuantity().tap();
    	appModel.AdvantageShoppingApplication().ProductQuantityEditField().setText("3");
    	appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();
    	
    	
    	 waitUntilElementExists( appModel.AdvantageShoppingApplication().ProductDetail());
    	appModel.AdvantageShoppingApplication().UPDATEPRODUCTButton().tap();
    	
    	device.back();
    	
    	waitUntilElementExists( appModel.AdvantageShoppingApplication().CartAccess());
    	CheckOut();
	    
    	
    }
  
    
   @Test
    public void ChangePasswordTest() throws GeneralLeanFtException, InterruptedException {
    	
    	/*
    	 *
    	 *  Try to login with incorrect credentials
���� 		Verify that correct message appears
������ 		Login with correct credentials
���� 		Click setting
����� 		Click change password
����� 		Change the password
������ 		Logout and login again with the new password
    	 * 
    	 * 
    	 * 
    	 */

    	String Oldpass =  PASS;
 	   
 	  if(SignIn(true))
 	    	SignOut();
 	
 	appModel.AdvantageShoppingApplication().MainMenu().tap();    
 	appModel.AdvantageShoppingApplication().Login().tap();
   	appModel.AdvantageShoppingApplication().UserNameEdit().setText(UNAME);
   	appModel.AdvantageShoppingApplication().PassEdit().setText("some pass");
   	appModel.AdvantageShoppingApplication().LOGINButton().tap();
   	Verification(Verify.isTrue(appModel.AdvantageShoppingApplication().InvalidUserNameOrPas().exists(),"Verification - Change Password", "Verify that the user NOT login with incorrect password"));

   	//step 1 - change to new pass
   	changepassword("Password1");
   	Verification(Verify.isTrue(SignIn(false),"Verification - Change Password step 1 - change to new pass", "Verify that the user login with the new password"));


   	//step 2 -  change back to the default pass
	   changepassword(Oldpass);
	   Verification(Verify.isTrue(SignIn(false),"Verification - Change Password step 2 -  change back to the default pass", "Verify that the user login with the new password"));
   	
   	
    	   
 	   
    }

    public void changepassword(String newpass) throws GeneralLeanFtException, InterruptedException {

		SignIn(false);
		appModel.AdvantageShoppingApplication().MainMenu().tap();
		appModel.AdvantageShoppingApplication().AccountDetails().tap();
		Thread.sleep(2000);
		waitUntilElementExists(appModel.AdvantageShoppingApplication().ChangePasswordObject());

		//change to another password
		appModel.AdvantageShoppingApplication().ChangePasswordLabel().tap();
		appModel.AdvantageShoppingApplication().OldPassEditField().setText(PASS);
		appModel.AdvantageShoppingApplication().NewPassEditField().setText(newpass);
		appModel.AdvantageShoppingApplication().ConfirmNewPassEditField().setText(newpass);
		appModel.AdvantageShoppingApplication().UPDATEAccountButton().tap();

		app.restart();
		SignOut();

		PASS = newpass;

	}
    
    @Test
    public void SilentLoginTest() throws GeneralLeanFtException, InterruptedException {
    	
    	SignOut();
    	SignIn(false);
    	app.launch();
        Verify.isTrue(SignIn(true),"Verification - Sign In", "Verify that the user " + UNAME + " In still sign in.");
    	 
    	
    }
 	   

  
    @Test
    public void PurchaseHugeQuantityTest() throws GeneralLeanFtException, InterruptedException{
    	/*
    	 * �Login
���� 		Select Speakers tile
����� 		Select Manufacture filter � HP
�� 			Select a Speaker
����� 		Change Color
�� 			add +1000 speakers
��� 		Click on Add to Cart
���� 		Check quantity message warning
 			Open cart menu and click checkout
� 			Select SafePay as Payment method
� 			Fill in SafePay user & pass
� 			Un-Check �Save changes in profile for future use�
� 			Click Pay Now
�			Verify receipt
� 			Validate safePay details didn�t changed (via my account)
    	 */
    	
    	
    	SignIn(false);
    	
    	EmptyCart();
    	
    	//make  a filter
    	
    	 waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
    	appModel.AdvantageShoppingApplication().MainMenu().tap();
    	appModel.AdvantageShoppingApplication().SPEAKERSLabel().tap();
    	appModel.AdvantageShoppingApplication().ImageViewFilter().tap();
    	appModel.AdvantageShoppingApplication().BYMANUFACTURERLabel().tap();
    	appModel.AdvantageShoppingApplication().HPLabel().tap();
    	appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();
    	
    	//choose item and change his color
    	
    	
    	 waitUntilElementExists(appModel.AdvantageShoppingApplication().tabletItem());
    	appModel.AdvantageShoppingApplication().tabletItem().tap();
    	appModel.AdvantageShoppingApplication().ProductColor().tap();
    	appModel.AdvantageShoppingApplication().colorObject().tap();
    	
    	//set quantity
    	
    	appModel.AdvantageShoppingApplication().ProductQuantity().tap();
    	appModel.AdvantageShoppingApplication().ProductQuantityEditField().setText("1000");
    	appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();
    	
    	/// need to verify an error msg - not support
    	 waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductDetail());
    	appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

		waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess());
		appModel.AdvantageShoppingApplication().CartAccess().tap();
    	
    	CheckOut(); // use safepay
    	
    	
    	
    }
    
    @Test
    public void OutOfStockTest() throws GeneralLeanFtException{
    	
    	waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
    	
    	appModel.AdvantageShoppingApplication().MainMenu().tap();
    	appModel.AdvantageShoppingApplication().HEADPHONESLabel().tap();
    	appModel.AdvantageShoppingApplication().SOLDout().tap();
    	
    	/*
    	 * verify that we can change color - in web the user can edit the color but here we chack that 
    	 * in not an option
    	 */
    	
    	
    	
    	// all the verification are not working because the attribute "isEnabled" are not include in this version of the app.  
    	Verify.isFalse(appModel.AdvantageShoppingApplication().ProductColor().isEnabled(),"Verification - Out Of Stock", "Verify that we can't change color.");
   
    	//verify that we can't change quantity or add to cart
    	Verify.isFalse(appModel.AdvantageShoppingApplication().ProductQuantity().isEnabled(),"Verification - Out Of Stock", "Verify that we can't change quantity.");
    	Verify.isFalse(appModel.AdvantageShoppingApplication().ADDTOCARTButton().isEnabled(),"Verification - Out Of Stock", "Verify that we can't ADD TO CART.");
    	
    	
    }
    
    
    /*@Test
    public void PayMasterCreditTest() throws GeneralLeanFtException, InterruptedException{
    	
    	
    	
    	
    	
    	    Login if not logged in
���         Select Laptops category
����        Select Operating system filter - Win 10
����� 		Select a laptop
���� 		Select another laptop
���� 		Change color
��� 		Click Add to Cart
���� 		Open cart menu
� 			Verify price correctness
� 			Click Checkout, verify shipping cost is not free(?)
� 			Click �Edit shipping details�
� 			Change shipping �postal code�
�		    Un-Check �Save changes in profile for future use�(?)
� 			Click next
� 			Select MasterCredit as Payment method
�		    Insert all card details
� 			Un-Check �Save changes in profile for future use�
�		    Click Pay Now
� 			Verify receipt
� 			Validate shipping details didn�t changed (via my account)
�			 Validate MasterCredit details didn�t changed (via my account)
    	 
    	
    	
    	SignIn(false);
    	
    	appModel.AdvantageShoppingApplication().MainMenu().tap();
    	appModel.AdvantageShoppingApplication().LAPTOPSLabel().tap();
    	appModel.AdvantageShoppingApplication().ImageViewFilter().tap();
    	
    	
    	
    }*/
    
  
    
    /*@Test
    public void MobileWebTest() throws GeneralLeanFtException, InterruptedException{
    	
    	
    	Browser browser = BrowserFactory.launch(BrowserType.CHROME,device);
    	browser.navigate("http://advantageonlineshopping.com");	
    	
    	
    	AdvantageAppModel webappmodel = new AdvantageAppModel(browser);
    	
    	webappmodel.AdvantageShoppingPage().CONTACTUSMainWebElement().click();
    	
    	
    	
    }*/
    
    
    
    @Test
    public void SignOutTest() throws GeneralLeanFtException, InterruptedException{
    	//Perform logout and make sure you are not logged in: in the menu, the option is to login .
    	
    	//In Method "SignIn() we make all the validation in the user are logged in- here we use this "
    	
    	
    	if(!SignIn(true))
    		SignIn(false);
    	
    	SignOut();
    	
    	Verify.isFalse(SignIn(true),"Verification - Sign Out","Verify that the user sign out correctly");
    }
    
    
    @Test
    public void PayButtonRegExTest() throws GeneralLeanFtException, InterruptedException{
    	
    	//The button text always starts with Pay to allow for adding regular expressions in object identification.
    	
    	//In  web the button calls "CHECKOUT ({{RegEx}})"
    	
    	
    	String pattern = "CHECKOUT(.*)(\\d+).(\\d+)[')']";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        
        SignIn(false);
        BuyLeptop();
        
        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess());
        appModel.AdvantageShoppingApplication().CartAccess().tap();
        
        waitUntilElementExists(appModel.AdvantageShoppingApplication().FirstCartItem());
        
        String innerTxt = appModel.AdvantageShoppingApplication().CHECKOUT().getText();
        
        Matcher m = r.matcher(innerTxt);
		boolean match  = m.find();
	    System.out.println("PayButtonRegExTest- "+innerTxt + " :: " + match);

		Verify.isTrue(match,"Verification - Verify CHECKOUT RegEx","Verify that the text in CHECKOUT button start with 'CHECKOUT ({{RegEx}})' .");		
        
        
    	
    }
    public void checkOutMasterCredit(String cardnum ,String CVV,String HolderName, boolean save ) throws GeneralLeanFtException{
    	
    	appModel.AdvantageShoppingApplication().CartAccess().tap();
  	    appModel.AdvantageShoppingApplication().CHECKOUT().tap();
  	    
  	  appModel.AdvantageShoppingApplication().PaymentDetails().tap();
  	  appModel.AdvantageShoppingApplication().ImageViewMasterCredit().tap();
  	  
     //set the details
  	  appModel.AdvantageShoppingApplication().PaymentDetails().tap();
  	  appModel.AdvantageShoppingApplication().CVVMasterCreditEditField().setText(CVV);
  	
    	
    }
    
    
   public boolean SignIn(Boolean quiet ) throws GeneralLeanFtException, InterruptedException{
	   
	   
	   
	  
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
	   appModel.AdvantageShoppingApplication().MainMenu().tap();
	   String innerTxt = appModel.AdvantageShoppingApplication().LoginObj().getVisibleText();
	   //System.out.println(appModel.AdvantageShoppingApplication().AccountDetails().exists(2))
	   
	   if(innerTxt.equals("LOGIN")){
		   
		    if(!quiet){
   	        appModel.AdvantageShoppingApplication().Login().tap();
	    	appModel.AdvantageShoppingApplication().UserNameEdit().setText(UNAME);
	    	appModel.AdvantageShoppingApplication().PassEdit().setText(PASS);
	    	appModel.AdvantageShoppingApplication().LOGINButton().tap();

	    	if (!appModel.AdvantageShoppingApplication().InvalidUserNameOrPas().exists())
			{
				System.out.println(UNAME + "  Login Success");
				Verify.isTrue(true,"Verification - Sign In", "Verify that the user " + UNAME + " signed in properly.");
				return true;
			}
				return false;

		    }
	    	
	    	return false;
		   
	   }
	   
		   System.out.println(UNAME + " allready logged in");
		   	return true;
		
	   
   }
   
   
   public void SignOut() throws GeneralLeanFtException, InterruptedException{
	   if (SignIn(true)){
		   
		   appModel.AdvantageShoppingApplication().MainMenu().tap();
		   appModel.AdvantageShoppingApplication().SIGNOUTLabel().tap();
		   appModel.AdvantageShoppingApplication().YESButton().tap();
		   
		   
		   System.out.println(UNAME + "Signed Out in success");
		   
	   }
   }
   
  
   
   public void BuyLeptop() throws GeneralLeanFtException, InterruptedException{
	   
	   EmptyCart();
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
	   appModel.AdvantageShoppingApplication().MainMenu().tap(); 
   	  
	   appModel.AdvantageShoppingApplication().LAPTOPSLabel().tap();
	   appModel.AdvantageShoppingApplication().LaptopItem2().tap();
	   waitUntilElementExists( appModel.AdvantageShoppingApplication().ProductColor());
	   appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap(); 
	   
	   
   }
   
   public void CheckOut() throws GeneralLeanFtException, InterruptedException{
	   
	   
	   /*waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess());
	   appModel.AdvantageShoppingApplication().CartAccess().tap();
	  
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().FirstCartItem());*/
	   appModel.AdvantageShoppingApplication().CHECKOUT().tap();
	  
	   //pay with safepay and don't save details
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
	   SafePay(false);
	   
	   appModel.AdvantageShoppingApplication().PAYNOWButton().tap();
	   Verify.isTrue(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject().exists(),"Verify- purchase success"," verift that the payment success and we recive the order detail window" );
	    
   }    
   
   public void SafePay(boolean save) throws GeneralLeanFtException{ 
	   
	   //start with edit Safepay details
	   
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().PaymentDetails());
	  
	   appModel.AdvantageShoppingApplication().PaymentDetails().tap();
	   appModel.AdvantageShoppingApplication().ImageViewSafePay().tap();
	   appModel.AdvantageShoppingApplication().SafePayUserfieldEditField().setText(UNAME);
	   appModel.AdvantageShoppingApplication().SafePayPassFieldEditField().setText(PASS);
	   
	   if (!save){
		   //by default the checkbox is checked , tap on it - don't save the details
		   appModel.AdvantageShoppingApplication().SaveSafePayCredentialsCheckBox().tap(); 
	   }
	   
	  appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();
	   
   }
   
 
  
   
   public void EmptyCart() throws NumberFormatException, GeneralLeanFtException, InterruptedException{
	   
	 
	   
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess());
	   
	   appModel.AdvantageShoppingApplication().HOME().tap();
	   appModel.AdvantageShoppingApplication().CartAccess().tap();
	   while(appModel.AdvantageShoppingApplication().FirstCartItem().exists()){
		   appModel.AdvantageShoppingApplication().FirstCartItem().swipe(SwipeDirection.RIGHT);
		   //Thread.sleep(1000);
		   appModel.AdvantageShoppingApplication().CartRemove().tap();
	   }
	   device.back();
	   
   }
   
   public void PurchaseTablet(String quantity) throws GeneralLeanFtException, NumberFormatException, InterruptedException{
	   
	   
	   EmptyCart();
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
	   appModel.AdvantageShoppingApplication().MainMenu().tap();
	   appModel.AdvantageShoppingApplication().TABLETSLabel().tap();
	   appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();
	   
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductQuantity());
	   
	   appModel.AdvantageShoppingApplication().ProductQuantity().tap();
	   appModel.AdvantageShoppingApplication().ProductQuantityEditField().setText(quantity);
	   
	
	   
	   
	   
   }
   
   
   public boolean waitUntilElementExists(UiObject appElem) throws GeneralLeanFtException
   {
   	return WaitUntilTestObjectState.waitUntil(appElem,new WaitUntilEvaluator<UiObject>(){				
   		   public boolean evaluate(UiObject we){				
   		    try{				
   		     return we.exists();                                    				
   		    }				
   		    catch(Exception e){				
   		     return false;				
   		    }				
   		   }				
   		  });
   }

	public void Verification(boolean VerifyMethod) throws GeneralLeanFtException{

		if(!VerifyMethod)
			throw new GeneralLeanFtException("varfication ERORR - verification of test fails! check runresults.html");
	}
   

}