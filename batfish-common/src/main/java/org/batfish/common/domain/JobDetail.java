package org.batfish.common.domain;
/**
* @author Tango
* @date 2018年1月17日 下午8:44:52
* @since 
*/
public class JobDetail {

    /**
     * ID
     */
    private String id;
    /**
     * URL连接
     */
    private String url;
    /**
     * 公司
     */
    private String compny;
    /**
     * 需求
     */
    private String detail;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getCompny() {
        return compny;
    }
    public void setCompny(String compny) {
        this.compny = compny;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    
}
