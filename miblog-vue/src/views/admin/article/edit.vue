<template>
    <div>
        <el-breadcrumb separator="/" style="padding-left:10px;padding-bottom:10px;font-size:12px;">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>添加文章</el-breadcrumb-item>
        </el-breadcrumb>
        <el-row :gutter="15">
            <el-form ref="article" :model="article" :rules="rules" size="medium" label-width="100px">

                <el-form-item label="帖子图" prop="titleImg">
                    <el-upload
                            class="avatar-uploader"
                            action="string"
                            :show-file-list="false"
                            :http-request="uploadImg"
                            :before-upload="beforeAvatarUpload">
                        <img v-if="article.imgurl" width="100%" height="100%" :src="article.imgurl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>

                <el-form-item label="文章标题" prop="title" style="width: 600px">
                    <el-input v-model="article.title"></el-input>
                </el-form-item>
                <el-form-item label="摘要" prop="summary" style="width: 600px">
                    <el-input type="textarea" v-model="article.summary"></el-input>
                </el-form-item>
                <el-form-item label="内容" prop="content">
                    <v-md-editor  height="400px"
                                  :disabled-menus="[]"
                                  v-model="article.content"
                    ></v-md-editor>
                </el-form-item>


                <el-form-item label="类型" prop="typeId" style="width: 600px">
                    <el-select v-model="article.typeId" placeholder="请选择">
                        <el-option
                                v-for="item in type"
                                :key="item.id"
                                :label="item.typeName"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-col :span="12">
                    <el-form-item>
                        <el-button type="primary" @click="submitForm()">立即创建</el-button>
                        <el-button @click="resetForm('ruleForm')">草稿</el-button>
                    </el-form-item>
                </el-col>
            </el-form>
        </el-row>
    </div>
</template>

<script>
    import {upload} from "../../../api/upload";
    import {all} from "../../../api/type";
    import {articleUpdate,detail} from "../../../api/article";
    import token from "../../../../public/js/token";
    import {update} from "../../../api/user";
    export default {
        name: "Insert",
        data() {
            return {
                article: {
                    title: '',
                    imgurl:'',
                    content:'',
                    summary:'',
                    typeId:'',
                    userId:'',
                    status:''
                },
                type:[],
                rules: {
                    title: [
                        { required: true, message: '请输入活动名称', trigger: 'blur' },
                    ],
                    content: [
                        { required: true, message: '请输入文章内容', trigger: 'blur' },
                    ],
                    typeId: [
                        { required: true, message: '请输入选择', trigger: 'blur' },
                    ],
                }
            };
        },
        mounted() {
            this.typeAll()
            this.article.userId=token.getUser().id
            detail(this.$route.query.id).then(data=>{
                console.log('detail')
                console.log(data)
                this.article=data.data
            })
        },
        methods:{
            submitForm() {
                this.$refs['article'].validate((valid) => {
                    if (valid) {
                        this.article.status=0
                        this.article.userId=token.getUser().userId
                        console.log('cdscdsnkjdf111')
                        console.log(this.article)
                        articleUpdate(this.article).then(data=>{
                            console.log(data)
                            this.$message.success('文章修改成功')
                            this.$router.push('/article/myself')
                        })

                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm() {
                this.$refs['article'].validate((valid) => {
                    if (valid) {
                        this.article.status=1
                        this.article.userId=token.getUser().userId
                        articleUpdate(this.article).then(()=>{
                            this.$message.success('草稿修改成功')
                            this.$router.push('/article/myself')
                        })

                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            typeAll(){
                all().then(data=>{
                    console.log(data)
                    this.type=data.data
                })
            },
            uploadImg(param){
                const  formData = new FormData();
                formData.append("file",param.file)
                upload(formData).then(data=>{
                    this.article.imgurl = data.data
                }).catch(error=>{
                    this.$message.error(error)
                })
            },
            //上传之前的验证
            beforeAvatarUpload(file){
                const isIMAGE = (file.type === 'image/jpeg') ||  (file.type === 'image/jpg') || (file.type ==='image/png')
                const isLt2M = file.size / 1024 / 1024 < 2
                if (!isIMAGE) {
                    this.$message.error('上传文件只能是图片格式!')
                }
                if (!isLt2M) {
                    this.$message.error('上传文件大小不能超过 2MB!')
                }
                return isIMAGE && isLt2M
            },
        }
    }
</script>

<style scoped>
    .avatar-uploader{
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        width:200px;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 24px;
        color: #8c939d;
        width: 200px;
        height: 140px;
        line-height: 140px;
        text-align: center;
    }
    .avatar {
        width: 200px;
        height: 100%;
        display: block;
    }


    .el-form-item--medium .el-form-item__content, .el-form-item--medium .el-form-item__label {
        line-height: 15px;
    }

</style>
