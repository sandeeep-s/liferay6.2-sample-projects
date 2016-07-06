package com.liferay.docs.guestbook.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for Guestbook. This utility wraps
 * {@link com.liferay.docs.guestbook.service.impl.GuestbookServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see GuestbookService
 * @see com.liferay.docs.guestbook.service.base.GuestbookServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.impl.GuestbookServiceImpl
 * @generated
 */
public class GuestbookServiceUtil {
    private static GuestbookService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.liferay.docs.guestbook.service.impl.GuestbookServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static com.liferay.docs.guestbook.model.Guestbook addGuestbook(
        long userId, java.lang.String name,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().addGuestbook(userId, name, serviceContext);
    }

    public static com.liferay.docs.guestbook.model.Guestbook deleteGuestbook(
        long guestbookId,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteGuestbook(guestbookId, serviceContext);
    }

    public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getGuestbooks(groupId);
    }

    public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getGuestbooks(groupId, start, end);
    }

    public static int getGuestbooksCount(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getGuestbooksCount(groupId);
    }

    public static com.liferay.docs.guestbook.model.Guestbook updateGuestbook(
        long userId, long guestbookId, java.lang.String name,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateGuestbook(userId, guestbookId, name, serviceContext);
    }

    public static void clearService() {
        _service = null;
    }

    public static GuestbookService getService() {
        if (_service == null) {
            InvokableService invokableService = (InvokableService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    GuestbookService.class.getName());

            if (invokableService instanceof GuestbookService) {
                _service = (GuestbookService) invokableService;
            } else {
                _service = new GuestbookServiceClp(invokableService);
            }

            ReferenceRegistry.registerReference(GuestbookServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(GuestbookService service) {
    }
}
