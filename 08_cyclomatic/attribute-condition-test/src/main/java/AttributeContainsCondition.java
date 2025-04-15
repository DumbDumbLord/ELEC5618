public class AttributeContainsCondition {
    private final String attribute;
    private final String value;

    public AttributeContainsCondition(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public boolean checkNode(SimpleNode node) {
        for (AttributeRow row : node.getAttributes()) {
            if ("ANY".equals(attribute)) {
                if (checkText(row.name))
                    return true;
            } else if (!row.name.equals(attribute)) {
                continue;
            }
            String text = row.value;
            if (checkText(text)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkText(String text) {
        return text != null && text.contains(value);
    }
}
