/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.tools.service.builder.test.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.model.UADPartialEntry;
import com.liferay.portal.tools.service.builder.test.service.UADPartialEntryService;
import com.liferay.portal.tools.service.builder.test.service.persistence.UADPartialEntryPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the uad partial entry remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.UADPartialEntryServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.UADPartialEntryServiceImpl
 * @generated
 */
public abstract class UADPartialEntryServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, UADPartialEntryService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>UADPartialEntryService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.tools.service.builder.test.service.UADPartialEntryServiceUtil</code>.
	 */

	/**
	 * Returns the uad partial entry local service.
	 *
	 * @return the uad partial entry local service
	 */
	public com.liferay.portal.tools.service.builder.test.service.
		UADPartialEntryLocalService getUADPartialEntryLocalService() {

		return uadPartialEntryLocalService;
	}

	/**
	 * Sets the uad partial entry local service.
	 *
	 * @param uadPartialEntryLocalService the uad partial entry local service
	 */
	public void setUADPartialEntryLocalService(
		com.liferay.portal.tools.service.builder.test.service.
			UADPartialEntryLocalService uadPartialEntryLocalService) {

		this.uadPartialEntryLocalService = uadPartialEntryLocalService;
	}

	/**
	 * Returns the uad partial entry remote service.
	 *
	 * @return the uad partial entry remote service
	 */
	public UADPartialEntryService getUADPartialEntryService() {
		return uadPartialEntryService;
	}

	/**
	 * Sets the uad partial entry remote service.
	 *
	 * @param uadPartialEntryService the uad partial entry remote service
	 */
	public void setUADPartialEntryService(
		UADPartialEntryService uadPartialEntryService) {

		this.uadPartialEntryService = uadPartialEntryService;
	}

	/**
	 * Returns the uad partial entry persistence.
	 *
	 * @return the uad partial entry persistence
	 */
	public UADPartialEntryPersistence getUADPartialEntryPersistence() {
		return uadPartialEntryPersistence;
	}

	/**
	 * Sets the uad partial entry persistence.
	 *
	 * @param uadPartialEntryPersistence the uad partial entry persistence
	 */
	public void setUADPartialEntryPersistence(
		UADPartialEntryPersistence uadPartialEntryPersistence) {

		this.uadPartialEntryPersistence = uadPartialEntryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return UADPartialEntryService.class.getName();
	}

	protected Class<?> getModelClass() {
		return UADPartialEntry.class;
	}

	protected String getModelClassName() {
		return UADPartialEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = uadPartialEntryPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(
		type = com.liferay.portal.tools.service.builder.test.service.UADPartialEntryLocalService.class
	)
	protected com.liferay.portal.tools.service.builder.test.service.
		UADPartialEntryLocalService uadPartialEntryLocalService;

	@BeanReference(type = UADPartialEntryService.class)
	protected UADPartialEntryService uadPartialEntryService;

	@BeanReference(type = UADPartialEntryPersistence.class)
	protected UADPartialEntryPersistence uadPartialEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}