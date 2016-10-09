<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>MontPic ErrorGeocode</title>

	<link rel="stylesheet" href="<c:url value="/resources/css/styleError.css"/>">
</head>
<body>
	
	<h1>MOUNTAIN NOT FOUND</h1>
	
	<p>Sorry, but we couldn't geocode "${keyword}" as a mountain.<br/><br/>Possible causes:<br/>*You entered a non valid mountain name.<br/>*The mountain does not exist in the geonames archive.</p>

	
	<spring:url value="/" var="searchView"/>
	<a href="${searchView}"><button class="goToSearch">Search again</button></a>
	
	<footer>
		<div id="images_hz">
			<a href="http://www.polimi.it/"><img src="https://upload.wikimedia.org/wikipedia/en/b/be/Logo_Politecnico_Milano.png" alt="" width="50" height="50"/></a>
			<a href="http://spring.io/"><img src="http://www.lessonslab.com/wp-content/uploads/2014/11/spring-logo-220x150.png" alt="" width="60" height="60"/></a>
			<a href="https://www.mapbox.com/"><img src="http://eric-j-anderson.com/wp-content/uploads/2014/04/mapbox-logo-256.png" alt="" width="60" height="60"/></a>
			<a href="http://leafletjs.com/"><img src="http://leafletjs.com/docs/images/logo.png" alt="" width="100" height="50"/></a>
			<a href="https://eclipse.org/"><img src="https://eclipse.org/eclipse.org-common/themes/solstice/public/images/logo/eclipse-800x188.png" alt="" width="90" height="35"/></a>
			<a href="http://maven.apache.org/"><img src="http://maven.apache.org/images/maven-logo-black-on-white.png" alt="" width="70" height="40"/></a>
			<a href="http://hibernate.org/"><img src="http://static.jboss.org/hibernate/images/hibernate_logo_whitebkg_200px.png" alt="" width="70" height="40"/></a>
			<a href="https://www.mysql.com/"><img src="https://www.mysql.com/common/logos/logo-mysql-110x57.png" alt="" width="60" height="60"/></a>
			<a href="http://www.geonames.org/"><img src="https://www.drupal.org/files/project-images/geonames_0.png" alt="" width="90" height="40"/></a>
		</div>
	</footer>
						
</body>
</html>