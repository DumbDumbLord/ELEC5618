package org.freeplane.features.explorer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.freeplane.features.map.NodeModel;
import org.junit.Test;

public class CommandSpec {
    @Test
    public void NodeMatcherBlank() throws Exception {
        AccessedNodes accessedNodes = mock(AccessedNodes.class);
        NodeModel parent = new NodeModel("", null);

        parent.insert(new NodeModel("a", null));
        parent.insert(new NodeModel("b", null));
        parent.insert(new NodeModel("c", null));
        parent.insert(new NodeModel("d", null));

        Command command = new Command(ExploringStep.CHILD, "\'b\'", accessedNodes);

        assertEquals(command.getNodes(parent).toString(), "[b]");
    }
}
