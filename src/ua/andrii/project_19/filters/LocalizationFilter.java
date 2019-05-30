package ua.andrii.project_19.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(LocalizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Changes locale in session to the one from the request and forwards to the URI
     * which is specified in the request
     *
     * @param request     ServletRequest object
     * @param response    ServletResponse object
     * @param filterChain FilterChain object
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String lang = request.getParameter("lang");

        logger.debug(lang);
        if (lang != null && !lang.isEmpty()) {
            HttpSession session = req.getSession(true);
            logger.debug("lang = " + lang);
            if (lang.equals("rus")) {
                session.setAttribute("locale", new Locale("ru", "UA"));
            } else {
                session.setAttribute("locale", new Locale("en", "US"));
            }
        }
        filterChain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}

