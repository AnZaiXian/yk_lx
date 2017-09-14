package com.bw.repository;

import com.bw.entity.Clockingin;
import com.bw.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2017/7/27.
 */
public interface ClockingInRepository extends JpaRepository<Clockingin,Integer>{

    /**
     * 查询每个员工的出勤情况
     */
    @Query(value="select c.*,e.ename from t_clockingin c,t_employee e where e.eid=c.employee_eid",nativeQuery = true)
    List<Clockingin> selectAll();
    /**
     * 修改好友的信息
     * @Transactional注解 添加事物的注解,对查询超时的时设置
     *  @Modifying 他是API在执行增删改时需要加载@query()注解上的
     */
    @Transactional
    @Modifying
    @Query("update Clockingin f set f.starttime = ?1,f.endtime=?2,f.daytime=?3 ,f.employee.eid=?4 where f.cid=?5")
    void updateById(String starttime, String endtime, String daytime,  Integer eid, Integer cid);

    //update t_clockingin set starttime='09:00:00',endtime='22:00:00'
    // ,daytime='2017-07-28',employee_eid=3 where cid =3



}
