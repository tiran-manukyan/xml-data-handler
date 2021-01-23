import main.SearchInput;
import main.data.DataFilter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataFilterTest {

    @Test
    public void filterDataBySearchInputTest() {
        List<String> filPaths = Arrays.asList("test_node/test-789421.txt", "test_node/file-784346411.xml", "test_node/first_dir/config.txt", "test_node/first_dir/NodeConverterTest.java", "test_node/file-487431.xml");

        List<String> actualPaths1 = DataFilter.filterDataBySearchInput(filPaths, new SearchInput("*.txt", null));
        List<String> expectedPaths1 = Arrays.asList("test_node/test-789421.txt", "test_node/first_dir/config.txt");
        assertEquals(expectedPaths1, actualPaths1);

        List<String> actualPaths2 = DataFilter.filterDataBySearchInput(filPaths, new SearchInput("test_node/first_dir/NodeConverterTest.java", null));
        List<String> expectedPaths2 = Collections.singletonList("test_node/first_dir/NodeConverterTest.java");
        assertEquals(expectedPaths2, actualPaths2);

        List<String> actualPaths3 = DataFilter.filterDataBySearchInput(filPaths, new SearchInput(null, ".*?[a-z]{4}-\\d+\\.[a-z]+"));
        List<String> expectedPaths3 = Arrays.asList("test_node/test-789421.txt", "test_node/file-784346411.xml", "test_node/file-487431.xml");
        assertEquals(expectedPaths3, actualPaths3);

        List<String> actualPaths4 = DataFilter.filterDataBySearchInput(filPaths, new SearchInput(null, null));
        assertEquals(filPaths, actualPaths4);

        assertThrows(IllegalArgumentException.class, () -> new SearchInput("*.java", ".*?[a-z]{4}-\\d+\\.[a-z]+"));
    }
}
