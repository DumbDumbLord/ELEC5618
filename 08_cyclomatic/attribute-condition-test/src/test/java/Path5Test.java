import org.junit.Test;
import static org.junit.Assert.*;

public class Path5Test {
    @Test
    public void testPath5() {
        SimpleNode node = new SimpleNode();
        node.addAttribute("type", "no match here");
        AttributeContainsCondition cond = new AttributeContainsCondition("type", "MATCH");
        assertFalse(cond.checkNode(node));
    }
}