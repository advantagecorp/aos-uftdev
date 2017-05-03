package Mobile;

import Web.AdvantageStagingAppModel;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.WaitUntilTestObjectState;
import com.hp.lft.sdk.mobile.Device;
import com.hp.lft.sdk.mobile.MobileLab;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import com.hp.lft.sdk.web.WebElement;
import com.hp.lft.verifications.Verify;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gadian on 01/05/2017.
 */
public class MobileWeb {

    static AdvantageStagingAppModel appModel;
    static Device device;
    static Browser browser;
    String  appUrl;


   public MobileWeb(String deviceID , String Url) throws GeneralLeanFtException {

       device   = MobileLab.lockDeviceById(deviceID);
       browser  = BrowserFactory.launch(BrowserType.CHROME,device);
       appUrl   = Url;

       //navigate to Advantage online
       browser.navigate(appUrl);
       appModel = new AdvantageStagingAppModel(browser);


    }



    public boolean signIn(String USERNAME , String PASSWORD) throws GeneralLeanFtException, InterruptedException
    {
        boolean isSignedIn = true;

        waitUntilElementExists(appModel.AdvantageShoppingPage().MobileBtnWeb());

        if(!isSignedIn())
        {
            // Click the sign-in icon
            appModel.AdvantageShoppingPage().MobileBtnWeb().click();
            appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
            waitUntilElementExists(appModel.AdvantageShoppingPage().SIGNINButton());
            // Fill in the user name and password
            appModel.AdvantageShoppingPage().UsernameLoginEditField().setValue(USERNAME);
            appModel.AdvantageShoppingPage().PasswordLoginEditField().setValue(PASSWORD);
            // Check the Remember Me checkbox
            appModel.AdvantageShoppingPage().RememberMeCheckBox().set(true);
            // Click on sign in button
            appModel.AdvantageShoppingPage().SIGNINButton().click();

            Thread.sleep(2000);

            isSignedIn = isSignedIn();
            Verify.isTrue(isSignedIn,"Verification - Sign In Mobile web"  ,"Verify that the user " + USERNAME + " signed in properly.");


        }

        return isSignedIn;
    }

    public boolean isSignedIn() throws GeneralLeanFtException
    {
        waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

        String loggedInUserName = getUsernameFromSignOutElement();

        if(loggedInUserName.isEmpty())
            return false;

        return true;
    }

    public String getUsernameFromSignOutElement() throws GeneralLeanFtException
    {
        // Get the regular expression pattern from the Sign in Out object design time description
        String pattern = appModel.AdvantageShoppingPage().SignOutMainIconWebElement().getDescription().getInnerText().toString();
        // Get the actual inner text of the Sign in Out object during runtime
        String signInOutIconElementInnerText = appModel.AdvantageShoppingPage().SignOutMainIconWebElement().getInnerText();

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object
        Matcher m = r.matcher(signInOutIconElementInnerText);
        m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String loggedInUserName = m.group(1).trim();

        return loggedInUserName;
    }


    public void Login() throws GeneralLeanFtException {





    }





    public boolean waitUntilElementExists(WebElement webElem) throws GeneralLeanFtException
    {
        return WaitUntilTestObjectState.waitUntil(webElem,new WaitUntilTestObjectState.WaitUntilEvaluator<WebElement>(){
            public boolean evaluate(WebElement we){
                try{
                    return we.exists() && we.isVisible();
                }
                catch(Exception e){
                    return false;
                }
            }
        });
    }

}
