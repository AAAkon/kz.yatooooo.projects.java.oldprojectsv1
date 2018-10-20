package listeners;

import java.io.IOException;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebListener
public class AppContextAttributeListener implements ServletContextAttributeListener {

    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
    	System.out.println("ServletContext attribute added::{"+servletContextAttributeEvent.getName()+","+servletContextAttributeEvent.getValue()+"}");
    }

    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
    	System.out.println("ServletContext attribute replaced::{"+servletContextAttributeEvent.getName()+","+servletContextAttributeEvent.getValue()+"}");
    }

    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
    	System.out.println("ServletContext attribute removed::{"+servletContextAttributeEvent.getName()+","+servletContextAttributeEvent.getValue()+"}");
    }
	
}
