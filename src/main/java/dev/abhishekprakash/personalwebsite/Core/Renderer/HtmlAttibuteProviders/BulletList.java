package dev.abhishekprakash.personalwebsite.Core.Renderer.HtmlAttibuteProviders;

import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

public class BulletList implements AttributeProvider {

    @Override
    public void setAttributes(Node node, String s, Map<String, String> attributes) {
        attributes.put("class", "list-disc leading-loose ml-5");
    }

}