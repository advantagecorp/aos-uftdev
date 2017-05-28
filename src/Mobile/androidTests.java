package Mobile;

import static java.lang.String.*;
import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import Web.AdvantageStagingAppModel;
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
import Web.AdvantageWebTest.*;


import com.hp.lft.unittesting.*;

import unittesting.*;




//Make sure the tests run at the ascending alphabet name order (JUnit 4.11 and above)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class androidTests extends UnitTestClassBase {
	
	static AdvantageAndroidApp appModel;
	protected static Device device;
	protected static Application app;
	
	static String UNAME = "androidUser";
	String         PASS = "Password1";
	
    static String appURL2 = "52.88.236.171"; //"52.32.172.3:8080";//"35.162.69.22:8080";//
	static String appURL = System.getProperty("url", "defaultvalue");

	
	
	

    public androidTests() {
        //Change this constructor to private if you supply your own public constructor
    	
    
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        instance = new androidTests();
        globalSetup(androidTests.class);


		if(appURL.equals("defaultvalue")){

			appURL = appURL2;
			InitBeforeclassLocal();

		}
		else
			InitBeforeclass();



	}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
       
    	device.unlock();
    	globalTearDown();
     
    }

    @Before
    public void setUp() throws Exception {
    	app.restart();
		waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
    }

    @After
    public void tearDown() throws Exception {
    	
    	//SignOut();
    
    }



    public  void InitSetUP() throws GeneralLeanFtException, InterruptedException {


		//change the setting of the server
		setting();
		//create a new user for testing if not exists

		CreateNewUser(false);

	}

    public  void setting() throws GeneralLeanFtException, InterruptedException {

		if(appModel.AdvantageShoppingApplication().ServerNotReachableLabel().exists(5)) {
			appModel.AdvantageShoppingApplication().OKButton().tap();
			appModel.AdvantageShoppingApplication().EditTextServer().setText(appURL);

			appModel.AdvantageShoppingApplication().ConnectButton().tap();
			Thread.sleep(2000);

			waitUntilElementExists(appModel.AdvantageShoppingApplication().ButtonPanelSettingUiObject());

			appModel.AdvantageShoppingApplication().OKButton().tap();
		}


      else {
			waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());

			appModel.AdvantageShoppingApplication().MainMenu().tap();
			appModel.AdvantageShoppingApplication().SETTINGSLabel().tap();

			String server = appModel.AdvantageShoppingApplication().EditTextServer().getText();

			if (!server.equals(appURL)) { // check if the setting already set up

				appModel.AdvantageShoppingApplication().EditTextServer().setText(appURL);

				appModel.AdvantageShoppingApplication().ConnectButton().tap();
				Thread.sleep(2000);

				waitUntilElementExists(appModel.AdvantageShoppingApplication().ButtonPanelSettingUiObject());

				appModel.AdvantageShoppingApplication().OKButton().tap();

			}
		}


       app.restart();


	}

    public void  CreateNewUser(boolean isTest) throws GeneralLeanFtException , InterruptedException{
    	// create new user if not exists to run all tests
        if (!isTest){ // create a new user
			if(!SignIn(false)) {


				appModel.AdvantageShoppingApplication().SignUp().tap();

				waitUntilElementExists(appModel.AdvantageShoppingApplication().SignUpObject());

				//Set up private details
				appModel.AdvantageShoppingApplication().UserNameSignUp().setText(UNAME);
				appModel.AdvantageShoppingApplication().EmailSignUp().setText(UNAME + "@default.com");
				appModel.AdvantageShoppingApplication().PasswordSignUp().setText(PASS);
				appModel.AdvantageShoppingApplication().ConfirmPassSignUp().setText(PASS);
				device.swipe(SwipeDirection.UP);
				device.swipe(SwipeDirection.UP);

				//set up address details
				appModel.AdvantageShoppingApplication().AddressSignUpEditField().setText("Altalef 5");
				appModel.AdvantageShoppingApplication().CitySignUpEditField().setText("Yahud");
				appModel.AdvantageShoppingApplication().ZIPSignUpEditField().setText("454545");

				appModel.AdvantageShoppingApplication().REGISTERButton().tap();
				waitUntilElementExists(appModel.AdvantageShoppingApplication().AdvantageObjectUiObject());

				Verification(Verify.isTrue(SignIn(true), "New User creation", "verify that the creation of new user for testing  succeeds"));


				}
		}
		else{ // create existing user test
			SignOut();
			waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
			appModel.AdvantageShoppingApplication().MainMenu().tap();
			appModel.AdvantageShoppingApplication().Login().tap();

			appModel.AdvantageShoppingApplication().SignUp().tap();

			waitUntilElementExists(appModel.AdvantageShoppingApplication().SignUpObject());

			appModel.AdvantageShoppingApplication().UserNameSignUp().setText(UNAME);
			appModel.AdvantageShoppingApplication().EmailSignUp().setText(UNAME + "@default.com");
			appModel.AdvantageShoppingApplication().PasswordSignUp().setText(PASS);
			appModel.AdvantageShoppingApplication().ConfirmPassSignUp().setText(PASS);
			device.swipe(SwipeDirection.UP);
			device.swipe(SwipeDirection.UP);

			appModel.AdvantageShoppingApplication().REGISTERButton().tap();
			waitUntilElementExists(appModel.AdvantageShoppingApplication().AdvantageObjectUiObject());

			Verification(Verify.isFalse(SignIn(true), "Existing new User creation", "verify that the creation of Existing user NOT succeed"));



		}


	}


	/////////////////////////////////////  Start of tests  //////////////////////////////////////////////////////



	@Test
	public void AddNewUserAndCheckInitials() throws GeneralLeanFtException, InterruptedException {

    	InitSetUP();
	}

	@Test
	public void SilentLoginTest() throws GeneralLeanFtException, InterruptedException {

		SignOut();
		SignIn(false);
		app.launch();
		Verify.isTrue(SignIn(true),"Verification - Sign In", "Verify that the user " + UNAME + " In still sign in.");


	}

	@Test
	public void CreateExsitingUserTest() throws GeneralLeanFtException, InterruptedException{

		CreateNewUser(true);

	}

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
	public void NegativeLogin() throws GeneralLeanFtException, InterruptedException {

    	/*
    	 Try to login with incorrect credentials
 		Verify that correct message appears
    	*/

		if(SignIn(true))
			SignOut();

		appModel.AdvantageShoppingApplication().MainMenu().tap();
		appModel.AdvantageShoppingApplication().Login().tap();
		appModel.AdvantageShoppingApplication().UserNameEdit().setText(UNAME);
		appModel.AdvantageShoppingApplication().PassEdit().setText("some pass");
		appModel.AdvantageShoppingApplication().LOGINButton().tap();
		Verification(Verify.isTrue(appModel.AdvantageShoppingApplication().InvalidUserNameOrPas().exists(),"Verification - Negative Login", "Verify that the user NOT login with incorrect password"));


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


			waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductDetail());
			appModel.AdvantageShoppingApplication().UPDATEPRODUCTButton().tap();
			waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());

			device.back();

			waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess());
			appModel.AdvantageShoppingApplication().CartAccess().tap();
			CheckOut();


	    
    	
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
		waitUntilElementExists(appModel.AdvantageShoppingApplication().tabletItem());
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
    	waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
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



    @Test
    public void PayMasterCreditTest() throws GeneralLeanFtException, InterruptedException{
    	

    	
/*    	    Login if not logged in
���         Select Laptops category
����        Select Operating system filter - Win 10
����� 		Select a laptop
���� 		Select another laptop
���� 		Change color
��� 		Click Add to Cart
���� 		Open cart menu
� 			Click 'Edit shipping details'
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
*/

    	 
    	
    	//todo: run and check
    	SignIn(false);
    	
    	appModel.AdvantageShoppingApplication().MainMenu().tap();
    	appModel.AdvantageShoppingApplication().LAPTOPSLabel().tap();
    	appModel.AdvantageShoppingApplication().ImageViewFilter().tap();


    	//apply filter- Win 10
		appModel.AdvantageShoppingApplication().BYOPERATINGSYSTEMLabel().tap();
		appModel.AdvantageShoppingApplication().Windows10Label().tap();

		appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();
		appModel.AdvantageShoppingApplication().LaptopitemWin10().tap();

		waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor());
		appModel.AdvantageShoppingApplication().ProductColor().tap();
		appModel.AdvantageShoppingApplication().colorObject().tap();
		appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

        //check out and pay with master credit the card number nedded to 12 digits
		waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());

		checkOutMasterCredit("123456789123","456",UNAME,false,true);

		appModel.AdvantageShoppingApplication().PAYNOWButton().tap();
		waitUntilElementExists(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject());
		Verify.isTrue(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject().exists(),"Verify- purchase success MasterCredit"," verift that the payment success and we recive the order detail window" );
        appModel.AdvantageShoppingApplication().CloseDialog().tap();






	}
    
  
    
    /*@Test
    public void MobileWebTest() throws GeneralLeanFtException, InterruptedException{
    	

    	waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());

    	MobileWeb mobileWeb = new MobileWeb("05157df581dae805",appURL);

    	mobileWeb.PurchaseTest(UNAME , PASS);

    	
    }*/
    
    
    

    
    
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



	@Test
	public void ChangePasswordTest() throws GeneralLeanFtException, InterruptedException {

    	/*
    	 *
    	 *
 		Login
		Click setting
		Click change password
		Change the password
		Logout and login again with the new password
    	 *
    	 *
    	 */


		String Oldpass =  PASS;

		if(SignIn(true))
			SignOut();

		//step 1 - change to new pass
		changepassword("Password23");
		Verification(Verify.isTrue(SignIn(false),"Verification - Change Password step 1 - change to new pass", "Verify that the user login with the new password"));

		//step 2 -  change back to the default pass
		changepassword(Oldpass);
		Verification(Verify.isTrue(SignIn(false),"Verification - Change Password step 2 -  change back to the default pass", "Verify that the user login with the new password"));




	}

	/*@Test
	public void test() throws GeneralLeanFtException, InterruptedException {

    	InitBeforeclass();

	}*/

    /////////////////////////////////////  End of tests  //////////////////////////////////////////////////////


    public void checkOutMasterCredit(String cardnum ,String CVV,String HolderName, boolean savedetails, boolean changeShipping) throws GeneralLeanFtException, InterruptedException {
    	
    	appModel.AdvantageShoppingApplication().CartAccess().tap();
  	    appModel.AdvantageShoppingApplication().CHECKOUT().tap();
  	    Thread.sleep(2000);
		waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
  	    //waitUntilElementExists(appModel.AdvantageShoppingApplication().PaymentDetails());

  	    if(changeShipping){
			appModel.AdvantageShoppingApplication().EditShippingUiObject().tap();
			appModel.AdvantageShoppingApplication().ZIPshippingDetaildEditField().setText("12345");
			appModel.AdvantageShoppingApplication().ShippingCheckBox().tap();
			appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();
		}

  	   appModel.AdvantageShoppingApplication().PaymentDetails().tap();
  	   appModel.AdvantageShoppingApplication().ImageViewMasterCredit().tap();
  	  
     //set the details

		appModel.AdvantageShoppingApplication().CardNumderMasterCreditEditField().setText(cardnum);
		appModel.AdvantageShoppingApplication().CardNumderMasterCreditEditField().setText(cardnum);
  	  appModel.AdvantageShoppingApplication().CVVMasterCreditEditField().setText(CVV);
  	  appModel.AdvantageShoppingApplication().CardHolderMasterCreditEditField().setText(HolderName);

  	  if(!savedetails){ // by default it save the details

		  appModel.AdvantageShoppingApplication().SaveMasterCreditCredenCheckBox().tap();

	  }

	  appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();


    	
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
		device.swipe(SwipeDirection.UP);
		device.swipe(SwipeDirection.UP);
		appModel.AdvantageShoppingApplication().UPDATEAccountButton().tap();

		PASS = newpass;
		SignOut();



	}
    
    
   public boolean SignIn(Boolean quiet ) throws GeneralLeanFtException, InterruptedException{


	   waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
	   appModel.AdvantageShoppingApplication().MainMenu().tap();
	   String innerTxt = appModel.AdvantageShoppingApplication().LinearLayoutLogin().getVisibleText();
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
	       device.back();
	       waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
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
	   appModel.AdvantageShoppingApplication().LaptopitemWin10().tap();
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor());
	   Thread.sleep(3000);
	   appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap(); 
	   
	   
   }

	public void EditShipping() throws GeneralLeanFtException, InterruptedException{

    	waitUntilElementExists(appModel.AdvantageShoppingApplication().EditShippingUiObject());
		appModel.AdvantageShoppingApplication().EditShippingUiObject().tap();



	}
   
   public void CheckOut() throws GeneralLeanFtException, InterruptedException{
	   

	   //while(appModel.AdvantageShoppingApplication().CHECKOUT().exists())
	   // when taping on "CheckOut" the server is very slow and after the taping we need to wait and check ir the request has sanded

	   //{
	   	appModel.AdvantageShoppingApplication().CHECKOUT().tap();
	   	Thread.sleep(30000);
	   //}

	   waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
	   //pay with safepay and don't save details
	   SafePay(false);
	   
	   appModel.AdvantageShoppingApplication().PAYNOWButton().tap();
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject());
	   Verify.isTrue(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject().exists(),"Verify- purchase success"," verift that the payment success and we recive the order detail window" );
	   appModel.AdvantageShoppingApplication().CloseDialog().tap();
   }    
   
   public void SafePay(boolean save) throws GeneralLeanFtException{ 
	   
	   //start with edit Safepay details
	   
	   waitUntilElementExists(appModel.AdvantageShoppingApplication().PaymentDetails());
	  
	   appModel.AdvantageShoppingApplication().PaymentDetails().tap();
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
	   
	   appModel.AdvantageShoppingApplication().MainMenu().tap();
	   appModel.AdvantageShoppingApplication().CARTLabel().tap();
	   while(appModel.AdvantageShoppingApplication().FirstCartItem().exists(2)){
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


	public  static  void InitBeforeclass() throws GeneralLeanFtException {

    	String deviceID  = "";


		for (DeviceInfo deviceInfo : MobileLab.getDeviceList()) {
			//System.out.printf("The device ID is: %s, and its name is: %s\n\n", deviceInfo.getId(), deviceInfo.getName());
			String[] s = deviceInfo.getOSVersion().split("\\.");
			String Join = "";
			for (String s1:s)
				Join += s1;

			int version = Integer.parseInt(Join);
			if (deviceInfo.getOSType().equals("ANDROID") && version >= 600) {
				deviceID = deviceInfo.getId();
				break;
			}

		}

		device = MobileLab.lockDeviceById(deviceID);// ID For galaxy S6


		// Describe the AUT.
		app = device.describe(Application.class, new ApplicationDescription.Builder()
				.identifier("com.Advantage.aShopping").packaged(true).build());

		//connect between the appModel and the device
		appModel = new AdvantageAndroidApp(device);

		app.install();


	}

	//use this in local testing

	public static void  InitBeforeclassLocal() throws GeneralLeanFtException {


		device = MobileLab.lockDeviceById("05157df581dae805");// ID For galaxy S6
		//device = MobileLab.lockDeviceById("06157df623745934");// ID For galaxy S6

		// Describe the AUT.
		app = device.describe(Application.class, new ApplicationDescription.Builder()
				.identifier("com.Advantage.aShopping").packaged(true).build());

		//connect between the appModel and the device
		appModel = new AdvantageAndroidApp(device);

		//app.install();



	}
   

}