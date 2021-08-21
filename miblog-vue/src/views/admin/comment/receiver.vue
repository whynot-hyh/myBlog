<template>
<div>
    <el-breadcrumb separator="/" style="padding-left:10px;padding-bottom:10px;font-size:12px;">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="whitebg bloglist">
        <div>
            <el-form :inline="true" ref="requestComment" :model="requestComment" label-width="80px">
                <el-form-item>
                    <el-input v-model="requestComment.search" placeholder="按评论名字查询"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button icon="el-icon-search" @click="search" type="primary" >查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <el-table
                :data="commentTable"
                stripe
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label="id"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="user.nickname"
                    label="姓名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="评论内容" width="180">
            </el-table-column>
            <el-table-column
                    prop="article.title"
                    label="评论文章" width="180">
            </el-table-column>
            <el-table-column
                    prop="gmtCreate"
                    label="创建日期" width="180">
            </el-table-column>

            <el-table-column
                    align="center"
                    width="180"
                    label="操作">
                <template slot-scope="scope">
                    <el-button @click="del(scope.row)"  size="small" type="danger" icon="el-icon-delete"></el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
                background
                layout="total,prev, pager, next,jumper"
                @current-change="DeparthandleCurrentChange"
                :current-page="page"
                :page-size="8"
                :total="total" style="padding-top: 15px">
        </el-pagination>
    </div>
</div>
</template>

<script>
    import {getReceiverComments,search} from "../../../api/comment";
    import token from "../../../../public/js/token";
    import axiosIstance from "../../../utils/request";
    export default {
        name: "myself",
        data(){
            return{
                commentTable: [],
                requestComment:{
                    search:'',
                    userId:'',
                    receiver:'',
                    index:1,
                    size:8
                },
                page:1,
                total:0,
            }
        },
        methods:{
            DeparthandleCurrentChange(val){
                this.requestComment.index=val
                this.requestComment.userId=token.getUser().userId
                this.list(this.requestComment)
            },
            del(row){
                this.$confirm('确定要删除'+row.name+'的评论吗？','提示').then(()=>{
                    console.log(row)
                    axiosIstance.get('comment/delete?id='+row.id).then(()=>{
                        this.$message.success('删除成功')
                        this.requestComment.userId=token.getUser().userId
                        this.list(this.requestComment)
                    })
                })
            },
            search(){
                this.requestComment.userId=token.getUser().userId
                search(this.requestComment).then(data=>{
                    console.log(data)
                    this.commentTable=data.data.list
                    this.total=data.data.total
                }).catch(error=>{
                    this.$message.error(error)
                })
            },
            list(user){
                getReceiverComments(user).then(data=>{
                    console.log(data)
                    this.commentTable=data.data.list
                    this.total=data.data.total
                }).catch(error=>{
                    this.message.error(error)
                })
            }
        },
        created() {
            this.requestComment.receiver=token.getUser().userId

            this.list(this.requestComment)
        }

    }
</script>

<style scoped>

</style>
