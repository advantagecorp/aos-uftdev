package NET;

import static org.junit.Assert.*;


import com.hp.lft.sdk.wpf.UiObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.winforms.*;
import com.hp.lft.verifications.*;

import unittesting.*;

import java.io.IOException;

public class DotNetTests extends UnitTestClassBase {


    private static DotNetAppModel appModel;
    private static ProcessBuilder window;

    private static String applocation = "C:\\Users\\gadian\\Desktop\\AOS\\2017_05_24_AdvantageShopAdministrator\\Debug\\AdvantageShopAdministrator.exe";
    private static String SERVER = "http://52.32.172.3:8080";

    public DotNetTests()  {
        //Change this constructor to private if you supply your own public constructor


    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new DotNetTests();
        globalSetup(DotNetTests.class);

        InitBeforeClass();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
        window.command("close");
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    ///////////////////////////////////////////// START TESTS //////////////////////////////////

    @Test
    public void AddColorAndQuantityTest() throws GeneralLeanFtException, InterruptedException {

        ///just for test the list object
        appModel.AdvantageShopAdministrator().SideList().select("PRODUCTS");
        Thread.sleep(3000);
        waitUntilElementExists(appModel.AdvantageShopAdministrator().PRODUCTSUiObject());
        appModel.AdvantageShopAdministrator().ProductsDataGridTable().selectCell(9,"Name");

        appModel.AdvantageShopAdministrator().ProductsWpfTabStrip().select("CUSTOMISATION");

        appModel.AdvantageShopAdministrator().ADDCOLORSButton().click();
        appModel.AdvantageShopAdministrator().YelloeCheckBox().click();
        //add quantity
        /////

        appModel.AdvantageShopAdministrator().ProductsColorsGridTable().selectCell(2,"QUANTITY");
        appModel.AdvantageShopAdministrator().SAVEButton().click();
        appModel.AdvantageShopAdministrator().SAVEButton().click();

    }


    ///////////////////////////////////////////// END TESTS //////////////////////////////////


    public static void InitBeforeClass() throws IOException, GeneralLeanFtException {

        appModel = new DotNetAppModel();
        window  = new ProcessBuilder(applocation);

        window.start();
        SignIn();

    }


    public static void SignIn() throws GeneralLeanFtException {
        appModel.AdvantageShopAdministrator().UserNameEditField().setText("admin");
        appModel.AdvantageShopAdministrator().PasswordEditField().setText("adm1n");
        appModel.AdvantageShopAdministrator().ServerEditField().setText(SERVER);

        appModel.AdvantageShopAdministrator().SIGNINButton().click();
        appModel.AdvantageShopAdministrator().SIGNINButton().click();
        waitUntilElementExists(appModel.AdvantageShopAdministrator().PRODUCTSUiObject());


    }




    public static boolean waitUntilElementExists(UiObject appElem) throws GeneralLeanFtException
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

    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException{

        if(!VerifyMethod)
            throw new GeneralLeanFtException("varfication ERORR - verification of test fails! check runresults.html");
    }
}