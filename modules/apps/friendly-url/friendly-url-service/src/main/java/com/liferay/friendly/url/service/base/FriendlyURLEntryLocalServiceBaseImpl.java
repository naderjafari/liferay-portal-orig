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

package com.liferay.friendly.url.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalization;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalService;
import com.liferay.friendly.url.service.persistence.FriendlyURLEntryLocalizationPersistence;
import com.liferay.friendly.url.service.persistence.FriendlyURLEntryMappingPersistence;
import com.liferay.friendly.url.service.persistence.FriendlyURLEntryPersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the friendly url entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.friendly.url.service.impl.FriendlyURLEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.friendly.url.service.impl.FriendlyURLEntryLocalServiceImpl
 * @generated
 */
public abstract class FriendlyURLEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, FriendlyURLEntryLocalService,
			   IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>FriendlyURLEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.friendly.url.service.FriendlyURLEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the friendly url entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param friendlyURLEntry the friendly url entry
	 * @return the friendly url entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FriendlyURLEntry addFriendlyURLEntry(
		FriendlyURLEntry friendlyURLEntry) {

		friendlyURLEntry.setNew(true);

		return friendlyURLEntryPersistence.update(friendlyURLEntry);
	}

	/**
	 * Creates a new friendly url entry with the primary key. Does not add the friendly url entry to the database.
	 *
	 * @param friendlyURLEntryId the primary key for the new friendly url entry
	 * @return the new friendly url entry
	 */
	@Override
	@Transactional(enabled = false)
	public FriendlyURLEntry createFriendlyURLEntry(long friendlyURLEntryId) {
		return friendlyURLEntryPersistence.create(friendlyURLEntryId);
	}

	/**
	 * Deletes the friendly url entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param friendlyURLEntryId the primary key of the friendly url entry
	 * @return the friendly url entry that was removed
	 * @throws PortalException if a friendly url entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FriendlyURLEntry deleteFriendlyURLEntry(long friendlyURLEntryId)
		throws PortalException {

		return friendlyURLEntryPersistence.remove(friendlyURLEntryId);
	}

	/**
	 * Deletes the friendly url entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param friendlyURLEntry the friendly url entry
	 * @return the friendly url entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FriendlyURLEntry deleteFriendlyURLEntry(
		FriendlyURLEntry friendlyURLEntry) {

		return friendlyURLEntryPersistence.remove(friendlyURLEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			FriendlyURLEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return friendlyURLEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.friendly.url.model.impl.FriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return friendlyURLEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.friendly.url.model.impl.FriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return friendlyURLEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return friendlyURLEntryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return friendlyURLEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public FriendlyURLEntry fetchFriendlyURLEntry(long friendlyURLEntryId) {
		return friendlyURLEntryPersistence.fetchByPrimaryKey(
			friendlyURLEntryId);
	}

	/**
	 * Returns the friendly url entry matching the UUID and group.
	 *
	 * @param uuid the friendly url entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching friendly url entry, or <code>null</code> if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry fetchFriendlyURLEntryByUuidAndGroupId(
		String uuid, long groupId) {

		return friendlyURLEntryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the friendly url entry with the primary key.
	 *
	 * @param friendlyURLEntryId the primary key of the friendly url entry
	 * @return the friendly url entry
	 * @throws PortalException if a friendly url entry with the primary key could not be found
	 */
	@Override
	public FriendlyURLEntry getFriendlyURLEntry(long friendlyURLEntryId)
		throws PortalException {

		return friendlyURLEntryPersistence.findByPrimaryKey(friendlyURLEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			friendlyURLEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FriendlyURLEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("friendlyURLEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			friendlyURLEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(FriendlyURLEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"friendlyURLEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			friendlyURLEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FriendlyURLEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("friendlyURLEntryId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");

					StagedModelType stagedModelType =
						exportActionableDynamicQuery.getStagedModelType();

					long referrerClassNameId =
						stagedModelType.getReferrerClassNameId();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					if ((referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ALL) &&
						(referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ANY)) {

						dynamicQuery.add(
							classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
					else if (referrerClassNameId ==
								StagedModelType.REFERRER_CLASS_NAME_ID_ANY) {

						dynamicQuery.add(classNameIdProperty.isNotNull());
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<FriendlyURLEntry>() {

				@Override
				public void performAction(FriendlyURLEntry friendlyURLEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, friendlyURLEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(FriendlyURLEntry.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return friendlyURLEntryLocalService.deleteFriendlyURLEntry(
			(FriendlyURLEntry)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return friendlyURLEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the friendly url entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the friendly url entries
	 * @param companyId the primary key of the company
	 * @return the matching friendly url entries, or an empty list if no matches were found
	 */
	@Override
	public List<FriendlyURLEntry> getFriendlyURLEntriesByUuidAndCompanyId(
		String uuid, long companyId) {

		return friendlyURLEntryPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of friendly url entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the friendly url entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching friendly url entries, or an empty list if no matches were found
	 */
	@Override
	public List<FriendlyURLEntry> getFriendlyURLEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FriendlyURLEntry> orderByComparator) {

		return friendlyURLEntryPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the friendly url entry matching the UUID and group.
	 *
	 * @param uuid the friendly url entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching friendly url entry
	 * @throws PortalException if a matching friendly url entry could not be found
	 */
	@Override
	public FriendlyURLEntry getFriendlyURLEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return friendlyURLEntryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the friendly url entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.friendly.url.model.impl.FriendlyURLEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of friendly url entries
	 * @param end the upper bound of the range of friendly url entries (not inclusive)
	 * @return the range of friendly url entries
	 */
	@Override
	public List<FriendlyURLEntry> getFriendlyURLEntries(int start, int end) {
		return friendlyURLEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of friendly url entries.
	 *
	 * @return the number of friendly url entries
	 */
	@Override
	public int getFriendlyURLEntriesCount() {
		return friendlyURLEntryPersistence.countAll();
	}

	/**
	 * Updates the friendly url entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param friendlyURLEntry the friendly url entry
	 * @return the friendly url entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FriendlyURLEntry updateFriendlyURLEntry(
		FriendlyURLEntry friendlyURLEntry) {

		return friendlyURLEntryPersistence.update(friendlyURLEntry);
	}

	@Override
	public FriendlyURLEntryLocalization fetchFriendlyURLEntryLocalization(
		long friendlyURLEntryId, String languageId) {

		return friendlyURLEntryLocalizationPersistence.
			fetchByFriendlyURLEntryId_LanguageId(
				friendlyURLEntryId, languageId);
	}

	@Override
	public FriendlyURLEntryLocalization getFriendlyURLEntryLocalization(
			long friendlyURLEntryId, String languageId)
		throws PortalException {

		return friendlyURLEntryLocalizationPersistence.
			findByFriendlyURLEntryId_LanguageId(friendlyURLEntryId, languageId);
	}

	@Override
	public List<FriendlyURLEntryLocalization> getFriendlyURLEntryLocalizations(
		long friendlyURLEntryId) {

		return friendlyURLEntryLocalizationPersistence.findByFriendlyURLEntryId(
			friendlyURLEntryId);
	}

	@Override
	public FriendlyURLEntryLocalization updateFriendlyURLEntryLocalization(
			FriendlyURLEntry friendlyURLEntry, String languageId,
			String urlTitle)
		throws PortalException {

		friendlyURLEntry = friendlyURLEntryPersistence.findByPrimaryKey(
			friendlyURLEntry.getPrimaryKey());

		FriendlyURLEntryLocalization friendlyURLEntryLocalization =
			friendlyURLEntryLocalizationPersistence.
				fetchByFriendlyURLEntryId_LanguageId(
					friendlyURLEntry.getFriendlyURLEntryId(), languageId);

		return _updateFriendlyURLEntryLocalization(
			friendlyURLEntry, friendlyURLEntryLocalization, languageId,
			urlTitle);
	}

	@Override
	public List<FriendlyURLEntryLocalization>
			updateFriendlyURLEntryLocalizations(
				FriendlyURLEntry friendlyURLEntry,
				Map<String, String> urlTitleMap)
		throws PortalException {

		friendlyURLEntry = friendlyURLEntryPersistence.findByPrimaryKey(
			friendlyURLEntry.getPrimaryKey());

		Map<String, String[]> localizedValuesMap =
			new HashMap<String, String[]>();

		for (Map.Entry<String, String> entry : urlTitleMap.entrySet()) {
			String languageId = entry.getKey();

			String[] localizedValues = localizedValuesMap.get(languageId);

			if (localizedValues == null) {
				localizedValues = new String[1];

				localizedValuesMap.put(languageId, localizedValues);
			}

			localizedValues[0] = entry.getValue();
		}

		List<FriendlyURLEntryLocalization> friendlyURLEntryLocalizations =
			new ArrayList<FriendlyURLEntryLocalization>(
				localizedValuesMap.size());

		for (FriendlyURLEntryLocalization friendlyURLEntryLocalization :
				friendlyURLEntryLocalizationPersistence.
					findByFriendlyURLEntryId(
						friendlyURLEntry.getFriendlyURLEntryId())) {

			String[] localizedValues = localizedValuesMap.remove(
				friendlyURLEntryLocalization.getLanguageId());

			if (localizedValues == null) {
				friendlyURLEntryLocalizationPersistence.remove(
					friendlyURLEntryLocalization);
			}
			else {
				friendlyURLEntryLocalization.setGroupId(
					friendlyURLEntry.getGroupId());
				friendlyURLEntryLocalization.setCompanyId(
					friendlyURLEntry.getCompanyId());
				friendlyURLEntryLocalization.setClassNameId(
					friendlyURLEntry.getClassNameId());
				friendlyURLEntryLocalization.setClassPK(
					friendlyURLEntry.getClassPK());

				friendlyURLEntryLocalization.setUrlTitle(localizedValues[0]);

				friendlyURLEntryLocalizations.add(
					friendlyURLEntryLocalizationPersistence.update(
						friendlyURLEntryLocalization));
			}
		}

		long batchCounter =
			counterLocalService.increment(
				FriendlyURLEntryLocalization.class.getName(),
				localizedValuesMap.size()) - localizedValuesMap.size();

		for (Map.Entry<String, String[]> entry :
				localizedValuesMap.entrySet()) {

			String languageId = entry.getKey();
			String[] localizedValues = entry.getValue();

			FriendlyURLEntryLocalization friendlyURLEntryLocalization =
				friendlyURLEntryLocalizationPersistence.create(++batchCounter);

			friendlyURLEntryLocalization.setFriendlyURLEntryId(
				friendlyURLEntry.getFriendlyURLEntryId());
			friendlyURLEntryLocalization.setGroupId(
				friendlyURLEntry.getGroupId());
			friendlyURLEntryLocalization.setCompanyId(
				friendlyURLEntry.getCompanyId());
			friendlyURLEntryLocalization.setClassNameId(
				friendlyURLEntry.getClassNameId());
			friendlyURLEntryLocalization.setClassPK(
				friendlyURLEntry.getClassPK());

			friendlyURLEntryLocalization.setLanguageId(languageId);

			friendlyURLEntryLocalization.setUrlTitle(localizedValues[0]);

			friendlyURLEntryLocalizations.add(
				friendlyURLEntryLocalizationPersistence.update(
					friendlyURLEntryLocalization));
		}

		return friendlyURLEntryLocalizations;
	}

	private FriendlyURLEntryLocalization _updateFriendlyURLEntryLocalization(
			FriendlyURLEntry friendlyURLEntry,
			FriendlyURLEntryLocalization friendlyURLEntryLocalization,
			String languageId, String urlTitle)
		throws PortalException {

		if (friendlyURLEntryLocalization == null) {
			long friendlyURLEntryLocalizationId = counterLocalService.increment(
				FriendlyURLEntryLocalization.class.getName());

			friendlyURLEntryLocalization =
				friendlyURLEntryLocalizationPersistence.create(
					friendlyURLEntryLocalizationId);

			friendlyURLEntryLocalization.setFriendlyURLEntryId(
				friendlyURLEntry.getFriendlyURLEntryId());
			friendlyURLEntryLocalization.setLanguageId(languageId);
		}

		friendlyURLEntryLocalization.setGroupId(friendlyURLEntry.getGroupId());
		friendlyURLEntryLocalization.setCompanyId(
			friendlyURLEntry.getCompanyId());
		friendlyURLEntryLocalization.setClassNameId(
			friendlyURLEntry.getClassNameId());
		friendlyURLEntryLocalization.setClassPK(friendlyURLEntry.getClassPK());

		friendlyURLEntryLocalization.setUrlTitle(urlTitle);

		return friendlyURLEntryLocalizationPersistence.update(
			friendlyURLEntryLocalization);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			FriendlyURLEntryLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		friendlyURLEntryLocalService = (FriendlyURLEntryLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FriendlyURLEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return FriendlyURLEntry.class;
	}

	protected String getModelClassName() {
		return FriendlyURLEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = friendlyURLEntryPersistence.getDataSource();

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

	protected FriendlyURLEntryLocalService friendlyURLEntryLocalService;

	@Reference
	protected FriendlyURLEntryPersistence friendlyURLEntryPersistence;

	@Reference
	protected FriendlyURLEntryLocalizationPersistence
		friendlyURLEntryLocalizationPersistence;

	@Reference
	protected FriendlyURLEntryMappingPersistence
		friendlyURLEntryMappingPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}