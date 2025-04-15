import org.junit.Test;
import static org.junit.Assert.*;

public class Path4Test {
    @Test
    public void testPath4() {
        SimpleNode node = new SimpleNode();
        node.addAttribute("type", "this MATCHes!");
        AttributeContainsCondition cond = new AttributeContainsCondition("type", "MATCH");
        assertTrue(cond.checkNode(node));
    }
}