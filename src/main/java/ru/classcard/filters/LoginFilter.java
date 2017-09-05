package ru.classcard.filters;

import ru.classcard.model.User;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Фильтр для проверки авторизации пользователя
 */
public class LoginFilter implements Filter {

    private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<partial-response><redirect url=\"%s\"></redirect></partial-response>";
    public static final String USER_SESSION_ATTR = "currentUserAttr";

    private static final String LOGIN_URL_PATH = "/login.xhtml";
    private static final String ACCESS_DENIED_URL_PATH = "/error/accessDenied.xhtml";
    private static final String ROOT_PAGE_PATH = "/";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //No implementation needed
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = getUser(request);
        String path = request.getRequestURI().substring(request.getContextPath().length());
        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");

        if (isAuthorizedAndLoginPageRequested(user, path)) {
            redirectToUrl(request, response, ROOT_PAGE_PATH);
        } else if (isIgnorableURL(path, resourceRequest)) {
            filterChain.doFilter(servletRequest, response);
        } else if (user != null && ROOT_PAGE_PATH.equals(path)) {
            filterChain.doFilter(servletRequest, response);
        } else if (user == null) {
            redirectToUrl(request, response, LOGIN_URL_PATH);
        } else if (!checkAccess(user, path)) {
            redirectToUrl(request, response, ACCESS_DENIED_URL_PATH);
        } else {
            filterChain.doFilter(servletRequest, response);
        }
    }

    private boolean isIgnorableURL(String path, boolean resourceRequest) {
        return LOGIN_URL_PATH.equals(path) || ACCESS_DENIED_URL_PATH.equals(path) || resourceRequest;
    }

    private boolean isAuthorizedAndLoginPageRequested(User user, String path) {
        return LOGIN_URL_PATH.equals(path) && user != null;
    }

    @Override
    public void destroy() {
        //No implementation needed
    }

    private void redirectToUrl(HttpServletRequest request, HttpServletResponse response, String urlPath) throws IOException {
        boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces-Request"));
        if (ajaxRequest) {
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().printf(AJAX_REDIRECT_XML, request.getContextPath() + urlPath);
            // Возвращаем специальный XML response говорящий JSF ajax сделать редирект.
        } else {
            response.sendRedirect(request.getContextPath() + urlPath);
        }
    }

    private User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (User) session.getAttribute(USER_SESSION_ATTR) : null;

    }

    private boolean checkAccess(User user, String urlPath) {
        //TODO check access to requested page
        //return getAccessService(user).isURLPathAccessible(urlPath);
        return true;
    }
}
