package com.jiao.spit.service;

import com.jiao.spit.dao.SpitDao;
import com.jiao.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * Created by jiao on 12/10/2018.
 */
@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查找全部
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 根据id查找一个
     */
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }
    /**
     * 增加
     */
    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+""); // 设置主键值
        spitDao.save(spit);
    }
    /**
     * 修改
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }
    /**
     * 删除
     */
    public void deleteById(String id){
        spitDao.deleteById(id);
    }
    /**
     * 点赞吐槽
     * 两种方法
     */
    public void thumbup(String id){
        /**
         * 传统方法 两次操作数据库，效率不高
         */
        //Spit spit = spitDao.findById(id).get();
        //Integer thumbup = spit.getThumbup();
        //spit.setThumbup((thumbup == null?0:thumbup)+1);
        //spitDao.save(spit);
        /**
         * 利用mongdb的自增 ，只要操作一次数据库
         */
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
    /**
     * 根据上级ID查询吐槽数据（分页）
     */
    public Page<Spit> commment(String parentid ,  int page , int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Spit> spits = spitDao.findByParentid(parentid, pageable);
        return spits;
    }
}
