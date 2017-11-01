
package com.ezmusic.tsongling5.ezmusic.QQMusic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    private String keyword;
    private Integer priority;
    private List<Object> qc = null;
    private Semantic semantic;
    private Song song;
    private Double totaltime;
    private Zhida zhida;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public List<Object> getQc() {
        return qc;
    }

    public void setQc(List<Object> qc) {
        this.qc = qc;
    }

    public Semantic getSemantic() {
        return semantic;
    }

    public void setSemantic(Semantic semantic) {
        this.semantic = semantic;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Double getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(Double totaltime) {
        this.totaltime = totaltime;
    }

    public Zhida getZhida() {
        return zhida;
    }

    public void setZhida(Zhida zhida) {
        this.zhida = zhida;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
