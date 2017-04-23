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
	
	
	static String UNAME = "Mercury";
	static String PASS = "Mercury";
	
	
	

    public IOSTests() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new IOSTests();
        globalSetup(IOSTests.class);
        
         device = MobileLab.lockDeviceById("9746992f742625a7f4e8ef18300bbd5956e809a6");// ID For iPhone 5s
        
        // Describe the AUT.
         app = device.describe(Application.class, new ApplicationDescription.Builder()
         .identifier("com.hpe.advantage").build());
         
         appModel = new AdvantageIOSApp(device);
        
      
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
    	
    	
    }

    @Test
    public void signIn() throws GeneralLeanFtException {
    	
    	SignIn();
    	
    	
    }
    
    
    
    public void SignIn() throws GeneralLeanFtException{
    	appModel.AdvantageShoppingApplication().MenuButton().tap();
    	appModel.AdvantageShoppingApplication().LOGIN().tap();
    	appModel.AdvantageShoppingApplication().UserNameEditField().setText(UNAME);
    	//appModel.AdvantageShoppingApplication().PASSWORDEditField().setText(PASS);
    	
    	appModel.AdvantageShoppingApplication().LOGINButton().tap();
    	Print(UNAME + " - Login success");
    	
    	
    	
    	
    }
    
    
    public void Print(String msg){
    	System.out.println(msg);
    }
    

}