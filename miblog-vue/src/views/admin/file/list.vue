<template>
    <div>

    <div class="attachments">
        <!-- 面包导航 -->
        <el-breadcrumb separator="/" style="padding-left:10px;padding-bottom:10px;font-size:12px;">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>系统管理</el-breadcrumb-item>
            <el-breadcrumb-item>附件管理</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 图片列表部分 -->
        <el-card>
            <!-- 搜索部分 -->
            <el-form :size="'small'" :inline="true" :model="queryMap" class="demo-form-inline">
                <el-form-item>
                    <el-button type="primary" @click="centerDialogVisible=true">
                        上传
                        <i class="el-icon-upload el-icon--right"></i>
                    </el-button>
                </el-form-item>
            </el-form>
            <!-- 图片展示部分 -->
            <el-row :gutter="20" style="height:430px;" v-loading="loading">
                <el-col style="margin-top:10px;" v-for="image in this.list" :key="image.id" :span="6">
                    <div class="grid-content bg-purple">
                        <el-image
                                :alt="image.imgurl"
                                :fit="fits"
                                :preview-src-list="srcList"
                                style="width:200px;height:170px"
                                :src="image.imgurl"
                        >
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </el-image>
                        <div>
                                <el-button
                                        style="margin-left:30px;"
                                        icon="el-icon-delete"
                                        size="mini"
                                        type="text"
                                        slot="reference"
                                        @click="del(image.imgurl)"
                                >删除</el-button>
                        </div>
                    </div>

                </el-col>
            </el-row>


            <!-- 上传弹出框 -->
            <el-dialog title="上传图片附件" @close="closeUpload" :visible.sync="centerDialogVisible" width="38%" center>
        <span>
          <el-upload
                  accept="image/*"
                  :auto-upload="false"
                  :multiple="true"
                  ref="upload"
                  :limit="10"
                  :on-exceed="exceed"
                  class="upload-demo"
                  drag
                  :on-success="handleUploadSuccess"
                  :action="uploadUrl"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过10MB,最多支持10张图片一起上传</div>
          </el-upload>
        </span>
                <span slot="footer" class="dialog-footer">
          <el-button @click="closeUpload" size="small"  >取消返回</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
        </span>
            </el-dialog>
        </el-card>
    </div>
        <div style="background-color: #ffffff">
            <el-pagination
                    background
                    layout="total,prev, pager, next,jumper"
                    @current-change="DeparthandleCurrentChange"
                    :current-page="page"
                    :page-size="12"
                    :total="total" style="padding-top: 15px">
            </el-pagination>
        </div>
    </div>
</template>


<script>
    import {photoList,del,insert} from "../../../api/picture";
    import token from "../../../../public/js/token";
    import axiosIstance from "../../../utils/request";
    export default {
        data() {
            return {
                uploadUrl:"http://localhost:8081/uploadFile",
                centerDialogVisible: false,
                loading: true,
                total: 0,
                page:1,
                pageEntity:{
                    userId:'',
                    index:1,
                    size:12
                },
                photo:{
                    userId:'',
                    imgurl:''
                },
                fits: "contain",
                queryMap: {},
                list: [],
                srcList: [],
            };
        },
        methods: {
            /**
             * 取消上传
             */
            closeUpload(){
                this.centerDialogVisible=false;
                this.$refs.upload.clearFiles();
            },
            /**
             * 上传之后的回调
             */
            handleUploadSuccess: function (response, file, fileList) {
                if (response.status!==200) {
                    this.$refs.upload.clearFiles();
                    return this.$message.error("上传失败:" + response.data.errorMsg);
                } else {
                    this.closeUpload()
                    console.log(response)
                    this.photo.userId=token.getUser().userId
                    this.photo.imgurl=response.data
                    insert(this.photo).then(()=>{
                        this.$message.success('上传成功')
                        this.pageEntity.userId=token.getUser().userId
                        this.ageImageList(this.pageEntity);
                    })
                }
            },
            /**
             *
             * 点击上传到服务器
             */
            submitUpload() {
                this.$refs.upload.submit();
            },
            DeparthandleCurrentChange(val){
                this.pageEntity.userId=token.getUser().userId
                this.pageEntity.index=val
                this.ageImageList(this.pageEntity);
            },
            /**
             * 删除图片
             */
            del: async function (url) {
                this.$confirm('确定要删除这张图片吗吗？','提示').then(()=>{
                 axiosIstance.get('photo/delete?imgUrl='+url).then(()=>{
                     this.$message.success('已经删除完成')
                     this.pageEntity.userId=token.getUser().userId
                     this.ageImageList(this.pageEntity);
                 })
                })

            },
            /**
             * 加载附件列表
             */
            async ageImageList(page) {
                photoList(page).then(data=>{
                    this.list=data.data.list
                    this.total=data.data.total
                }).catch(error=>{
                    this.$message.error(error)
                })
            },
            //改变页码
            handleSizeChange(newSize) {
                // this.queryMap.pageSize = newSize;
                // this.ageImageList();
            },
            //翻页
            handleCurrentChange(current) {
                this.ageImageList(current)
                // this.queryMap.pageNum = current;
                // this.ageImageList();
            },
            /**
             * 搜索
             */
            search() {
                this.queryMap.pageNum = 1;
                this.ageImageList();
            },
            /**
             * 超出允许上传的时候
             */
            exceed(){
                this.$message.warning("超出允许上传图片的数量");
            }
        },
        created() {
            this.pageEntity.userId=token.getUser().userId
            this.ageImageList(this.pageEntity);
            setTimeout(() => {
                this.loading = false;
            }, 500);
        }
    };
</script>

<style>
    .el-upload-dragger {
        width: 530px !important;

    }
</style>
