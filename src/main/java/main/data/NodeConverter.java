package main.data;

import xml.entity.Child;
import xml.entity.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeConverter {

    public static List<String> extractFilePaths(Node node) {
        List<String> filePaths = new ArrayList<>();
        String parentName = node.getName().equals("/") ? "" : node.getName();
        extractFilePaths(node, parentName, filePaths);

        return filePaths;
    }

    private static void extractFilePaths(Object element, String parentName, List<String> filePaths) {
        if (element instanceof Node) {
            Node node = (Node) element;
            if (node.isFile()) {
                filePaths.add(node.getName());
            } else if (node.getChildren() != null) {
                extractFilePaths(node.getChildren().getChildList(), parentName, filePaths);
            }
        } else if (element instanceof List) {
            List<Child> childList = (List<Child>) element;
            for (Child child : childList) {
                extractFilePaths(child, parentName, filePaths);
            }
        } else if (element instanceof Child) {
            Child child = (Child) element;
            String name = parentName + "/" + child.getName();
            if (child.isFile()) {
                filePaths.add(name);
            } else if (child.getChildren() != null) {
                extractFilePaths(child.getChildren().getChildList(), name, filePaths);
            }
        }
    }
}
