<template>
    <div>
        <el-breadcrumb separator="/" style="padding-left:10px;padding-bottom:10px;font-size:12px;">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>文章管理</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="whitebg bloglist">

            <div>
                <el-form :inline="true" ref="requestType" :model="requestType" label-width="80px">
                    <el-form-item>
                        <el-input v-model="requestType.search" placeholder="按姓名查询"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button icon="el-icon-search" @click="search" type="primary" >查询</el-button>
                        <el-button icon="el-icon-plus" @click="add" type="success" >新增</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <el-table
                    :data="articleTable"
                    stripe
                    style="width: 100%">
                <el-table-column
                        prop="id"
                        label="id"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="typeName"
                        label="类型名称" width="150">
                </el-table-column>
`
                <el-table-column
                        prop="gmtCreate"
                        label="发布时间" width="180">
                </el-table-column>
                <el-table-column
                        align="center"
                        width="180"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button @click="edit(scope.row)" size="small" type="primary" icon="el-icon-edit"></el-button>
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
            <el-dialog :visible.sync="AddUserVisble" v-if="AddUserVisble" :close-on-click-modal="false" title="添加类型">
                <AddType @after="after" @hideDialog="hidden"></AddType>
            </el-dialog>

            <el-dialog :visible.sync="EditUserVisble" v-if="EditUserVisble" :close-on-click-modal="false" title="修改员工">
                <EditType @after="after" :data="TypeData" @hideDialog="hidden"></EditType>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import {typeList} from "../../../api/type";
    import AddType from "../../../components/admin/type/AddType";
    import EditType from "../../../components/admin/type/EditType";
    import token from "../../../../public/js/token";
    import axiosIstance from "../../../utils/request";
    export default {
        name: "index",
        components:{AddType,EditType},
        data(){
            return{
                articleTable:[],
                pageEntity:{
                    userId:'',
                    index: 1,
                    size:8
                },
                TypeData:[],
                article:{
                  title:''
                },
                requestType:{
                    search:'',
                    userId:'',
                    index:1,
                    size:8
                },
                userData:[],
                page:1,
                total:0,
                AddUserVisble: false,
                EditUserVisble: false
            }
        },
        methods:{
            hidden(){
                this.AddUserVisble=false
                this.EditUserVisble=false
            },
            DeparthandleCurrentChange(val){
                this.pageEntity.index=val
                this.list(this.pageEntity)
            },
            after(){
                this.list(this.pageEntity)
            },
            add(){
                this.AddUserVisble=true
            },
            edit(row){
                this.TypeData=row
                this.EditUserVisble=true
            },
            del(row){
                this.$confirm('确定要删除该分类吗？','提示').then(()=>{
                    console.log(row)
                    axiosIstance.delete('type/delete?id='+row.id).then(()=>{
                        this.$message.success('删除成功')
                        this.list(this.pageEntity)
                    })
                })
            },
            search(){
               this.requestType.userId=token.getUser().userId
                axiosIstance.post('type/search',this.requestType).then(data=>{
                    this.articleTable=data.data.list
                    this.total=data.data.total
                })
            },
            list(page){
                typeList(page).then(data=>{
                    console.log(data)
                    this.articleTable=data.data.list
                    this.total=data.data.total
                }).catch(error=>{
                    this.$message.error(error)
                })
            }
        },
        created() {
          this.list(this.pageEntity)
        }
    }
</script>

<style scoped>
    .whitebg { background: #fff; border-radius: 3px; padding: 20px; margin-bottom: 20px; overflow: hidden; }
    /*bloglist*/
    .bloglist>ul { padding: 10px 0 0 }
    .bloglist>ul>li { overflow: hidden; margin-bottom: 20px; border-bottom: #eee 1px dashed; padding-bottom: 20px; position: relative; min-height: 130px; }
    .blogtitle { margin: 0 0 10px 0; font-size: 18px; overflow: hidden; }
    .bloglist>ul>li:hover .blogtitle a { color: #337ab7; }
    .blogtitle b { color: #a9a9a9
    }
</style>
