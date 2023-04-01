package net.therap.webQuizEngine.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author adnan
 * @since 3/26/2023
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Configuration
    protected static class JpaWebConfiguration implements WebMvcConfigurer {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor());
        }

        @Bean
        public OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor() {
            return new OpenEntityManagerInViewInterceptor();
        }

    }
}
