package com.bw.repository;

import com.bw.entity.Redis;
import com.bw.entity.Ttree;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.naming.Name;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lenovo on 2017/7/27.
 */

@CacheConfig(cacheNames = "ztree")
public interface TreeRepository extends JpaRepository<Ttree,Integer>{

    //将查询出来的集合存到redis缓存中
    @Cacheable
    List<Ttree> findAll();

}
