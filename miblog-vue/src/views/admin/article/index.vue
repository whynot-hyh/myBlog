<template>
    <div>
        <el-breadcrumb separator="/" style="padding-left:10px;padding-bottom:10px;font-size:12px;">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>文章管理</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="whitebg bloglist">

            <el-table
                    :data="articleTable"
                    stripe
                    style="width: 100%">
                <el-table-column
                        prop="title"
                        label="标题"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="view"
                        label="浏览" width="150">
                </el-table-column>
`
                <el-table-column
                        prop="user.nickname"
                        label="发布人" width="180">
                </el-table-column>
                <el-table-column
                        prop="type.typeName"
                        label="分类" width="180">
                </el-table-column>
                <el-table-column
                        prop="statusName"
                        label="状态" width="180">
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
        </div>
    </div>
</template>

<script>

    import {list,search,del} from "../../../api/article";
    import token from "../../../../public/js/token";

    export default {
        name: "index",
        data(){
            return{
                articleTable:[],
                pageEntity:{
                    userId:'',
                    index: 1,
                    size:8
                },
                article:{
                  title:''
                },
                userData:[],
                page:1,
                total:0,
                AddUserVisble: false,
                EditUserVisble: false
            }
        },
        methods:{
            DeparthandleCurrentChange(val){
                this.pageEntity.index=val
                this.pageEntity.size=8
                this.list(this.pageEntity)
            },
            edit(row){
                this.$router.push({
                    path:'/article/edit',
                    query:{
                        id: row.id
                    }
                })
            },
            del(row){
                this.$confirm('确定要删除'+row.title+'文章吗？','提示').then(()=>{
                    console.log(row)
                    del (row.id).then(()=>{
                        this.$message.success('删除完成')
                        this.list(this.pageEntity)
                    }).catch(error=>{
                        this.$message.error(error)
                    })
                })
            },
            search(){
                search(this.article).then(data=>{
                    this.articleTable=data.data.records
                    this.total=data.data.total
                }).catch(error=>{
                    this.$message.error(error)
                })
            },
            list(page){
                list(page).then(data=>{
                    console.log(data)
                    this.articleTable=data.data.list
                    this.total=data.data.total
                }).catch(error=>{
                    this.$message.error(error)
                })
            }
        },
        created() {
            this.pageEntity.userId=token.getUser().userId
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
