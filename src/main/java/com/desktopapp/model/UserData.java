package com.desktopapp.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "UserData")
public class UserData {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "UserName")
    private String username;
    @Column(name = "UserPass")
    private String userpass;
    @Column(name = "UserCPF")
    private String usercpf;
    @Column(name = "UserBirthDate")
    private Date userbirthdate;
    @Column(name = "UserPhone")
    private String userphone;
    @Column(name = "UserEmail")
    private String useremail;
    @Column(name = "UserBalance")
    private Double userbalance;

    private boolean isShowing;

    public UserData(String username, String userpass, String usercpf, Date userbirthdate, String userphone, String useremail) {
        this.username = username;
        this.userpass = userpass;
        this.usercpf = usercpf;
        this.userbirthdate = userbirthdate;
        this.userphone = userphone;
        this.useremail = useremail;
        this.userbalance = 0.0;

        this.isShowing = false;
    }


    public boolean getIsShowing() {
        return isShowing;
    }
    public void setIsShowing(boolean isShowing) {
        this.isShowing = isShowing;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserpass() {
        return userpass;
    }
    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }
    public String getUsercpf() {
        return usercpf;
    }
    public void setUsercpf(String usercpf) {
        this.usercpf = usercpf;
    }
    public Date getUserbirthdate() {
        return userbirthdate;
    }
    public void setUserbirthdate(Date userbirthdate) {
        this.userbirthdate = userbirthdate;
    }
    public String getUserphone() {
        return userphone;
    }
    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }
    public String getUseremail() {
        return useremail;
    }
    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
    public Double getUserbalance() {
        return userbalance;
    }
    public void setUserbalance(Double userbalance) {
        this.userbalance = userbalance;
    }

}

