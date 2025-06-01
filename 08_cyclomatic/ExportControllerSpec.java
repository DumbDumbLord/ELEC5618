package org.freeplane.features.export.mindmapmode;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.*;

public class ExportControllerSpec {

    private MockExportController controller;

    @Before
    public void setUp() {
        controller = new MockExportController();
    }

    @Test
    public void testAddMapExportEngineOnce() {
        DummyExporter exporter = new DummyExporter("PNG");
        controller.addMapExportEngine(exporter.getFilter(), exporter);
        assertEquals(1, controller.getMapExportFileFilters().size());
    }

    @Test
    public void testAddMapExportEngineDuplicate() {
        DummyExporter exporter = new DummyExporter("PNG");
        controller.addMapExportEngine(exporter.getFilter(), exporter);
        controller.addMapExportEngine(exporter.getFilter(), exporter);
        assertEquals(1, controller.getMapExportFileFilters().size());
    }

    @Test
    public void testAddTwoDifferentMapEngines() {
        DummyExporter exporter1 = new DummyExporter("PNG");
        DummyExporter exporter2 = new DummyExporter("JPG");
        controller.addMapExportEngine(exporter1.getFilter(), exporter1);
        controller.addMapExportEngine(exporter2.getFilter(), exporter2);
        assertEquals(2, controller.getMapExportFileFilters().size());
    }

    @Test
    public void testAddBranchExportEngineOnce() {
        DummyExporter exporter = new DummyExporter("PDF");
        controller.addBranchExportEngine(exporter.getFilter(), exporter);
        assertEquals(1, controller.getBranchExportFileFilters().size());
    }

    // === Minimal dummy exporter and filter ===
    private static class DummyExporter implements IExportEngine {
        private final FileFilter filter;

        public DummyExporter(String name) {
            this.filter = new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return true;
                }

                @Override
                public String getDescription() {
                    return name;
                }
            };
        }

        public FileFilter getFilter() {
            return filter;
        }

        @Override
        public void export(List nodes, File toFile) {
            // No real logic for mock
        }
    }

    private static class MockExportController {
        private final Map<FileFilter, IExportEngine> mapExportEngines = new HashMap<>();
        private final Map<FileFilter, IExportEngine> branchExportEngines = new HashMap<>();
        private final List<FileFilter> mapExportFileFilters = new ArrayList<>();
        private final List<FileFilter> branchExportFileFilters = new ArrayList<>();

        public void addMapExportEngine(FileFilter filter, IExportEngine engine) {
            if (!mapExportEngines.containsValue(engine)) {
                mapExportEngines.put(filter, engine);
                mapExportFileFilters.add(filter);
            }
        }

        public void addBranchExportEngine(FileFilter filter, IExportEngine engine) {
            if (!branchExportEngines.containsValue(engine)) {
                branchExportEngines.put(filter, engine);
                branchExportFileFilters.add(filter);
            }
        }

        public List<FileFilter> getMapExportFileFilters() {
            return mapExportFileFilters;
        }

        public List<FileFilter> getBranchExportFileFilters() {
            return branchExportFileFilters;
        }
    }
}
