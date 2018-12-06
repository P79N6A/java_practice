package com.jiao.ts_base.service;

import com.jiao.ts_base.dao.LableDao;
import com.jiao.ts_base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

/**
 * Created by jiao on 12/6/2018.
 */
@Service
@Transactional
public class LabelService {
    @Autowired
    LableDao lableDao;

    @Autowired
    IdWorker idWorker;

    public List<Label> findAll(){
        return lableDao.findAll();
    }

    public Label findById(String id){
        return lableDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        lableDao.save(label);
    }
    public void deleteById(String id){
        lableDao.deleteById(id);
    }
    public void update(Label label){
        lableDao.save(label);
    }
}
