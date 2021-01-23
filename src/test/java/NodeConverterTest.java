import main.data.NodeConverter;
import org.junit.jupiter.api.Test;
import xml.entity.Child;
import xml.entity.Children;
import xml.entity.Node;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NodeConverterTest {

    @Test
    public void extractFilePathsTest() {
        Child firstFile = new Child("first_file.txt", true, null);
        Child secondFile = new Child("second_file.xml", true, null);
        Child configFile = new Child("config.txt", true, null);
        Child javaFile = new Child("NodeConverterTest.java", true, null);
        Children innerChildren = new Children(Arrays.asList(configFile, javaFile));
        Child firstDir = new Child("first_dir", false, innerChildren);
        Children outerChildren = new Children(Arrays.asList(firstFile, secondFile, firstDir));
        Node node = new Node("test_node", false, outerChildren);

        List<String> filePaths = NodeConverter.extractFilePaths(node);
        List<String> expectedFilePaths = Arrays.asList("test_node/first_file.txt", "test_node/second_file.xml", "test_node/first_dir/config.txt", "test_node/first_dir/NodeConverterTest.java");

        assertNotNull(filePaths);
        assertEquals(expectedFilePaths, filePaths);
    }
}
