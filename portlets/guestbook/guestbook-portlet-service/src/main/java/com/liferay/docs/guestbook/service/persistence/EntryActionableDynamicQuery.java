package com.liferay.docs.guestbook.service.persistence;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class EntryActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public EntryActionableDynamicQuery() throws SystemException {
        setBaseLocalService(EntryLocalServiceUtil.getService());
        setClass(Entry.class);

        setClassLoader(com.liferay.docs.guestbook.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("entryId");
    }
}
