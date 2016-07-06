package com.liferay.docs.guestbook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GuestbookService}.
 *
 * @author Brian Wing Shun Chan
 * @see GuestbookService
 * @generated
 */
public class GuestbookServiceWrapper implements GuestbookService,
    ServiceWrapper<GuestbookService> {
    private GuestbookService _guestbookService;

    public GuestbookServiceWrapper(GuestbookService guestbookService) {
        _guestbookService = guestbookService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _guestbookService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _guestbookService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _guestbookService.invokeMethod(name, parameterTypes, arguments);
    }

    @Override
    public com.liferay.docs.guestbook.model.Guestbook addGuestbook(
        long userId, java.lang.String name,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.addGuestbook(userId, name, serviceContext);
    }

    @Override
    public com.liferay.docs.guestbook.model.Guestbook deleteGuestbook(
        long guestbookId,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.deleteGuestbook(guestbookId, serviceContext);
    }

    @Override
    public java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.getGuestbooks(groupId);
    }

    @Override
    public java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.getGuestbooks(groupId, start, end);
    }

    @Override
    public int getGuestbooksCount(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.getGuestbooksCount(groupId);
    }

    @Override
    public com.liferay.docs.guestbook.model.Guestbook updateGuestbook(
        long userId, long guestbookId, java.lang.String name,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.updateGuestbook(userId, guestbookId, name,
            serviceContext);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public GuestbookService getWrappedGuestbookService() {
        return _guestbookService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedGuestbookService(GuestbookService guestbookService) {
        _guestbookService = guestbookService;
    }

    @Override
    public GuestbookService getWrappedService() {
        return _guestbookService;
    }

    @Override
    public void setWrappedService(GuestbookService guestbookService) {
        _guestbookService = guestbookService;
    }
}
