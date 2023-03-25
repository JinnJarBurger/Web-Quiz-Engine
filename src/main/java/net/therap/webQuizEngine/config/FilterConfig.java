package net.therap.webQuizEngine.config;

import net.therap.webQuizEngine.filter.AuthenticationFilter;
import net.therap.webQuizEngine.filter.SiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;
import static javax.servlet.DispatcherType.ERROR;
import static javax.servlet.DispatcherType.FORWARD;

/**
 * @author adnan
 * @since 3/25/2023
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new AuthenticationFilter());

        filter.addUrlPatterns(
                "/quiz/*",
                "/question/*",
                "/summary/*",
                "/history/*",
                "/answer/*"
        );

        filter.setOrder(2);

        return filter;
    }

    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean<>();
        filter.setFilter(new SiteMeshFilter());
        filter.setDispatcherTypes(ERROR, FORWARD);
        filter.setOrder(3);

        return filter;
    }
}
