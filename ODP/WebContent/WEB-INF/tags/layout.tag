<%@tag description="Overall Page template" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="${translation._lang}">
	<head>
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
		<meta name="turbolinks-root" content="${pageContext.request.contextPath}/xsp/app" />
		
		<base href="${pageContext.request.contextPath}/" />
		
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.png" />
		<link rel="apple-touch-icon" sizes="32x32" href="${pageContext.request.contextPath}/img/favicon.png" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tabs.css" />
		
		<link rel="alternate" href="${urlBean.requestUri.resolve('xsp/app/feed.xml')}" type="application/rss+xml" title="${fn:escapeXml(translation.feedRssBlog)}">
		<link rel="EditURI" type="application/rsd+xml" href="${urlBean.requestUri.resolve('xsp/app/rsd.xml')}" />
		
		<!-- 
		<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/hotwired__turbo/7.1.0/dist/turbo.es2017-umd.js"></script>
		 -->
		
		<title>${translation.appTitle}</title>
	</head>
	<body>
		<header id="pageheader">
			<a href="${pageContext.request.contextPath}/xsp/app/" class="logo">
				<img src="img/openntf_222222_bg.jpg" alt="${fn:escapeXml(translation.appTitle)}" />
			</a>
			<!-- 
			<nav id="pagenav" role="navigation">
				<ul>
					<c:forEach items="${NavLinks}" var="link">
						<t:navlink value="${link}"/>
					</c:forEach>
				</ul>
			</nav>
			 -->
			
			<div class="user-info">
				<c:if test="${pageContext.request.remoteUser eq 'Anonymous'}">
					<a href="${pageContext.request.contextPath}?Login&RedirectTo=${pageContext.request.contextPath}/xsp/app">Log In</a>
				</c:if>
				<c:if test="${pageContext.request.remoteUser ne 'Anonymous'}">
					${fn:escapeXml(encoder.abbreviateName(pageContext.request.remoteUser))}
				</c:if>
			</div>
		</header>
		<main id="pagebody">
		<!-- 
			<c:if test="${not empty redirectMessages}">
				<ul>
					<c:forEach items="${redirectMessages}" var="message">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		 -->
			<jsp:doBody />
		</main>
		<footer id="pagefooter">
			<p>${translation.copyright}</p>
		</footer>
	</body>
</html>