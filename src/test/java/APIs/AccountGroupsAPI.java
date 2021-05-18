package test.java.APIs;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import test.java.TestBase.TestBase;
import test.java.pojos.AccountGroups;

import io.restassured.response.Response;

public class AccountGroupsAPI extends TestBase {
	
	public static ArrayList<String> groupsAdded = new ArrayList<String>();
	
	public static Response createAccountGroupApi(String cloudType, String groupName, String description, String accName1, String accName2, String accName3) {
//		JSONObject jsonObject = new JSONObject();
//        jsonObject.put("groupName", data.get("groupName"));
//        jsonObject.put("cloudType",data.get("cloudType") );
//        jsonObject.put("Description", data.get("Description"));
//
//        JSONArray acc_names = new JSONArray();
//        acc_names.put(data.get("AccName1"));
//        acc_names.put(data.get("AccName2"));
//        acc_names.put(data.get("AccName3"));
//
//        jsonObject.put("cloudAccounts",acc_names);
		AccountGroups accountGroup = new AccountGroups(groupName,cloudType,description);
        accountGroup.setAccNames(accName1,accName2,accName3);
        Response response = given()
                .header("Authorization",
                        "Bearer " + config.getProperty("validAccessKey")).contentType("application/json")
                .body(accountGroup)
                .post("/account-groups");
        if(response.statusCode()==200) {
        	groupsAdded.add(cloudType+"##"+groupName);
        }
        return response;
	}
	
	public static Response listAccountGroupsApi(String page,String pageSize, String sortOn,String sortDirection, String cloudType, String searchString) {
		Response response = given()
                .header("Authorization",
                        "Bearer " + config.getProperty("validAccessKey")).contentType("application/json").queryParam("page", page).queryParam("pageSize", pageSize).queryParam("sortOn", sortOn)
                .queryParam("sortDirection", sortDirection).queryParam("cloudType", cloudType).queryParam("searchString", searchString)
                .get("/account-groups/list/search");
		return response;
	}
	
	public static String getAccountGroupId(String cloudType, String accountGroupName) {
		
		Response response = listAccountGroupsApi("1","500","groupName","ASC",cloudType,accountGroupName);
		String groupId = response.jsonPath().getString("accountGroups[0].groupId");
		return groupId;
		
	}

	public static Response listAccountGroupsDefault(String cloudType, String accountGroupName){
		Response response = listAccountGroupsApi("1","500","groupName","ASC",cloudType,accountGroupName);
		return response;
	}
	
	public static Response deleteAccountGroupApi(String groupId) {
		Response response = given()
                .header("Authorization",
                        "Bearer " + config.getProperty("validAccessKey")).contentType("application/json").pathParam("id", groupId)
                .delete("/account-groups/{id}");
		return response;
	}
	
	public static void deleteAllAccountGroups() {
		for(int i=0;i<groupsAdded.size();i++) {
			String cloudType = groupsAdded.get(i).split("##")[0];
			String groupName = groupsAdded.get(i).split("##")[1];
			String groupId=getAccountGroupId(cloudType,groupName);
			Response response = deleteAccountGroupApi(groupId);
			groupsAdded.remove(i);
		}
	}
	
}
