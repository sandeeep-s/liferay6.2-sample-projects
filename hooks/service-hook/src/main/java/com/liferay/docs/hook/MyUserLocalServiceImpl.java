package com.liferay.docs.hook;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceWrapper;

public class MyUserLocalServiceImpl extends UserLocalServiceWrapper {

	public MyUserLocalServiceImpl(UserLocalService userLocalService) {
		super(userLocalService);
	}
	
	@Override
	public User deleteUser(long userId) throws PortalException, SystemException {
		
		System.out.println("Dleting user through MyUserLocalServiceImpl");
		
		return super.deleteUser(userId);
	}
}
