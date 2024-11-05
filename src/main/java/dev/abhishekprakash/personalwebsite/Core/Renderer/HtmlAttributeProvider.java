package dev.abhishekprakash.personalwebsite.Core.Renderer;

import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

public class HtmlAttributeProvider implements AttributeProvider {

    @Override
    public void setAttributes(Node node, String s, Map<String, String> attributes) {
        HtmlAttributeProviderFactory
                .createAttributeProvider(node, s)
                .setAttributes(node, s, attributes);
    }

}