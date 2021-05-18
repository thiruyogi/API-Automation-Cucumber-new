package test.java.pojos;

import java.util.ArrayList;

public class AccountGroups {
	
	private String groupName;
	private String cloudType;
	private String Description;
	private ArrayList<String> cloudAccounts ;
	
	public AccountGroups(String groupName,String cloudType,String Description)
	{
		this.groupName = groupName;
		this.cloudType = cloudType;
		this.Description = Description;
		this.cloudAccounts = new ArrayList<String>();
	}
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getCloudType() {
		return cloudType;
	}
	public void setCloudType(String cloudType) {
		this.cloudType = cloudType;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public ArrayList<String> getCloudAccounts() {
		return cloudAccounts;
	}
	public void setCloudAccounts(ArrayList<String> cloudAccounts) {
		this.cloudAccounts = cloudAccounts;
	}
	
	public void setAccNames(String...accNames) {
		for(int i=0;i<accNames.length;i++) {
			this.cloudAccounts.add(accNames[i]);
		}
	}

}
