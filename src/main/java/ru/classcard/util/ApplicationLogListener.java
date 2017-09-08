package ru.classcard.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static org.slf4j.bridge.SLF4JBridgeHandler.install;
import static org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger;

public class ApplicationLogListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        removeHandlersForRootLogger();
        install();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //No implementation needed
    }
}
