package cn.tx.model;

public class Song {
    private Integer sid;

    private Integer tid;

    private Integer srid;

    private Integer aid;

    private String sname;

    private String pic;

    private String mp3;

    private Integer isHot;

    private Integer isNew;

    private Integer ptimes;

    private Integer auditStatus;

    private Integer showStatus;

    private String lrc;

    // 新增：歌手名称（用于页面展示/查询）
    private String srname;

    // 新增：专辑名称（用于页面展示/查询）
    private String aname;

    // 新增：流派名称（可选，避免后续页面访问${mq.tname}报错）
    private String tname;


    // 新增：关联的歌手对象（解决 ${song.songer} 报错）
    private Songer songer;
    // 新增：关联的专辑对象（解决 ${song.album} 报错）
    private Album album;
    private Mtype mtype;      // 关联流派对象


    public Mtype getMtype() {
        return mtype;
    }

    public void setMtype(Mtype mtype) {
        this.mtype = mtype;
    }

    public Songer getSonger() {
        return songer;
    }

    public void setSonger(Songer songer) {
        this.songer = songer;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getSrname() {
        return srname;
    }

    public void setSrname(String srname) {
        this.srname = srname;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getSrid() {
        return srid;
    }

    public void setSrid(Integer srid) {
        this.srid = srid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3 == null ? null : mp3.trim();
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getPtimes() {
        return ptimes;
    }

    public void setPtimes(Integer ptimes) {
        this.ptimes = ptimes;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc == null ? null : lrc.trim();
    }
}