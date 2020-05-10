package com.bs.demo.model;

public class OrderBean {
    int oid;
    int sjid;
    int userid;
    UserInfoBean userInfoBean;
    SjBean sjBean;
    String xt;
    String neicun;
    String xinghao;
    String yanse;
    String pingmuwaiguang;
    String shexiangtou;
    String jishenwaiguang;
    String weixiushi;
    String status;
    String baojia;

    public String getBaojia() {
        return baojia;
    }

    public void setBaojia(String baojia) {
        this.baojia = baojia;
    }

    public SjBean getSjBean() {
        return sjBean;
    }

    public void setSjBean(SjBean sjBean) {
        this.sjBean = sjBean;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }

    public int getSjid() {
        return sjid;
    }

    public void setSjid(int sjid) {
        this.sjid = sjid;
    }

    public String getXt() {
        return xt;
    }

    public void setXt(String xt) {
        this.xt = xt;
    }

    public String getNeicun() {
        return neicun;
    }

    public void setNeicun(String neicun) {
        this.neicun = neicun;
    }



    public String getYanse() {
        return yanse;
    }

    public void setYanse(String yanse) {
        this.yanse = yanse;
    }

    public String getPingmuwaiguang() {
        return pingmuwaiguang;
    }

    public void setPingmuwaiguang(String pingmuwaiguang) {
        this.pingmuwaiguang = pingmuwaiguang;
    }

    public String getShexiangtou() {
        return shexiangtou;
    }

    public void setShexiangtou(String shexiangtou) {
        this.shexiangtou = shexiangtou;
    }

    public String getJishenwaiguang() {
        return jishenwaiguang;
    }

    public void setJishenwaiguang(String jishenwaiguang) {
        this.jishenwaiguang = jishenwaiguang;
    }

    public String getWeixiushi() {
        return weixiushi;
    }

    public void setWeixiushi(String weixiushi) {
        this.weixiushi = weixiushi;
    }

}
