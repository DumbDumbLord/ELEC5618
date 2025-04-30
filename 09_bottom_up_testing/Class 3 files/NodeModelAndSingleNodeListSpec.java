package org.freeplane.features.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NodeModelAndSingleNodeListSpec {

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

    @Test
    public void SingleNodeListCreate() {
        MapModel map = null;
        NodeModel node = new NodeModel("single", map);
        SingleNodeList list = new SingleNodeList(node, null);
        assertNotNull(list);
    }

    @Test
    public void SingleNodeListIterate() {
        MapModel map = null;
        NodeModel node = new NodeModel("single", map);
        SingleNodeList list = new SingleNodeList(node, null);

        NodeModel iteratedNode = null;
        for (NodeModel n : list) {
            iteratedNode = n;
        }
        assertEquals(node, iteratedNode);
    }
}
