package advantagetestsmobile;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hp.lft.sdk.*;
import com.hp.lft.verifications.*;

import junitAdvantageMobileApp.AdvantageMobileAppModel;
import junitAdvantageStagingAppModel.AdvantageStagingAppModel;
import unittesting.*;
import com.hp.lft.sdk.mobile.*;

public class AdvantageMobileTest extends UnitTestClassBase {

	AdvantageMobileAppModel appModel;
	
	public AdvantageMobileTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new AdvantageMobileTest();
        globalSetup(AdvantageMobileTest.class);
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
    	
    	//Lock device by its Mobile Center name. 
    	Device device = MobileLab.lockDeviceById("015d46d94f241611");
    			
    	//Describe the AUT to use. 
    	Application app = device.describe(Application.class, new ApplicationDescription.Builder().identifier("com.Advantage.aShopping").packaged(false).build());

    	// Instantiate the application model object
    	appModel = new AdvantageMobileAppModel(app);
    	
		//Launch or restart the app
		//app.restart();
    	app.launch();
		
    	Label laptopsLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("LAPTOPS").className("Label").resourceId("com.Advantage.aShopping:id/textViewCategory").mobileCenterIndex(1).build());
		
    	
    	Label speakersLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("SPEAKERS").className("Label").resourceId("com.Advantage.aShopping:id/textViewCategory").mobileCenterIndex(2).build());
    	
    	Label tabletsLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("TABLETS").className("Label").resourceId("com.Advantage.aShopping:id/textViewCategory").mobileCenterIndex(3).build());
    	
    	Label headphonesLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("HEADPHONES").className("Label").resourceId("com.Advantage.aShopping:id/textViewCategory").mobileCenterIndex(3).build());
    	
    	Label miceLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("MICE").className("Label").resourceId("com.Advantage.aShopping:id/textViewCategory").mobileCenterIndex(4).build());
    	
    	UiObject mainMenu = app.describe(UiObject.class, new UiObjectDescription.Builder()
    			.className("ImageView").resourceId("com.Advantage.aShopping:id/imageViewMenu").mobileCenterIndex(0).build());
    	
    	UiObject cartAccess = app.describe(UiObject.class, new UiObjectDescription.Builder()
    			.accessibilityId("Cart access").className("ImageView").resourceId("com.Advantage.aShopping:id/imageViewCart").mobileCenterIndex(1).build());
    	
    	EditField searchEditBox = app.describe(EditField.class, new EditFieldDescription.Builder()
    			.className("Input").resourceId("com.Advantage.aShopping:id/editTextSearch").mobileCenterIndex(0).build());
    	
    	UiObject searchIcon = app.describe(UiObject.class, new UiObjectDescription.Builder()
    			.className("ImageView").resourceId("com.Advantage.aShopping:id/imageViewSearch").mobileCenterIndex(2).build());
    	
    	Button serverNotReachableOKButton = app.describe(Button.class, new ButtonDescription.Builder()
    			.text("OK").className("Button").resourceId("android:id/button1").mobileCenterIndex(0).build());
    	
    	
    	EditField searchMenuEditBox = app.describe(EditField.class, new EditFieldDescription.Builder()
    			.className("Input").resourceId("com.Advantage.aShopping:id/editViewMenuSearch").mobileCenterIndex(1).build());
    	
    	Label loginMenuLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("LOGIN").className("Label").resourceId("com.Advantage.aShopping:id/textViewMenuUser").mobileCenterIndex(6).build());
    	
    	Label homeMenuLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("HOME").className("Label").resourceId("com.Advantage.aShopping:id/textViewMenuHome").mobileCenterIndex(7).build());
    	
    	
    	Label laptopsMenuLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("LAPTOPS").className("Label").resourceId("com.Advantage.aShopping:id/textViewMenuLaptops").mobileCenterIndex(9).build());
    	
    	Label speakersMenuLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("SPEAKERS").className("Label").resourceId("com.Advantage.aShopping:id/textViewMenuSpeakers").mobileCenterIndex(10).build());
    	
    	Label tabletsMenuLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("TABLETS").className("Label").resourceId("com.Advantage.aShopping:id/textViewMenuTablets").mobileCenterIndex(11).build());
    	
    	Label miceMenuLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("MICE").className("Label").resourceId("com.Advantage.aShopping:id/textViewMenuMice").mobileCenterIndex(12).build());
    	
    	Label headphonesMenuLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("HEADPHONES").className("Label").resourceId("com.Advantage.aShopping:id/textViewMenuHeadphones").mobileCenterIndex(13).build());
    	
    	Label cartMenuLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("CART").className("Label").resourceId("com.Advantage.aShopping:id/textViewMenuCart").mobileCenterIndex(14).build());
    	
    	Label settingsMenuLabel = app.describe(Label.class, new LabelDescription.Builder()
    			.text("SETTINGS").className("Label").resourceId("com.Advantage.aShopping:id/textViewMenuSettings").mobileCenterIndex(15).build());
    	
		int a=0;
    }

}