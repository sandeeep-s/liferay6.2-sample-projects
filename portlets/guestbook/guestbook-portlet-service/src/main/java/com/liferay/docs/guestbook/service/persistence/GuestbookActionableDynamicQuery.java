package com.liferay.docs.guestbook.service.persistence;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class GuestbookActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public GuestbookActionableDynamicQuery() throws SystemException {
        setBaseLocalService(GuestbookLocalServiceUtil.getService());
        setClass(Guestbook.class);

        setClassLoader(com.liferay.docs.guestbook.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("guestbookId");
    }
}
