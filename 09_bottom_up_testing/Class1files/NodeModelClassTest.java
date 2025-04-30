package org.freeplane.features.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

public class NodeModelClassTest {

    @Test
    void testSetAndGetText() {
        MapModel mapModel = mock(MapModel.class);
        NodeModel node = new NodeModel(mapModel);
        node.setText("hello");
        assertEquals("hello", node.getText());
    }

    @Test
    void testInsertAndGetChild() {
        MapModel mapModel = mock(MapModel.class);
        NodeModel parent = new NodeModel(mapModel);
        NodeModel child = new NodeModel(mapModel);
        parent.insert(child);
        assertEquals(1, parent.getChildCount());
        assertSame(child, parent.getChildAt(0));
        assertEquals(parent, child.getParentNode());
    }

    @Test
    void testFoldedState() {
        MapModel mapModel = mock(MapModel.class);
        NodeModel node = new NodeModel(mapModel);
        assertFalse(node.isFolded());
        node.setFolded(true);
        assertTrue(node.isFolded());
        node.setFolded(false);
        assertFalse(node.isFolded());
    }

    @Test
    void testSetAndGetSide() {
        MapModel mapModel = mock(MapModel.class);
        NodeModel node = new NodeModel(mapModel);
        node.setSide(NodeModel.Side.TOP_OR_LEFT);
        assertEquals(NodeModel.Side.TOP_OR_LEFT, node.getSide());
    }

    @Test
    void testSetAndGetUserObject() {
        MapModel mapModel = mock(MapModel.class);
        NodeModel node = new NodeModel(mapModel);
        node.setUserObject("data");
        assertEquals("data", node.getUserObject());
    }

    @Test
    void testChildrenListUnmodifiable() {
        MapModel mapModel = mock(MapModel.class);
        NodeModel parent = new NodeModel(mapModel);
        NodeModel child = new NodeModel(mapModel);
        parent.insert(child);
        List<NodeModel> children = parent.getChildren();
        assertThrows(UnsupportedOperationException.class, () -> children.add(new NodeModel(mapModel)));
    }

    @Test
    void testIsLeaf() {
        MapModel mapModel = mock(MapModel.class);
        NodeModel node = new NodeModel(mapModel);
        assertTrue(node.isLeaf());
        node.insert(new NodeModel(mapModel));
        assertFalse(node.isLeaf());
    }

    @Test
    void testSetAndGetXmlText() {
        MapModel mapModel = mock(MapModel.class);
        NodeModel node = new NodeModel(mapModel);
        node.setXmlText("<xml>test</xml>");
        assertEquals("<text>&lt;xml&gt;test&lt;/xml&gt;</text>", node.getXmlText());
    }

    @Test
    void testDepth() {
        MapModel mapModel = mock(MapModel.class);
        NodeModel root = new NodeModel(mapModel);
        NodeModel child = new NodeModel(mapModel);
        root.insert(child);
        NodeModel grandchild = new NodeModel(mapModel);
        child.insert(grandchild);
        assertEquals(0, root.depth());
        assertEquals(1, child.depth());
        assertEquals(2, grandchild.depth());
    }
}