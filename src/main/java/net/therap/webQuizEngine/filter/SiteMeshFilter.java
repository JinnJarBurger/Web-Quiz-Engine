package net.therap.webQuizEngine.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * @author adnan
 * @since 3/26/2023
 */
public class SiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/META-INF/jsp/decorator.jsp")
                .setIncludeErrorPages(true);
    }
}
