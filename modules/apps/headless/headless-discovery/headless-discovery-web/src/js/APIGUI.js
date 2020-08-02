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
import ClayDropDown from '@clayui/drop-down';
import ClayLayout from '@clayui/layout';
import ClayModal, {useModal} from '@clayui/modal';
import GraphiQL from 'graphiql';
import React, {useCallback, useEffect, useState} from 'react';
import SwaggerUI from 'swagger-ui-react';

import Icon from './Icon';
import apiFetch from './util/apiFetch';

import 'graphiql/graphiql.css';

const APIGUI = () => {
	const [active, setActive] = useState(false);
	const [endpoints, setEndpoints] = useState([]);
	const [endpoint, setEndpoint] = useState();
	const [showHeaders, setShowHeaders] = useState(false);
	const [showGraphQL, setShowGraphQL] = useState(false);
	const [headers, setHeaders] = useState([{key: '', value: ''}]);

	const {observer, onClose} = useModal({
		onClose: () => setShowHeaders(false),
	});

	const handleInputChange = (index, event) => {
		const values = [...headers];
		values[index][event.target.name] = event.target.value;
		setHeaders(values);
	};

	useEffect(() => {
		apiFetch('/o/openapi', 'get', {}).then((response) => {
			setEndpoints(
				Object.keys(response)
					.flatMap((key) => response[key])
					.map((url) => url.replace('openapi.yaml', 'openapi.json'))
			);
		});
	}, []);

	const graphQLFetcher = useCallback(
		(graphQLParams) =>
			apiFetch(
				'/o/graphql',
				'post',
				graphQLParams,
				'application/json',
				headers
			),
		[headers]
	);

	const requestInterceptor = (req) => {
		req.headers['x-csrf-token'] = document.querySelector(
			'meta[name="csrf-token"]'
		).content;

		for (let i = 0; i < headers.length; i++) {
			const header = headers[i];
			if (header.key && header.value) {
				req.headers[header.key] = header.value;
			}
		}

		return req;
	};

	return (
		<div className="api-gui-root">
			<nav className="navbar navbar-expand-md navbar-underline">
				<div className="container-fluid container-fluid-max-xl">
					<svg
						fill="none"
						height="40"
						viewBox="0 0 295 40"
						width="295"
						xmlns="http://www.w3.org/2000/svg"
					>
						<path
							clipRule="evenodd"
							d="M6.80859 8.51063C6.80859 7.57057 7.57066 6.8085 8.51072 6.8085H28.0852C29.0252 6.8085 29.7873 7.57057 29.7873 8.51063V28.0851C29.7873 29.0252 29.0252 29.7872 28.0852 29.7872H8.51072C7.57066 29.7872 6.80859 29.0252 6.80859 28.0851V8.51063ZM10.2128 10.6383C10.2128 10.4033 10.4034 10.2128 10.6384 10.2128H13.1916C13.4266 10.2128 13.6171 10.4033 13.6171 10.6383V13.1915C13.6171 13.4265 13.4266 13.617 13.1916 13.617H10.6384C10.4034 13.617 10.2128 13.4265 10.2128 13.1915V10.6383ZM14.8937 10.2128C14.6587 10.2128 14.4682 10.4033 14.4682 10.6383V13.1915C14.4682 13.4265 14.6587 13.617 14.8937 13.617H17.4469C17.6819 13.617 17.8724 13.4265 17.8724 13.1915V10.6383C17.8724 10.4033 17.6819 10.2128 17.4469 10.2128H14.8937ZM18.7235 10.6383C18.7235 10.4033 18.914 10.2128 19.149 10.2128H21.7022C21.9372 10.2128 22.1277 10.4033 22.1277 10.6383V13.1915C22.1277 13.4265 21.9372 13.617 21.7022 13.617H19.149C18.914 13.617 18.7235 13.4265 18.7235 13.1915V10.6383ZM10.6384 14.4681C10.4034 14.4681 10.2128 14.6586 10.2128 14.8936V17.4468C10.2128 17.6818 10.4034 17.8723 10.6384 17.8723H13.1916C13.4266 17.8723 13.6171 17.6818 13.6171 17.4468V14.8936C13.6171 14.6586 13.4266 14.4681 13.1916 14.4681H10.6384ZM14.4682 14.8936C14.4682 14.6586 14.6587 14.4681 14.8937 14.4681H17.4469C17.6819 14.4681 17.8724 14.6586 17.8724 14.8936V17.4468C17.8724 17.6818 17.6819 17.8723 17.4469 17.8723H14.8937C14.6587 17.8723 14.4682 17.6818 14.4682 17.4468V14.8936ZM23.4043 14.4681C23.1693 14.4681 22.9788 14.6586 22.9788 14.8936V17.4468C22.9788 17.6818 23.1693 17.8723 23.4043 17.8723H25.9575C26.1925 17.8723 26.3831 17.6818 26.3831 17.4468V14.8936C26.3831 14.6586 26.1925 14.4681 25.9575 14.4681H23.4043ZM10.2128 19.1489C10.2128 18.9139 10.4034 18.7234 10.6384 18.7234H13.1916C13.4266 18.7234 13.6171 18.9139 13.6171 19.1489V21.7021C13.6171 21.9371 13.4266 22.1277 13.1916 22.1277H10.6384C10.4034 22.1277 10.2128 21.9371 10.2128 21.7021V19.1489ZM19.149 18.7234C18.914 18.7234 18.7235 18.9139 18.7235 19.1489V21.7021C18.7235 21.9371 18.914 22.1277 19.149 22.1277H21.7022C21.9372 22.1277 22.1277 21.9371 22.1277 21.7021V19.1489C22.1277 18.9139 21.9372 18.7234 21.7022 18.7234H19.149ZM22.9788 19.1489C22.9788 18.9139 23.1693 18.7234 23.4043 18.7234H25.9575C26.1925 18.7234 26.3831 18.9139 26.3831 19.1489V21.7021C26.3831 21.9371 26.1925 22.1277 25.9575 22.1277H23.4043C23.1693 22.1277 22.9788 21.9371 22.9788 21.7021V19.1489ZM14.8937 22.9787C14.6587 22.9787 14.4682 23.1692 14.4682 23.4042V25.9574C14.4682 26.1925 14.6587 26.383 14.8937 26.383H17.4469C17.6819 26.383 17.8724 26.1925 17.8724 25.9574V23.4042C17.8724 23.1692 17.6819 22.9787 17.4469 22.9787H14.8937ZM18.7235 23.4042C18.7235 23.1692 18.914 22.9787 19.149 22.9787H21.7022C21.9372 22.9787 22.1277 23.1692 22.1277 23.4042V25.9574C22.1277 26.1925 21.9372 26.383 21.7022 26.383H19.149C18.914 26.383 18.7235 26.1925 18.7235 25.9574V23.4042ZM23.4043 22.9787C23.1693 22.9787 22.9788 23.1692 22.9788 23.4042V25.9574C22.9788 26.1925 23.1693 26.383 23.4043 26.383H25.9575C26.1925 26.383 26.3831 26.1925 26.3831 25.9574V23.4042C26.3831 23.1692 26.1925 22.9787 25.9575 22.9787H23.4043Z"
							fill="#0B63CE"
							fillRule="evenodd"
						></path>
						<path
							d="M48.6808 27.7872H38.5957V6.93615C38.5957 6.8085 38.5106 6.72339 38.3829 6.72339H36.8085C36.6808 6.72339 36.5957 6.8085 36.5957 6.93615V29.3191C36.5957 29.4468 36.6808 29.5319 36.8085 29.5319H48.6808C48.8085 29.5319 48.8936 29.4468 48.8936 29.3191V27.9574C48.8936 27.8723 48.8085 27.7872 48.6808 27.7872Z"
							fill="#272833"
						></path>
						<path
							d="M63.6168 12.8085H60.0423V9.48935C60.0423 7.14893 60.8082 5.99999 62.3827 5.99999C63.021 5.99999 63.6593 6.1702 64.255 6.42552C64.2976 6.46808 64.3827 6.46808 64.4253 6.42552C64.4678 6.38297 64.5104 6.34042 64.5529 6.29786L64.9785 5.06382C65.021 4.97871 64.9785 4.85105 64.8508 4.8085C64.0423 4.46808 63.1487 4.25531 62.3827 4.25531C59.6593 4.25531 58.1274 6.0851 58.1274 9.36169V12.8085L56.0848 12.9362C55.9572 12.9362 55.8721 13.0213 55.8721 13.1489V14.2979C55.8721 14.4255 55.9572 14.5106 56.0848 14.5106H58.1274V29.3191C58.1274 29.4468 58.2125 29.5319 58.3402 29.5319H59.8295C59.9572 29.5319 60.0423 29.4468 60.0423 29.3191V14.5106H63.6168C63.7444 14.5106 63.8295 14.4255 63.8295 14.2979V12.9787C63.8295 12.8936 63.7444 12.8085 63.6168 12.8085Z"
							fill="#272833"
						></path>
						<path
							d="M71.3616 12.383C67.8297 12.383 64.0425 15.4893 64.0425 21.1915C64.0425 26.3404 67.234 29.9574 71.7872 29.9574C74.2127 29.9574 75.7021 29.1489 77.0638 28.3404C77.1489 28.2979 77.1914 28.1702 77.1489 28.0425L76.5106 26.8936C76.468 26.8511 76.4255 26.8085 76.3829 26.8085C76.3404 26.8085 76.2552 26.8085 76.2127 26.8511C74.851 27.8298 73.4893 28.2553 71.9574 28.2553C68.5106 28.2553 66.1701 25.5745 66.0425 21.617H77.5744C77.6595 21.617 77.7446 21.5319 77.7872 21.4468C77.8723 21.0213 77.8723 20.5532 77.8723 20.1702C77.7872 15.2766 75.4042 12.383 71.3616 12.383ZM65.9999 19.9149C66.3829 16.4681 68.5531 14.0851 71.3616 14.0851C74.2978 14.0851 75.9148 16.1702 75.9574 19.9149H65.9999Z"
							fill="#272833"
						></path>
						<path
							d="M88.6808 12.766C88.1702 12.5106 87.6595 12.4255 86.9787 12.4255C85.2766 12.4255 83.6595 13.4894 82.4255 15.4894L82.2978 13.0213C82.2978 12.8936 82.2127 12.8085 82.0851 12.8085H80.8085C80.6808 12.8085 80.5957 12.8936 80.5957 13.0213V29.3617C80.5957 29.4894 80.6808 29.5745 80.8085 29.5745H82.2978C82.4255 29.5745 82.5106 29.4894 82.5106 29.3617V18.2979C83.5319 15.7447 85.1063 14.2553 86.7659 14.2553C87.3617 14.2553 87.617 14.3404 88.1702 14.5106C88.2127 14.5106 88.2978 14.5106 88.3404 14.5106C88.3829 14.4681 88.4255 14.4255 88.4255 14.383L88.7659 13.0213C88.8085 12.8936 88.7659 12.8085 88.6808 12.766Z"
							fill="#272833"
						></path>
						<path
							d="M102.128 12.8085H100.808C100.681 12.8085 100.596 12.8936 100.596 13.0213L100.511 14.2553C99.1063 13.1489 97.7446 12.4255 95.7871 12.4255C91.4467 12.4255 88.3403 16.1277 88.3403 21.234C88.3403 26.7234 90.9361 30 95.3191 30C97.1063 30 98.8935 29.1915 100.553 27.6596L100.681 29.4043C100.681 29.5319 100.766 29.617 100.894 29.617H102.17C102.298 29.617 102.383 29.5319 102.383 29.4043V13.0213C102.34 12.8936 102.255 12.8085 102.128 12.8085ZM100.425 16.1277V25.617C98.6808 27.4043 97.1488 28.1702 95.5318 28.1702C92.2978 28.1702 90.3403 25.5319 90.3403 21.1489C90.3403 17.1915 92.7659 14.0851 95.8722 14.0851C97.4893 14.1277 98.851 14.7234 100.425 16.1277Z"
							fill="#272833"
						></path>
						<path
							d="M118.383 12.8936C118.34 12.8511 118.298 12.8085 118.213 12.8085H116.681C116.596 12.8085 116.511 12.8511 116.468 12.9362L112.979 23.1915C112.809 23.7447 112.596 24.3404 112.426 24.9362C112.17 25.7872 111.872 26.6383 111.617 27.4043C111.277 26.5532 110.936 25.617 110.596 24.7234C110.383 24.1702 110.17 23.6596 110 23.1489L106.085 12.8936C106.043 12.8085 105.957 12.766 105.872 12.766H104.255C104.17 12.766 104.128 12.8085 104.085 12.8511C104.043 12.8936 104.043 12.9787 104.085 13.0638L110.766 29.617L110.34 30.9787C109.702 32.8936 108.511 35.1489 106.34 35.1489C105.915 35.1489 105.404 35.0213 105.064 34.8936C105.021 34.8936 104.936 34.8936 104.894 34.8936C104.851 34.9362 104.809 34.9787 104.766 35.0213L104.426 36.3404C104.383 36.4255 104.468 36.5532 104.553 36.5957C105.021 36.8085 105.702 36.9362 106.298 36.9362C108.809 36.9362 110.766 35.0213 112 31.4043L118.426 13.0213C118.468 13.0213 118.426 12.9362 118.383 12.8936Z"
							fill="#272833"
						></path>
						<path
							d="M52.936 9.10639C53.1913 9.10639 53.4041 8.89363 53.4041 8.6383V7.10639C53.4041 6.85107 53.1913 6.63831 52.936 6.63831H51.4041C51.1488 6.63831 50.936 6.85107 50.936 7.10639V8.6383C50.936 8.89363 51.1488 9.10639 51.4041 9.10639H52.936Z"
							fill="#272833"
						></path>
						<path
							d="M51.4041 12.8085H52.8935C53.0211 12.8085 53.1062 12.8936 53.1062 13.0213V29.3617C53.1062 29.4894 53.0211 29.5745 52.8935 29.5745H51.4041C51.2765 29.5745 51.1913 29.4894 51.1913 29.3617V13.0213C51.1913 12.8936 51.2765 12.8085 51.4041 12.8085Z"
							fill="#272833"
						></path>
						<path
							d="M136.552 21.424L135.816 18.864C135.475 17.7547 135.144 16.6133 134.824 15.44C134.525 14.2667 134.227 13.104 133.928 11.952H133.8C133.501 13.1253 133.203 14.2987 132.904 15.472C132.605 16.624 132.285 17.7547 131.944 18.864L131.208 21.424H136.552ZM137.384 24.336H130.344L128.712 30H124.936L131.752 9.072H136.104L142.92 30H139.016L137.384 24.336ZM145.5 9.072H152.54C153.692 9.072 154.748 9.17867 155.708 9.392C156.689 9.60533 157.532 9.968 158.236 10.48C158.94 10.992 159.494 11.664 159.9 12.496C160.305 13.3067 160.508 14.32 160.508 15.536C160.508 16.688 160.305 17.6907 159.9 18.544C159.494 19.3973 158.929 20.112 158.204 20.688C157.5 21.2427 156.657 21.6587 155.676 21.936C154.716 22.192 153.67 22.32 152.54 22.32H149.212V30H145.5V9.072ZM152.252 19.344C155.324 19.344 156.86 18.0747 156.86 15.536C156.86 14.2133 156.476 13.3067 155.708 12.816C154.94 12.304 153.788 12.048 152.252 12.048H149.212V19.344H152.252ZM164.437 9.072H168.149V30H164.437V9.072ZM180.031 9.072H192.639V12.208H183.743V17.552H191.263V20.688H183.743V26.864H192.959V30H180.031V9.072ZM200.034 21.808L195.362 14.288H199.33L201.09 17.264C201.324 17.6907 201.559 18.1387 201.794 18.608C202.05 19.056 202.295 19.4933 202.53 19.92H202.658C202.85 19.4933 203.052 19.056 203.266 18.608C203.479 18.1387 203.682 17.6907 203.874 17.264L205.378 14.288H209.186L204.546 22.224L209.538 30H205.57L203.65 26.896C203.394 26.4267 203.127 25.9573 202.85 25.488C202.594 25.0187 202.327 24.56 202.05 24.112H201.922C201.687 24.56 201.452 25.0187 201.218 25.488C201.004 25.936 200.78 26.4053 200.546 26.896L198.882 30H195.042L200.034 21.808ZM216.016 31.28V36.208H212.336V14.288H215.376L215.632 15.952H215.76C216.421 15.3973 217.157 14.9173 217.968 14.512C218.779 14.1067 219.621 13.904 220.496 13.904C221.477 13.904 222.341 14.096 223.088 14.48C223.856 14.8427 224.507 15.376 225.04 16.08C225.573 16.784 225.979 17.6267 226.256 18.608C226.533 19.5893 226.672 20.688 226.672 21.904C226.672 23.248 226.48 24.4533 226.096 25.52C225.733 26.5653 225.243 27.4507 224.624 28.176C224.005 28.88 223.291 29.424 222.48 29.808C221.669 30.192 220.827 30.384 219.952 30.384C219.269 30.384 218.587 30.2347 217.904 29.936C217.221 29.6373 216.56 29.2107 215.92 28.656L216.016 31.28ZM216.016 26.032C216.592 26.5227 217.147 26.864 217.68 27.056C218.213 27.248 218.715 27.344 219.184 27.344C220.229 27.344 221.104 26.896 221.808 26C222.512 25.0827 222.864 23.728 222.864 21.936C222.864 20.3573 222.597 19.1307 222.064 18.256C221.531 17.3813 220.667 16.944 219.472 16.944C218.363 16.944 217.211 17.5307 216.016 18.704V26.032ZM230.399 7.408H234.079V26.16C234.079 26.608 234.164 26.928 234.335 27.12C234.505 27.2907 234.687 27.376 234.879 27.376C234.964 27.376 235.039 27.376 235.103 27.376C235.188 27.376 235.305 27.3547 235.455 27.312L235.935 30.064C235.444 30.2773 234.793 30.384 233.983 30.384C232.66 30.384 231.732 29.9893 231.199 29.2C230.665 28.4107 230.399 27.3333 230.399 25.968V7.408ZM238.062 22.16C238.062 20.8587 238.265 19.696 238.67 18.672C239.075 17.648 239.619 16.784 240.302 16.08C241.006 15.376 241.806 14.8427 242.702 14.48C243.598 14.096 244.537 13.904 245.518 13.904C246.499 13.904 247.438 14.096 248.334 14.48C249.251 14.8427 250.051 15.376 250.734 16.08C251.417 16.784 251.961 17.648 252.366 18.672C252.793 19.696 253.006 20.8587 253.006 22.16C253.006 23.4613 252.793 24.624 252.366 25.648C251.961 26.672 251.417 27.536 250.734 28.24C250.051 28.944 249.251 29.4773 248.334 29.84C247.438 30.2027 246.499 30.384 245.518 30.384C244.537 30.384 243.598 30.2027 242.702 29.84C241.806 29.4773 241.006 28.944 240.302 28.24C239.619 27.536 239.075 26.672 238.67 25.648C238.265 24.624 238.062 23.4613 238.062 22.16ZM241.838 22.16C241.838 23.7387 242.158 25.008 242.798 25.968C243.459 26.9067 244.366 27.376 245.518 27.376C246.67 27.376 247.577 26.9067 248.238 25.968C248.899 25.008 249.23 23.7387 249.23 22.16C249.23 20.56 248.899 19.2907 248.238 18.352C247.577 17.392 246.67 16.912 245.518 16.912C244.366 16.912 243.459 17.392 242.798 18.352C242.158 19.2907 241.838 20.56 241.838 22.16ZM256.649 14.288H259.689L259.945 17.072H260.073C260.627 16.048 261.299 15.2693 262.089 14.736C262.878 14.1813 263.689 13.904 264.521 13.904C265.267 13.904 265.865 14.0107 266.313 14.224L265.673 17.424C265.395 17.3387 265.139 17.2747 264.905 17.232C264.67 17.1893 264.382 17.168 264.041 17.168C263.422 17.168 262.771 17.4133 262.089 17.904C261.406 18.3733 260.819 19.2053 260.329 20.4V30H256.649V14.288ZM267.25 22.16C267.25 20.88 267.452 19.728 267.858 18.704C268.263 17.68 268.796 16.816 269.458 16.112C270.14 15.408 270.919 14.864 271.794 14.48C272.668 14.096 273.564 13.904 274.482 13.904C275.548 13.904 276.487 14.0853 277.298 14.448C278.108 14.8107 278.78 15.3227 279.314 15.984C279.868 16.6453 280.284 17.4347 280.562 18.352C280.839 19.2693 280.978 20.272 280.978 21.36C280.978 22.0853 280.924 22.6613 280.818 23.088H270.834C271.004 24.5173 271.495 25.616 272.306 26.384C273.138 27.152 274.194 27.536 275.474 27.536C276.156 27.536 276.786 27.44 277.362 27.248C277.959 27.0347 278.546 26.7467 279.122 26.384L280.37 28.688C279.623 29.1787 278.791 29.584 277.874 29.904C276.956 30.224 275.996 30.384 274.994 30.384C273.906 30.384 272.892 30.2027 271.954 29.84C271.015 29.456 270.194 28.912 269.49 28.208C268.786 27.504 268.231 26.6507 267.826 25.648C267.442 24.624 267.25 23.4613 267.25 22.16ZM277.778 20.752C277.778 19.4933 277.511 18.512 276.978 17.808C276.466 17.104 275.666 16.752 274.578 16.752C273.639 16.752 272.818 17.0933 272.114 17.776C271.41 18.4373 270.972 19.4293 270.802 20.752H277.778ZM284.492 14.288H287.532L287.788 17.072H287.916C288.471 16.048 289.143 15.2693 289.932 14.736C290.722 14.1813 291.532 13.904 292.364 13.904C293.111 13.904 293.708 14.0107 294.156 14.224L293.516 17.424C293.239 17.3387 292.983 17.2747 292.748 17.232C292.514 17.1893 292.226 17.168 291.884 17.168C291.266 17.168 290.615 17.4133 289.932 17.904C289.25 18.3733 288.663 19.2053 288.172 20.4V30H284.492V14.288Z"
							fill="#272833"
						></path>
					</svg>

					<ul className="navbar-nav">
						<li className="nav-item">
							<ClayDropDown
								active={active}
								onActiveChange={setActive}
								trigger={
									<ClayButton displayType="unstyled">
										{'Rest Applications'}
										<Icon symbol="caret-bottom-l" />
									</ClayButton>
								}
							>
								<ClayDropDown.ItemList>
									<ClayDropDown.Group>
										{[...endpoints].map((endpoint, i) => (
											<ClayDropDown.Item
												key={i}
												onClick={() => {
													setActive(false);
													setEndpoint(endpoint);
												}}
											>
												{endpoint
													.substring(
														endpoint.indexOf(
															'/o/'
														) + 3
													)
													.replace(
														'/openapi.json',
														''
													)}
											</ClayDropDown.Item>
										))}
									</ClayDropDown.Group>
								</ClayDropDown.ItemList>
							</ClayDropDown>
						</li>
						<li className="nav-item">
							<ClayButton
								displayType="unstyled"
								onClick={() => {
									setShowHeaders(true);
								}}
							>
								Headers
							</ClayButton>
						</li>
						<li className="nav-item">
							<ClayButton
								displayType="unstyled"
								onClick={() => {
									setShowGraphQL(!showGraphQL);
								}}
							>
								{showGraphQL ? 'Swagger' : 'GraphQL'}
							</ClayButton>
						</li>
					</ul>
				</div>
			</nav>

			<ClayLayout.ContainerFluid>
				{showHeaders && (
					<ClayModal observer={observer} size="lg" status="info">
						<ClayModal.Header>{'Headers'}</ClayModal.Header>

						<ClayModal.Body>
							<h1>
								Add, edit, and remove headers in your request.
							</h1>

							{headers.map((header, i) => (
								<div className="form-group-autofit" key={i}>
									<div className="form-group-item">
										<input
											className="form-control"
											name="key"
											onChange={(event) =>
												handleInputChange(i, event)
											}
											placeholder="Header Key"
											type="text"
											value={header.key}
										/>
									</div>

									<div className="form-group-item">
										<input
											className="form-control"
											name="value"
											onChange={(event) =>
												handleInputChange(i, event)
											}
											placeholder="Header Value"
											type="text"
											value={header.value}
										/>
									</div>

									<ClayButton
										className="btn btn-warning"
										displayType={'secondary'}
										onClick={() => {
											const values = [...headers];
											values.splice(i, 1);
											setHeaders(values);
										}}
									>
										<Icon symbol="minus-circle" />
									</ClayButton>
								</div>
							))}
						</ClayModal.Body>

						<ClayModal.Footer
							first={
								<ClayButton.Group spaced>
									<ClayButton
										displayType="secondary"
										onClick={() => {
											setHeaders([
												...headers,
												{key: '', value: ''},
											]);
										}}
									>
										Add Header
									</ClayButton>
								</ClayButton.Group>
							}
							last={
								<ClayButton
									onClick={() => {
										onClose();
									}}
								>
									Save
								</ClayButton>
							}
						/>
					</ClayModal>
				)}

				{!showGraphQL && (
					<SwaggerUI
						displayOperationId={true}
						requestInterceptor={requestInterceptor}
						supportedSubmitMethods={[
							'get',
							'put',
							'post',
							'delete',
							'patch',
						]}
						url={
							endpoint ||
							endpoints.find((url) => url.includes('delivery'))
						}
					/>
				)}

				{showGraphQL && (
					<ClayLayout.Row className="vh-100">
						<GraphiQL fetcher={graphQLFetcher} />
					</ClayLayout.Row>
				)}
			</ClayLayout.ContainerFluid>
		</div>
	);
};

export default APIGUI;
