package yspay.trade.order.test.dao.mapper;

import java.util.List;

import yspay.trade.order.test.dao.model.Test_Guoy;
import yspay.trade.order.test.dao.model.Test_Iface;

public interface Test_GuoyMapper {
	int insert(Test_Guoy record);

	int insertSelective(Test_Guoy record);

	int insertByBatch(List<Test_Iface> list);
}