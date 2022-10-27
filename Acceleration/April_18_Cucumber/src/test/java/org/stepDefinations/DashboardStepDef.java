package org.stepDefinations;

import org.Pages.LoginPage;
import org.factory.DriverFactory;
import org.testng.Assert;

import io.cucumber.java.en.Then;


public class DashboardStepDef {	
	@Then("Dashboard page is displayed")
	public void dashboard_page_is_displayed() {
		LoginPage lp_Obj = new LoginPage(DriverFactory.getDriver());
		Assert.assertTrue(lp_Obj.verifyDashboardLogoIsDisplayed());
	}
	@Then("Dashboard URL is {string}")
	public void dashboard_url_is(String expURL) {

	}
	@Then("Dashboard Title is {string}")
	public void dashboard_title_is(String expTitle) {

	}
	@Then("Dashboard displayed {int} tabs")
	public void dashboard_displayed(Integer int1) {

	}
}