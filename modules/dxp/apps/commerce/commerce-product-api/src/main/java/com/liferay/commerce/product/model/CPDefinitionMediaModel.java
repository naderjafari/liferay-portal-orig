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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CPDefinitionMedia service. Represents a row in the &quot;CPDefinitionMedia&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.product.model.impl.CPDefinitionMediaModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.product.model.impl.CPDefinitionMediaImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPDefinitionMedia
 * @see com.liferay.commerce.product.model.impl.CPDefinitionMediaImpl
 * @see com.liferay.commerce.product.model.impl.CPDefinitionMediaModelImpl
 * @generated
 */
@ProviderType
public interface CPDefinitionMediaModel extends BaseModel<CPDefinitionMedia>,
	GroupedModel, ShardedModel, StagedAuditedModel, TrashedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cp definition media model instance should use the {@link CPDefinitionMedia} interface instead.
	 */

	/**
	 * Returns the primary key of this cp definition media.
	 *
	 * @return the primary key of this cp definition media
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cp definition media.
	 *
	 * @param primaryKey the primary key of this cp definition media
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this cp definition media.
	 *
	 * @return the uuid of this cp definition media
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this cp definition media.
	 *
	 * @param uuid the uuid of this cp definition media
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the cp definition media ID of this cp definition media.
	 *
	 * @return the cp definition media ID of this cp definition media
	 */
	public long getCPDefinitionMediaId();

	/**
	 * Sets the cp definition media ID of this cp definition media.
	 *
	 * @param CPDefinitionMediaId the cp definition media ID of this cp definition media
	 */
	public void setCPDefinitionMediaId(long CPDefinitionMediaId);

	/**
	 * Returns the group ID of this cp definition media.
	 *
	 * @return the group ID of this cp definition media
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this cp definition media.
	 *
	 * @param groupId the group ID of this cp definition media
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this cp definition media.
	 *
	 * @return the company ID of this cp definition media
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cp definition media.
	 *
	 * @param companyId the company ID of this cp definition media
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cp definition media.
	 *
	 * @return the user ID of this cp definition media
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cp definition media.
	 *
	 * @param userId the user ID of this cp definition media
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cp definition media.
	 *
	 * @return the user uuid of this cp definition media
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cp definition media.
	 *
	 * @param userUuid the user uuid of this cp definition media
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cp definition media.
	 *
	 * @return the user name of this cp definition media
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cp definition media.
	 *
	 * @param userName the user name of this cp definition media
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cp definition media.
	 *
	 * @return the create date of this cp definition media
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cp definition media.
	 *
	 * @param createDate the create date of this cp definition media
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cp definition media.
	 *
	 * @return the modified date of this cp definition media
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cp definition media.
	 *
	 * @param modifiedDate the modified date of this cp definition media
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the cp definition ID of this cp definition media.
	 *
	 * @return the cp definition ID of this cp definition media
	 */
	public long getCPDefinitionId();

	/**
	 * Sets the cp definition ID of this cp definition media.
	 *
	 * @param CPDefinitionId the cp definition ID of this cp definition media
	 */
	public void setCPDefinitionId(long CPDefinitionId);

	/**
	 * Returns the file entry ID of this cp definition media.
	 *
	 * @return the file entry ID of this cp definition media
	 */
	public long getFileEntryId();

	/**
	 * Sets the file entry ID of this cp definition media.
	 *
	 * @param fileEntryId the file entry ID of this cp definition media
	 */
	public void setFileEntryId(long fileEntryId);

	/**
	 * Returns the ddm content of this cp definition media.
	 *
	 * @return the ddm content of this cp definition media
	 */
	@AutoEscape
	public String getDDMContent();

	/**
	 * Sets the ddm content of this cp definition media.
	 *
	 * @param DDMContent the ddm content of this cp definition media
	 */
	public void setDDMContent(String DDMContent);

	/**
	 * Returns the priority of this cp definition media.
	 *
	 * @return the priority of this cp definition media
	 */
	public int getPriority();

	/**
	 * Sets the priority of this cp definition media.
	 *
	 * @param priority the priority of this cp definition media
	 */
	public void setPriority(int priority);

	/**
	 * Returns the cp media type ID of this cp definition media.
	 *
	 * @return the cp media type ID of this cp definition media
	 */
	public long getCPMediaTypeId();

	/**
	 * Sets the cp media type ID of this cp definition media.
	 *
	 * @param CPMediaTypeId the cp media type ID of this cp definition media
	 */
	public void setCPMediaTypeId(long CPMediaTypeId);

	/**
	 * Returns the status of this cp definition media.
	 *
	 * @return the status of this cp definition media
	 */
	@Override
	public int getStatus();

	/**
	 * Returns the trash entry created when this cp definition media was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this cp definition media.
	 *
	 * @return the trash entry created when this cp definition media was moved to the Recycle Bin
	 */
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws PortalException;

	/**
	 * Returns the class primary key of the trash entry for this cp definition media.
	 *
	 * @return the class primary key of the trash entry for this cp definition media
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns the trash handler for this cp definition media.
	 *
	 * @return the trash handler for this cp definition media
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler();

	/**
	 * Returns <code>true</code> if this cp definition media is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this cp definition media is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if the parent of this cp definition media is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this cp definition media is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrashContainer();

	@Override
	public boolean isInTrashExplicitly();

	@Override
	public boolean isInTrashImplicitly();

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(CPDefinitionMedia cpDefinitionMedia);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CPDefinitionMedia> toCacheModel();

	@Override
	public CPDefinitionMedia toEscapedModel();

	@Override
	public CPDefinitionMedia toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}