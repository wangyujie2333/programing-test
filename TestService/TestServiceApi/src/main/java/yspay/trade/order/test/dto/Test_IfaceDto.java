/*
 * 文件名：Test_IfaceDto.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2017年4月6日
 * 修改内容：
 */

package yspay.trade.order.test.dto;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guoyong
 * @version 2017年4月6日
 * @see Test_IfaceDto
 * @since
 */

public class Test_IfaceDto implements Serializable {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */

	private static final long serialVersionUID = 1L;
	private String id;
	private String desc1;

	public String getId() {

		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc1() {

		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public static long getSerialversionuid() {

		return serialVersionUID;
	}

}
