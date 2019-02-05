package form;

import driver.DriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import pages.PostFormPage;
import pages.ResponsePage;
import service.CustomJson;
import service.StringExtension;
import service.model.PizzaSize;
import service.model.PizzaTopping;
import service.model.PostForm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPostForm {
    @Before
    public void setUp() {
        DriverManager.getInstance().navigate().to("http://0.0.0.0/forms/post");
    }

    @AfterClass
    public static void tearDown() {
        DriverManager.getInstance().close();
    }

    @Test
    public void noFields() {
        // setup
        PostFormPage postForm = new PostFormPage();
        postForm.clickSubmitOrder();

        // verify
        ResponsePage responsePage = new ResponsePage();
        responsePage.getViewRawData().click();
        PostForm actualResponse = new CustomJson(responsePage.getResponseJson().getText()).getPostForm();
        PostForm expectedResponse = PostForm.builder().build();
        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void allFields() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String customerName = StringExtension.generateString();
        String telephone = StringExtension.generatePhone();
        String email = StringExtension.generateEmail();
        String size = StringExtension.randomEnum(PizzaSize.class).toString();
        String[] toppings = StringExtension.enumToArray(PizzaTopping.class);
        String deliveryTime = String.format("%s:00", StringExtension.generateNumber(11, 21));
        String comments = String.format("%s\r\n%s", StringExtension.generateString(), StringExtension.generateString());

        // populate form
        postForm.getCustomerName().sendKeys(customerName);
        postForm.getCustomerTel().sendKeys(telephone);
        postForm.getCustomerEmail().sendKeys(email);
        postForm.setPizzaSize(size);
        postForm.setPizzaToppings(toppings);
        postForm.getDeliveryTime().sendKeys(deliveryTime);
        postForm.getDeliveryInstructions().sendKeys(comments);
        postForm.clickSubmitOrder();

        // verify
        ResponsePage responsePage = new ResponsePage();
        responsePage.getViewRawData().click();
        PostForm actualResponse = new CustomJson(responsePage.getResponseJson().getText()).getPostForm();
        PostForm expectedResponse = PostForm.builder()
                .custname(customerName)
                .custtel(telephone)
                .custemail(email)
                .size(size)
                .topping(toppings)
                .delivery(deliveryTime)
                .comments(comments)
                .build();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void invalidEmailAddress() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String email = StringExtension.generateString();

        // populate form
        postForm.getCustomerEmail().sendKeys(email);
        postForm.clickSubmitOrder();

        // verify
        String validationMessage = postForm.getValidationMessage();
        assertEquals("Please enter an email address.", validationMessage);
    }

    @Test
    public void tooEarlyDeliveryTime() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String deliveryTime = String.format("%02d:00", StringExtension.generateNumber(0, 10));

        // populate form
        postForm.getDeliveryTime().sendKeys(deliveryTime);
        postForm.clickSubmitOrder();

        // verify
        String validationMessage = postForm.getValidationMessage();
        assertEquals("Please select a value that is no earlier than 11:00.", validationMessage);
    }

    @Test
    public void tooLateDeliveryTime() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String deliveryTime = String.format("%s:00", StringExtension.generateNumber(22, 23));

        // populate form
        postForm.getDeliveryTime().sendKeys(deliveryTime);
        postForm.clickSubmitOrder();

        // verify
        String validationMessage = postForm.getValidationMessage();
        assertEquals("Please select a value that is no later than 21:00.", validationMessage);
    }

    @Test
    public void invalidDeliveryTimeFormat() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String deliveryTime = String.format("%s:01", StringExtension.generateNumber(11, 20));

        // populate form
        postForm.getDeliveryTime().sendKeys(deliveryTime);
        postForm.clickSubmitOrder();

        // verify
        String validationMessage = postForm.getValidationMessage();
        assertTrue(validationMessage.contains("Please select a valid value."));
    }

}
