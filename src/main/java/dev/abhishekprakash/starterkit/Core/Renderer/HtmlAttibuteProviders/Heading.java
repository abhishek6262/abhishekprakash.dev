package dev.abhishekprakash.starterkit.Core.Renderer.HtmlAttibuteProviders;

import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

public class Heading implements AttributeProvider {

    @Override
    public void setAttributes(Node node, String s, Map<String, String> attributes) {
        int headingLevel = ((org.commonmark.node.Heading) node).getLevel();

        String classAttribute = switch (headingLevel) {
            case 1 -> "mb-4 text-5xl font-extrabold leading-[50px]";
            case 2 -> "mb-4 text-4xl font-bold leading-[50px]";
            case 3 -> "mb-4 text-3xl font-bold leading-[50px]";
            case 4 -> "mb-4 text-2xl font-bold leading-[50px]";
            case 5 -> "mb-4 text-xl font-bold leading-[50px]";
            default -> "mb-4 text-lg font-bold leading-[50px]";
        };

        attributes.put("class", classAttribute);
    }

}