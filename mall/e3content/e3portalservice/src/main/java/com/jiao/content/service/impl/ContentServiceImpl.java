package com.jiao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiao.common.jedis.JedisClient;
import com.jiao.common.pojo.DataGridResult;
import com.jiao.common.utils.JsonUtils;
import com.jiao.common.utils.TaotaoResult;
import com.jiao.content.service.ContentService;
import com.jiao.mapper.TbContentMapper;
import com.jiao.pojo.TbContent;
import com.jiao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by jiao on 2018/11/14.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;

    // 绑定内容列表在缓存中的key
    @Value("${TbContent_List_Key}")
    private String TbContent_List_Key;
    /**
    * @Description:  tbContent表的分页查询
    * @Param: [page, rows]
    * @return: com.jiao.common.pojo.DataGridResult
    * @Author: Mr.Jiao
    * @Date: 2018/11/14
    */
    public DataGridResult getContentList(long categoryId ,int page, int rows) {
        PageHelper.startPage(page,rows);
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);
        PageInfo<TbContent> tbContentPageInfo = new PageInfo<>(tbContents);
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setTotal(tbContentPageInfo.getTotal());
        dataGridResult.setRows(tbContents);
        return dataGridResult;
    }
    /**
     tbContent = {TbContent@7779}
     id = null
     categoryId = {Long@7804} "89"
     title = "3434"
     subTitle = "434"
     titleDesc = "23423"
     url = "43432"
     pic = "http://192.168.8.132:8888/group1/M00/00/00/wKgIhFvr5EmADRy2AAAQjLR_hEw999.jpg"
     pic2 = "http://192.168.8.132:8888/group1/M00/00/00/wKgIhFvr5E-AOMtRAAAQjLR_hEw996.jpg"
     created = null
     updated = null
     content = "43243"
     */

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        tbContentMapper.insert(tbContent);
        // 缓存同步
        jedisClient.hdel(TbContent_List_Key,tbContent.getCategoryId()+"");
        return TaotaoResult.ok();
    }

    /**
    * @Description: 根据分类categoryId查找内容列表
    * @Param: [categoryId]
    * @return: java.util.List<com.jiao.pojo.TbContent>
    * @Author: Mr.Jiao
    * @Date: 2018/11/14
    */
    /**
    * @Description: 添加缓存
    * @Param: [categoryId]
    * @return: java.util.List<com.jiao.pojo.TbContent>
    * @Author: Mr.Jiao
    * @Date: 11/15/2018
    */
    @Override
    public List<TbContent> getContentByCatId(long categoryId) {
        // 首先查缓存 ，查操作缓存可能出现异常，不能影响正常的工作流程，可以使用try-catch
        try {
            String json = jedisClient.hget(TbContent_List_Key,categoryId+"");
            if(StringUtils.isNotBlank(json)){
                // 将json转换成列表返回
                return JsonUtils.jsonToList(json,TbContent.class);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        // 如果缓存中有，就直接返回
        // 如果没有缓存，就查询数据库
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(tbContentExample);
        // 如果没有缓存，就查询数据库,返回结果之前，将结果添加到缓存
        try {
            jedisClient.hset(TbContent_List_Key,categoryId+"", JsonUtils.objectToJson(tbContents));
        }catch (Exception e){
            e.printStackTrace();

        }
        return tbContents;
    }
}
