package cn.nmmpa.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 获取公网ip
 * @author xiang
 *
 */
public class LocalPublicIpv4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalPublicIpv4.class);


    public String publicip() {
        try {
            // 打开连接
            Document doc = Jsoup.connect("http://chaipip.com/").get();
            Elements eles = doc.select("#ip");
            return eles.attr("value");
        }catch (IOException e) {
            LOGGER.error("获取公网IP失败:{}" , e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取公网ip
     * @return
     */
    public String getPublicIp(){
        RestTemplate beanByClass = BeanFactoryUtil
                .getBeanByClass(RestTemplate.class);
        return beanByClass.getForObject("http://icanhazip.com/", String.class);
    }
	
    public static void main(String args[]) {
    	LocalPublicIpv4 ip = new LocalPublicIpv4();
        System.out.println(ip.publicip());
    }
}
