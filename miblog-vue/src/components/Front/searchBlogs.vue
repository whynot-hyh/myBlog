<template>
    <div class="whitebg bloglist">
        <div class="tab_box whitebg">
            <span>分类</span>
            <div class="tab_buttons">
                <ul>
                    <li  v-for="item in type" @click="typeList(item.id)">{{item.typeName}}</li>
                </ul>
            </div>
        </div>
        <h2 class="htitle">搜索结果</h2>
        <ul>
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
    import {search} from "../../api/article";
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
                    search:'',
                    typeId:'',
                    index: 1,
                    size:10
                },
                pageEntity:{
                    search:'',
                    nickname:'',
                    index:1,
                    size:10
                },
                truth:false,
                total:0,
                page:1,
                typeId:''
            }
        },
        methods:{
            DeparthandleCurrentChange(val){
                this.requestArticle.page=val
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
                console.log('search')
                console.log(article)
                axiosIstance.post('/search',article).then(data=>{
                    this.article=data.data.list
                    this.total=data.data.total
                })
                // search(article).then(data=>{
                //     console.log('aaa')
                //     console.log(data)
                //     this.article=data.data.list
                //     this.total=data.data.total
                // })
            }
        },
        created() {
            console.log('search')
            console.log(this.$route.query.title)
            all().then(data=>{
                console.log(data)
                this.type=data.data
            })
            console.log(this.$route.query.title)
            console.log(this.$route.query.nickname)
            this.pageEntity.search=this.$route.query.title
            this.pageEntity.nickname=this.$route.query.nickname
            this.list(this.pageEntity)
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
