package cn.cote;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "people")//获取前缀是people的配置
public class PeoplePOJO {
    private String IQ;
    private int EQ;

    public String getIQ() {
        return IQ;
    }

    public void setIQ(String IQ) {
        this.IQ = IQ;
    }

    public int getEQ() {
        return EQ;
    }

    public void setEQ(int EQ) {
        this.EQ = EQ;
    }
}
