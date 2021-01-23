package xml.entity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


@XmlRootElement(name = "child")
@XmlAccessorType(XmlAccessType.FIELD)
public class Child implements Serializable {

    @XmlElement(name = "name")
    private String name;

    @XmlAttribute(name = "is-file")
    private Boolean isFile;

    @XmlElement(name = "children")
    private Children children;

    public Child() {
    }

    public Child(String name, Boolean isFile, Children children) {
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
