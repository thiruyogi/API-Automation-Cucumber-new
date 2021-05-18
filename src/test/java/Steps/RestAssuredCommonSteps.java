package test.java.Steps;

import test.java.TestBase.TestBase;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredCommonSteps extends TestBase {


    @Before
    public void setup() {
        init();
    }

    @And("response includes the following in any order")
    public void response_contains_in_any_order(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                assertThat(TestBase.response.jsonPath().get(field.getKey()), Matchers.<Object>equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                assertThat(TestBase.response.jsonPath().get(field.getKey()), Matchers.<Object>equalTo(field.getValue()));
                //TestBase.response.then().body(field.getKey(), containsInAnyOrder(field.getValue()));
            }
        }
    }

    @And("response includes the following$")
    public void response_equals(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                TestBase.response.then().body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                TestBase.response.then().body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }

}
