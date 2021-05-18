package test.java.Steps;

import test.java.APIs.AccountGroupsAPI;
import test.java.TestBase.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("deprecation")
public class AccountGroupsSteps extends TestBase {

	
	@Before
	public void setup() {
		init();
	}

	@Given("User creates account group providing cloud type as {string} account group name as {string} description as {string} account names as {string} {string} {string}")
	public void user_creates_account_group_providing_cloud_type_as_account_group_name_as_description_as_account_names_as(String cloudType, String groupName, String description, String accName1, String accName2, String accName3) {
	   TestBase.response= AccountGroupsAPI.createAccountGroupApi(cloudType, groupName, description, accName1, accName2, accName3);
	}
	@Then("verify the status is {int}")
	public void verify_the_status_is(int status_code) {
	   Assert.assertEquals(TestBase.response.getStatusCode(), status_code);
	}
	@Then("verify the error message is {string}")
	public void verify_the_error_message_is(String error_message) {
		//Assert.assertEquals(TestBase.response.jsonPath().get("message").toString(), error_message);
		Assert.assertTrue(TestBase.response.jsonPath().get("message").toString().contains(error_message));
	}
	
	@When("User deletes account group by getting group id using cloud type {string} and name {string}")
	public void user_deletes_account_group_by_getting_group_id_using_cloud_type_and_name(String cloudType, String accountGroupName) {
	    String groupId = AccountGroupsAPI.getAccountGroupId(cloudType, accountGroupName);
	    TestBase.response = AccountGroupsAPI.deleteAccountGroupApi(groupId);
	}

	@Then("get the list of account groups providing cloud type as {string} account group name as {string}")
	public void get_the_list_of_account_groups_providing_cloud_type_as_account_group_name_as(String cloudType, String accountGroupName) {
		TestBase.response = AccountGroupsAPI.listAccountGroupsDefault(cloudType,accountGroupName);
	}
	
	@After
	public void tearDown() {
		AccountGroupsAPI.deleteAllAccountGroups();
	}
}
