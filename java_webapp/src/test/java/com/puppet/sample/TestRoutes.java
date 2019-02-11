package com.puppet.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;


public class TestRoutes
{
    @BeforeClass
    public static void beforeClass() throws Exception {
      App.main(null);
	    Thread.sleep(10000);
    }

    @AfterClass
    public static void afterClass() throws Exception {
	    Thread.sleep(1000);
      Spark.stop();
    }

    @Test
    public void testEnMsg() throws IOException {
        TestResponse res = request("GET", "/en");
        assertEquals(200, res.status);
        assertTrue(res.body.contains("Hello World!"));
	}

	@Test
    public void testSpMsg() throws IOException {
        TestResponse res = request("GET", "/sp");
        assertEquals(200, res.status);
        assertTrue(res.body.contains("¡Hola Mundo!"));
	}

	@Test
    public void testZhMsg() throws IOException {
        TestResponse res = request("GET", "/zh");
        assertEquals(200, res.status);
        assertTrue(res.body.contains("你好，世界!"));
	}

	@Test
    public void testHiMsg() throws IOException {
        TestResponse res = request("GET", "/hi");
        assertEquals(200, res.status);
        assertTrue(res.body.contains("नमस्ते दुनिया"));
	}

	@Test
    public void testArMsg() throws IOException {
        TestResponse res = request("GET", "/ar");
        assertEquals(200, res.status);
        assertTrue(res.body.contains("مرحبا بالعالم!"));
    }

	@Test
    public void testJpMsg() throws IOException {
        TestResponse res = request("GET", "/jp");
        assertEquals(200, res.status);
        assertTrue(res.body.contains("こんにちは世界!"));
    }

    private TestResponse request(String method, String path) throws java.io.IOException {
		try {
			URL url = new URL("http://localhost:9999" + path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.connect();
			String body = IOUtils.toString(connection.getInputStream());
			return new TestResponse(connection.getResponseCode(), body);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Sending request failed: " + e.getMessage());
			return null;
		}
	}

	private static class TestResponse {

		public final String body;
		public final int status;

		public TestResponse(int status, String body) {
			this.status = status;
			this.body = body;
		}

    }
}
