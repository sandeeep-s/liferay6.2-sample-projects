package com.liferay.docs.hook;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.User;

public class MyUserListener extends BaseModelListener<User>{

	@Override
	public void onAfterCreate(User user) throws ModelListenerException {
		System.out.println("Username added: "+user.getFullName());
	}
	
	@Override
	public void onAfterUpdate(User user) throws ModelListenerException {
		System.out.println("Username updated: "+user.getFullName());
	}
	
}
