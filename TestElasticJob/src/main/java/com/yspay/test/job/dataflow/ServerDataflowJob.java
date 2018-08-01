package com.yspay.test.job.dataflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

public class ServerDataflowJob implements DataflowJob<String> {

	private static Logger logger = LoggerFactory
			.getLogger(ServerDataflowJob.class);

	@Override
	public List<String> fetchData(ShardingContext context) {
		String sno = UUID.randomUUID().toString().replaceAll("-", "")
				.toUpperCase();
		logger.info("ServerDataflowJob do (" + context.getShardingItem() + ")");
		switch (context.getShardingItem()) {
		case 0:
			List<String> list0 = doLog(sno, context.getShardingItem());
			return list0;
		case 1:
			List<String> list1 = doLog(sno, context.getShardingItem());
			return list1;
		case 2:
			List<String> list2 = doLog(sno, context.getShardingItem());
			return list2;
		}
		return null;
	}

	@Override
	public void processData(ShardingContext context, List<String> list) {
		// process data
		// ...
		int size = 0;
		if (null != list) {
			size = list.size();
		}
		int item = context.getShardingItem();
		logger.info("processData:size(" + size + "),item_" + item);
		if (null != list && list.size() > 0) {
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					logger.info("processData:item_" + item + "_" + list.get(i));
				}
			}
		} else {
			logger.info("context.getShardingItem( " + item
					+ " ) processData list is null.");
		}
	}

	private List<String> doLog(String sno, int itemNo) {
		// do something by sharding item 0
		String msg = "sno=" + sno + "_item=" + itemNo;
		java.util.Date bd = new java.util.Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = f.format(bd);
		List<String> list = new ArrayList<String>();
		String info = msg + "_" + dateStr;
		list.add(info);
		logger.info("fetchData执行：" + info);
		return list;
	}
}