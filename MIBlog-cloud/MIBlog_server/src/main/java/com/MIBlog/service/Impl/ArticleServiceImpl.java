package com.MIBlog.service.Impl;

import com.MIBlog.VO.ArticleVO;
import com.MIBlog.converter.ArticleConverter;
import com.MIBlog.dao.ArticleMapper;
import com.MIBlog.dao.TypeMapper;
import com.MIBlog.dao.UserMapper;
import com.MIBlog.dataobject.Article;
import com.MIBlog.dataobject.Comment;
import com.MIBlog.dataobject.Photo;
import com.MIBlog.dataobject.User;
import com.MIBlog.entity.PageArticleVO;
import com.MIBlog.exmple.ArticleExample;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.exmple.UserExample;
import com.MIBlog.service.ArticleService;
import com.MIBlog.tunnel.ArticleTunnel;
import com.MIBlog.tunnel.CommentTunnel;
import com.MIBlog.tunnel.PhotoTunnel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("all")
public class ArticleServiceImpl implements ArticleService {

    private ArticleMapper articleMapper;

    private ArticleConverter articleConverter;

    private ArticleTunnel articleTunnel;

    private CommentTunnel commentTunnel;

    private UserMapper userMapper;

    private TypeMapper typeMapper;

    private PhotoTunnel photoTunnel;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleConverter articleConverter, ArticleTunnel articleTunnel, CommentTunnel commentTunnel, UserMapper userMapper, TypeMapper typeMapper, PhotoTunnel photoTunnel) {
        this.articleMapper = articleMapper;
        this.articleConverter = articleConverter;
        this.articleTunnel = articleTunnel;
        this.commentTunnel = commentTunnel;
        this.userMapper = userMapper;
        this.typeMapper = typeMapper;
        this.photoTunnel = photoTunnel;
    }

    @Override
    public int insert(Article article) {
        article.setView(0);
        article.setTop(0);
        article.setGmtCreate(new Date());
        return articleMapper.insert(article);
    }

    @Override
    public PageArticleVO getArticleList(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andUserIdEqualTo(pageEntity.getUserId());
        //查询出数据
        List<Article> articles = articleMapper.selectByExampleWithBLOBs(example);
        //查询用户和类型
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
       return articleConverter.converter(articles,pageInfo);
    }

    @Override
    public PageArticleVO getList(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andStatusEqualTo(0);
        //查询出数据
        List<Article> articles = articleMapper.selectByExampleWithBLOBs(example);
        PageInfo<Article> page = new PageInfo<>(articles);
        //查询用户和类型
        return articleConverter.converter(articles,page);
    }

    @Override
    public PageArticleVO getSearch(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
            ArticleExample example = new ArticleExample();
            example.createCriteria()
                    .andTitleLike("%"+pageEntity.getSearch()+"%");
        List<Article> articles = articleMapper.selectByExampleWithBLOBs(example);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        return articleConverter.converter(articles,pageInfo);
    }

    @Override
    public PageArticleVO getTypeArticle(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        ArticleExample example = new ArticleExample();
        example.createCriteria()
                .andTypeIdEqualTo(pageEntity.getTypeId());
        List<Article> articles = articleMapper.selectByExampleWithBLOBs(example);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        return articleConverter.converter(articles,pageInfo);
    }

    @Override
    public PageArticleVO getArticleView() {
        PageHelper.startPage(1,10);
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("view desc");
        example.createCriteria();
        List<Article> articles = articleMapper.selectByExample(example);
        PageInfo<Article> pageInfo = new PageInfo<>();
        return articleConverter.converter(articles,pageInfo);
    }

    @Override
    public PageArticleVO get10Article() {
        PageHelper.startPage(1,8);
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("id desc");
        example.createCriteria();
        List<Article> articles = articleMapper.selectByExample(example);
        PageInfo<Article> pageInfo = new PageInfo<>();
        return articleConverter.converter(articles,pageInfo);
    }

    @Override
    public List<ArticleVO> getTops() {
        articleTunnel.queryTopList();
         List<ArticleVO> articleVOS = articleConverter.converter(articleTunnel.queryTopList()).stream().map(x->{
            x.setUser(userMapper.selectByPrimaryKey(x.getUserId()));
            x.setType(typeMapper.selectByPrimaryKey(x.getTypeId()));
            if (x.getStatus()==1){
                x.setStatusName("草稿");
            }else {
                x.setStatusName("已发布");
            }
            return x;
        }).collect(Collectors.toList());
         return articleVOS;
    }

    @Override
    public List<Integer> getDataViews() {
        List<Integer> data = new ArrayList<>();
        //查询出所有的文章
        List<Article> articles = articleTunnel.queryArticles();
        Integer view=0;
        //查询所有的浏览量
        for (Article article : articles) {
            view+=article.getView();
        }
        List<Article>  tops = articles.stream().filter(a -> a.getTop() == 1).collect(Collectors.toList());
        List<Comment> comments = commentTunnel.queryCommentList();
        List<Photo> photos = photoTunnel.queryList();
        data.add(articles.size());
        data.add(view);
        data.add(tops.size());
        data.add(comments.size());
        data.add(photos.size());
        return data;
    }

    @Override
    public ArticleVO detail(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article,articleVO);
        articleVO.setUser(userMapper.selectByPrimaryKey(article.getUserId()));
        articleVO.setType(typeMapper.selectByPrimaryKey(article.getTypeId()));
        return articleVO;
    }

    @Override
    public ArticleVO cat(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        //更新视图数据
        article.setView(article.getView()+1);
        articleMapper.updateByPrimaryKey(article);

        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article,articleVO);
        articleVO.setUser(userMapper.selectByPrimaryKey(article.getUserId()));
        articleVO.setType(typeMapper.selectByPrimaryKey(article.getTypeId()));
        return articleVO;
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageArticleVO getUserAndTitleSearch(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());

        //查询出所有的文章
        ArticleExample example = new ArticleExample();
        example.createCriteria().andTitleLike("%"+pageEntity.getSearch()+"%");
        List<Article> articles = articleMapper.selectByExampleWithBLOBs(example);
        //查询用户
        UserExample example1 = new UserExample();
        example1.createCriteria().andNicknameLike("%"+pageEntity.getNickname()+"%");
        List<User> users = userMapper.selectByExample(example1);

        List<Article> list = new ArrayList<>();

//        users.stream().map(x -> {
//            List<Article> search = articles.stream().filter(y -> x.getUserId().equals(y.getUserId())).collect(Collectors.toList());
//            return search;
//        }).collect(Collectors.toList());
        List<Article> collect = articles.stream().map(x -> {
//            if (users.stream().anyMatch(a -> a.getUserId().equals(x.getUserId()))) {
//                list.add(x);
//            }
            users.stream().map(y->{
                if (x.getUserId().equals(y.getUserId())){
                    list.add(x);
                }
                return y;
            }).collect(Collectors.toList());
            return x;
        }).collect(Collectors.toList());

        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return articleConverter.converter(list,pageInfo);
    }

    @Override
    public PageArticleVO getUserArticle(PageEntity pageEntity) {
        UserExample example = new UserExample();
        example.createCriteria().andNicknameLike(pageEntity.getNickname());
        List<User> users = userMapper.selectByExample(example);
        List<String> userIds = new ArrayList<>();

        users.stream().map(x->{
            userIds.add(x.getUserId());
            return x;
        }).collect(Collectors.toList());
        List<Article> articleList = articleTunnel.queryUserIds(userIds);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return articleConverter.converter(articleList,pageInfo);
    }

    @Override
    public int update(Article article) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andIdEqualTo(article.getId());
        return articleMapper.updateByExampleSelective(article,example);
    }

    @Override
    public Integer count() {
        //查询出所有文章数量
        ArticleExample example = new ArticleExample();
        example.createCriteria();
        return articleMapper.selectByExample(example).size();
    }

    @Override
    public void delete(Integer id) {
        articleMapper.deleteByPrimaryKey(id);
    }



}
