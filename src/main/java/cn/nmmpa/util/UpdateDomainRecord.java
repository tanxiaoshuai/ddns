package cn.nmmpa.util;

import java.util.List;

import cn.nmmpa.pojo.Aliyun;
import cn.nmmpa.pojo.DdnsProperties;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 调用阿里api,更新DNS域名解析
 *
 * @author xiang
 *
 */
public class UpdateDomainRecord {

	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateDomainRecord.class);

	private static String OLD_IP = "";

	/**
	 * 设置域名参数
	 * 
	 * @param request
	 */
	public void setParam(DescribeDomainRecordsRequest request) {
		DdnsProperties ddnsProperties = BeanFactoryUtil
				.getBeanByClass(DdnsProperties.class);
		request.putQueryParameter("DomainName", ddnsProperties.getDomainName());
	}

	/**
	 * 解析DNS信息
	 */
	public void analysisDns() {
		DdnsProperties ddnsProperties = BeanFactoryUtil
				.getBeanByClass(DdnsProperties.class);
		// 获取公网ip
		LocalPublicIpv4 ip = new LocalPublicIpv4();
		String ipV4 = ip.getPublicIp();
		LOGGER.info("old IP [{}] , new ip[{}]" , OLD_IP , ipV4);
		if(ipV4 == null || "".equals(ipV4)){
			LOGGER.info("获取公网ip失败...");
		}
		//判断当前公网ip是否与上次记录ip是否一致，如果一致直接返回
		if(OLD_IP.equals(ipV4)){
			LOGGER.info("当前公网IP[{}]不需要更新！" , ipV4);
			return;
		}
		//重新赋值
		OLD_IP = ipV4;
		// 获取解析的数据
		String actionName = "DescribeDomainRecords";
		DescribeDomainRecordsResponse response;
		// 获取request
		DescribeDomainRecordsRequest request = AliDdnsUtils.getRequestQuery(actionName);
		// 设置request参数
		setParam(request);
		try {
			response = AliDdnsUtils.getClient().getAcsResponse(request);
			// 声明解析对象
			DemoListDomains demo = new DemoListDomains();
			// 获取阿里云的数据
			List<Record> list = response.getDomainRecords();
			if (list == null || list.isEmpty()) {
				return;
			}
			//更新ip
			Record record = list.stream()
					.filter(r -> ddnsProperties.getrR().equals(r.getRR()))
					.findFirst().orElse(null);
			if(record == null){
				return;
			}
			Aliyun yun = new Aliyun();
			// 进行判定记录是否需要更新
			if (record.getValue().equals(ipV4)) {
				// 不需要更新，继续下次循环
				LOGGER.info("当前域名解析地址为[{}]不需要更新！" , ipV4);
			}else {
				LOGGER.info("域名更换ip[{}]开始" , ipV4);
				// 进行替换关键数据
				yun.setIpV4(ipV4);
				yun.setRecordId(record.getRecordId());
				yun.setRr(record.getRR());
				yun.setTTL(record.getTTL());
				yun.setType(record.getType());
				demo.analysisDns(yun);
				LOGGER.info("域名更换ip[{}]成功" , ipV4);
			}
		} catch (Exception e) {
			LOGGER.error("域名解析失败:{}" , e.toString());
		}
	}
}
