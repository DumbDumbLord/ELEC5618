package org.freeplane.features.explorer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.freeplane.features.map.MapModel;
import org.freeplane.features.map.NodeModel;
import org.junit.Test;


public class NodeMatcherSpec {
    @Test
	public void NodeMatcherBlank() throws Exception {
        NodeModel node = new NodeModel("", null);

        NodeMatcher nodeMatching = new NodeMatcher("");
        
        assertTrue(nodeMatching.matches(node));
	}

    @Test
	public void NodeMatcherAlias() throws Exception {
        MapModel map = mock(MapModel.class);

        NodeModel node = new NodeModel("abc", map);
        NodeAlias.setAlias(node, "alias");

        NodeMatcher nodeMatching = new NodeMatcher("~alias");
        
        assertTrue(nodeMatching.matches(node));
	}

    @Test
	public void NodeMatcherTextFullTrue1() throws Exception {
        NodeModel node = new NodeModel("abc", null);

        NodeMatcher nodeMatching = new NodeMatcher("\"abc\"");

        assertTrue(nodeMatching.matches(node));
	}

    @Test
	public void NodeMatcherTextFullTrue2() throws Exception {
        NodeModel node = new NodeModel("abc", null);

        NodeMatcher nodeMatching = new NodeMatcher("\'abc\'");

        assertTrue(nodeMatching.matches(node));
	}

    @Test
	public void NodeMatcherTextPartTrue1() throws Exception {
        NodeModel node = new NodeModel("abc", null);

        NodeMatcher nodeMatching = new NodeMatcher("\"a...\"");

        assertTrue(nodeMatching.matches(node));
	}

    @Test
	public void NodeMatcherTextPartTrue2() throws Exception {
        NodeModel node = new NodeModel("abc", null);

        NodeMatcher nodeMatching = new NodeMatcher("\'a...\'");

        assertTrue(nodeMatching.matches(node));
	}

    @Test
	public void NodeMatcherTextFullFalse() throws Exception {
        NodeModel node = new NodeModel("a", null);

        NodeMatcher nodeMatching = new NodeMatcher("\"abc\"");

        assertFalse(nodeMatching.matches(node));
	}

    @Test
	public void NodeMatcherInvalid() throws Exception {
        try {
            NodeMatcher nodeMatching = new NodeMatcher("abc");
            assertTrue(true);
        }
        catch (IllegalArgumentException e) {
            assertEquals("invalid search string abc", e.getMessage());
        }
	}

    @Test
	public void NodeMatcherCounterMatch() throws Exception {
        NodeModel node = new NodeModel("a", null);

        NodeMatcher nodeMatching = new NodeMatcher("~1");

        try {
            nodeMatching.matches(node);
            assertTrue(true);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Can not match nodes by index", e.getMessage());
        }
	}

    @Test
	public void NodeMatcherList() throws Exception {
        AccessedNodes accessedNodes = mock(AccessedNodes.class);

        ArrayList<NodeModel> nodes = new ArrayList<NodeModel>();
        nodes.add(new NodeModel("a", null));
        nodes.add(new NodeModel("b", null));

        NodeMatcher nodeMatching = new NodeMatcher("\'b\'");
        
        assertEquals(nodeMatching.filterMatchingNodes(nodes, accessedNodes).get(0).getText(), "b");
	}

    @Test
	public void NodeMatcherCounter() throws Exception {
        AccessedNodes accessedNodes = mock(AccessedNodes.class);

        ArrayList<NodeModel> nodes = new ArrayList<NodeModel>();
        nodes.add(new NodeModel("a", null));
        nodes.add(new NodeModel("b", null));

        NodeMatcher nodeMatching = new NodeMatcher("~2");
        
        assertEquals(nodeMatching.filterMatchingNodes(nodes, accessedNodes).get(0).getText(), "b");
	}
    
    @Test
	public void NodeMatcherCounterEmpty() throws Exception {
        AccessedNodes accessedNodes = mock(AccessedNodes.class);

        ArrayList<NodeModel> nodes = new ArrayList<NodeModel>();
        nodes.add(new NodeModel("a", null));
        nodes.add(new NodeModel("b", null));

        NodeMatcher nodeMatching = new NodeMatcher("~3");
        
        assertTrue(nodeMatching.filterMatchingNodes(nodes, accessedNodes).isEmpty());
	}
}