
/*
* 文件名：AccountTradeCollapser.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2017年12月7日
* 修改内容：
*/

package com.ysepay.test.hystrix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCommand;

/**
 * 
 * 单转批请求入口类，构造函数传递单个记账请求 〈功能详细描述〉
 * 
 * @author Cindy
 * @version 2017年12月11日
 * @see AccountTradeCollapser
 * @since
 */
public class AccountTradeCollapser extends
		HystrixCollapser<List<AccountTradeResponse>, AccountTradeResponse, AccountTrade> {
	private static final Log log = LogFactory
			.getLog(AccountTradeCollapser.class);

	private AccountTrade accountTrade;

	AccountTradeCollapser(AccountTrade accountTrade, Setter setter) {
		super(setter);
		this.accountTrade = accountTrade;
	}

	@Override
	public AccountTrade getRequestArgument() {
		return this.accountTrade;
	}

	/**
	 * 
	 * 创建批量入账的命令对象。入参为shardRequests方法返回的集合中的一个值。这批入账对象有相同的账户id。
	 * 
	 * 每次从排队队列里面取到一批记账请求，按账户id分类后，相同的账户id记账请求作为一个批处理单元。
	 */
	@Override
	protected HystrixCommand<List<AccountTradeResponse>> createCommand(
			Collection<com.netflix.hystrix.HystrixCollapser.CollapsedRequest<AccountTradeResponse, AccountTrade>> requests) {
		List<AccountTrade> accountTradeBatch = new ArrayList<AccountTrade>();
		for (CollapsedRequest<AccountTradeResponse, AccountTrade> request : requests) {
			accountTradeBatch.add(request.getArgument());
		}

		return new BatchAccountTradeCommand(accountTradeBatch);
	}

	/**
	 * 每批相同账户id的记账执行完之后，把返回结果和请求对象进行关联。
	 */
	@Override
	protected void mapResponseToRequests(
			List<AccountTradeResponse> batchResponse,
			Collection<com.netflix.hystrix.HystrixCollapser.CollapsedRequest<AccountTradeResponse, AccountTrade>> requests) {

		if (batchResponse.size() != requests.size()) {
			throw new RuntimeException("lists don't match in size => "
					+ batchResponse.size() + " : " + requests.size());
		}
		int i = 0;
		for (CollapsedRequest<AccountTradeResponse, AccountTrade> request : requests) {
			request.setResponse(batchResponse.get(i++));
		}
	}

	/**
	 * 每次从排队的记账队列取一批请求（队列排队个数到了设置的最大值或者定时器间隔时间到），就会回调此方法进行分类
	 * 
	 * 把一批入账的请求按账号进行分类，账号相同的作为一个批量入账命令执行。有多少个账户就有多少个批量入账命令执行。
	 */
	@Override
	protected Collection<Collection<CollapsedRequest<AccountTradeResponse, AccountTrade>>> shardRequests(
			Collection<CollapsedRequest<AccountTradeResponse, AccountTrade>> requests) {
		
		Map<String, Collection<CollapsedRequest<AccountTradeResponse, AccountTrade>>> accountIdKeyMap = new HashMap<String, Collection<CollapsedRequest<AccountTradeResponse, AccountTrade>>>();

		for (CollapsedRequest<AccountTradeResponse, AccountTrade> request : requests) {
			Collection<CollapsedRequest<AccountTradeResponse, AccountTrade>> sameKeyRequest = accountIdKeyMap
					.get(request.getArgument().getAccountid());
			if (null != sameKeyRequest) {
				sameKeyRequest.add(request);
			} else {
				sameKeyRequest = new ArrayList<CollapsedRequest<AccountTradeResponse, AccountTrade>>();
				sameKeyRequest.add(request);
				accountIdKeyMap.put(request.getArgument().getAccountid(),
						sameKeyRequest);
			}
		}

		log.info("从排队的记账队列取一批请求（队列排队个数到了设置的最大值或者定时器间隔时间到），回调此方法进行分类，账户id分类个数："+accountIdKeyMap.keySet().size());
		return accountIdKeyMap.values();
	}

}
