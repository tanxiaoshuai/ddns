package cn.nmmpa.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: tan shuai
 * @Date: 2019/9/9 9:35
 * @Version 1.0
 */
@Component
public class DdnsProperties {

    @Value("${accessKeyID}")
    private String accessKeyID;
    @Value("${accessKeySecret}")
    private String accessKeySecret;
    @Value("${domainName}")
    private String domainName;
    @Value("${rR}")
    private String rR;
    @Value("${scannTime}")
    private String scannTime;

    public String getAccessKeyID() {
        return accessKeyID;
    }

    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getrR() {
        return rR;
    }

    public void setrR(String rR) {
        this.rR = rR;
    }

    public String getScannTime() {
        return scannTime;
    }

    public void setScannTime(String scannTime) {
        this.scannTime = scannTime;
    }
}
