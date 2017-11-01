
package com.ezmusic.tsongling5.ezmusic.QQMusic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Semantic {

    private Integer curnum;
    private Integer curpage;
    private List<Object> list = null;
    private Integer totalnum;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getCurnum() {
        return curnum;
    }

    public void setCurnum(Integer curnum) {
        this.curnum = curnum;
    }

    public Integer getCurpage() {
        return curpage;
    }

    public void setCurpage(Integer curpage) {
        this.curpage = curpage;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
