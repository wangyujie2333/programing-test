/*
 * 文件名：Test_GuoyDto.java
 * 版权：Copyright by www.ysepay.com
 * 修改人：guoyong
 * 修改时间：2017年4月5日
 * 修改内容：
 */

package yspay.trade.order.test.dto;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guoyong
 * @version 2017年4月5日
 * @see Test_GuoyDto
 * @since
 */

public class Test_GuoyDto implements Serializable {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}
