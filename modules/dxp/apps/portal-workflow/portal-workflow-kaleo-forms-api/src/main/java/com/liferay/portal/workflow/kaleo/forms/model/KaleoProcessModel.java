/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.kaleo.forms.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the KaleoProcess service. Represents a row in the &quot;KaleoProcess&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessImpl</code>.
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcess
 * @generated
 */
@ProviderType
public interface KaleoProcessModel
	extends BaseModel<KaleoProcess>, GroupedModel, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kaleo process model instance should use the {@link KaleoProcess} interface instead.
	 */

	/**
	 * Returns the primary key of this kaleo process.
	 *
	 * @return the primary key of this kaleo process
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kaleo process.
	 *
	 * @param primaryKey the primary key of this kaleo process
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this kaleo process.
	 *
	 * @return the uuid of this kaleo process
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this kaleo process.
	 *
	 * @param uuid the uuid of this kaleo process
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the kaleo process ID of this kaleo process.
	 *
	 * @return the kaleo process ID of this kaleo process
	 */
	public long getKaleoProcessId();

	/**
	 * Sets the kaleo process ID of this kaleo process.
	 *
	 * @param kaleoProcessId the kaleo process ID of this kaleo process
	 */
	public void setKaleoProcessId(long kaleoProcessId);

	/**
	 * Returns the group ID of this kaleo process.
	 *
	 * @return the group ID of this kaleo process
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kaleo process.
	 *
	 * @param groupId the group ID of this kaleo process
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kaleo process.
	 *
	 * @return the company ID of this kaleo process
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kaleo process.
	 *
	 * @param companyId the company ID of this kaleo process
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kaleo process.
	 *
	 * @return the user ID of this kaleo process
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kaleo process.
	 *
	 * @param userId the user ID of this kaleo process
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kaleo process.
	 *
	 * @return the user uuid of this kaleo process
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kaleo process.
	 *
	 * @param userUuid the user uuid of this kaleo process
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kaleo process.
	 *
	 * @return the user name of this kaleo process
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kaleo process.
	 *
	 * @param userName the user name of this kaleo process
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kaleo process.
	 *
	 * @return the create date of this kaleo process
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kaleo process.
	 *
	 * @param createDate the create date of this kaleo process
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kaleo process.
	 *
	 * @return the modified date of this kaleo process
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kaleo process.
	 *
	 * @param modifiedDate the modified date of this kaleo process
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the ddl record set ID of this kaleo process.
	 *
	 * @return the ddl record set ID of this kaleo process
	 */
	public long getDDLRecordSetId();

	/**
	 * Sets the ddl record set ID of this kaleo process.
	 *
	 * @param DDLRecordSetId the ddl record set ID of this kaleo process
	 */
	public void setDDLRecordSetId(long DDLRecordSetId);

	/**
	 * Returns the ddm template ID of this kaleo process.
	 *
	 * @return the ddm template ID of this kaleo process
	 */
	public long getDDMTemplateId();

	/**
	 * Sets the ddm template ID of this kaleo process.
	 *
	 * @param DDMTemplateId the ddm template ID of this kaleo process
	 */
	public void setDDMTemplateId(long DDMTemplateId);

	/**
	 * Returns the workflow definition name of this kaleo process.
	 *
	 * @return the workflow definition name of this kaleo process
	 */
	@AutoEscape
	public String getWorkflowDefinitionName();

	/**
	 * Sets the workflow definition name of this kaleo process.
	 *
	 * @param workflowDefinitionName the workflow definition name of this kaleo process
	 */
	public void setWorkflowDefinitionName(String workflowDefinitionName);

	/**
	 * Returns the workflow definition version of this kaleo process.
	 *
	 * @return the workflow definition version of this kaleo process
	 */
	public int getWorkflowDefinitionVersion();

	/**
	 * Sets the workflow definition version of this kaleo process.
	 *
	 * @param workflowDefinitionVersion the workflow definition version of this kaleo process
	 */
	public void setWorkflowDefinitionVersion(int workflowDefinitionVersion);

	@Override
	public KaleoProcess cloneWithOriginalValues();

}