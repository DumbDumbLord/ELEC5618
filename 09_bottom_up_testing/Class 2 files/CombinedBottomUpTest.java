package org.freeplane.features.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CombinedBottomUpTest {

    private MapController controller;
    private MapModel map;
    private NodeModel root;

    @Before
    public void setUp() {
        controller = mock(MapController.class);

        map = new MapModel(new DummyNodeDuplicator(), null, new DummyNodeChangeAnnouncer());

        when(controller.toString()).thenReturn("MockMapController");
    }

    @Test
    public void shouldCreateMapAndSetRootNode() {
        root = new NodeModel(map);
        root.setID("rootNode");
        map.setRoot(root);

        assertSame(root, map.getRootNode());
        assertEquals("rootNode", map.getRootNode().getID());
    }

    @Test
    public void shouldInsertChildNodesAndRetrieve() {
        root = new NodeModel(map);
        map.setRoot(root);

        NodeModel child1 = new NodeModel(map);
        child1.setID("child1");
        root.insert(child1);

        NodeModel child2 = new NodeModel(map);
        child2.setID("child2");
        root.insert(child2);

        map.registryNode(child1);
        map.registryNode(child2);

        assertEquals(2, root.getChildCount());
        assertSame(child1, root.getChildAt(0));
        assertSame(child2, root.getChildAt(1));
        assertSame(child1, map.getNodeForID("child1"));
        assertSame(child2, map.getNodeForID("child2"));
    }

    @Test
    public void shouldSimulateFullMapCreation() {
        root = new NodeModel(map);
        root.setID("root123");
        map.setRoot(root);

        NodeModel childA = new NodeModel(map);
        childA.setID("childA");
        root.insert(childA);

        NodeModel childB = new NodeModel(map);
        childB.setID("childB");
        childA.insert(childB);

        map.registryNode(childA);
        map.registryNode(childB);

        assertEquals(childA, root.getChildAt(0));
        assertEquals(childB, childA.getChildAt(0));
        assertEquals(childB, map.getNodeForID("childB"));
    }
}
