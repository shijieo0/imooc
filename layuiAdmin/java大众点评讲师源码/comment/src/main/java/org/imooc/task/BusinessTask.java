package org.imooc.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.imooc.bean.SysParam;
import org.imooc.constant.SysParamKeyConst;
import org.imooc.dao.BusinessDao;
import org.imooc.dao.SysParamDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商户相关的定时任务
 */
@Component("BusinessTask")
public class BusinessTask {

	private static final Logger logger = LoggerFactory.getLogger(BusinessTask.class);
	
	@Resource
	private BusinessDao businessDao;
	
	@Resource
	private SysParamDao sysParamDao;

	/**
	 * 同步已售数量
	 */
	public void synNumber() {
		logger.info("已同步！");
	}
	
	/**
	 * 同步星级
	 */
	@Transactional
	public void synStar() {
		logger.info("synStar start");
		// 先获取上次同步的时间(最后同步时间)
		SysParam sysParam = sysParamDao.selectByKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
		Map<String,Date> map = new HashMap<>();
		map.put("startTime", sysParam.getParamValue());
		// 以当前系统时间做为同步的截止时间，同时也做为下次同步的起始时间
		Date endTime = new Date();
		map.put("endTime", endTime);
		// 按这样起始时间-结束时间同步：商户对应的【星星总数】、【评论总次数】
		// 如果起始时间为NULL，那说明是第一次同步，需要将历史数据全步同步，一直同步到当前系统时间为止。
		businessDao.updateStar(map);
		// 将当前这个系统时间更新到系统参数表中，做为下次同步的起始时间
		SysParam sysParamForUpdate = new SysParam();
		sysParamForUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
		sysParamForUpdate.setParamValue(endTime);
		sysParamDao.updateByKey(sysParamForUpdate);
		logger.info("synStar end");
	}
}