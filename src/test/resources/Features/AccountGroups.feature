Feature: Account Groups
  QA Team wants to verify the CRUD Operations in Account Groups

  Scenario Outline: Validate account group creation for all cloud providers with valid data
    Given User creates account group providing cloud type as "<cloudType>" account group name as "<groupName>" description as "<description>" account names as "<accName1>" "<accName2>" "<accName3>"
    Then verify the status is 200
    And get the list of account groups providing cloud type as "<cloudType>" account group name as "<groupName>"
    And response includes the following in any order
    | accountGroups[0].groupName        |  <groupName>|
    | accountGroups[0].numCloudAccounts | 3           |
    | accountGroups[0].cloudType        | <cloudType> |


    Examples: 
      | cloudType | groupName      | description            | accName1               | accName2   | accName3   |
      | AWS       | Test AWS Group | Test Automation Group1 | fling-m                | fling-n    | fling-s    |
      | GCP       | Test GCP Group | Test Automation Group2 | mohan-test-gcp-project | c3m-demo-2 | c3m-demo-1 |
      | AZURE     | Test Azu Group | Test Automation Group3 | azure-01               | azure-02   | azure-03   |
      
  Scenario: Validate error message when account group created with duplicate name
  	Given User creates account group providing cloud type as "AWS" account group name as "Test Duplicate Group" description as "Test Duplicate Group" account names as "fling-t" "fling-n" "fling-s"
  	When  User creates account group providing cloud type as "AWS" account group name as "Test Duplicate Group" description as "Test Duplicate Group" account names as "fling-t" "fling-n" "fling-s"
  	Then verify the status is 422
  	Then verify the error message is "Failed to add account group with name Test Duplicate Group. Account group already exists."

