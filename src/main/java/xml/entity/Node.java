package xml.entity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


@XmlRootElement(name = "node")
@XmlAccessorType(XmlAccessType.FIELD)
public class Node implements Serializable {

    @XmlElement(name = "name")
    private String name;

    @XmlAttribute(name = "is-file")
    private Boolean isFile;

    @XmlElement(name = "children")
    private Children children;

    public Node() {
    }

    public Node(String name, Boolean isFile, Children children) {
        this.name = name;
        this.isFile = isFile;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public Boolean isFile() {
        return isFile;
    }

    public Children getChildren() {
        return children;
    }
}
