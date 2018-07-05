/*
  * RemoteUser authentication module for Cascade
  *
  * This module lets you use Tomcat or Apache authentication
  * with Cascade.
  *
  * February 2008, Earl Fogel, University of Saskatchewan
*/
// /opt/cascade/tomcat/webapps/ROOT/WEB-INF/classes/org/apache/cascade
package org.apache.cascade;

import java.io.*;
import javax.servlet.http.*;
import com.hannonhill.cascade.model.security.authentication.*;

public class RemoteUserAuth extends java.lang.Object
  implements com.hannonhill.cascade.model.security.authentication.Authenticator {

    /* Constructors */
    public RemoteUserAuth() {}

    /* Methods */
    public boolean redirect(HttpServletRequest request, HttpServletResponse response, AuthenticationPhase phase) throws IOException {

        /*
          * User has not authenticated, show Cascade login screen.
        */
        if (request.getHeader("CAS-User") == null ) {
            return false;
        }

        /*
          * User has authenticated with web server.
          * Redirect to customauth for login, somewhere else for logout.
        */
        if (phase == AuthenticationPhase.LOGIN) {
            response.sendRedirect("/customauth");
        }
        else {
            response.sendRedirect("http://auth.bethel.edu/cas/logout");
        }
        return true;
    }

    public String authenticate(HttpServletRequest request, HttpServletResponse response) {
        /* This line was originally 
             return request.getRemoteUser();
           but that method isn't working. Our workaround is to set a custom header in the Apache vhost named 
           "CAS-User" and then read it here and in the if statement above.
        */
        return request.getHeader("CAS-User");
    }
}
