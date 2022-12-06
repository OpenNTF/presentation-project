<%@tag description="Individual header nav link" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="value" required="true" type="java.lang.Object" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<li>
	<a href="${empty pageScope.value.url ? 'javascript:void(0)' : urlBean.relativizeUrl(pageScope.value.url)}">${fn:escapeXml(pageScope.value.label)}</a>
	<c:if test="${not empty pageScope.value.children}">
		<ul>
			<c:forEach items="${pageScope.value.children}" var="childLink">
				<t:navlink value="${childLink}"/>
			</c:forEach>
		</ul>
	</c:if>
</li>