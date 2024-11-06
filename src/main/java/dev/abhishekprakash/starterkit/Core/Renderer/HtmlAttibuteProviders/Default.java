package dev.abhishekprakash.starterkit.Core.Renderer.HtmlAttibuteProviders;

import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

public class Default implements AttributeProvider {

    @Override
    public void setAttributes(Node node, String s, Map<String, String> attributes) {
        // This will set for all non-matching html tags.
    }

}