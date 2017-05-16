package Mobile;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.mobile.*;

import com.hp.lft.verifications.*;

import unittesting.*;

public class IOSTests extends UnitTestClassBase {
	
	protected static AdvantageIOSApp appModel;
	protected static Device device;
	protected static Application app;
	
	
	static String UNAME = "IosUser";
	        String PASS = "Password1";
    static String appURL2 ="52.32.172.3:8080";//"52.88.236.171:8080"; //"35.162.69.22:8080";//
    static String appURL = System.getProperty("url", "defaultvalue");
	
	
	

    public IOSTests() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new IOSTests();
        globalSetup(IOSTests.class);

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
    	waitUntilElementExists(appModel.AdvantageShoppingApplication().MenuObjUiObject());
    }

    @After
    public void tearDown() throws Exception {
    	
    	
    }

    ///////////////////////////////////////                Start of tests  ////////////////////////////////////////////////

    @Test
    public void InvalidLoginTest() throws GeneralLeanFtException {

        //SignIn();


    }
    @Test
    public void AddNewUserTest() throws GeneralLeanFtException, InterruptedException {



        if(!SignIn(false)){
            CreateNewUser();

        }


    }

    @Test
    public void CreateExistingUserTest() throws GeneralLeanFtException {

        //SignIn();


    }

    @Test
    public void SilentLoginTest() throws GeneralLeanFtException {
    	
    	//SignIn();
    	
    	
    }
    @Test
    public void LogOutTest() throws GeneralLeanFtException, InterruptedException {

        if(SignIn(true))
            SignOut();

        //SignIn();


    }
    @Test
    public void UpdateCartTest() throws GeneralLeanFtException {

        //SignIn();


    }
    @Test
    public void OutOfStockTest() throws GeneralLeanFtException {

        //SignIn();


    }
    @Test
    public void PurchaseHugeQuantityTest() throws GeneralLeanFtException, InterruptedException {

        SignIn(false);
        appModel.AdvantageShoppingApplication().MenuButton().tap();
        appModel.AdvantageShoppingApplication().SPEAKERSLabel().tap();
        appModel.AdvantageShoppingApplication().SpeakerImgUiObject().tap();
        appModel.AdvantageShoppingApplication().ColorButton().tap();
        appModel.AdvantageShoppingApplication().ColorObj().tap();

        //todo: the quntity need to be EditField
        //todo: the 'Apply' need to be separate

        appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();





        //SignIn();


    }

    @Test
    public void PurchseWithMasterCreditTest() throws GeneralLeanFtException, InterruptedException {

        if (!SignIn(true))
            SignIn(false);

        Buy(appModel.AdvantageShoppingApplication().LAPTOPSLabel() , appModel.AdvantageShoppingApplication().LeptopItem(), "MasterCredit");

        //SignIn();


    }
    @Test
    public void ChangePasswordTest() throws GeneralLeanFtException {

        //SignIn();


    }
    
    //////////////////////////////////////////////        end of tests   ///////////////////////////////////////////////
    
    public boolean SignIn(boolean quiet) throws GeneralLeanFtException, InterruptedException {

        /*app.restart();
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MenuObjUiObject());*/
    	appModel.AdvantageShoppingApplication().MenuButton().tap();
    	String loginTxt = appModel.AdvantageShoppingApplication().LoginLabel().getText();

    	if (loginTxt.equals("LOG IN"))
        {
              if(!quiet){
                  appModel.AdvantageShoppingApplication().LoginObj().tap();
                  appModel.AdvantageShoppingApplication().UserNameLoginditField().setText(UNAME);
                  appModel.AdvantageShoppingApplication().PasswordLoginEditField().setText(PASS);

                  appModel.AdvantageShoppingApplication().LOGINButton().tap();
                  Thread.sleep(2000);
                  if(!appModel.AdvantageShoppingApplication().InvalidUserNameOrPasLabel().exists(3)){
                      Print(UNAME + " - Login success");
                      Verify.isTrue(true,"Sign in" , "verify that user sign in success");

                      return true;
                  }
                  Verify.isTrue(true,"Invalid Sign in" , "verify that user can't sign in with invalid user");
                  return false;


              }
              return false;

        }
        appModel.AdvantageShoppingApplication().MenuButton().tap();
        return  true;





    }


    public void Print(String msg){
    	System.out.println(msg);
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
            if (deviceInfo.getOSType().equals("IOS") && version >= 900) {
                deviceID = deviceInfo.getId();
                break;
            }

        }

        device = MobileLab.lockDeviceById(deviceID);// ID For galaxy S6


        // Describe the AUT.
        app = device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.iShopping").packaged(true).build());

        //connect between the appModel and the device
        appModel = new AdvantageIOSApp(device);

        app.install();



    }

    //use this in local testing

    public static void  InitBeforeclassLocal() throws GeneralLeanFtException {


        //device = MobileLab.lockDeviceById("05157df581dae805");// ID For galaxy S6
        device = MobileLab.lockDeviceById("98b098dc0c6c797784460d811cffc6de7b887c8d");// ID iPhone S6

        // Describe the AUT.
        app = device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.iShopping").packaged(true).build());

        //connect between the appModel and the device
        appModel = new AdvantageIOSApp(device);

    }
    public void SignOut() throws GeneralLeanFtException, InterruptedException {
        if(SignIn(true)){

            appModel.AdvantageShoppingApplication().LoginLabel().tap();
            appModel.AdvantageShoppingApplication().YesSignOutButton().tap();
            Verification(Verify.isTrue(!SignIn(true),"Sign Out " , "verify that user sign out from app"));




        }

    }

    public void CreateNewUser() throws GeneralLeanFtException, InterruptedException {

        //appModel.AdvantageShoppingApplication().LoginObj().tap();
        appModel.AdvantageShoppingApplication().SignUpButton().tap();
        appModel.AdvantageShoppingApplication().UserNameSignUpEditField().setText(UNAME);
        appModel.AdvantageShoppingApplication().EmailSignUpEditField().setText(UNAME + "@default.com");
        appModel.AdvantageShoppingApplication().PasswordSignUpEditField().setText(PASS);
        appModel.AdvantageShoppingApplication().ConfirnPasswordSignUpEditField().setText(PASS);

        device.swipe(SwipeDirection.UP);
        device.swipe(SwipeDirection.UP);

        /*appModel.AdvantageShoppingApplication().StreetSignUpEditField().setText("Altalef 5");
        appModel.AdvantageShoppingApplication().CitySignUpEditField().setText("Yahud");
        appModel.AdvantageShoppingApplication().ZIPSignUpEditField().setText("454545");

        appModel.AdvantageShoppingApplication().CountryLabel().tap();
        appModel.AdvantageShoppingApplication().CountryDropDown().select("Andora");*/
        appModel.AdvantageShoppingApplication().UseMyLocationLabel().tap();
        Thread.sleep(2000);

        appModel.AdvantageShoppingApplication().REGISTERButton().tap();
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MenuObjUiObject());

        Verification(Verify.isTrue(SignIn(true),"Create New User" , "verify that user was created in success"));






    }



    public void CheckOut(String paymethod) throws GeneralLeanFtException {

        appModel.AdvantageShoppingApplication().CarticonButton().tap();
        appModel.AdvantageShoppingApplication().CHECKOUTButton().tap();

        waitUntilElementExists(appModel.AdvantageShoppingApplication().PaymentEditUiObject());
        appModel.AdvantageShoppingApplication().PaymentEditUiObject().tap();

        if(paymethod.equals("safepay")){
            // pay with safepay

            SafePay(false);
        }

        // todo: need to separate the images of safepay and master credit
        else {
            // pay with master credit

            MasterCredit("1234567812347894" ,"458" ,UNAME,  false);
        }





    }




    public void SafePay(boolean save) throws GeneralLeanFtException {

        //appModel.AdvantageShoppingApplication().safepay().tap();
        appModel.AdvantageShoppingApplication().SAFEPAYUSERNAMEEditField().setText(UNAME);
        appModel.AdvantageShoppingApplication().SAFEPAYPASSWORDEditField().setText(PASS);
        if(!save)
            appModel.AdvantageShoppingApplication().SaveSafePAyCredentialsLabel().tap();

        appModel.AdvantageShoppingApplication().APPLYButton().tap();
        appModel.AdvantageShoppingApplication().PAYNOWButton().tap();
        waitUntilElementExists(appModel.AdvantageShoppingApplication().OrderObj());

        Verification(Verify.isTrue(appModel.AdvantageShoppingApplication().OrderObj().exists(),"Purchase safepay" , "verify that user purchased in success using safepay"));
        appModel.AdvantageShoppingApplication().OkButton().tap();



    }
    public void MasterCredit(String cardnum, String CVV , String holdername, boolean save) throws GeneralLeanFtException {
        //appModel.AdvantageShoppingApplication().MasterCredit().tap();
        appModel.AdvantageShoppingApplication().CARDNUMBEREditField().setText(cardnum);
        appModel.AdvantageShoppingApplication().CVVNUMBEREditField().setText(CVV);

        appModel.AdvantageShoppingApplication().MmLabel().tap();
        appModel.AdvantageShoppingApplication().MonthDropDown().select("07");
        appModel.AdvantageShoppingApplication().DoneButton().tap();

        appModel.AdvantageShoppingApplication().YyyyLabel().tap();
        appModel.AdvantageShoppingApplication().YearDropDown().select("2019");
        appModel.AdvantageShoppingApplication().DoneButton().tap();

        appModel.AdvantageShoppingApplication().CARDHOLDERNAMEEditField().setText(holdername);

        if(!save)
            appModel.AdvantageShoppingApplication().SaveMasterCreditCredenLabel().tap();

        appModel.AdvantageShoppingApplication().APPLYButton().tap();
        waitUntilElementExists(appModel.AdvantageShoppingApplication().OrderObj());

        Verification(Verify.isTrue(appModel.AdvantageShoppingApplication().OrderObj().exists(2),"Purchase Master Credit" , "verify that user purchased in success using Master Credit"));
        appModel.AdvantageShoppingApplication().OkButton().tap();



    }


    public void Buy(Label category, UiObject item , String payment ) throws GeneralLeanFtException {

        appModel.AdvantageShoppingApplication().MenuButton().tap();
        category.tap();
        item.tap();
        appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();
        CheckOut(payment);




    }

    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException{

        if(!VerifyMethod)
            throw new GeneralLeanFtException("varfication ERORR - verification of test fails! check runresults.html");
    }

    public boolean waitUntilElementExists(UiObject appElem) throws GeneralLeanFtException
    {
        return WaitUntilTestObjectState.waitUntil(appElem,new WaitUntilTestObjectState.WaitUntilEvaluator<UiObject>(){
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


}