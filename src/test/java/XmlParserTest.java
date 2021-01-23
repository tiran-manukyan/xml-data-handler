import org.junit.jupiter.api.Test;
import xml.entity.Child;
import xml.entity.Node;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class XmlParserTest {

    @Test
    public void parseXmlToNodeTest() throws JAXBException, URISyntaxException {
        URL url = this.getClass().getResource("test-files.xml");
        File file = new File(url.toURI());
        if (!file.isFile()) {
            throw new IllegalArgumentException("Invalid file path");
        }

        Node node = xml.XmlParser.parseXmlToNode(file);
        assertEquals(node.isFile(), false);
        assertEquals(node.getName(), "test_node");
        assertNotNull(node.getChildren());

        List<Child> childList = node.getChildren().getChildList();
        assertNotNull(childList);
        assertEquals(childList.size(), 3);

        Child child1 = childList.get(0);
        assertEquals(child1.isFile(), true);
        assertEquals(child1.getName(), "first_file.xml");

        Child child2 = childList.get(1);
        assertEquals(child2.isFile(), true);
        assertEquals(child2.getName(), "second_file.xml");

        Child child3 = childList.get(2);
        assertEquals(child3.isFile(), false);
        assertEquals(child3.getName(), "first_dir");
        assertNotNull(child3.getChildren());

        List<Child> childList2 = child3.getChildren().getChildList();
        assertNotNull(childList2);
        assertEquals(childList2.size(), 2);

        Child child3_1 = childList2.get(0);
        assertEquals(child3_1.isFile(), true);
        assertEquals(child3_1.getName(), "config.txt");

        Child child3_2 = childList2.get(1);
        assertEquals(child3_2.isFile(), true);
        assertEquals(child3_2.getName(), "XmlParserTest.java");
    }
}

