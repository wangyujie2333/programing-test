
/*
* 文件名：FallbackPassLease.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2016年11月18日
* 修改内容：
*/

package com.ysepay.test.curator;

import java.io.IOException;

import org.apache.curator.framework.recipes.locks.Lease;

/**
 * 
 * 降级，如果获取信号量超时，默认通过 这时候关闭的时候，关掉信号量里面所有的lease(在超时后依然可能获取信号量成功)
 * 
 * @author Cindy
 * @version 2016年11月18日
 * @see FallbackPassLease
 * @since
 */
public class FallbackPassLease implements Lease {

	public FallbackPassLease() {
	}

	@Override
	public void close() throws IOException {

	}

	@Override
	public byte[] getData() throws Exception {
		return null;
	}

}
