package com.bw.controller;

import com.bw.entity.Clockingin;
import com.bw.entity.Redis;
import com.bw.entity.Ttree;
import com.bw.repository.ClockingInRepository;
import com.bw.repository.EmployeeRepository;
import com.bw.repository.ReadisRepository;
import com.bw.repository.TreeRepository;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/7/27.
 */

@Controller
public class ClockController {

    //创建ztree的jpa对象
    @Autowired
    private TreeRepository treeRepository;
    //创建clock的jpa对象
    @Autowired
    private ClockingInRepository clockingInRepository;
    //创建EmployeeRepository的jpa对象
    @Autowired
    private EmployeeRepository employeeRepository;
    //创建对配置文件的密码加密属性
    @Autowired
    private StringEncryptor stringEncryptor;
    //创建redis的jpa对象
    @Autowired
    private ReadisRepository readisRepository;
    //redis自带的类
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * readis测试RedisTemplate
     */
    @RequestMapping("/selectUserByName")
    @ResponseBody
    public Redis selectUserByName(String name){
        Redis user = null;
        // 在RedisTemplate中，已经提供了一个工厂方法:opsForValue()。这个方法会返回一个默认的操作类
        //这给个类对redis中的数据进行一定的操作
        ValueOperations<String,Redis> operations = redisTemplate.opsForValue();
        //在RedisTemplate类中，提供了判断键是否在换粗中存在的方法
        Boolean exists = redisTemplate.hasKey("user");

        if(exists){
            //如果该键在缓存中存在就取出来
            user =  operations.get("user");
            System.out.println("exists is true" + user.getName());

        }else{

            //如果redis缓存中不存在,就在从数据库中进行查询,存到redis的缓存中
             user = readisRepository.findUserByName(name);
            //修改寸处对象的键值
            operations.set("user",user);
            System.out.println("exists is false");
        }
        return user;

    }

    //redis的第一次访问,即将查出来的数据存到redis缓存中
    @RequestMapping("/getUserName")
    @ResponseBody
    public Redis getUserName(String name){
        //第一次根据用户名查询,存到redis缓存中
        System.out.println(name);
        return  readisRepository.findUserByName(name);
    }




    /**
     * 给配置文件的密码加密==StringEncryptor
     * 在pom文件中导入Java Simplified Encryption加密的依赖
     * 次依赖提供了一个加密的类==>StringEncryptor,通过该类的encrypt方法给密码加密
     */
    @RequestMapping("ENC")
    @ResponseBody
    public String enc(){
        String pwd = stringEncryptor.encrypt("root");
        return  pwd;
    }



    /**
     * 修改好修的信息
     */
    @RequestMapping("updateEmployee")
    public String updateFriends(Clockingin c){
        System.out.println("======修改好修的信息========");
        System.out.println(c);
        //void updateById(String starttime, String endtime, Date daytime,  Integer eid, Integer cid);
        clockingInRepository.updateById(c.getStarttime(),c.getEndtime(),c.getDaytime(),c.getEmployee().getEid(),c.getCid());
        return "redirect:selectemploy";

    }


    /**
     * 根据id查询对象
     */
    @RequestMapping("hxEmployee")
    @ResponseBody
    public Clockingin hxFriends(Integer cid){
        System.out.println("=======根据id查询对象==========="+cid);
        Clockingin one = clockingInRepository.findOne(cid);
        System.out.println(one);
        return  one;
    }

    /**
     * 删除好友
     */
    @RequestMapping("deleteUser")
    public String deleteUser(Integer id){
        System.out.println("==========删除方法=====");
        //调用删除的方法
        clockingInRepository.delete(id);
        //重定向到查询方法
        return  "redirect:selectemploy";
    }


    /**
     * 查询每个员工的出勤情况
     */
    @RequestMapping("selectemploy")
    public String selectEmploy(Map<String,Object> map){
        List<Clockingin> list = clockingInRepository.selectAll();
        for(Clockingin c: list){
            System.out.println(c);
        }
        map.put("list",list);
        return "showEmploy";
    }


    /**
     * 进入到创建页面布局的html
     */
    @RequestMapping("tree")
    public String totree(){
        return "toFreamSet";
    }


    /**
     * 查询所有的ztree的数据
     */
    @RequestMapping("ztree")
    @ResponseBody
    public List<Ttree> getTree(){

        List<Ttree> all = treeRepository.findAll();
        for (Ttree t : all){
            System.out.println(t);
        }
        return  all;
    }


}
