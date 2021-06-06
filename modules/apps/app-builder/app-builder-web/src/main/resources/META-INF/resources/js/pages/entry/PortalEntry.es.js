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

import {getItem} from 'data-engine-js-components-web/js/utils/client.es';
import {TranslationManager} from 'data-engine-taglib';
import React, {useContext, useEffect, useState} from 'react';
import {createPortal} from 'react-dom';

import {AppContext} from '../../AppContext.es';
import usePermissions from '../../hooks/usePermissions.es';
import {getLocalizedUserPreferenceValue} from '../../utils/lang.es';
import PersonalMenu from './PersonalMenuEntry.es';

const STORAGE_KEY = '@app-builder/standalone/language';

const portalKeys = {
	APP_STANDALONE_NAME: '#appStandaloneName',
	PERSONAL_MENU: ' #app-personal-menu',
	TRANSLATION_MANAGER: '#appTranslationManager',
};

const setStorageLanguageId = (appId, value) => {
	localStorage.setItem(`${STORAGE_KEY}/${appId}`, value);
};

export const getStorageLanguageId = (appId) => {
	return (
		localStorage.getItem(`${STORAGE_KEY}/${appId}`) ||
		themeDisplay.getLanguageId()
	);
};

const TranslationManagerPortal = ({
	appId,
	dataDefinitionId,
	setUserLanguageId,
	showAppName,
	userLanguageId,
}) => {
	const {view: viewPermission} = usePermissions();
	const [standaloneNameContainer, setStandaloneNameContainer] = useState();
	const [
		translationManagerContainer,
		setTranslationManagerContainer,
	] = useState();

	const [{app, dataDefinition}, setState] = useState({
		app: {
			name: {},
		},
		dataDefinition: {
			availableLanguageIds: [],
			defaultLanguageId: '',
		},
	});

	const defaultLanguageId = dataDefinition.defaultLanguageId;

	const onEditingLanguageIdChange = (languageId) => {
		setStorageLanguageId(appId, languageId);
		setUserLanguageId(languageId);
	};

	useEffect(() => {
		if (standaloneNameContainer) {
			return;
		}

		setStandaloneNameContainer(
			document.querySelector(portalKeys.APP_STANDALONE_NAME)
		);
	}, [standaloneNameContainer]);

	useEffect(() => {
		if (translationManagerContainer) {
			return;
		}

		setTranslationManagerContainer(
			document.querySelector(portalKeys.TRANSLATION_MANAGER)
		);
	}, [translationManagerContainer]);

	useEffect(() => {
		if (viewPermission && showAppName) {
			getItem(`/o/app-builder/v1.0/apps/${appId}`).then((app) =>
				setState((prevState) => ({
					...prevState,
					app,
				}))
			);
		}
	}, [appId, showAppName, viewPermission]);

	useEffect(() => {
		if (viewPermission && dataDefinitionId) {
			getItem(
				`/o/data-engine/v2.0/data-definitions/${dataDefinitionId}`
			).then((dataDefinition) =>
				setState((prevState) => ({
					...prevState,
					dataDefinition,
				}))
			);
		}
	}, [dataDefinitionId, viewPermission]);

	const availableLanguageIds = dataDefinition.availableLanguageIds.reduce(
		(acc, cur) => {
			acc[cur] = cur;

			return acc;
		},
		{}
	);

	const getEditingLanguageId = () => {
		if (availableLanguageIds[userLanguageId]) {
			return userLanguageId;
		}

		return defaultLanguageId;
	};

	if (!viewPermission) {
		return <></>;
	}

	return (
		<div>
			{showAppName &&
				standaloneNameContainer &&
				createPortal(
					getLocalizedUserPreferenceValue(
						app.name,
						userLanguageId,
						defaultLanguageId
					),
					standaloneNameContainer
				)}

			{translationManagerContainer &&
				createPortal(
					<TranslationManager
						availableLanguageIds={availableLanguageIds}
						editingLanguageId={getEditingLanguageId()}
						onEditingLanguageIdChange={onEditingLanguageIdChange}
						showUserView
					/>,
					translationManagerContainer
				)}
		</div>
	);
};

export default (props) => {
	const {appId, portraitURL} = useContext(AppContext);
	const [appPersonalContainer, setAppPersonalContainer] = useState(null);

	useEffect(() => {
		if (appPersonalContainer !== null) {
			return;
		}
		setAppPersonalContainer(
			document.querySelector(portalKeys.PERSONAL_MENU)
		);
	}, [appPersonalContainer]);

	return (
		<>
			{appPersonalContainer &&
				themeDisplay.isSignedIn() &&
				createPortal(
					<PersonalMenu
						items={[
							{
								label: themeDisplay.getUserName(),
								type: 'group',
							},
							{
								type: 'divider',
							},
							{
								label: Liferay.Language.get('sign-out'),
								onClick: () => {
									window.location.href = `${window.location.origin}/c/portal/logout`;
								},
							},
						]}
						portraitURL={portraitURL}
					/>,
					appPersonalContainer
				)}
			<TranslationManagerPortal appId={appId} {...props} />
		</>
	);
};
