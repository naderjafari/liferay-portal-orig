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

package com.liferay.search.experiences.web.internal.portal.profile;

import com.liferay.portal.profile.BaseDSModulePortalProfile;
import com.liferay.portal.profile.PortalProfile;
import com.liferay.portal.util.PropsValues;
import com.liferay.search.experiences.web.internal.power.tools.portlet.SXPPowerToolsPortlet;
import com.liferay.search.experiences.web.internal.power.tools.portlet.action.ImportGooglePlacesMVCActionCommand;
import com.liferay.search.experiences.web.internal.power.tools.portlet.action.ImportWikipediaMVCActionCommand;

import java.util.Arrays;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(immediate = true, service = PortalProfile.class)
public class ModulePortalProfile extends BaseDSModulePortalProfile {

	@Activate
	protected void activate(ComponentContext componentContext) {
		if (!PropsValues.SEARCH_EXPERIENCES_POWER_TOOLS_ENABLED) {
			return;
		}

		init(
			componentContext,
			Arrays.asList(
				PortalProfile.PORTAL_PROFILE_NAME_CE,
				PortalProfile.PORTAL_PROFILE_NAME_DXP),
			ImportGooglePlacesMVCActionCommand.class.getName(),
			ImportWikipediaMVCActionCommand.class.getName(),
			SXPPowerToolsPortlet.class.getName());
	}

}