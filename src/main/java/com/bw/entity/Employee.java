package com.bw.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lenovo on 2017/7/27.
 */

@Data
@Table(name="t_employee")
@Entity
public class Employee implements Serializable{

    @Id
    @GeneratedValue
    private Integer eid;
    private String ename;


}
