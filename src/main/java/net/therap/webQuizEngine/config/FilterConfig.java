package net.therap.webQuizEngine.config;

import net.therap.webQuizEngine.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
