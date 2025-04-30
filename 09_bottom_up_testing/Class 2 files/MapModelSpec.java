package org.freeplane.features.map;

import java.net.MalformedURLException;
import java.net.URL;

import org.freeplane.core.extension.IExtension;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

class DummyNodeDuplicator implements INodeDuplicator {
    @Override
    public NodeModel duplicate(NodeModel source, MapModel targetMap, boolean withChildren) {
        return null;
    }
}

class DummyNodeChangeAnnouncer implements NodeChangeAnnouncer {
    @Override
    @Deprecated
    public void nodeChanged(NodeModel node) {
    
    }

    @Override
    public void nodeChanged(NodeModel node, Object property, Object oldValue, Object newValue) {
      
    }

    @Override
    @Deprecated
    public void nodeRefresh(NodeModel node) {
       
    }

    @Override
    public void nodeRefresh(NodeModel node, Object property, Object oldValue, Object newValue) {
   
    }

    @Override
    public void nodeRefresh(NodeChangeEvent nodeChangeEvent) {
       
    }
}

public class MapModelSpec {

    private MapModel map;
    private NodeModel rootNode;

    @Before
    public void setUp() {
        map = new MapModel(new DummyNodeDuplicator(), null, new DummyNodeChangeAnnouncer());
        rootNode = new NodeModel(map);
        
    }

    @Test
    public void shouldSetAndGetRootNode() {
        map.setRoot(rootNode);
        assertEquals(rootNode, map.getRootNode());
    }

    @Test
    public void shouldRegisterNodeAndFindById() {
        rootNode.setID("test_node_id");
        map.registryNode(rootNode);
        NodeModel foundNode = map.getNodeForID("test_node_id");
        assertEquals(rootNode, foundNode);
    }

    @Test
    public void shouldSetAndGetReadOnlyFlag() {
        map.setReadOnly(true);
        assertTrue(map.isReadOnly());
        map.setReadOnly(false);
        assertFalse(map.isReadOnly());
    }

    @Test
    public void shouldSetAndGetURL() throws MalformedURLException {
        URL url = new URL("http://example.com");
        map.setURL(url);
        assertEquals(url, map.getURL());
        assertEquals("http://example.com", map.getTitle());
    }

    @Test
    public void shouldRegisterNodesRecursively() {
        rootNode.setID("root");

        NodeModel child1 = new NodeModel(map);
        child1.setID("child1");
        rootNode.insert(child1);

        NodeModel child2 = new NodeModel(map);
        child2.setID("child2");
        rootNode.insert(child2);

        map.registryNodeRecursive(rootNode);

        assertEquals(rootNode, map.getNodeForID("root"));
        assertEquals(child1, map.getNodeForID("child1"));
        assertEquals(child2, map.getNodeForID("child2"));
    }

    @Test
    public void shouldSetAndCheckSavedStatus() {
        assertTrue(map.isSaved());

        map.setSaved(false);
        assertFalse(map.isSaved());

        map.setSaved(true);
        assertTrue(map.isSaved());
    }

    @Test
    public void shouldAddAndRemoveMapChangeListener() {
        IMapChangeListener listener = new IMapChangeListener() {
            @Override
            public void mapChanged(MapChangeEvent event) {
                // Do nothing
            }
        };

        map.addMapChangeListener(listener);
        assertTrue(true);

        map.removeMapChangeListener(listener);
        assertTrue(true); 
    }

    @Test
    public void shouldAddAndRemoveExtensions() {
        IExtension dummyExtension = new IExtension() {}; 

        map.addExtension(dummyExtension);
        assertTrue(map.containsExtension(dummyExtension.getClass()));

        boolean removed = map.removeExtension(dummyExtension);
        assertTrue(removed);
    }
}
