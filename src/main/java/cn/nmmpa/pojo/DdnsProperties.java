package cn.nmmpa.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Value("${rRs}")
    private String rRs;
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

    public String getrRs() {
        return rRs;
    }

    public void setrRs(String rRs) {
        this.rRs = rRs;
    }

    public String getScannTime() {
        return scannTime;
    }

    public void setScannTime(String scannTime) {
        this.scannTime = scannTime;
    }

    public List<String> getrRList(){
        if(this.rRs == null || "".equals(this.rRs)){
            return new ArrayList<>();
        }
        String[] arr = this.rRs.split(",");
        return Arrays.asList(arr);
    }
}
