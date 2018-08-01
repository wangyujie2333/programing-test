/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.ysepay.test.hibernate.entity;

import java.io.Serializable;

import com.ysepay.framework.sensitive.DBDecodeSensitive;
import com.ysepay.framework.sensitive.DBEncodeSensitive;
import com.ysepay.framework.sensitive.LogMaskSensitive;
import com.ysepay.framework.sensitive.fieldconverter.sample.TelNoSensitiveDBDecodeConverter;
import com.ysepay.framework.sensitive.fieldconverter.sample.TelNoSensitiveDBEncodeConverter;
import com.ysepay.framework.sensitive.fieldconverter.sample.TelNoSensitiveLogMaskConverter;
import com.ysepay.framework.sensitive.objectconverter.IDBSensitiveObject;

public class Order implements Serializable, IDBSensitiveObject {

	private boolean hasEncoded = false;

	@Override
	public boolean getHasEncoded() {

		return this.hasEncoded;

	}

	@Override
	public void setHasEncoded(boolean hasEncoded) {

		this.hasEncoded = hasEncoded;

	}

	private static final long serialVersionUID = 661434701950670670L;

	private long orderId;

	private int userId;

	private String status;

	@DBEncodeSensitive(converter = TelNoSensitiveDBEncodeConverter.class)
	@DBDecodeSensitive(converter = TelNoSensitiveDBDecodeConverter.class)
	@LogMaskSensitive(converter = TelNoSensitiveLogMaskConverter.class)
	private String userTelNo;

	@DBEncodeSensitive(converter = TelNoSensitiveDBEncodeConverter.class)
	@DBDecodeSensitive(converter = TelNoSensitiveDBDecodeConverter.class)
	@LogMaskSensitive(converter = TelNoSensitiveLogMaskConverter.class)
	private String ownerTelNo;

	@DBEncodeSensitive(converter = TelNoSensitiveDBEncodeConverter.class)
	@DBDecodeSensitive(converter = TelNoSensitiveDBDecodeConverter.class)
	@LogMaskSensitive(converter = TelNoSensitiveLogMaskConverter.class)
	private String yourTelNo;

	@DBEncodeSensitive(converter = TelNoSensitiveDBEncodeConverter.class)
	@DBDecodeSensitive(converter = TelNoSensitiveDBDecodeConverter.class)
	@LogMaskSensitive(converter = TelNoSensitiveLogMaskConverter.class)
	private String hisTelNo;

	@DBEncodeSensitive(converter = TelNoSensitiveDBEncodeConverter.class)
	@DBDecodeSensitive(converter = TelNoSensitiveDBDecodeConverter.class)
	@LogMaskSensitive(converter = TelNoSensitiveLogMaskConverter.class)
	private String herTelNo;

	public String getOwnerTelNo() {

		return ownerTelNo;
	}

	public void setOwnerTelNo(String ownerTelNo) {
		this.ownerTelNo = ownerTelNo;
	}

	public String getYourTelNo() {

		return yourTelNo;
	}

	public void setYourTelNo(String yourTelNo) {
		this.yourTelNo = yourTelNo;
	}

	public String getHisTelNo() {

		return hisTelNo;
	}

	public void setHisTelNo(String hisTelNo) {
		this.hisTelNo = hisTelNo;
	}

	public String getHerTelNo() {

		return herTelNo;
	}

	public void setHerTelNo(String herTelNo) {
		this.herTelNo = herTelNo;
	}

	public String getUserTelNo() {
		return userTelNo;
	}

	public void setUserTelNo(String userTelNo) {
		this.userTelNo = userTelNo;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(final long orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(final int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("order_id: %s, user_id: %s, status: %s", orderId,
				userId, status);
	}
}
