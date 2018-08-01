package com.yspay.test.job.simplejob;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 单服务器： execute：不区分item
 * 
 * @author Administrator
 * 
 */
public class ServerExecuteJob implements SimpleJob {

	private static Logger logger = LoggerFactory
			.getLogger(ServerExecuteJob.class);

	@Override
	public void execute(ShardingContext context) {
		String sno = UUID.randomUUID().toString().replaceAll("-", "")
				.toUpperCase();
		logger.info("ServerExecuteJob do (" + context.getShardingItem() + ")");
		doLog(sno, context.getShardingItem());
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