package cn.tx.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Album {
    private Integer aid;

    private String aname;

    private String pic;

    private String company;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pdate;

    private String lang;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Album{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", pic='" + pic + '\'' +
                ", company='" + company + '\'' +
                ", pdate='" + pdate + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }

    public Album(Integer aid, String aname, String pic, String company, Date pdate, String lang) {
        this.aid = aid;
        this.aname = aname;
        this.pic = pic;
        this.company = company;
        this.pdate = pdate;
        this.lang = lang;
    }

    public Album() {}
}