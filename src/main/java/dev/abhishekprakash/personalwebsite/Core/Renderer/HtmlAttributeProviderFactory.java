package dev.abhishekprakash.personalwebsite.Core.Renderer;

import dev.abhishekprakash.personalwebsite.Core.Renderer.HtmlAttibuteProviders.*;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class HtmlAttributeProviderFactory {

    private static final Map<Class<? extends Node>, Supplier<AttributeProvider>> nodeProviderMap = new HashMap<>();
    private static final Map<String, Supplier<AttributeProvider>> tagProviderMap = new HashMap<>();

    static {
        // Register Node types with corresponding providers
        nodeProviderMap.put(org.commonmark.node.BlockQuote.class, BlockQuote::new);
        nodeProviderMap.put(org.commonmark.node.BulletList.class, BulletList::new);
        nodeProviderMap.put(org.commonmark.node.FencedCodeBlock.class, FencedCodeBlock::new);
        nodeProviderMap.put(org.commonmark.node.Heading.class, Heading::new);
        nodeProviderMap.put(org.commonmark.node.Link.class, Link::new);
        nodeProviderMap.put(org.commonmark.node.OrderedList.class, OrderedList::new);
        nodeProviderMap.put(org.commonmark.node.Paragraph.class, Paragraph::new);

        // Register specific tags if needed
        tagProviderMap.put("hr", HorizontalRule::new);
    }

    private HtmlAttributeProviderFactory() {
    }

    public static AttributeProvider createAttributeProvider(Node node, String tag) {
        // Check if the node's class has a registered provider
        Supplier<AttributeProvider> provider = nodeProviderMap.get(node.getClass());

        if (provider != null) {
            return provider.get();
        }

        // Check if the tag has a registered provider
        provider = tagProviderMap.get(tag);

        if (provider != null) {
            return provider.get();
        }

        return new Default();
    }
}