package com.liferay.docs.hook.indexer;

import java.util.Locale;

import javax.portlet.PortletURL;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.model.User;

public class SampleIndexerPostProcessor extends BaseIndexerPostProcessor {

	public void postProcessContextQuery(BooleanQuery booleanQuery, SearchContext searchcontext) throws Exception {
		if (_log.isDebugEnabled())
			_log.debug(" postProcessContextQuery()");
	}

	public void postProcessDocument(Document document, Object object) throws Exception {
		User userEntity = (User) object;
		String indexerUserTitle = "";
		try {
			indexerUserTitle = userEntity.getJobTitle();
		} catch (Exception e) {
		}
		if (indexerUserTitle.length() > 0)
			document.addText(Field.TITLE, indexerUserTitle);
	}

	public void postProcessFullQuery(BooleanQuery fullQuery, SearchContext searchcontext) throws Exception {
		if (_log.isDebugEnabled())
			_log.debug(" postProcessFullQuery()");
	}

	public void postProcessSearchQuery(BooleanQuery searchquery, SearchContext searchcontext) throws Exception {
		if (_log.isDebugEnabled())
			_log.debug(" postProcessSearchQuery()");
	}

	public void postProcessSummary(Summary summary, Document document, Locale locale, String snippet,
			PortletURL portletURL) {
		if (_log.isDebugEnabled())
			_log.debug("postProcessSummary()");
	}

	private static Log _log = LogFactoryUtil.getLog(SampleIndexerPostProcessor.class);

}
