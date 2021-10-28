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

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DSLQueryStatusEntry service. Represents a row in the &quot;DSLQueryStatusEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.tools.service.builder.test.model.impl.DSLQueryStatusEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.tools.service.builder.test.model.impl.DSLQueryStatusEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DSLQueryStatusEntry
 * @generated
 */
@ProviderType
public interface DSLQueryStatusEntryModel
	extends BaseModel<DSLQueryStatusEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a dsl query status entry model instance should use the {@link DSLQueryStatusEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this dsl query status entry.
	 *
	 * @return the primary key of this dsl query status entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this dsl query status entry.
	 *
	 * @param primaryKey the primary key of this dsl query status entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the dsl query status entry ID of this dsl query status entry.
	 *
	 * @return the dsl query status entry ID of this dsl query status entry
	 */
	public long getDslQueryStatusEntryId();

	/**
	 * Sets the dsl query status entry ID of this dsl query status entry.
	 *
	 * @param dslQueryStatusEntryId the dsl query status entry ID of this dsl query status entry
	 */
	public void setDslQueryStatusEntryId(long dslQueryStatusEntryId);

	/**
	 * Returns the dsl query entry ID of this dsl query status entry.
	 *
	 * @return the dsl query entry ID of this dsl query status entry
	 */
	public long getDslQueryEntryId();

	/**
	 * Sets the dsl query entry ID of this dsl query status entry.
	 *
	 * @param dslQueryEntryId the dsl query entry ID of this dsl query status entry
	 */
	public void setDslQueryEntryId(long dslQueryEntryId);

	/**
	 * Returns the status of this dsl query status entry.
	 *
	 * @return the status of this dsl query status entry
	 */
	@AutoEscape
	public String getStatus();

	/**
	 * Sets the status of this dsl query status entry.
	 *
	 * @param status the status of this dsl query status entry
	 */
	public void setStatus(String status);

	/**
	 * Returns the status date of this dsl query status entry.
	 *
	 * @return the status date of this dsl query status entry
	 */
	public Date getStatusDate();

	/**
	 * Sets the status date of this dsl query status entry.
	 *
	 * @param statusDate the status date of this dsl query status entry
	 */
	public void setStatusDate(Date statusDate);

	@Override
	public DSLQueryStatusEntry cloneWithOriginalValues();

}