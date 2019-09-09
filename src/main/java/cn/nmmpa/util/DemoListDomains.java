 package cn.nmmpa.util;

import java.util.List;
import cn.nmmpa.pojo.Aliyun;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse.Domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 /**
 * 阿里sdk
 * 
 * @author xiang
 *
 */
public class DemoListDomains {

	 private static final Logger LOGGER = LoggerFactory.getLogger(DemoListDomains.class);

	 /**
	 * 设置参数
	 * 
	 * @param request
	 */
	public void setParam(DescribeDomainsRequest request, Aliyun yun) {
		// 设置参数
		request.putQueryParameter("RecordId", yun.getRecordId());
		request.putQueryParameter("RR", yun.getRr());
		request.putQueryParameter("Type", yun.getType());
		request.putQueryParameter("Value", yun.getIpV4());
		request.putQueryParameter("TTL", yun.getTTL());
	}

	/**
	 * 解析DNS信息
	 */
	public void analysisDns(Aliyun yun) {
		String actionName = "UpdateDomainRecord";
		DescribeDomainsRequest request = AliDdnsUtils.getRequest(actionName);
		DescribeDomainsResponse response;
		setParam(request, yun);

		try {
			response = AliDdnsUtils.getClient().getAcsResponse(request);
			List<Domain> list = response.getDomains();
			for (Domain domain : list) {
				LOGGER.info(domain.getDomainName());
			}
		} catch (Exception e) {
			LOGGER.error("dns信息获取失败:{}" , e.toString());
		}
	}

}