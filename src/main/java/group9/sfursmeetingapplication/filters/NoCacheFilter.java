/**
 * This class is a filter that prevents the browser from caching the pages.
 * This is done to prevent the user from accessing the pages without logging in.
 * 
 */
package group9.sfursmeetingapplication.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter implements Filter {

    /**
     * This method is used to destroy the filter.
     */
    @Override
    public void destroy() {}

    /**
     * This method is used to initialize the filter.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * This method is used to prevent the browser from caching the pages that are requested by the user.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

        chain.doFilter(req, res);
    }
}