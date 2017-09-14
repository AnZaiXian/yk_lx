package com.bw.repository;

import com.bw.entity.Redis;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by lenovo on 2017/7/12.
 */
//@Transactional
@CacheConfig(cacheNames = "userMapper")
public interface ReadisRepository extends JpaRepository<Redis,Integer> {

    @Cacheable(key = "#p0")
    Redis findUserByName(String userName);

}
