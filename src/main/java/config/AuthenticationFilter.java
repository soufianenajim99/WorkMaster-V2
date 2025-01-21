package config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")  // This will apply the filter to all pages
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter initialization if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Skip login, login form, or any public pages
        String uri = httpRequest.getRequestURI();
        if (uri.contains("/index.jsp") || uri.contains("/login")) {
            chain.doFilter(request, response);  // Allow access to login and public pages
            return;
        }

        // Check if the user is logged in (i.e., "user" is set in session)
        HttpSession session = httpRequest.getSession();
        if (session.getAttribute("user") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");  // Redirect to login page
        } else {
            chain.doFilter(request, response);  // Allow access if user is logged in
        }
    }

    @Override
    public void destroy() {
        // Clean up any resources if needed
    }
}


