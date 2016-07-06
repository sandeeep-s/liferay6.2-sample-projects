package com.liferay.docs.hook.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

public class ExampleStrutsPortletAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		Long currentuser = themeDisplay.getUserId();

		if (currentuser != null) {
			System.out.println("Custom Struts Action 2");

		}
		originalStrutsPortletAction.processAction(originalStrutsPortletAction, portletConfig, actionRequest,
				actionResponse);
	}

	@Override
	public String render(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		System.out.println("Custom Struts Action");
		return originalStrutsPortletAction.render(originalStrutsPortletAction, portletConfig, renderRequest,
				renderResponse);
	}

	@Override
	public void serveResource(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig,
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		// TODO Auto-generated method stub
		originalStrutsPortletAction.serveResource(originalStrutsPortletAction, portletConfig, resourceRequest,
				resourceResponse);
	}

}
