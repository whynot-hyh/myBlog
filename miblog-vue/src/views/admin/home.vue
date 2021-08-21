<template>
    <div id="welcome">
        <el-breadcrumb separator="/" style="padding-left:10px;padding-bottom:10px;font-size:12px;">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>欢迎</el-breadcrumb-item>
        </el-breadcrumb>
        <el-row :gutter="15">
            <!-- 左边部分 -->
            <el-col :span="13">
                <!-- 用户信息表格 -->
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>用户信息</span>
                    </div>
                    <el-tooltip class="item" effect="dark" content="你真好看" placement="top-start">
                        <el-avatar
                                shape="square"
                                :size="80"
                                :src="userInfo.avatar"
                                style="float:left;"
                                :key="1"
                        ></el-avatar>
                    </el-tooltip>
                    <div class="right" style="float:right;width:520px;">
                        <el-table :data="tableInfo" border height="100">
                            <el-table-column prop="username" label="用户账号"></el-table-column>
                            <el-table-column prop="nickname" label="用户昵称"></el-table-column>
                            <el-table-column prop="AdminName" label="身份"></el-table-column>
                        </el-table>
                    </div>
                </el-card>
                <!-- 功能列表 -->
                <el-row style="margin-top:10px;" :gutter="10">

                    <el-card style="margin-top: 10px">
                        <span style="float: left">我的近期文章</span>

                        <el-table
                                :data="article"
                                stripe
                                style="width: 100%">
                            <el-table-column
                                    prop="title"
                                    label="标题"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="like"
                                    label="点赞数"
                                    width="150">
                            </el-table-column>
                            <el-table-column
                                    prop="view"
                                    label="点击数"
                                    width="150">
                            </el-table-column>
                            <el-table-column
                                    prop="gmtCreate"
                                    label="发布时间">
                            </el-table-column>
                        </el-table>

                        <el-pagination
                                background
                                layout="total,prev, pager, next,jumper"
                                @current-change="DeparthandleCurrentChange"
                                :current-page="page"
                                :page-size="5"
                                :total="total" style="padding-top: 15px">
                        </el-pagination>
                    </el-card>
                </el-row>
            </el-col>
            <!-- 右边部分 -->
            <el-col :span="11">
                <div class="grid-content bg-purple">
                    <el-card style="min-height:700px;">
                        <el-carousel height="180px">
                            <el-carousel-item v-for="item in 3" :key="item"></el-carousel-item>
                        </el-carousel>
                        <aplayer
                                style="margin-top:20px;margin-bottom:30px;"
                                autoplay
                                :music="{
                  title: '给我一个理由忘记',
                  artist: 'A-Lin',
                  src: 'http://music.163.com/song/media/outer/url?id=25640799.mp3',
                  pic: 'http://p2.music.126.net/0POVOSSjqgVoOUGc5haWBQ==/109951163392311918.jpg'
              }"
                                :list="musicList"
                        ></aplayer>
                        <el-divider></el-divider>
                    </el-card>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
    import token from "../../../public/js/token";
    import {list} from "../../api/article";
    import {detail} from "../../api/user";
    import aplayer from "vue-aplayer";
    export default {
        components: {
            aplayer
        },
        data() {
            return {
                notice:[],
                userInfo: {},
                requestUser:{
                    id:''
                },
                article:[],
                requestArticle:{
                    userId:'',
                    index:1,
                    size:7
                },
                tableInfo: [],
                page: 1,
                total: 0,
                musicList:[{
                    "artist": "Eminem",
                    "lrc": "",
                    "title": "Airplanes",
                    "src": "http://music.163.com/song/media/outer/url?id=26714821.mp3",
                    "pic": "http://p4.music.126.net/H9HJibEzTL34aIT6nsqKsQ==/5682276092402519.jpg"
                },{
                    "artist": "Tinashe",
                    "lrc": "",
                    "title": "Story of Us",
                    "src": "http://music.163.com/song/media/outer/url?id=1403428061.mp3",
                    "pic": "http://p3.music.126.net/l2XttTpEa14IEZtUsQX1HA==/109951164486978461.jpg"
                }, {
                    "artist": "Chris Brown",
                    "lrc": "",
                    "title": "War For You",
                    "src": "http://music.163.com/song/media/outer/url?id=30431534.mp3",
                    "pic": "http://p3.music.126.net/YWkl1JXVKm7bOBAew72lGg==/109951163958771792.jpg"
                }, {
                    "artist": "Sarah Darling",
                    "lrc": "",
                    "title": "Jack of Hearts",
                    "src": "http://music.163.com/song/media/outer/url?id=19132440.mp3",
                    "pic": "http://p4.music.126.net/4q3kpn5VLo3x7hVWttj0QA==/109951164802108652.jpg"
                }, {
                    "artist": "Benjamin Ingrosso",
                    "lrc": "",
                    "title": "Costa Rica",
                    "src": "http://music.163.com/song/media/outer/url?id=1372897252.mp3",
                    "pic": "http://p4.music.126.net/mmm97zC81t73rToPFuXXnw==/109951164159882466.jpg"
                }, {
                    "artist": "Yo Trane",
                    "lrc": "",
                    "title": "Affection",
                    "src": "http://music.163.com/song/media/outer/url?id=1393553542.mp3",
                    "pic": "http://p4.music.126.net/T_vdbfQPO4HE4zVE_8rgCQ==/109951164389023010.jpg"
                }]
            };
        },
        methods: {
            DeparthandleCurrentChange(val){
                this.requestArticle.index=val
                this.listArticle(this.requestArticle)
            },
            listArticle(user){
                list(user).then(data=>{
                    console.log('list')
                    console.log(data)
                    this.article=data.data.list
                    this.total=data.data.total
                }).catch(error=>{
                    this.message.error(error)
                })
            }
        },

        created() {
            detail(token.getUser().userId).then(data=>{
                console.log('aaa')
                console.log(data)
                this.userInfo = data.data
                this.tableInfo.push({
                    username: this.userInfo.userName,
                    nickname: this.userInfo.nickname,
                    AdminName:this.userInfo.isAdminName
                });
            })
            this.requestArticle.userId=token.getUser().userId
            this.listArticle(this.requestArticle)
        }

    };
</script>

<style scoped>
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
        background-image: url("https://oss.liuyanzhao.com/slide/ForestBlog.jpg");
        background-size: 100% 100%;
    }

    .el-carousel__item:nth-child(2n + 1) {
        background-color: #d3dce6;
        background-image: url("https://oss.liuyanzhao.com/slide/SENS.jpg");
        background-size: 100% 100%;
    }
</style>
