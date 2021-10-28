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

package com.liferay.commerce.price.list.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommercePriceListAccountRel service. Represents a row in the &quot;CommercePriceListAccountRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListAccountRel
 * @generated
 */
@ProviderType
public interface CommercePriceListAccountRelModel
	extends BaseModel<CommercePriceListAccountRel>, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce price list account rel model instance should use the {@link CommercePriceListAccountRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce price list account rel.
	 *
	 * @return the primary key of this commerce price list account rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce price list account rel.
	 *
	 * @param primaryKey the primary key of this commerce price list account rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this commerce price list account rel.
	 *
	 * @return the uuid of this commerce price list account rel
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce price list account rel.
	 *
	 * @param uuid the uuid of this commerce price list account rel
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the commerce price list account rel ID of this commerce price list account rel.
	 *
	 * @return the commerce price list account rel ID of this commerce price list account rel
	 */
	public long getCommercePriceListAccountRelId();

	/**
	 * Sets the commerce price list account rel ID of this commerce price list account rel.
	 *
	 * @param commercePriceListAccountRelId the commerce price list account rel ID of this commerce price list account rel
	 */
	public void setCommercePriceListAccountRelId(
		long commercePriceListAccountRelId);

	/**
	 * Returns the company ID of this commerce price list account rel.
	 *
	 * @return the company ID of this commerce price list account rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce price list account rel.
	 *
	 * @param companyId the company ID of this commerce price list account rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce price list account rel.
	 *
	 * @return the user ID of this commerce price list account rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce price list account rel.
	 *
	 * @param userId the user ID of this commerce price list account rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce price list account rel.
	 *
	 * @return the user uuid of this commerce price list account rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce price list account rel.
	 *
	 * @param userUuid the user uuid of this commerce price list account rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce price list account rel.
	 *
	 * @return the user name of this commerce price list account rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce price list account rel.
	 *
	 * @param userName the user name of this commerce price list account rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce price list account rel.
	 *
	 * @return the create date of this commerce price list account rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce price list account rel.
	 *
	 * @param createDate the create date of this commerce price list account rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce price list account rel.
	 *
	 * @return the modified date of this commerce price list account rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce price list account rel.
	 *
	 * @param modifiedDate the modified date of this commerce price list account rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce account ID of this commerce price list account rel.
	 *
	 * @return the commerce account ID of this commerce price list account rel
	 */
	public long getCommerceAccountId();

	/**
	 * Sets the commerce account ID of this commerce price list account rel.
	 *
	 * @param commerceAccountId the commerce account ID of this commerce price list account rel
	 */
	public void setCommerceAccountId(long commerceAccountId);

	/**
	 * Returns the commerce price list ID of this commerce price list account rel.
	 *
	 * @return the commerce price list ID of this commerce price list account rel
	 */
	public long getCommercePriceListId();

	/**
	 * Sets the commerce price list ID of this commerce price list account rel.
	 *
	 * @param commercePriceListId the commerce price list ID of this commerce price list account rel
	 */
	public void setCommercePriceListId(long commercePriceListId);

	/**
	 * Returns the order of this commerce price list account rel.
	 *
	 * @return the order of this commerce price list account rel
	 */
	public int getOrder();

	/**
	 * Sets the order of this commerce price list account rel.
	 *
	 * @param order the order of this commerce price list account rel
	 */
	public void setOrder(int order);

	/**
	 * Returns the last publish date of this commerce price list account rel.
	 *
	 * @return the last publish date of this commerce price list account rel
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this commerce price list account rel.
	 *
	 * @param lastPublishDate the last publish date of this commerce price list account rel
	 */
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public CommercePriceListAccountRel cloneWithOriginalValues();

}