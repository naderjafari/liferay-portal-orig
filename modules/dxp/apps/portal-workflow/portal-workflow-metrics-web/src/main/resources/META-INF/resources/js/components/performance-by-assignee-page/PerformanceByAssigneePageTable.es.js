/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayTable from '@clayui/table';
import React from 'react';

import ListHeadItem from '../../shared/components/list/ListHeadItem.es';
import UserAvatar from '../../shared/components/user-avatar/UserAvatar.es';
import {formatDuration} from '../../shared/util/duration.es';

function Item({assignee: {image, name}, durationTaskAvg, id, taskCount}) {
	const formattedDuration = formatDuration(durationTaskAvg);

	return (
		<ClayTable.Row>
			<ClayTable.Cell>
				<UserAvatar className="mr-3" image={image} />

				<span>{name || id}</span>
			</ClayTable.Cell>

			<ClayTable.Cell className="text-right">
				<span>{taskCount}</span>
			</ClayTable.Cell>

			<ClayTable.Cell className="text-right">
				<span>{formattedDuration}</span>
			</ClayTable.Cell>
		</ClayTable.Row>
	);
}

function Table({items}) {
	return (
		<ClayTable>
			<ClayTable.Head>
				<ClayTable.Row>
					<ClayTable.Cell headingCell style={{width: '60%'}}>
						{Liferay.Language.get('assignee-name')}
					</ClayTable.Cell>

					<ClayTable.Cell
						className="text-right"
						headingCell
						style={{width: '20%'}}
					>
						<ListHeadItem
							name="taskCount"
							title={Liferay.Language.get('completed-tasks')}
						/>
					</ClayTable.Cell>

					<ClayTable.Cell
						className="text-right"
						headingCell
						style={{width: '20%'}}
					>
						<ListHeadItem
							name="durationTaskAvg"
							title={Liferay.Language.get(
								'average-completion-time'
							)}
						/>
					</ClayTable.Cell>
				</ClayTable.Row>
			</ClayTable.Head>

			<ClayTable.Body>
				{items.map((item, index) => (
					<Table.Item {...item} key={index} />
				))}
			</ClayTable.Body>
		</ClayTable>
	);
}

Table.Item = Item;

export default Table;
