# store-api
Create MySQL Data base
Go to WebContent/WEB-INF/spring-servlet.xml
Search bean configuration in spring-servlet.xml file with an ID: dataSource
Update the bean with your MySQL configuration (PORT, USERNAME, PASSWORD, DATA BASE NAME).
Update Maven dependencies
Add maven dependencies to the web deployment properties (Right click on project > Properties >Deploymnet Assembly > Add.. > Java Build path entries > Maven dependencies > apply & close)
Add Apache tomcat Server (V7.0 adviced)
Run on server.
Once running Please test it with POSTMAN (be careful with how you formate your Requests body In JSON) and to check routes Check the package org.eclipse.controllers
