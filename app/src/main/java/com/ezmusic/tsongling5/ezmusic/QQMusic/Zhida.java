
package com.ezmusic.tsongling5.ezmusic.QQMusic;

import java.util.HashMap;
import java.util.Map;

public class Zhida {

    private Integer chinesesinger;
    private Integer type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getChinesesinger() {
        return chinesesinger;
    }

    public void setChinesesinger(Integer chinesesinger) {
        this.chinesesinger = chinesesinger;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
