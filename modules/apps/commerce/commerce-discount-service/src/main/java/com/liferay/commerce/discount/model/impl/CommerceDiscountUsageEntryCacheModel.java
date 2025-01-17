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

package com.liferay.commerce.discount.model.impl;

import com.liferay.commerce.discount.model.CommerceDiscountUsageEntry;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceDiscountUsageEntry in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class CommerceDiscountUsageEntryCacheModel
	implements CacheModel<CommerceDiscountUsageEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceDiscountUsageEntryCacheModel)) {
			return false;
		}

		CommerceDiscountUsageEntryCacheModel
			commerceDiscountUsageEntryCacheModel =
				(CommerceDiscountUsageEntryCacheModel)object;

		if (commerceDiscountUsageEntryId ==
				commerceDiscountUsageEntryCacheModel.
					commerceDiscountUsageEntryId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceDiscountUsageEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{commerceDiscountUsageEntryId=");
		sb.append(commerceDiscountUsageEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", commerceAccountId=");
		sb.append(commerceAccountId);
		sb.append(", commerceOrderId=");
		sb.append(commerceOrderId);
		sb.append(", commerceDiscountId=");
		sb.append(commerceDiscountId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceDiscountUsageEntry toEntityModel() {
		CommerceDiscountUsageEntryImpl commerceDiscountUsageEntryImpl =
			new CommerceDiscountUsageEntryImpl();

		commerceDiscountUsageEntryImpl.setCommerceDiscountUsageEntryId(
			commerceDiscountUsageEntryId);
		commerceDiscountUsageEntryImpl.setCompanyId(companyId);
		commerceDiscountUsageEntryImpl.setUserId(userId);

		if (userName == null) {
			commerceDiscountUsageEntryImpl.setUserName("");
		}
		else {
			commerceDiscountUsageEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceDiscountUsageEntryImpl.setCreateDate(null);
		}
		else {
			commerceDiscountUsageEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceDiscountUsageEntryImpl.setModifiedDate(null);
		}
		else {
			commerceDiscountUsageEntryImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		commerceDiscountUsageEntryImpl.setCommerceAccountId(commerceAccountId);
		commerceDiscountUsageEntryImpl.setCommerceOrderId(commerceOrderId);
		commerceDiscountUsageEntryImpl.setCommerceDiscountId(
			commerceDiscountId);

		commerceDiscountUsageEntryImpl.resetOriginalValues();

		return commerceDiscountUsageEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceDiscountUsageEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceAccountId = objectInput.readLong();

		commerceOrderId = objectInput.readLong();

		commerceDiscountId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(commerceDiscountUsageEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(commerceAccountId);

		objectOutput.writeLong(commerceOrderId);

		objectOutput.writeLong(commerceDiscountId);
	}

	public long commerceDiscountUsageEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceAccountId;
	public long commerceOrderId;
	public long commerceDiscountId;

}