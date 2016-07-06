package com.liferay.docs.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookServiceUtil;
import com.liferay.docs.guestbook.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactory;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;

/**
 * Portlet implementation class GuestbookPortlet
 */
@Controller
@RequestMapping("VIEW")
public class GuestbookController {

	@ActionMapping(params = "action=addGuestbook")
	public void addGuestbook(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Guestbook.class.getName(), actionRequest);

		String name = ParamUtil.getString(actionRequest, "guestbookName");

		try {
			GuestbookServiceUtil.addGuestbook(serviceContext.getUserId(), name, serviceContext);
			SessionMessages.add(actionRequest, "guestbookAdded");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
			actionResponse.setRenderParameter("action", "viewGuestbookEditForm");
		}

	}

	@ActionMapping(params = "action=addEntry")
	public void addEntry(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(Entry.class.getName(), actionRequest);

		String userName = ParamUtil.getString(actionRequest, "name");
		String email = ParamUtil.getString(actionRequest, "email");
		String message = ParamUtil.getString(actionRequest, "message");
		// long guestbookId = ParamUtil.getLong(actionRequest, "guestbookId");
		String guestbookName = ParamUtil.getString(actionRequest, "guestbookName");
		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
		OrderByComparator orderByComparator = orderByComparatorFactory.create("guestbook", "name", true);

		Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbookByG_N(serviceContext.getScopeGroupId(),
				guestbookName, orderByComparator);

		if (entryId > 0) {
			try {
				EntryServiceUtil.updateEntry(serviceContext.getUserId(), guestbook.getGuestbookId(), entryId, userName,
						email, message, serviceContext);
				SessionMessages.add(actionRequest, "entryUpdated");

				actionResponse.setRenderParameter("guestbookName", guestbookName);

			} catch (Exception e) {
				SessionErrors.add(actionRequest, e.getClass().getName());
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("action", "viewEntryEditForm");
				e.printStackTrace();
			}
		} else {
			try {
				EntryServiceUtil.addEntry(serviceContext.getUserId(), guestbook.getGuestbookId(), userName, email,
						message, serviceContext);
				SessionMessages.add(actionRequest, "entryAdded");

				actionResponse.setRenderParameter("guestbookName", guestbookName);

			} catch (Exception e) {
				SessionErrors.add(actionRequest, e.getClass().getName());
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("action", "viewEntryEditForm");
				e.printStackTrace();
			}
		}

	}

	@ActionMapping(params = "action=deleteEntry")
	public void deleteEntry(ActionRequest actionRequest, ActionResponse actionResponse) {
		long entryId = ParamUtil.getLong(actionRequest, "entryId");
		long guestbookId = ParamUtil.getLong(actionRequest, "guestbookId");

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(Entry.class.getName(), actionRequest);
			EntryServiceUtil.deleteEntry(entryId, serviceContext);
			actionResponse.setRenderParameter("guestbookId", String.valueOf(guestbookId));

		} catch (Exception e) {
		}
	}

	@RenderMapping(params = "action=viewGuestbookEditForm")
	public String renderGuestbookEditForm(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {
		loadGuestbookData(renderRequest);
		return "guestbook/edit_guestbook";
	}

	@RenderMapping(params = "action=viewEntryEditForm")
	public String renderEntryEditForm(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {
		loadGuestbookData(renderRequest);
		return "guestbook/edit_entry";
	}

	@RenderMapping(params = "action=viewEntry")
	public String renderEntryView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {
		loadGuestbookData(renderRequest);
		return "guestbook/view_entry";
	}

	@RenderMapping(params = "action=viewSearchResults")
	public String renderSearchResult() {
		return "guestbook/view_search";
	}

	@RenderMapping
	public String render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {

		loadGuestbookData(renderRequest);

		return "guestbook/view";
	}

	protected RenderRequest loadGuestbookData(RenderRequest renderRequest) throws PortletException, IOException {

		System.out.println("In contorller render");
		try {

			Guestbook guestbook = null;

			ServiceContext serviceContext = ServiceContextFactory.getInstance(Guestbook.class.getName(), renderRequest);

			String guestbookName = "";

			long groupId = serviceContext.getScopeGroupId();

			// First, get all the guestbooks to populate the tabs
			List<Guestbook> guestbooks = GuestbookLocalServiceUtil.getGuestbooks(groupId);

			if (guestbooks.size() == 0) {
				guestbook = GuestbookLocalServiceUtil.addGuestbook(serviceContext.getUserId(), "Main", serviceContext);

				// If we had to create the default guestbook, put it in the
				// request
				renderRequest.setAttribute(WebKeys.GUESTBOOK, guestbook);
			}

			// Now we check to see if the user selected a guestbook
			guestbook = (Guestbook) renderRequest.getAttribute(WebKeys.GUESTBOOK);

			if (guestbook == null) {

				// The user still could have selected a guestbook
				guestbookName = ParamUtil.getString(renderRequest, "guestbookName");
				if (guestbookName.equalsIgnoreCase("")) {

					guestbook = guestbooks.get(0);

				} else {

					OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil
							.getOrderByComparatorFactory();
					OrderByComparator orderByComparator = orderByComparatorFactory.create("guestbook", "name", true);

					guestbook = GuestbookLocalServiceUtil.getGuestbookByG_N(serviceContext.getScopeGroupId(),
							guestbookName, orderByComparator);
				}

			}

			renderRequest.setAttribute(WebKeys.GUESTBOOK, guestbook);

		} catch (Exception e) {

			throw new PortletException(e);
		}

		return renderRequest;
	}

}
