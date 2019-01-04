package com.alipay.api.domain;

import java.util.Date;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 收支明细
 *
 * @author auto create
 * @since 1.0, 2018-11-14 11:46:59
 */
public class BillDingBizOrder extends AlipayObject {

	private static final long serialVersionUID = 8589345899669527663L;

	/**
	 * 收支金额,收入为正,支出为负
	 */
	@ApiField("amount")
	private String amount;

	/**
	 * 钉钉业务单据号
	 */
	@ApiField("biz_trans_id")
	private String bizTransId;

	/**
	 * 业务类型标识。支持类型有：0100-群收款，0101-报销，0102-支付，0103-收款，0104-转账，0105-工资
	 */
	@ApiField("biz_type")
	private String bizType;

	/**
	 * 资金流向。S表示收入，B表示支出
	 */
	@ApiField("direction")
	private String direction;

	/**
	 * 付款完成时间
	 */
	@ApiField("gmt_finish")
	private Date gmtFinish;

	/**
	 * id
	 */
	@ApiField("id")
	private String id;

	/**
	 * 脱敏后的对方支付宝账号
	 */
	@ApiField("opp_login_id")
	private String oppLoginId;

	/**
	 * 对方昵称
	 */
	@ApiField("opp_nick_name")
	private String oppNickName;

	/**
	 * 对方姓名
	 */
	@ApiField("opp_user_name")
	private String oppUserName;

	/**
	 * 支付宝流水号
	 */
	@ApiField("order_id")
	private String orderId;

	/**
	 * 外部流水号
	 */
	@ApiField("out_order_no")
	private String outOrderNo;

	/**
	 * 业务类型, 有合作伙伴进行业务处理是传入,这里再透传回去
	 */
	@ApiField("sub_biz_scene")
	private String subBizScene;

	/**
	 * 标题
	 */
	@ApiField("title")
	private String title;

	public String getAmount() {
		return this.amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBizTransId() {
		return this.bizTransId;
	}
	public void setBizTransId(String bizTransId) {
		this.bizTransId = bizTransId;
	}

	public String getBizType() {
		return this.bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getDirection() {
		return this.direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Date getGmtFinish() {
		return this.gmtFinish;
	}
	public void setGmtFinish(Date gmtFinish) {
		this.gmtFinish = gmtFinish;
	}

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getOppLoginId() {
		return this.oppLoginId;
	}
	public void setOppLoginId(String oppLoginId) {
		this.oppLoginId = oppLoginId;
	}

	public String getOppNickName() {
		return this.oppNickName;
	}
	public void setOppNickName(String oppNickName) {
		this.oppNickName = oppNickName;
	}

	public String getOppUserName() {
		return this.oppUserName;
	}
	public void setOppUserName(String oppUserName) {
		this.oppUserName = oppUserName;
	}

	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOutOrderNo() {
		return this.outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

	public String getSubBizScene() {
		return this.subBizScene;
	}
	public void setSubBizScene(String subBizScene) {
		this.subBizScene = subBizScene;
	}

	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
