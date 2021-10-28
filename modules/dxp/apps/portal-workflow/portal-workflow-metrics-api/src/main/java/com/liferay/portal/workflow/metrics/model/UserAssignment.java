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

package com.liferay.portal.workflow.metrics.model;

/**
 * @author Feliphe Marinho
 */
public class UserAssignment implements Assignment {

	public UserAssignment(long assignmentId, String name) {
		_assignmentId = assignmentId;
		_name = name;
	}

	@Override
	public long getAssignmentId() {
		return _assignmentId;
	}

	public String getName() {
		return _name;
	}

	public void setAssignmentId(long assignmentId) {
		_assignmentId = assignmentId;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _assignmentId;
	private String _name;

}