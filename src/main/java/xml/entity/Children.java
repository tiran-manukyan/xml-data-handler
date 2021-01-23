package xml.entity;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "children")
@XmlAccessorType(XmlAccessType.FIELD)
public class Children {

    @XmlElement(name = "child")
    private List<Child> childList;

    public Children() {
    }

    public Children(List<Child> childList) {
        this.childList = childList;
    }

    public List<Child> getChildList() {
        return childList;
    }

}
