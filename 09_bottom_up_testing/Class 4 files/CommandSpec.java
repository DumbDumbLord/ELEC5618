package org.freeplane.features.explorer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.freeplane.features.map.NodeModel;
import org.junit.Test;

public class CommandSpec {
    @Test
    public void CommandChild() throws Exception {
        AccessedNodes accessedNodes = mock(AccessedNodes.class);
        NodeModel parent = new NodeModel("", null);

        parent.insert(new NodeModel("a", null));
        parent.insert(new NodeModel("b", null));
        parent.insert(new NodeModel("c", null));
        parent.insert(new NodeModel("d", null));

        Command command = new Command(ExploringStep.CHILD, "\'b\'", accessedNodes);

        assertEquals(command.getNodes(parent).toString(), "[b]");
    }

    @Test
    public void CommandDescendant() throws Exception {
        AccessedNodes accessedNodes = mock(AccessedNodes.class);
        NodeModel parent = new NodeModel("", null);

        parent.insert(new NodeModel("a", null));
        NodeModel node = new NodeModel("b", null);
        node.insert(new NodeModel("d", null));
        parent.insert(node);
        parent.insert(new NodeModel("c", null));

        Command command = new Command(ExploringStep.DESCENDANT, "\'d\'", accessedNodes);

        assertEquals(command.getNodes(parent).toString(), "[d]");
    }

    @Test
    public void CommandAncestor() throws Exception {
        AccessedNodes accessedNodes = mock(AccessedNodes.class);
        NodeModel parent = new NodeModel("a", null);

        parent.insert(new NodeModel("e", null));
        NodeModel nodeB = new NodeModel("b", null);
        NodeModel nodeC = new NodeModel("c", null);
        nodeB.insert(nodeC);
        parent.insert(nodeB);
        parent.insert(new NodeModel("d", null));

        Command command = new Command(ExploringStep.ANCESTOR, "\'a\'", accessedNodes);

        assertEquals(command.getNodes(nodeC).toString(), "[a]");
    }

    @Test
    public void CommandString() throws Exception {
        AccessedNodes accessedNodes = mock(AccessedNodes.class);

        Command command = new Command(ExploringStep.CHILD, "\'a\'", accessedNodes);

        assertEquals(command.toString(), "Command [operator=CHILD, searchedString='a']");
    }
}
