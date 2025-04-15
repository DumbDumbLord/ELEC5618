import org.junit.Test;
import static org.junit.Assert.*;

public class Path3Test {
    @Test
    public void testPath3() {
        SimpleNode node = new SimpleNode();
        AttributeContainsCondition cond = new AttributeContainsCondition("targetAttr", "MATCH");
        assertFalse(cond.checkNode(node));
    }
}