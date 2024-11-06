package dev.abhishekprakash.starterkit.Core.Renderer.HtmlAttibuteProviders;

import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

public class BlockQuote implements AttributeProvider {

    @Override
    public void setAttributes(Node node, String s, Map<String, String> attributes) {
        attributes.put("class", "p-4 my-6 border-s-4 border-gray-300 bg-gray-50");
    }

}