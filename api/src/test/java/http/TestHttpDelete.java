package http;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHttpDelete {

    @Test
    public void performDelete() {
        DeleteHttp deleteHttp = new DeleteHttp();
        int statusCode = deleteHttp.perform();
        assertEquals(200, statusCode);
    }
}
