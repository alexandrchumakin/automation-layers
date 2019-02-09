package http;

import org.junit.Test;
import service.StringExtension;
import service.model.HttpPostObject;
import service.model.PizzaSize;
import service.model.PizzaTopping;
import service.model.PostForm;

import static org.junit.Assert.assertEquals;

public class TestHttpPost {
    @Test
    public void noFields() {
        PostForm requestBody = PostForm.builder().build();
        PostHttp postHttp = new PostHttp();
        HttpPostObject responseForm = postHttp.perform(requestBody);

        assertEquals(requestBody.toHttpObject(), responseForm);
    }

    @Test
    public void allFields() {
        String customerName = StringExtension.generateString();
        String telephone = StringExtension.generatePhone();
        String email = StringExtension.generateEmail();
        String size = StringExtension.randomEnum(PizzaSize.class).toString();
        String[] toppings = StringExtension.enumToArray(PizzaTopping.class);
        String deliveryTime = String.format("%s:00", StringExtension.generateNumber(11, 21));
        String comments = String.format("%s\r\n%s", StringExtension.generateString(), StringExtension.generateString());
        PostForm requestBody = PostForm.builder()
                .custname(customerName)
                .custtel(telephone)
                .custemail(email)
                .size(size)
                .topping(toppings)
                .delivery(deliveryTime)
                .comments(comments)
                .build();
        PostHttp postHttp = new PostHttp();
        HttpPostObject responseForm = postHttp.perform(requestBody);

        assertEquals(requestBody.toHttpObject(), responseForm);
    }

    @Test
    public void randomEmail() {
        String email = StringExtension.generateString();
        PostForm requestBody = PostForm.builder()
                .custemail(email)
                .build();
        PostHttp postHttp = new PostHttp();
        HttpPostObject responseForm = postHttp.perform(requestBody);

        assertEquals(requestBody.toHttpObject(), responseForm);
    }

    @Test
    public void randomName() {
        String name = StringExtension.generateString();
        PostForm requestBody = PostForm.builder()
                .custname(name)
                .build();
        PostHttp postHttp = new PostHttp();
        HttpPostObject responseForm = postHttp.perform(requestBody);

        assertEquals(requestBody.toHttpObject(), responseForm);
    }

    @Test
    public void randomPhone() {
        String phone = StringExtension.generateString();
        PostForm requestBody = PostForm.builder()
                .custtel(phone)
                .build();
        PostHttp postHttp = new PostHttp();
        HttpPostObject responseForm = postHttp.perform(requestBody);

        assertEquals(requestBody.toHttpObject(), responseForm);
    }

    @Test
    public void randomPizzaSize() {
        String size = StringExtension.generateString();
        PostForm requestBody = PostForm.builder()
                .size(size)
                .build();
        PostHttp postHttp = new PostHttp();
        HttpPostObject responseForm = postHttp.perform(requestBody);

        assertEquals(requestBody.toHttpObject(), responseForm);
    }

    @Test
    public void multiplePizzaToppings() {
        String[] toppings = StringExtension.enumToArray(PizzaTopping.class);
        PostForm requestBody = PostForm.builder()
                .topping(toppings)
                .build();
        PostHttp postHttp = new PostHttp();
        HttpPostObject responseForm = postHttp.perform(requestBody);

        assertEquals(requestBody.toHttpObject(), responseForm);
    }

    @Test
    public void randomDeliveryInstructions() {
        String comments = StringExtension.generateString();
        PostForm requestBody = PostForm.builder()
                .comments(comments)
                .build();
        PostHttp postHttp = new PostHttp();
        HttpPostObject responseForm = postHttp.perform(requestBody);

        assertEquals(requestBody.toHttpObject(), responseForm);
    }

    @Test
    public void randomDeliveryTime() {
        String deliveryTime = StringExtension.generateString();
        PostForm requestBody = PostForm.builder()
                .delivery(deliveryTime)
                .build();
        PostHttp postHttp = new PostHttp();
        HttpPostObject responseForm = postHttp.perform(requestBody);

        assertEquals(requestBody.toHttpObject(), responseForm);
    }

}
