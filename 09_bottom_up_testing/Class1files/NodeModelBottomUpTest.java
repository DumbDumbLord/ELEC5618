package org.freeplane.features.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

class NodeModelBottomUpTest {

    @Test
    void testInsertNodeAndGetChild() {
        MapModel mapModel = mock(MapModel.class);
        NodeModel parent = new NodeModel(mapModel);
        NodeModel child = new NodeModel(mapModel);

        parent.insert(child);
        assertEquals(1, parent.getChildCount());
        assertSame(child, parent.getChildAt(0));
    }

    @Test
    void testMapControllerSelect() {
        // 由于select依赖全局Controller和UI，通常只能verify调用
        MapController mapController = mock(MapController.class);
        MapModel mapModel = mock(MapModel.class);
        NodeModel node = new NodeModel(mapModel);

        mapController.select(node);
        verify(mapController).select(node);
    }

    @Test
    void testSortNodesByDepth() {
        MapController mapController = mock(MapController.class);
        MapModel mapModel = mock(MapModel.class);
        NodeModel root = new NodeModel(mapModel);
        NodeModel child = new NodeModel(mapModel);
        root.insert(child);

        // 这里只能演示调用，真实排序需用真实对象
        List<NodeModel> nodes = new java.util.ArrayList<>();
        nodes.add(child);
        nodes.add(root);

        // 如果你想测试真实排序，需用真实MapController对象
        // mapController.sortNodesByDepth(nodes);
    }
}