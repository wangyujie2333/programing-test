package com.yspay.test.job.simplejob;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MyElasticJob implements SimpleJob {

	private static Logger logger = LoggerFactory.getLogger(MyElasticJob.class);

	@Override
	public void execute(ShardingContext context) {
		String sno = UUID.randomUUID().toString().replaceAll("-", "")
				.toUpperCase();
		logger.info("MyElasticJob do (" + context.getShardingItem() + ")");
		switch (context.getShardingItem()) {
		case 0:
			doLog(sno, context.getShardingItem());

			break;
		case 1:
			// do something by sharding item 1
			doLog(sno, context.getShardingItem());
			break;
		case 2:
			// do something by sharding item 2
			doLog(sno, context.getShardingItem());
			break;
		// case n: ...
		}
	}

	private void doLog(String sno, int itemNo) {
		String msg = "sno=" + sno + "_item=" + itemNo;
		java.util.Date bd = new java.util.Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = f.format(bd);
		String info = msg + "_" + dateStr;
		logger.info("fetchData执行：" + info);
	}
}