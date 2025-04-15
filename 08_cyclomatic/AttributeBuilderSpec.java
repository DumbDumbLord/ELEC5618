package org.freeplane.features.attribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.freeplane.features.attribute.AttributeBuilder.AttributeProperties;
import org.freeplane.features.attribute.AttributeBuilder.RegisteredAttributeProperties;
import org.freeplane.n3.nanoxml.XMLElement;
import org.junit.Test;


public class AttributeBuilderSpec {
    @Test
	public void createElementAttributeProperties() throws Exception {
        AttributeBuilder aBulid = new AttributeBuilder(null, null);
        Object result = aBulid.createElement(null, AttributeBuilder.XML_NODE_ATTRIBUTE, new XMLElement());

        assertTrue(result instanceof AttributeProperties);
	}

    @Test
	public void createElementRegisteredAttributeProperties() throws Exception {
        AttributeBuilder aBulid = new AttributeBuilder(null, null);
        Object result = aBulid.createElement(null, AttributeBuilder.XML_NODE_REGISTERED_ATTRIBUTE_NAME, new XMLElement());

        assertTrue(result instanceof RegisteredAttributeProperties);
	}

    @Test
	public void createElementParent() throws Exception {
        AttributeBuilder aBulid = new AttributeBuilder(null, null);
        Object result = aBulid.createElement(1, AttributeBuilder.XML_NODE_ATTRIBUTE_REGISTRY, new XMLElement());

        assertEquals(1, result);
	}

    @Test
	public void createElementNull() throws Exception {
        AttributeBuilder aBulid = new AttributeBuilder(null, null);
        Object result = aBulid.createElement(null, AttributeBuilder.XML_NODE_ATTRIBUTE_LAYOUT, new XMLElement());

        assertNull(result);
	}
}
