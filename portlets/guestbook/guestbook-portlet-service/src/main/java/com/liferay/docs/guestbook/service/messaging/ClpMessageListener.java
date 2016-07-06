package com.liferay.docs.guestbook.service.messaging;

import com.liferay.docs.guestbook.service.ClpSerializer;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.EntryServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;


public class ClpMessageListener extends BaseMessageListener {
    public static String getServletContextName() {
        return ClpSerializer.getServletContextName();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        String command = message.getString("command");
        String servletContextName = message.getString("servletContextName");

        if (command.equals("undeploy") &&
                servletContextName.equals(getServletContextName())) {
            EntryLocalServiceUtil.clearService();

            EntryServiceUtil.clearService();
            GuestbookLocalServiceUtil.clearService();

            GuestbookServiceUtil.clearService();
        }
    }
}
