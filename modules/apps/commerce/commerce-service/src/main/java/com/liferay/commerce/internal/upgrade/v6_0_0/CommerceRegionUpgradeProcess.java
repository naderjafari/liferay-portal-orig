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

package com.liferay.commerce.internal.upgrade.v6_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.RegionLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Date;

/**
 * @author Pei-Jung Lan
 */
public class CommerceRegionUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (Statement selectStatement = connection.createStatement()) {
			ResultSet resultSet = selectStatement.executeQuery(
				"select * from CommerceRegion order by commerceRegionId asc");

			while (resultSet.next()) {
				boolean active = resultSet.getBoolean("active_");
				String code = resultSet.getString("code_");
				long commerceCountryId = resultSet.getLong("commerceCountryId");
				long commerceRegionId = resultSet.getLong("commerceRegionId");
				Date lastPublishDate = resultSet.getTimestamp(
					"lastPublishDate");
				String name = resultSet.getString("name");
				Double priority = resultSet.getDouble("priority");

				Region region = RegionLocalServiceUtil.fetchRegion(
					commerceCountryId, code);

				if (region != null) {
					region = _updateRegion(
						region, active, name, priority, code, lastPublishDate);
				}
				else {
					region = _addRegion(
						commerceRegionId, resultSet.getLong("companyId"),
						resultSet.getLong("userId"),
						resultSet.getString("userName"),
						resultSet.getTimestamp("createDate"),
						resultSet.getTimestamp("modifiedDate"),
						commerceCountryId, active, name, priority, code,
						lastPublishDate);
				}

				if (region.getRegionId() != commerceRegionId) {
					for (String tableName : _TABLE_NAMES) {
						_updateRegionId(
							tableName, region.getRegionId(), commerceRegionId);
					}
				}
			}

			runSQL("drop table CommerceRegion");
		}
	}

	private Region _addRegion(
		long regionId, long companyId, long userId, String userName,
		Date createDate, Date modifiedDate, long countryId, boolean active,
		String name, Double position, String regionCode, Date lastPublishDate) {

		if (RegionLocalServiceUtil.fetchRegion(regionId) != null) {
			regionId = increment();
		}

		Region region = RegionLocalServiceUtil.createRegion(regionId);

		region.setCompanyId(companyId);
		region.setUserId(userId);
		region.setUserName(userName);
		region.setCreateDate(createDate);
		region.setModifiedDate(modifiedDate);
		region.setCountryId(countryId);
		region.setActive(active);
		region.setName(name);
		region.setPosition(position);
		region.setRegionCode(regionCode);
		region.setLastPublishDate(lastPublishDate);

		return RegionLocalServiceUtil.addRegion(region);
	}

	private Region _updateRegion(
		Region region, boolean active, String name, Double position,
		String regionCode, Date lastPublishDate) {

		region.setActive(active);
		region.setName(name);
		region.setPosition(position);
		region.setRegionCode(regionCode);
		region.setLastPublishDate(lastPublishDate);

		return RegionLocalServiceUtil.updateRegion(region);
	}

	private void _updateRegionId(
			String tableName, long newRegionId, long oldRegionId)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set regionId = ");
		sb.append(newRegionId);
		sb.append(" where regionId = ");
		sb.append(oldRegionId);

		runSQL(sb.toString());
	}

	private static final String[] _TABLE_NAMES = {
		"CommerceAddress", "CommerceTaxFixedRateAddressRel",
		"CShippingFixedOptionRel"
	};

}