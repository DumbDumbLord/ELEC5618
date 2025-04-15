import org.junit.Test;
import static org.junit.Assert.*;

public class Path1Test {
    @Test
    public void testPath1() {
        SimpleNode node = new SimpleNode();
        node.addAttribute("random", "no_match");
        AttributeContainsCondition cond = new AttributeContainsCondition("ANY", "MATCH");
        assertFalse(cond.checkNode(node));
    }
}