package com.cenlly.domain;

public class VideoItem {
    private int id;
    private String name;
    private String cover;
    private String view_count;
    private String like_count;
    private String duration;
    private String author_name;
    private int author_id;
    private String bvid;
    private String card_type;
    private String sort;
    private int filt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getBvid() {
        return bvid;
    }

    public void setBvid(String bvid) {
        this.bvid = bvid;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getFilt() {
        return filt;
    }

    public void setFilt(int filt) {
        this.filt = filt;
    }

    @Override
    public String toString() {
        return "VideoItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", view_count='" + view_count + '\'' +
                ", like_count='" + like_count + '\'' +
                ", duration='" + duration + '\'' +
                ", author_name='" + author_name + '\'' +
                ", author_id=" + author_id +
                ", bvid='" + bvid + '\'' +
                ", card_type='" + card_type + '\'' +
                ", sort='" + sort + '\'' +
                ", filt=" + filt +
                '}';
    }
}
