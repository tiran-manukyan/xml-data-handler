import main.Arguments;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentResolverTest {

    private static String filePath;

    @BeforeAll
    static void init() throws URISyntaxException {
        URL url = ArgumentResolverTest.class.getResource("test-files.xml");
        filePath = new File(url.toURI()).getPath();
    }

    @Test
    public void filePathTest() throws ParseException {
        String[] args = {"-f", filePath};
        Arguments arguments = Arguments.resolve(args);

        assertEquals(filePath, arguments.getFile().getPath());
    }

    @Test
    public void searchInputTemplateTest() throws ParseException {
        String searchInputTemplate = "file-1498940214.xhtml";

        String[] args = {"-f", filePath, "-s", searchInputTemplate};
        Arguments arguments = Arguments.resolve(args);

        assertEquals(searchInputTemplate, arguments.getSearchInput().getSearchTemplate());
    }

    @Test
    public void searchInputRegexTest() throws ParseException {
        String searchInputRegex = ".*?[a-z]{4}-\\d+\\.[a-z]+";

        String[] args = {"-f", filePath, "-S", searchInputRegex};
        Arguments arguments = Arguments.resolve(args);

        assertEquals(filePath, arguments.getFile().getPath());
        assertEquals(searchInputRegex, arguments.getSearchInput().getSearchRegex());
    }

    @Test
    public void invalidFilePathTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            String[] args = {"-f", "invalid-file-path.xml"};
            Arguments.resolve(args);
        });
    }

    @Test
    public void invalidFileExtensionTest() throws URISyntaxException {
        URL url = ArgumentResolverTest.class.getResource("test.txt");
        String filePath = new File(url.toURI()).getPath();
        assertThrows(IllegalArgumentException.class, () -> {
            String[] args = {"-f", filePath};
            Arguments.resolve(args);
        });
    }

    @Test
    public void unrecognizedOptionTest() {
        assertThrows(UnrecognizedOptionException.class, () -> {
            String[] args = {"-o", "value"};
            Arguments.resolve(args);
        });
    }

    @Test
    public void MissingOptionTest() {
        assertThrows(MissingOptionException.class, () -> {
            String[] args = {"-s", "value"};
            Arguments.resolve(args);
        });
    }

    @Test
    public void MissingArgumentTest() {
        assertThrows(MissingArgumentException.class, () -> {
            String[] args = {"-f"};
            Arguments.resolve(args);
        });
    }
}
