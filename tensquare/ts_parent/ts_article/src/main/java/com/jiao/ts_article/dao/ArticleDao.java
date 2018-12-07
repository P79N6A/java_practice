package com.jiao.ts_article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jiao.ts_article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{
    /**
     * 文章审核
     *  springdata中增删改都需要增加 @Modifying 注解
     *  ?1 代表方法中的第一个形参
     */
    @Modifying
    @Query(value = "UPDATE tb_article SET state = 1 WHERE id = ?1" , nativeQuery = true)
    void examine(String id);

    /**
     * 文章点赞
     */
    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup = thumbup +1 WHERE id = ?1" , nativeQuery = true)
    void thunbup(String id);
}
