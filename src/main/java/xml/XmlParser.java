package xml;

import xml.entity.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParser {

    public static Node parseXmlToNode(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Node.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Node) jaxbUnmarshaller.unmarshal(file);
    }
}
