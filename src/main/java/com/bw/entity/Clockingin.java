package com.bw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2017/7/27.
 */
@Data
@Table(name="t_clockingin")
@Entity
public class Clockingin implements Serializable{

    @Id
    @GeneratedValue
    private Integer cid;
    private String starttime;
    private String endtime;
    private String daytime;
    //private Integer eid;
    //单向一对一
    @OneToOne
    private Employee employee;


}
