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
                    <el-input v-model="requestComment.search" placeholder="按用户名称查询"></el-input>
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
                    prop="userName"
                    label="账号"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="nickname"
                    label="名称" width="180">
            </el-table-column>
            <el-table-column
                    prop="isAdminName"
                    label="权限" width="180">
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
                    <el-button @click="edit(scope.row)"  size="small" type="info" icon="el-icon-setting" title="修改权限"></el-button>
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
        <el-dialog :visible.sync="EditUserVisble" v-if="EditUserVisble" :close-on-click-modal="false" title="修改员工">
            <EditUser @after="after" :data="userData" @hideDialog="hidden"></EditUser>
        </el-dialog>
    </div>
</div>
</template>

<script>
    import {list,search} from "../../../api/user";
    import token from "../../../../public/js/token";
    import EditUser from "../../../components/admin/user/EditUser";
    import axiosIstance from "../../../utils/request";
    export default {
        name: "myself",
        components:{EditUser},
        data(){
            return{
                commentTable: [],
                requestComment:{
                    search:'',
                    userId:'',
                    index:1,
                    size:8
                },
                page:1,
                total:0,
                userData:[],
                AddUserVisble: false,
                EditUserVisble: false
            }
        },
        methods:{
            DeparthandleCurrentChange(val){
                this.list(val)
            },
            edit(row){
                this.userData=row
                this.EditUserVisble=true
            },
            hidden(){
                this.AddUserVisble=false
                this.EditUserVisble=false
            },  after(){
                this.requestComment.userId=token.getUser().userId
                this.list(this.requestComment)
            },
            del(row){
                this.$confirm('确定要删除'+row.name+'的评论吗？','提示').then(()=>{
                    axiosIstance.get('user/delete?userId='+row.userId).then(()=>{
                        this.$message.success('删除完成')
                        this.after()
                    })
                })
            },
            search(){
                search(this.requestComment).then(data=>{
                    console.log(data)
                    this.commentTable=data.data.list
                    this.total=data.data.total
                }).catch(error=>{
                    this.$message.error(error)
                })
            },
            list(user){
                list(user).then(data=>{
                    console.log(data)
                    this.commentTable=data.data.list
                    this.total=data.data.total
                }).catch(error=>{
                    this.message.error(error)
                })
            }
        },
        created() {
            this.requestComment.userId=token.getUser().userId
            this.list(this.requestComment)
        }

    }
</script>

<style scoped>

</style>
