package dev.abhishekprakash.personalwebsite.Core.Renderer.HtmlAttibuteProviders;

import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

public class FencedCodeBlock implements AttributeProvider {

    @Override
    public void setAttributes(Node node, String s, Map<String, String> attributes) {
        if (!s.equals("pre")) return;

        attributes.put("class", "overflow-y-auto my-6 p-6 bg-sky-950 text-gray-200 rounded");
    }

}