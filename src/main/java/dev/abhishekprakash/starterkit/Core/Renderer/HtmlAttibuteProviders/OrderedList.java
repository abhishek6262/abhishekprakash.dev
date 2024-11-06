package dev.abhishekprakash.starterkit.Core.Renderer.HtmlAttibuteProviders;

import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

public class OrderedList implements AttributeProvider {

    @Override
    public void setAttributes(Node node, String s, Map<String, String> attributes) {
        attributes.put("class", "list-decimal leading-loose ml-5");
    }

}