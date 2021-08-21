<template>
    <div class="whitebg bloglist">
        <div class="tab_box whitebg">
            <span>分类</span>
            <div class="tab_buttons">
                <ul style="">
                    <li  v-for="item in type" @click="typeList(item.id)">{{item.typeName}}</li>
<!--                    aaa-->
                </ul>
            </div>
        </div>
        <h2 class="htitle">最新博文ass</h2>
        <ul>
            <li v-for="(item,index) in articleTops" :key="index.id">
                <h3 class="blogtitle"><router-link :to='"/detail/"+item.id'  arget="_blank"><b>【置顶】</b>{{item.title}}</router-link></h3>
                <span class="blogpic imgscale"><i><router-link :to='"/detail/"+item.id'>{{item.type.typeName}}</router-link></i>
                    <router-link :to='"/detail/"+item.id' target="_blank">
                        <img v-if="item.imgurl==null ||item.imgurl=='' " width="130px" height="120px" src="http://shunxinblog.oss-cn-hangzhou.aliyuncs.com/2021/01/03/c188ca3b0ca94de7b0eaa6b2589659c2.jpg" alt="">
                        <img v-else  width="130px" height="130px"  :src="item.imgurl" alt="">
                    </router-link>
                </span>
                <p class="blogtext">{{item.summary}} </p>
                <p class="bloginfo">
                    <i class="avatar"><img :src="item.user.avatar"></i>
                    <span>{{item.user.nickname}}</span><span>{{item.gmtCreate}}</span></p>
                <router-link :to='"/detail/"+item.id' target="_blank"  class="viewmore"> 阅读更多</router-link >
            </li>
            <!--单图-->
            <li v-for="item in article" :key="item.id">
                <a href="#search" name="content"></a>
                <h3 class="blogtitle"><router-link :to='"/detail/"+item.id'target="_blank">{{item.title}}</router-link></h3>
                <span class="blogpic imgscale"><i><a href="/">{{item.type.typeName}}</a></i>
                     <router-link :to='"/detail/"+item.id' target="_blank">
                    <img v-if="item.imgurl!=='' && item.imgurl!==null"  height="120px" :src="item.imgurl" alt="">
                    <img v-else src="https://oss.liuyanzhao.com/slide/LawBBS.jpg" height="120px" alt="">
                    </router-link ></span>
                <p class="blogtext">{{item.summary}} </p>
                <p class="bloginfo"><i class="avatar"><img :src="item.user.avatar"></i><span>{{item.user.nickname}}</span><span>{{item.createTime}}</span></p>
                <router-link :to='"/detail/"+item.id'class="viewmore">阅读更多</router-link>
            </li>
        </ul>
        <el-pagination
                background
                layout="total,prev, pager, next,jumper"
                @current-change="DeparthandleCurrentChange"
                :current-page="page"
                :page-size="8"
                :total="total" style="padding-top: 15px">
        </el-pagination>
    </div>
</template>

<script>
    import {adminList} from "../../api/article";
    import {all} from "../../api/type";
    import axiosIstance from "../../utils/request";

    export default {
        name: "Blogs",
        props:['search','childevenTitle'],
        watch: {
            search: function(newVal,oldVal){
                this.article=newVal.records
                this.total=newVal.total
                this.truth=true
            },
            childevenTitle: function(newVal,oldVal){
                this.requestArticle.title=newVal
            },

        },
        data(){
            return{
                article:[],
                type:[],
                requestArticle:{
                    title:'',
                    typeId:'',
                    index: 1,
                    size:10
                },
                articleTops:[],
                truth:false,
                total:0,
                page:1,
                typeId:''
            }
        },
        methods:{
            DeparthandleCurrentChange(val){
                this.requestArticle.index=val
                this.list(this.requestArticle)
            },
            typeList(id){
                this.requestArticle.typeId=id
                axiosIstance.post("article/getTypeArticle",this.requestArticle).then(data=>{
                    this.article=data.data.list
                    this.total=data.data.total
                })
            },
            list(article){
                adminList(article).then(data=>{
                    console.log(data)
                    this.article=data.data.list
                    this.total=data.data.total
                })
            },
            tops(){
                axiosIstance.get('/article/getTops').then(data=>{
                    console.log('tops')
                    console.log(data)
                    this.articleTops=data.data
                })
            }
        },
        created() {
            all().then(data=>{
                console.log(data)
                this.type=data.data
            })
            this.list(this.requestArticle)
            this.tops();
        }
    }
</script>

<style scoped>
    .bloglist> li {
        overflow: hidden;
        margin-bottom: 20px;
        border-bottom: #eee 1px dashed;
        padding-bottom: 20px;
        position: relative;
        min-height: 120px;
    }
</style>
