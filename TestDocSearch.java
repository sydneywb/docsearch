import static org.junit.Assert.*;
import org.junit.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.beans.Transient;
import java.io.IOException;

public class TestDocSearch {
    @Test
    public void testIndex() throws URISyntaxException, IOException {
        Handler h = new Handler("./technical/");
        URI rootPath = new URI("http://localhost/");
        assertEquals("There are 1394 total files to search.", h.handleRequest(rootPath));
    }

    @Test
    public void testSearch() throws URISyntaxException, IOException {
        Handler h = new Handler("./technical/");
        URI rootPath = new URI("http://localhost/search?q=Resonance");
        String expect = "Found 2 paths:\n./technical/biomed/ar615.txt\n./technical/plos/journal.pbio.0020150.txt";
        assertEquals(expect, h.handleRequest(rootPath));
    }

    @Test // careful about spaces in actual versus expected output
    public void testTitle() throws URISyntaxException, IOException {
        Handler h = new Handler("./technical/");
        URI rootPath = new URI("http://localhost/search?title=nicole");
        // when printing paths to webpage, seems that there's actually a ./ at the start of the path
        // so when testing, make sure to put ./ at the beginning of the expected string
        String expect = "Hey, I found 1 files with nicole in the path:\n./technical/plos/nicole.txt";
        assertEquals(expect, h.handleRequest(rootPath));
    }
    @Test
    public void testTitle2() throws URISyntaxException, IOException {
        Handler h = new Handler("./technical/");
        URI rootPath = new URI("http://localhost/search?title=cinn");
        String expect = "Hey, I found 1 files with cinn in the path:\n./technical/cinnamon/baking.txt";
        assertEquals(expect, h.handleRequest(rootPath));
    }
}
