package cn.tx.model;

import java.util.List;

public class Songer {
    // 原有字段
    private Integer srid; // 歌手ID
    private Integer tid; // 流派ID（外键）
    private String srname; // 歌手姓名
    private String area; // 地区
    private String pic; // 头像
    private Integer isHot; // 是否热门
    private String intro; // 简介

    // 新增：关联Mtype实体（核心）
    private Mtype mtype;

    private List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }


    // 必须补充mtype的getter/setter（EL表达式依赖）
    public Mtype getMtype() {
        return mtype;
    }

    public void setMtype(Mtype mtype) {
        this.mtype = mtype;
    }

    // 原有字段的getter/setter（确保不缺失）
    public Integer getSrid() {
        return srid;
    }

    public void setSrid(Integer srid) {
        this.srid = srid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getSrname() {
        return srname;
    }

    public void setSrname(String srname) {
        this.srname = srname;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Songer{" +
                "srid=" + srid +
                ", tid=" + tid +
                ", srname='" + srname + '\'' +
                ", area='" + area + '\'' +
                ", pic='" + pic + '\'' +
                ", isHot=" + isHot +
                ", intro='" + intro + '\'' +
                '}';
    }

    public Songer(Integer srid, Integer tid, String srname, String area, String pic, Integer isHot, String intro) {
        this.srid = srid;
        this.tid = tid;
        this.srname = srname;
        this.area = area;
        this.pic = pic;
        this.isHot = isHot;
        this.intro = intro;
    }

    public Songer() {
    }
}