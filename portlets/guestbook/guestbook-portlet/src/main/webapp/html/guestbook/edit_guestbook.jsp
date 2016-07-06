<%@include file="/html/init.jsp"%>

<portlet:renderURL var="viewURL"></portlet:renderURL>

<portlet:actionURL var="addGuestbookURL">
	<portlet:param name="action" value="addGuestbook" />
</portlet:actionURL>

<liferay-ui:error key="existing-guestbook" message="existing-guestbook" />

<aui:form action="<%=addGuestbookURL%>" name="fm">

	<aui:fieldset>

		<aui:input name="guestbookName" />

	</aui:fieldset>

	<aui:button-row>

		<aui:button type="submit" />
		<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>

	</aui:button-row>

</aui:form>