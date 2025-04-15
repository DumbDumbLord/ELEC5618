import org.junit.Test;
import static org.junit.Assert.*;

public class Path2Test {
    @Test
    public void testPath2() {
        SimpleNode node = new SimpleNode();
        node.addAttribute("other", "someValue");
        AttributeContainsCondition cond = new AttributeContainsCondition("targetAttr", "MATCH");
        assertFalse(cond.checkNode(node));
    }
}