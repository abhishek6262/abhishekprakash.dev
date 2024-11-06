package dev.abhishekprakash.starterkit.Core.Renderer.HtmlAttibuteProviders;

import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

public class Link implements AttributeProvider {

    @Override
    public void setAttributes(Node node, String s, Map<String, String> attributes) {
        attributes.put("class", "border-b-2 py-2 md:pb-1 transition-colors hover:border-b-black");
    }

}