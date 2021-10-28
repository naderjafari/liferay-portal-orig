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

import com.liferay.commerce.discount.model.CommerceDiscountAccountRel;
import com.liferay.commerce.discount.model.CommerceDiscountAccountRelModel;
import com.liferay.commerce.discount.model.CommerceDiscountAccountRelSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceDiscountAccountRel service. Represents a row in the &quot;CommerceDiscountAccountRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceDiscountAccountRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceDiscountAccountRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceDiscountAccountRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceDiscountAccountRelModelImpl
	extends BaseModelImpl<CommerceDiscountAccountRel>
	implements CommerceDiscountAccountRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce discount account rel model instance should use the <code>CommerceDiscountAccountRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceDiscountAccountRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"commerceDiscountAccountRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceAccountId", Types.BIGINT},
		{"commerceDiscountId", Types.BIGINT}, {"order_", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceDiscountAccountRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceDiscountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("order_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceDiscountAccountRel (uuid_ VARCHAR(75) null,commerceDiscountAccountRelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceAccountId LONG,commerceDiscountId LONG,order_ INTEGER,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceDiscountAccountRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceDiscountAccountRel.order ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceDiscountAccountRel.order_ ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEACCOUNTID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEDISCOUNTID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ORDER_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CommerceDiscountAccountRel toModel(
		CommerceDiscountAccountRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceDiscountAccountRel model = new CommerceDiscountAccountRelImpl();

		model.setUuid(soapModel.getUuid());
		model.setCommerceDiscountAccountRelId(
			soapModel.getCommerceDiscountAccountRelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceAccountId(soapModel.getCommerceAccountId());
		model.setCommerceDiscountId(soapModel.getCommerceDiscountId());
		model.setOrder(soapModel.getOrder());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<CommerceDiscountAccountRel> toModels(
		CommerceDiscountAccountRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceDiscountAccountRel> models =
			new ArrayList<CommerceDiscountAccountRel>(soapModels.length);

		for (CommerceDiscountAccountRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.discount.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.discount.model.CommerceDiscountAccountRel"));

	public CommerceDiscountAccountRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceDiscountAccountRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceDiscountAccountRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceDiscountAccountRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceDiscountAccountRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceDiscountAccountRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceDiscountAccountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceDiscountAccountRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDiscountAccountRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceDiscountAccountRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceDiscountAccountRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceDiscountAccountRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceDiscountAccountRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceDiscountAccountRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceDiscountAccountRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceDiscountAccountRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceDiscountAccountRel.class.getClassLoader(),
			CommerceDiscountAccountRel.class, ModelWrapper.class);

		try {
			Constructor<CommerceDiscountAccountRel> constructor =
				(Constructor<CommerceDiscountAccountRel>)
					proxyClass.getConstructor(InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map
		<String, Function<CommerceDiscountAccountRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceDiscountAccountRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceDiscountAccountRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceDiscountAccountRel, Object>>();
		Map<String, BiConsumer<CommerceDiscountAccountRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceDiscountAccountRel, ?>>();

		attributeGetterFunctions.put(
			"uuid", CommerceDiscountAccountRel::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<CommerceDiscountAccountRel, String>)
				CommerceDiscountAccountRel::setUuid);
		attributeGetterFunctions.put(
			"commerceDiscountAccountRelId",
			CommerceDiscountAccountRel::getCommerceDiscountAccountRelId);
		attributeSetterBiConsumers.put(
			"commerceDiscountAccountRelId",
			(BiConsumer<CommerceDiscountAccountRel, Long>)
				CommerceDiscountAccountRel::setCommerceDiscountAccountRelId);
		attributeGetterFunctions.put(
			"companyId", CommerceDiscountAccountRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceDiscountAccountRel, Long>)
				CommerceDiscountAccountRel::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceDiscountAccountRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceDiscountAccountRel, Long>)
				CommerceDiscountAccountRel::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceDiscountAccountRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceDiscountAccountRel, String>)
				CommerceDiscountAccountRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceDiscountAccountRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceDiscountAccountRel, Date>)
				CommerceDiscountAccountRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceDiscountAccountRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceDiscountAccountRel, Date>)
				CommerceDiscountAccountRel::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceAccountId",
			CommerceDiscountAccountRel::getCommerceAccountId);
		attributeSetterBiConsumers.put(
			"commerceAccountId",
			(BiConsumer<CommerceDiscountAccountRel, Long>)
				CommerceDiscountAccountRel::setCommerceAccountId);
		attributeGetterFunctions.put(
			"commerceDiscountId",
			CommerceDiscountAccountRel::getCommerceDiscountId);
		attributeSetterBiConsumers.put(
			"commerceDiscountId",
			(BiConsumer<CommerceDiscountAccountRel, Long>)
				CommerceDiscountAccountRel::setCommerceDiscountId);
		attributeGetterFunctions.put(
			"order", CommerceDiscountAccountRel::getOrder);
		attributeSetterBiConsumers.put(
			"order",
			(BiConsumer<CommerceDiscountAccountRel, Integer>)
				CommerceDiscountAccountRel::setOrder);
		attributeGetterFunctions.put(
			"lastPublishDate", CommerceDiscountAccountRel::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<CommerceDiscountAccountRel, Date>)
				CommerceDiscountAccountRel::setLastPublishDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getCommerceDiscountAccountRelId() {
		return _commerceDiscountAccountRelId;
	}

	@Override
	public void setCommerceDiscountAccountRelId(
		long commerceDiscountAccountRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceDiscountAccountRelId = commerceDiscountAccountRelId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCommerceAccountId() {
		return _commerceAccountId;
	}

	@Override
	public void setCommerceAccountId(long commerceAccountId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceAccountId = commerceAccountId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceAccountId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceAccountId"));
	}

	@JSON
	@Override
	public long getCommerceDiscountId() {
		return _commerceDiscountId;
	}

	@Override
	public void setCommerceDiscountId(long commerceDiscountId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceDiscountId = commerceDiscountId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceDiscountId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceDiscountId"));
	}

	@JSON
	@Override
	public int getOrder() {
		return _order;
	}

	@Override
	public void setOrder(int order) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_order = order;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				CommerceDiscountAccountRel.class.getName()));
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceDiscountAccountRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceDiscountAccountRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceDiscountAccountRel>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceDiscountAccountRelImpl commerceDiscountAccountRelImpl =
			new CommerceDiscountAccountRelImpl();

		commerceDiscountAccountRelImpl.setUuid(getUuid());
		commerceDiscountAccountRelImpl.setCommerceDiscountAccountRelId(
			getCommerceDiscountAccountRelId());
		commerceDiscountAccountRelImpl.setCompanyId(getCompanyId());
		commerceDiscountAccountRelImpl.setUserId(getUserId());
		commerceDiscountAccountRelImpl.setUserName(getUserName());
		commerceDiscountAccountRelImpl.setCreateDate(getCreateDate());
		commerceDiscountAccountRelImpl.setModifiedDate(getModifiedDate());
		commerceDiscountAccountRelImpl.setCommerceAccountId(
			getCommerceAccountId());
		commerceDiscountAccountRelImpl.setCommerceDiscountId(
			getCommerceDiscountId());
		commerceDiscountAccountRelImpl.setOrder(getOrder());
		commerceDiscountAccountRelImpl.setLastPublishDate(getLastPublishDate());

		commerceDiscountAccountRelImpl.resetOriginalValues();

		return commerceDiscountAccountRelImpl;
	}

	@Override
	public CommerceDiscountAccountRel cloneWithOriginalValues() {
		CommerceDiscountAccountRelImpl commerceDiscountAccountRelImpl =
			new CommerceDiscountAccountRelImpl();

		commerceDiscountAccountRelImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		commerceDiscountAccountRelImpl.setCommerceDiscountAccountRelId(
			this.<Long>getColumnOriginalValue("commerceDiscountAccountRelId"));
		commerceDiscountAccountRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceDiscountAccountRelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceDiscountAccountRelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceDiscountAccountRelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceDiscountAccountRelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceDiscountAccountRelImpl.setCommerceAccountId(
			this.<Long>getColumnOriginalValue("commerceAccountId"));
		commerceDiscountAccountRelImpl.setCommerceDiscountId(
			this.<Long>getColumnOriginalValue("commerceDiscountId"));
		commerceDiscountAccountRelImpl.setOrder(
			this.<Integer>getColumnOriginalValue("order_"));
		commerceDiscountAccountRelImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return commerceDiscountAccountRelImpl;
	}

	@Override
	public int compareTo(
		CommerceDiscountAccountRel commerceDiscountAccountRel) {

		int value = 0;

		if (getOrder() < commerceDiscountAccountRel.getOrder()) {
			value = -1;
		}
		else if (getOrder() > commerceDiscountAccountRel.getOrder()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceDiscountAccountRel)) {
			return false;
		}

		CommerceDiscountAccountRel commerceDiscountAccountRel =
			(CommerceDiscountAccountRel)object;

		long primaryKey = commerceDiscountAccountRel.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceDiscountAccountRel> toCacheModel() {
		CommerceDiscountAccountRelCacheModel
			commerceDiscountAccountRelCacheModel =
				new CommerceDiscountAccountRelCacheModel();

		commerceDiscountAccountRelCacheModel.uuid = getUuid();

		String uuid = commerceDiscountAccountRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceDiscountAccountRelCacheModel.uuid = null;
		}

		commerceDiscountAccountRelCacheModel.commerceDiscountAccountRelId =
			getCommerceDiscountAccountRelId();

		commerceDiscountAccountRelCacheModel.companyId = getCompanyId();

		commerceDiscountAccountRelCacheModel.userId = getUserId();

		commerceDiscountAccountRelCacheModel.userName = getUserName();

		String userName = commerceDiscountAccountRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceDiscountAccountRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceDiscountAccountRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceDiscountAccountRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceDiscountAccountRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceDiscountAccountRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceDiscountAccountRelCacheModel.commerceAccountId =
			getCommerceAccountId();

		commerceDiscountAccountRelCacheModel.commerceDiscountId =
			getCommerceDiscountId();

		commerceDiscountAccountRelCacheModel.order = getOrder();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			commerceDiscountAccountRelCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			commerceDiscountAccountRelCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return commerceDiscountAccountRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceDiscountAccountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceDiscountAccountRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDiscountAccountRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceDiscountAccountRel)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CommerceDiscountAccountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceDiscountAccountRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDiscountAccountRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceDiscountAccountRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceDiscountAccountRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private String _uuid;
	private long _commerceDiscountAccountRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceAccountId;
	private long _commerceDiscountId;
	private int _order;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceDiscountAccountRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceDiscountAccountRel)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"commerceDiscountAccountRelId", _commerceDiscountAccountRelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commerceAccountId", _commerceAccountId);
		_columnOriginalValues.put("commerceDiscountId", _commerceDiscountId);
		_columnOriginalValues.put("order_", _order);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("order_", "order");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("commerceDiscountAccountRelId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("commerceAccountId", 128L);

		columnBitmasks.put("commerceDiscountId", 256L);

		columnBitmasks.put("order_", 512L);

		columnBitmasks.put("lastPublishDate", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceDiscountAccountRel _escapedModel;

}