import java.util.ArrayList;
import java.util.List;

public class SimpleNode {
    List<AttributeRow> attributes = new ArrayList<>();

    public void addAttribute(String name, String value) {
        attributes.add(new AttributeRow(name, value));
    }

    public List<AttributeRow> getAttributes() {
        return attributes;
    }
}
