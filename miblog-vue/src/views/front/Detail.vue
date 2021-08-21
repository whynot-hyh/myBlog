<template>
  <div class="detail-class">
      <Header></Header>
    <!--top end-->
    <article>
        <!--lbox begin-->
        <div class="lbox">
            <div class="content_box whitebg">
                <h2 class="htitle"><span class="con_nav">您现在的位置是：<a href="/">MIBlog</a>><a href="/">{{article.title}}</a></span>阅读文章</h2>
                <h1 class="con_tilte">{{article.title}}</h1>
                <p class="bloginfo"><i class="avatar"><img :src="user.avatar"></i><span>{{user.nickname}}</span><span>{{article.createTime}}</span><span>【<a href="/">{{type.typeName}}</a>】</span><span>{{article.view}}人已围观</span>
                    <router-link v-if="Like===0" to=""><span @click="like" class=" iconfont icon-dianzan"></span>{{likeCount}}</router-link>
                    <router-link v-else to=""><span @click="unlike" class=" iconfont icon-dianzan1">{{likeCount}}</span></router-link>
                </p>
                <p class="con_info"><b>简介</b>{{article.summary}}</p>
                <div class="con_text">
                    <v-md-preview :text="article.content"></v-md-preview>
                </div>
            </div>

                <div id="comments" class="comments-area" >
            <ol class="comment-list">
                <li class="comments-anchor">
                    <div id="respond" class="comment-respond">
                        <h3 id="reply-title" class="comment-reply-title"><span id="reply-title-word">发表评论</span></h3>
                        <Form  @after="after" ></Form>
                    </div>
                </li>

                <li class="comment fadeInUp" v-for=" item in comment" id="comment-2044" style="height: 100%" :key="item.id">
                    <div id="div-comment-2044" class="comment-body">
                        <div class="comment-author vcard">
                            <img v-if="item.user.avatar!==null && item.user.avatar!=='' " class="avatarss" :src="item.user.avatar" alt="avatar">
                            <img v-else class="avatarss" src="https://oss.liuyanzhao.com/uploads/admin/2020/9/3717d475b27d4c22b82c24ec82d2a016.gif" alt="avatar">
                            <div  style="margin-left: 90px">
                                <a href="/author/aaaaaaaaaaaa@qq.com.html" target="_blank"><strong>{{item.user.nickname}}</strong></a>
                                <span class="comment-meta commentmetadata">
                                                    <a href="#comment-2383"></a><br>
                                                    <span class="comment-aux">
                                                    {{item.description}}
                                                    </span>
                                                </span>
                                <p style="float: right">
                                    <el-button size="mini" @click="revert(item)" icon="el-icon-chat-dot-round" title="回复"></el-button>
                                </p>
                                <p class="comment-p">{{item.gmtCreate}}</p>
                            </div>
                            <ol v-if="item.children[0]!==null">
                                <li style="height: 50px ;margin-left: 80px; margin-top: 20px;"  v-for="children in item.children"  :key="children.id">
                                    <div class="comment-body" >
                                        <div class="comment-author vcard">
                                            <img v-if="children.user.avatar!==null && children.user.avatar!=='' " class="avatarss" :src="children.user.avatar" alt="avatar">
                                            <img v-else class="avatarss" src="https://oss.liuyanzhao.com/uploads/admin/2020/9/3717d475b27d4c22b82c24ec82d2a016.gif" alt="avatar">
                                            <div  style="margin-left: 90px">
                                                <a href="/author/aaaaaaaaaaaa@qq.com.html" target="_blank"><strong>{{children.user.nickname}}</strong> <strong>回复{{item.user.nickname}}</strong></a>
                                                <span class="comment-meta commentmetadata">
                                                    <a href="#comment-2383"></a><br>
                                                    <span class="comment-aux">
                                                    {{children.description}}
                                                    </span>
                                                </span>
                                                <p class="comment-meta"> {{children.gmtCreate}}</p>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ol>
                        </div>

                        <el-dialog :visible.sync="AddCommentVisble" v-if="AddCommentVisble" :close-on-click-modal="false" title="回复">
                            <reverForm  @after="after"  :data="commentData" @hideDialog="hidden"></reverForm>
                        </el-dialog>
                    </div>
                    <div id="anchor-2044"></div>
                    <ul class="children">
                    </ul>
                </li>
            </ol>
        </div>
            </div>
        <!--lbox end-->
        <Right></Right>
    </article>
    <footer>
        <div class="box">
            <div class="wxbox">
                <ul>
                    <li><img src="images/wxgzh.jpg"><span>微信公众号</span></li>
                    <li><img src="images/wx.png"><span>我的微信</span></li>
                </ul>
            </div>
            <div class="endnav">
                <p><b>站点声明：</b></p>
                <p>1、本站个人博客模板，均为杨青青本人设计，个人可以使用，但是未经许可不得用于任何商业目的。</p>
                <p>2、所有文章未经授权禁止转载、摘编、复制或建立镜像，如有违反，追究法律责任。举报邮箱：<a href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=HHh9cn95b3F1cHVye1xtbTJ-c3E" target="_blank">dacesmiling@qq.com</a></p>
                <p>Copyright © <a href="http://www.yangqq.com" target="_blank">www.yangqq.com</a> All Rights Reserved. 备案号：<a href="http://www.miitbeian.gov.cn/" target="_blank">蜀ICP备11002373号-1</a></p>
            </div>
        </div>
        <a href="#">
            <div class="top"></div>
        </a> </footer>
  </div>
</template>

<script>
    import '@/assets/css/base.css'
    import '@/assets/css/m.css'
    import '@/assets/css/icon.css'
    import {detail,cat} from "../../api/article";
    import Header from "../../components/Front/Header";
    import Form from "../../components/Front/From";
    import reverForm from "../../components/Front/reverForm";
    import Right from "../../components/Front/Right";
    import axiosIstance from "../../utils/request";
    import token from "../../../public/js/token";
    import {getArticle} from "../../api/comment";

    export default {
        name: "Detail",
        components: {Right, Form, Header,reverForm},
        data(){
            return{
                article:[],
                user:[],
                comment:[],
                commentData:[],
                type:[],
                requestArticle:{
                    page :1
                },
                thumb:{
                    articleId:'',
                    userId:''
                },
                Like: 0,
                likeCount:'',
                AddCommentVisble: false
            }
        },
        methods:{
            hidden(){
                this.AddCommentVisble=false
            },
            after(){
                this.commentList()
            },
            revert(row){
                this.commentData=row
                this.AddCommentVisble=true
            },
            like(){
                if (token.get()===null){
                    this.$message.error('登录以后才能点赞')
                    this.$router.push('/login')
                }else {
                    this.thumb.articleId=this.$route.params.id
                    this.thumb.userId=token.getUser().userId
                    axiosIstance.post('/thumb/like',this.thumb).then(()=>{
                        this.$message.success('已点赞成功')
                        this.count()
                        this.isLike()
                    })
                }
            },
            unlike(){
                this.thumb.articleId=this.$route.params.id
                this.thumb.userId=token.getUser().userId
                axiosIstance.post('/thumb/unlike',this.thumb).then(()=>{
                    this.$message.success('已取消点赞')
                    this.count()
                    this.isLike()
                })
            },

            detail(id){
                cat(id).then(data=>{
                    this.article=data.data
                    this.user=data.data.user
                    this.type=data.data.type
                })
            },
            commentList(){
                getArticle(this.$route.params.id).then(data=>{
                    console.log('comment')
                    console.log(data)
                    this.comment=data.data
                })
            },
            count(){
                axiosIstance.get('thumb/count?articleId='+this.$route.params.id).then(data=>{
                    this.likeCount=data.data
                })
            },
            isLike(){
                if (token.get()!==null){
                    axiosIstance.get('thumb/getIsLike?articleId='+this.$route.params.id+"&userId="+token.getUser().userId).then(data=>{
                        this.Like=data.data
                    })
                }
            }
        },
        created() {
            this.detail(this.$route.params.id)
            this.count()
            this.isLike()
            this.commentList()

        }
    }
</script>

<style scoped>
    .comment-list li {
        position: relative;
        background: #fff;
        margin: 0 0 10px 0;
        padding: 15px 20px 30px 20px;
        border: 1px solid #ddd;
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.04);
        border-radius: 2px;
        word-wrap: break-word;
        word-break: break-all;
    }


    .reply a {
        background: #fff;
        line-height: 25px;
        display: block;
        padding: 0 10px;
        border: 1px solid #ddd;
        border-radius: 2px;
    }

    .avatarss{
        position: relative;
        float: left;
        width: 60px;
        height: 60px;
        padding: 5px;
        display: block;
        background: -webkit-linear-gradient(bottom left, rgba(0,153,255,.9),
        rgba(42,228,197,.7)); background: -o-linear-gradient(bottom left, rgba(0,153,255,.9), rgba(42,228,197,.7)); background: linear-gradient(to top right, rgba(0,153,255,.9), rgba(42,228,197,.7));
    }

    #respond {
        background: #fff;
        margin: 0 0 10px 0;
        padding: 10px 20px;
        border: 1px solid #ddd;
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.04);
        border-radius: 2px;
    }

    .comment-meta{
        line-height: 0px;
        color: #1f022a;
        font-weight: normal;
        position: relative;
        margin-bottom: 10px;
    }

    .comment-p{
        color: #000000;
        font-weight: normal;
        position: relative;
        margin-bottom: 10px;
    }
    .detail-class{
        margin: 0px;padding: 0px;
        position: absolute;
        background: #ffffff;
        top: 0px;
        left: 0;
        right: 0;
        background: url("https://linxuan.fun/images/bg/教室近1.png") center center /
    cover no-repeat;
        background-color: #49b1f5;
        background-attachment: fixed;
        color: #fff !important;
        animation: header-effect 1s !important;
    }
</style>
