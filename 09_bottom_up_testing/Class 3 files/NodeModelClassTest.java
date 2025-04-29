package org.freeplane.features.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NodeModelClassTest {

    @Test
    public void NodeModelCreate() {
        MapModel map = null;
        NodeModel node = new NodeModel("root", map);
        assertEquals("root", node.getUserObject());
    }

    @Test
    public void NodeModelInsertChild() {
        MapModel map = null;
        NodeModel parent = new NodeModel("parent", map);
        NodeModel child = new NodeModel("child", map);
        parent.insert(child);
        assertEquals(1, parent.getChildCount());
    }

    @Test
    public void NodeModelGetChildAt() {
        MapModel map = null;
        NodeModel parent = new NodeModel("parent", map);
        NodeModel child = new NodeModel("child", map);
        parent.insert(child);
        assertEquals(child, parent.getChildAt(0));
    }

    @Test
    public void NodeModelSetUserObject() {
        MapModel map = null;
        NodeModel node = new NodeModel("old", map);
        node.setUserObject("new");
        assertEquals("new", node.getUserObject());
    }
}
