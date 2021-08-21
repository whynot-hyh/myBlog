<template>
    <div>
        <header id="header">
            <div class="navbox">
                <h2 id="mnavh"><span class="navicon"></span></h2>
                <div class="logo"><a href="/">MIBlogd</a></div>
                <nav>
                    <ul id="starlist">
                        <li><a href="index.html">首页</a></li>
<!--                        <li><router-link to="/time">归档</router-link></li>-->
                        <li v-if="this.verify"><router-link  to="/home">后台管理</router-link></li>
                        <li v-else><router-link to="/login">登录</router-link></li>
                        <li v-if="this.verify===false"><router-link to="/register">注册</router-link></li>
                    </ul>
                </nav>
                <div class="searchico"></div>
            </div>
        </header>
        <div class="searchbox">
            <div class="search">
                <el-form :inline="true" ref="article" :model="article" label-width="80px">
                    <el-form-item>
                        <el-input v-model="article.nickname" style="width: 100px" placeholder="作者昵称"></el-input>
                    </el-form-item>
                <el-form-item>
                    <el-input v-model="article.search" style="width: 330px" placeholder="按标题查询"></el-input>
                </el-form-item>
                <el-form-item>

                        <el-button icon="el-icon-search" @click="search" type="primary" >查询</el-button>
                </el-form-item>
            </el-form>
            </div>
            <div class="searchclose"></div>
        </div>
    </div>
</template>

<script>
    import $ from 'jquery'
    import {search} from "../../api/article";
    import {isToken} from "../../api/user";
    import token from "../../../public/js/token";
    export default {
        name: "Header",
        data(){
          return{
              article:{
                  nickname:'',
                  search:'',
                  index:1,
                  size:12
              },
              verify:false
          }
        },
        methods:{
            search(){
                let routeData =  this.$router.resolve({
                    path:'/search',
                    query:{
                        nickname:this.article.nickname,
                        title: this.article.search
                    }
                })
                window.open(routeData.href, '_blank');
            },
            list(param){
                search(param).then(data=>{
                    this.article=data.data.list
                    this.$emit('child-even',this.article)
                    this.$emit('child-evenTitle',this.article.title)
                    this.total=data.data.total
                }).catch(error=>{
                    this.$message.error(error)
                })
            },
        },
        mounted() {
            $("#mnavh").click(function() {
                $("#starlist").toggle();
                $("#mnavh").toggleClass("open");
            });

            //search
            $(".searchico").click(function() {
                $(".search").toggleClass("open");
            });
            //searchclose
            $(".searchclose").click(function() {
                $(".search").removeClass("open");
            });
        },
        created() {
            isToken(token.get()).then(data=>{
                this.verify=data.data
            })
        }
    }
</script>

<style scoped>

</style>
