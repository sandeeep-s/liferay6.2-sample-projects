package com.liferay.docs.guestbook.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class GuestbookPortlet
 */
public class GuestbookPortlet extends MVCPortlet {

	public void addGuestbook(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Guestbook.class.getName(), actionRequest);

		String name = ParamUtil.getString(actionRequest, "guestbookName");

		try {
			GuestbookServiceUtil.addGuestbook(serviceContext.getUserId(), name, serviceContext);
			SessionMessages.add(actionRequest, "guestbookAdded");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
			actionResponse.setRenderParameter("mvcPath", "/html/guestbook/edit_guestbook.jsp");
		}

	}

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
				actionResponse.setRenderParameter("mvcPath", "/html/guestbook/edit_entry.jsp");
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
				actionResponse.setRenderParameter("mvcPath", "/html/guestbook/edit_entry.jsp");
				e.printStackTrace();
			}
		}

	}

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

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {

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

		super.render(renderRequest, renderResponse);
	}

}
