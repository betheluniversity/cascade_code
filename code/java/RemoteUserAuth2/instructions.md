In order to interface our CAS with Cascade, we have to make a .jar file called `RemoteUserAuth.jar`.

It's based off of the instructions we found at this site: https://github.com/hannonhill/Custom-Authentication-Module-Examples/tree/master/CAS/remoteUserAuth

There are 3 files that are needed:
1. RemoteUserAuth.java was originally downloaded from https://github.com/hannonhill/Custom-Authentication-Module-Examples/blob/master/CAS/remoteUserAuth/RemoteUserAuth.java but has since been customized.
2. authentication-8.6.jar was downloaded from https://github.com/hannonhill/Cascade-Server-Authentication-API/tree/master/dist
3. servlet-api.jar was included in Cascade's .zip. It was extracted to /opt/cascade/tomcat/lib

Here's the executive summary of how to turn those three files into RemoteUserAuth.jar:
1. Make sure Java is installed.
2. Put authentication-8.6.jar in /opt/cascade/tomcat/lib, next to servlet-api.jar
3. `mkdir /opt/remote_user_auth`
4. `cd /opt/remote_user_auth`
5. `vi RemoteUserAuth.java` and set its content to be what's in the repository, adjacent to this .md file.
6. `javac -classpath /opt/cascade/tomcat/lib/authentication-8.6.jar:/opt/cascade/tomcat/lib/servlet-api.jar RemoteUserAuth.java`
8. `mkdir /opt/cascade/tomcat/webapps/ROOT/WEB-INF/classes/org/apache/cascade`
9. `cp RemoteUserAuth.class /opt/cascade/tomcat/webapps/ROOT/WEB-INF/classes/org/apache/cascade`
10. `jar cf RemoteUserAuth.jar /opt/cascade/tomcat/webapps/ROOT/WEB-INF/classes/org/apache/cascade/RemoteUserAuth.class`
11. `cp RemoteUserAuth.jar /opt/cascade/tomcat/webapps/ROOT/WEB-INF/lib`

This process is automatically done by Puppet, but I want to make sure that it gets documented here in case anyone needs to do it manually in the future. The Puppet manifest that does this is /webapps_modules/webapps_cascade/manifests/compile_remote_user_auth.pp
