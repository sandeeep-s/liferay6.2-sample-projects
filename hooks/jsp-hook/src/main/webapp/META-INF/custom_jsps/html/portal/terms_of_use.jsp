<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portal/terms_of_use.portal.jsp"/>
</liferay-util:buffer>

<%
	html = StringUtil.add("This is before original test terms of use", html, "\n");
	html = StringUtil.add(html,"This is after original test terms of use",  "\n");
%>

<%=html%>
