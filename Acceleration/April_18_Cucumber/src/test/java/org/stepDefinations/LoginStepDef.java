 package org.stepDefinations;

import org.Pages.LoginPage;
import org.applicationHooks.AppHooks;
import org.factory.DriverFactory;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {
	private LoginPage lp_Obj = new LoginPage(DriverFactory.getDriver());
	@Given("User is on login page")
	public void user_is_on_login_page() {
		DriverFactory.getDriver().get(AppHooks.prop.getProperty("url"));
	}
	@Then("User name field is displayed")
	public void user_name_field_is_displayed() {
		Assert.assertTrue(lp_Obj.isUserNameFieldDisplayed());
	}
	@Then("Password field is displayed")
	public void password_field_is_displayed() {
		Assert.assertTrue(lp_Obj.isPasswordFieldDisplayed());
	}  
	@Then("Keep me login check box is displayed")
	public void keep_me_login_check_box_is_displayed() {
		Assert.assertTrue(lp_Obj.isKeepMeLoginChkBoxDisplayed());
	}
	@Then("login button is displayed")
	public void login_button_is_displayed() {
		Assert.assertTrue(lp_Obj.isLoginBtnDisplayed());
	}
	@Then("login logos is displayed")
	public void login_logos_is_displayed() {
		Assert.assertTrue(lp_Obj.isLoginPageLogosDisplayed());
	}
	@When("User enters username")
	public void User_enters_username() {
		lp_Obj.enterUserName(AppHooks.prop.getProperty("userName"));
	}
	@When("User enters username password")
	public void user_user_enters_username_password() {
		lp_Obj.enterUserName(AppHooks.prop.getProperty("password"));
	}
	@When("User clicks on Login button")
	public void user_clicks_on_login_button() {
		lp_Obj.clickOnLoginBtn();
	}
}