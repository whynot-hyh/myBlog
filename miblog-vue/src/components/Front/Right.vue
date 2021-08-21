<template>
    <div class="rbox">
        <div class="card">
            <h2>我的名片</h2>
            <p>网名：DanceSmile | 即步非烟</p>
            <p>职业：Web前端设计师、网页设计</p>
            <p>现居：四川省-成都市</p>
            <p>Email：dancesmiling@qq.com</p>
            <ul class="linkmore">
                <li><a href="http://www.yangqq.com" target="_blank" class="iconfont icon-zhuye" title="网站地址"></a></li>
                <li><a href="http://wpa.qq.com/msgrd?v=3&uin=476847113&site=qq&menu=yes" target="_blank" class="iconfont icon---" title="QQ联系我"></a></li>
                <li id="weixin"><a href="#" target="_blank" class="iconfont icon-weixin" title="关注我的微信"></a><i><img src="images/wx.png"></i></li>
            </ul>
        </div>
        <div class="whitebg paihang">
            <h2 class="htitle">点击排行</h2>
            <section class="topnews imgscale"><a href="/"><img src="@/assets/images/right.jpg"><span></span></a></section>
            <ul>
                <li v-for=" item in article"><i></i><router-link :to='"/detail/"+item.id' target="_blank">{{item.title}}</router-link></li>
            </ul>
        </div>
        <div class="whitebg wenzi">
            <h2 class="htitle">最近发布</h2>
            <ul>
                <li v-for="item in latelyArticle"><router-link :to='"/detail/"+item.id' target="_blank">{{item.title}}!</router-link></li>
            </ul>
        </div>
        <div class="whitebg tongji">
            <h2 class="htitle">站点信息</h2>
            <ul class="right-platform">
                <li><b>建站时间</b>：2021年4月26日</li>
                <li><b>网站程序</b>：小杰博客</li>
                <li><b>文章统计</b>：{{articleCount}}条</li>
                <li><b>文章评论</b>：{{commentCount}}条</li>
            </ul>
        </div>
    </div>
</template>

<script>
    import {getArticleView,count,get10Article} from "../../api/article"

    export default {
        name: "Right",
        data(){
            return{
                article:[],
                message:[],
                latelyArticle:[],
                requestArticle:{
                  page :1
                },
                articleCount:'',
                commentCount:''
            }
        },
        created() {
            getArticleView().then(data=>{
              this.article=data.data.list
          })
            count().then(data=>{
                this.articleCount=data.data.articleCount
                this.commentCount=data.data.commentCount
            })
            get10Article().then(data=>{
                this.latelyArticle=data.data.list
            })
        }
    }
</script>

<style scoped>
    .right-platform{
        font-size: 16px;
        line-height: 40px;
        border-bottom: 1px solid #eee;
        color: #484848;
        font-weight: normal;
        position: relative;
        margin-bottom: 10px;
    }

</style>
