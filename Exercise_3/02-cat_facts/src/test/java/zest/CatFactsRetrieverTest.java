package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class CatFactsRetrieverTest {

    private HttpUtil httpUtil;
    private CatFactsRetriever catFactsRetriever;

    @BeforeEach
    public void setupClasses() {
        // Initialize class instances
        httpUtil = mock(HttpUtil.class);
        catFactsRetriever = new CatFactsRetriever(httpUtil);
    }

    @Test
    public void testRandomFactSuccess() throws IOException {
        // Define mock behaviour
        String httpResponse = createRandomFactJson();
        when(httpUtil.get("https://catfact.ninja/fact")).thenReturn(httpResponse);

        // Save return variable
        String result = catFactsRetriever.retrieveRandom();

        assertEquals(result, "This is an interesting cat fact.");
    }

    @Test
    public void testRandomFactException() throws IOException {
        // Define mock behaviour
        when(httpUtil.get("https://catfact.ninja/fact")).thenThrow(new IOException());

        assertThrows(IOException.class, () -> catFactsRetriever.retrieveRandom());
    }

    @Test
    public void testRetrieveLongestOfThreeFactsSuccess() throws IOException {
        // Define mock behaviour
        String httpResponse = createFactListJsonWithLimit3();
        when(httpUtil.get("https://catfact.ninja/facts?limit=3")).thenReturn(httpResponse);

        // Save return variable
        String result = catFactsRetriever.retrieveLongest(3);

        assertEquals(result, "Cats are animals.");
    }

    @Test
    public void testRetrieveLongestOfOneFactSuccess() throws IOException {
        // Define mock behaviour
        String httpResponse = createFactListJsonWithLimit1();
        when(httpUtil.get("https://catfact.ninja/facts?limit=1")).thenReturn(httpResponse);

        // Save return variable
        String result = catFactsRetriever.retrieveLongest(1);

        assertEquals(result, "Cats are nice.");
    }

    @Test
    public void testRetrieveLongestNonJsonReturnSuccess() throws IOException {
        // Define mock behaviour
        String httpResponse = createNonJsonListEntries();
        when(httpUtil.get("https://catfact.ninja/facts?limit=3")).thenReturn(httpResponse);

        // Save return variable
        String result = catFactsRetriever.retrieveLongest(3);

        assertEquals(result, "");
    }

    @Test
    public void testRetrieveLongestOfZeroFactsException() throws IOException, IllegalArgumentException {
        // Define mock behaviour
        when(httpUtil.get("https://catfact.ninja/facts?limit=0")).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> catFactsRetriever.retrieveLongest(0));
    }

    @Test
    public void testRetrieveLongestWithIoException() throws IOException, IllegalArgumentException {
        // Define mock behaviour
        when(httpUtil.get("https://catfact.ninja/facts?limit=5")).thenThrow(new IOException());

        assertThrows(IOException.class, () -> catFactsRetriever.retrieveLongest(5));
    }

    private String createRandomFactJson() {
        return "{\"fact\":\"This is an interesting cat fact.\"}";
    }

    private String createFactListJsonWithLimit1() {
        return "{\"data\":[" +
                "{\"fact\":\"Cats are nice.\", \"length\":14}," +
                "]}";
    }

    private String createFactListJsonWithLimit3() {
        return "{\"data\":[" +
                "{\"fact\":\"Cats are animals.\", \"length\":17}," +
                "{\"fact\":\"Cats are cuddly.\", \"length\":16}," +
                "{\"fact\":\"Cats are nice.\", \"length\":14}" +
                "]}";
    }

    private String createNonJsonListEntries() {
        return "{\"data\":[" +
                "This is not a json" +
                "]}";
    }

}
