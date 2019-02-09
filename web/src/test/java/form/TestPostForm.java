package form;

import driver.DriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
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
    public void randomName() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String customerName = StringExtension.generateString();

        // populate form
        postForm.getCustomerName().sendKeys(customerName);
        postForm.clickSubmitOrder();

        // verify
        ResponsePage responsePage = new ResponsePage();
        responsePage.getViewRawData().click();
        PostForm actualResponse = new CustomJson(responsePage.getResponseJson().getText()).getPostForm();
        PostForm expectedResponse = PostForm.builder().custname(customerName).build();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void randomEmail() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String email = StringExtension.generateEmail();

        // populate form
        postForm.getCustomerEmail().sendKeys(email);
        postForm.clickSubmitOrder();

        // verify
        ResponsePage responsePage = new ResponsePage();
        responsePage.getViewRawData().click();
        PostForm actualResponse = new CustomJson(responsePage.getResponseJson().getText()).getPostForm();
        PostForm expectedResponse = PostForm.builder().custemail(email).build();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void randomPhone() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String telephone = StringExtension.generatePhone();

        // populate form
        postForm.getCustomerTel().sendKeys(telephone);
        postForm.clickSubmitOrder();

        // verify
        ResponsePage responsePage = new ResponsePage();
        responsePage.getViewRawData().click();
        PostForm actualResponse = new CustomJson(responsePage.getResponseJson().getText()).getPostForm();
        PostForm expectedResponse = PostForm.builder().custtel(telephone).build();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void randomPizzaSize() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String size = StringExtension.randomEnum(PizzaSize.class).toString();

        // populate form
        postForm.setPizzaSize(size);
        postForm.clickSubmitOrder();

        // verify
        ResponsePage responsePage = new ResponsePage();
        responsePage.getViewRawData().click();
        PostForm actualResponse = new CustomJson(responsePage.getResponseJson().getText()).getPostForm();
        PostForm expectedResponse = PostForm.builder().size(size).build();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void multiplePizzaToppings() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String[] toppings = StringExtension.enumToArray(PizzaTopping.class);

        // populate form
        postForm.setPizzaToppings(toppings);
        postForm.clickSubmitOrder();

        // verify
        ResponsePage responsePage = new ResponsePage();
        responsePage.getViewRawData().click();
        PostForm actualResponse = new CustomJson(responsePage.getResponseJson().getText()).getPostForm();
        PostForm expectedResponse = PostForm.builder().topping(toppings).build();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void randomDeliveryInstructions() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String comments = String.format("%s\r\n%s", StringExtension.generateString(), StringExtension.generateString());

        // populate form
        postForm.getDeliveryInstructions().sendKeys(comments);
        postForm.clickSubmitOrder();

        // verify
        ResponsePage responsePage = new ResponsePage();
        responsePage.getViewRawData().click();
        PostForm actualResponse = new CustomJson(responsePage.getResponseJson().getText()).getPostForm();
        PostForm expectedResponse = PostForm.builder().comments(comments).build();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void randomDeliveryTime() {
        // prepare data
        PostFormPage postForm = new PostFormPage();
        String deliveryTime = String.format("%s:00", StringExtension.generateNumber(11, 21));

        // populate form
        postForm.getDeliveryTime().sendKeys(deliveryTime);
        postForm.clickSubmitOrder();

        // verify
        ResponsePage responsePage = new ResponsePage();
        responsePage.getViewRawData().click();
        PostForm actualResponse = new CustomJson(responsePage.getResponseJson().getText()).getPostForm();
        PostForm expectedResponse = PostForm.builder().delivery(deliveryTime).build();
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
