package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 多端小程序上架
 *
 * @author auto create
 * @since 1.0, 2018-11-01 17:43:57
 */
public class AlipayOpenMiniInnerversionOnlineModel extends AlipayObject {

	private static final long serialVersionUID = 6452243794291252821L;

	/**
	 * 下架版本
	 */
	@ApiField("app_offline_version")
	private String appOfflineVersion;

	/**
	 * 上架版本
	 */
	@ApiField("app_online_version")
	private String appOnlineVersion;

	/**
	 * 小程序ID
	 */
	@ApiField("mini_app_id")
	private String miniAppId;

	public String getAppOfflineVersion() {
		return this.appOfflineVersion;
	}
	public void setAppOfflineVersion(String appOfflineVersion) {
		this.appOfflineVersion = appOfflineVersion;
	}

	public String getAppOnlineVersion() {
		return this.appOnlineVersion;
	}
	public void setAppOnlineVersion(String appOnlineVersion) {
		this.appOnlineVersion = appOnlineVersion;
	}

	public String getMiniAppId() {
		return this.miniAppId;
	}
	public void setMiniAppId(String miniAppId) {
		this.miniAppId = miniAppId;
	}

}
