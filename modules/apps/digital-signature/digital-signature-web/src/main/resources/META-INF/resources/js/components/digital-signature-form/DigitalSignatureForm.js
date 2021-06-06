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

import ClayButton from '@clayui/button';
import ClayCard from '@clayui/card';
import ClayForm from '@clayui/form';
import ClayLayout from '@clayui/layout';
import {useFormik} from 'formik';
import {
	createResourceURL,
	fetch,
	objectToFormData,
	openToast,
} from 'frontend-js-web';
import React, {useContext} from 'react';

import {AppContext} from '../../AppContext';
import {isEmail, maxLength, required, validate} from '../form/FormValidation';
import DigitalSignatureFormBase from './DigitalSignatureFormBase';

const defaultRecipient = {
	email: '',
	fullName: '',
};

const DigitalSignatureForm = ({
	fileEntryId,
	history,
	showDocumentLibraryInput,
}) => {
	const {baseResourceURL, portletNamespace} = useContext(AppContext);
	const urlParams = new URLSearchParams(window.location.href);
	const backURL = urlParams.get(`${portletNamespace}backURL`);

	const onGoBack = () => {
		if (history) {
			return history.goBack();
		}

		return Liferay.Util.navigate(backURL);
	};

	const onSubmit = async (values) => {
		const formDataValues = {
			[`${portletNamespace}emailMessage`]: values.emailMessage,
			[`${portletNamespace}emailSubject`]: values.emailSubject,
			[`${portletNamespace}envelopeName`]: values.envelopeName,
			[`${portletNamespace}fileEntryId`]: values.fileEntryId,
			[`${portletNamespace}recipients`]: JSON.stringify(
				values.recipients
			),
		};

		try {
			const response = await fetch(
				createResourceURL(baseResourceURL, {
					p_p_resource_id: '/digital_signature/add_ds_envelope',
				}),
				{
					body: objectToFormData(formDataValues),
					method: 'POST',
				}
			);

			const {dsEnvelopeId} = await response.json();

			if (dsEnvelopeId) {
				openToast({
					message: Liferay.Language.get(
						'your-envelope-was-created-successfully'
					),
					title: Liferay.Language.get('success'),
					type: 'success',
				});
			}
		}
		catch (error) {
			openToast({
				message: Liferay.Language.get('an-unexpected-error-occurred'),
				title: Liferay.Language.get('error'),
				type: 'danger',
			});
		}

		onGoBack();
	};

	const onValidate = (values) => {
		let errorList = {};

		errorList = {
			...validate(
				{
					emailMessage: [(v) => maxLength(v, 10000)],
					emailSubject: [(v) => maxLength(v, 100), required],
					envelopeName: [(v) => maxLength(v, 100), required],
					fileEntryId: [required],
				},
				values
			),
		};

		const recipientErrors = values.recipients.map((recipient) =>
			validate(
				{
					email: [(v) => maxLength(v, 100), isEmail, required],
					fullName: [required],
				},
				recipient
			)
		);

		const withRecipientError = recipientErrors.some(
			(recipient) => recipient?.email || recipient?.fullName
		);

		if (withRecipientError) {
			errorList.recipients = recipientErrors;
		}

		return errorList;
	};

	const {
		errors,
		handleChange,
		handleSubmit,
		isSubmitting,
		isValid,
		setFieldValue,
		values,
	} = useFormik({
		initialValues: {
			emailMessage: '',
			emailSubject: '',
			envelopeName: '',
			fileEntryId,
			recipients: [defaultRecipient],
		},
		onSubmit,
		validate: onValidate,
	});

	return (
		<ClayLayout.Container className="collect-digital-signature">
			<ClayCard>
				<div className="header">
					<h1>
						{Liferay.Language.get('new-digital-signature-envelope')}
					</h1>
				</div>
				<hr />
				<ClayCard.Body className="m-2">
					<ClayForm onSubmit={handleSubmit}>
						<DigitalSignatureFormBase
							defaultRecipient={defaultRecipient}
							errors={errors}
							handleChange={handleChange}
							setFieldValue={setFieldValue}
							showDocumentLibraryInput={showDocumentLibraryInput}
							values={values}
						/>

						<ClayButton
							disabled={
								!isValid || isSubmitting || !values.envelopeName
							}
							type="submit"
						>
							{Liferay.Language.get('save')}
						</ClayButton>
						<ClayButton
							className="ml-2"
							displayType="secondary"
							onClick={onGoBack}
						>
							{Liferay.Language.get('cancel')}
						</ClayButton>
					</ClayForm>
				</ClayCard.Body>
			</ClayCard>
		</ClayLayout.Container>
	);
};

export default DigitalSignatureForm;
