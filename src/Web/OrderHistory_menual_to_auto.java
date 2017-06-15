package Web;


import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.*;


import com.hp.lft.verifications.*;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import unittesting.*;



@RunWith(Cucumber.class)
public class OrderHistory_menual_to_auto extends UnitTestClassBase {

    AdvantageWebTest webtests = new AdvantageWebTest();
    String ProductName = "";

    public OrderHistory_menual_to_auto() {
        //Change this constructor to private if you supply your own public constructor
    }


    @Before
    public void setUp(Scenario scenario) throws Exception {
        Reporter.startTest(scenario.getName());
        webtests.initBeforeTest();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Given("^user is logged in$")
    public void Login() throws GeneralLeanFtException, InterruptedException {

        webtests.signIn();

    }

    @When("^user buy an item and preform checkout.$")
    public void BuyAndCheckOut() throws GeneralLeanFtException, InterruptedException {

        webtests.selectItemToPurchase(webtests.appModel.AdvantageShoppingPage().LAPTOPSWebElement(),webtests.appModel.AdvantageShoppingPage().laptopFororderService());
        ProductName = webtests.appModel.AdvantageShoppingPage().LaptopName().getInnerText();
    }

    @Then("^get a success massege - the purchese success .$")
    public void thenResultShouldBe() throws GeneralLeanFtException {


        int numberOfitems = webtests.getCartProductsNumberFromCartObjectInnerText();
        Verify.isTrue(numberOfitems==0, "BuyAndCheckOut Success" );

    }



}