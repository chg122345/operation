package org.jleopard.datam.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private String id;

    private String name;

    private String password;

    private Byte role; // 0 -> 学生 1 -> 老师

    private String phone;

    private String clbum;

    private String img;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created;

    private Integer teamId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getClbum() {
        return clbum;
    }

    public void setClbum(String clbum) {
        this.clbum = clbum == null ? null : clbum.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}