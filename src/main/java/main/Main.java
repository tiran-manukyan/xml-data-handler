package main;

import main.data.DataFilter;
import main.data.NodeConverter;
import org.apache.commons.cli.ParseException;
import xml.XmlParser;
import xml.entity.Node;

import javax.xml.bind.JAXBException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JAXBException {
        Arguments arguments;
        try {
            arguments = Arguments.resolve(args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            return;
        }

        Node node = XmlParser.parseXmlToNode(arguments.getFile());
        List<String> filePaths = NodeConverter.extractFilePaths(node);
        List<String> filteredFilePaths = DataFilter.filterDataBySearchInput(filePaths, arguments.getSearchInput());

        for (String path : filteredFilePaths) {
            System.out.println(path);
        }
    }
}
