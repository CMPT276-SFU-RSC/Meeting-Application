/**
 * This class is used to configure the application. It is used to register the NoCacheFilter class to the application.
 * The NoCacheFilter class is used to prevent the browser from caching the pages that are requested by the user.
 * This is done to prevent the user from accessing the pages without logging in.
 
 */
package group9.sfursmeetingapplication.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import group9.sfursmeetingapplication.filters.NoCacheFilter;

@Configuration // This annotation indicates that this class is a configuration class.
public class AppConfig {

    /**
     * This method is used to register the NoCacheFilter class to the application.
     * @return FilterRegistrationBean<NoCacheFilter> This returns the FilterRegistrationBean object that is used to register the NoCacheFilter class to the application.
     */
    @Bean
    public FilterRegistrationBean<NoCacheFilter> noCacheFilterRegistration() {
        FilterRegistrationBean<NoCacheFilter> noCacheFilterRegistration = new FilterRegistrationBean<>();
        noCacheFilterRegistration.setFilter(new NoCacheFilter());
        noCacheFilterRegistration.addUrlPatterns("/dashboard/*");
        noCacheFilterRegistration.addUrlPatterns("/users/*");
        noCacheFilterRegistration.addUrlPatterns("/pollcreate/*");
        return noCacheFilterRegistration;
    }
}