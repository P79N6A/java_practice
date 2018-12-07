package com.jiao.ts_base.service;

import com.jiao.ts_base.dao.LableDao;
import com.jiao.ts_base.pojo.Label;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List<Label> findSearch(Label label){
        // lableDao.findAll();  // 不带条件差查询
        // lableDao.findAll(Specification specification); // 带条件查询
        // lableDao.findAll(PageLabel pageLabel); // 分页查询
        //  lableDao.findAll(Specification specification,PageLabel pageLabel); // 既带条件又分页查询
      return   lableDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是要把条件封装到哪个对象中，where 类名(table) = label.getId()
             * @param criteriaQuery 封装的都是查询的关键字，比如groupby，orderBy等,没什么用，如果要排序会选择直接写到sql语句中
             * @param criteriaBuilder 用来封装条件对象的，主要就是往这里面封装
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if(StringUtils.isNotBlank(label.getLabelname())){
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//where labelname like "%小明%"
                    list.add(predicate);
                }
                if (StringUtils.isNotBlank(label.getState())){
                    criteriaBuilder.equal(root.get("state").as(String.class),label.getState()); //where state = "1"
                }
                // 因为开始不知道数组的长度，可以先new一个集合来存放条件
                Predicate[] arr = new Predicate[list.size()];
                list.toArray(arr);
                return criteriaBuilder.and(arr); // 用and拼接条件
            }
        });

    }

    /**
     * 分页查询
     */
    public Page<Label> findPage(Label label , int page , int size){
        if (page<1)
            page = 1;
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Label> pages = lableDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是要把条件封装到哪个对象中，where 类名(table) = label.getId()
             * @param criteriaQuery 封装的都是查询的关键字，比如groupby，orderBy等,没什么用，如果要排序会选择直接写到sql语句中
             * @param criteriaBuilder 用来封装条件对象的，主要就是往这里面封装
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label
                            .getLabelname() + "%");//where labelname like "%小明%"
                    list.add(predicate);
                }
                if (StringUtils.isNotBlank(label.getState())) {
                    criteriaBuilder.equal(root.get("state").as(String.class), label.getState()); //where state = "1"
                }
                // 因为开始不知道数组的长度，可以先new一个集合来存放条件
                Predicate[] arr = new Predicate[list.size()];
                list.toArray(arr);
                return criteriaBuilder.and(arr); // 用and拼接条件
            }
        }, pageable);
        return pages;
    }
}
