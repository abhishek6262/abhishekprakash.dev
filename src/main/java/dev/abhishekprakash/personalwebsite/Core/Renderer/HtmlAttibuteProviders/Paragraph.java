package dev.abhishekprakash.personalwebsite.Core.Renderer.HtmlAttibuteProviders;

import org.commonmark.node.BlockQuote;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

public class Paragraph implements AttributeProvider {

    @Override
    public void setAttributes(Node node, String s, Map<String, String> attributes) {
        if (node.getParent() instanceof BlockQuote) {
            attributes.put("class", "text-xl italic font-medium leading-relaxed text-gray-900");
        } else {
            attributes.put("class", "mb-4 leading-relaxed");
        }
    }

}