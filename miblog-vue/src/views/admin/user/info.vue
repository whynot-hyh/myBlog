<template>
    <div>
        <el-breadcrumb separator="/" style="padding-left:10px;padding-bottom:10px;font-size:12px;">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>个人信息</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="whitebg bloglist">

            <el-row :gutter="15">
                <el-form ref="user" :model="user" :rules="rules"  label-width="100px">
                    <el-col :span="24">
                        <!-- 用户头像 -->
                        <el-form-item label="用户头像">
                            <!-- 头衔缩略图 -->
                            <pan-thumb :image="image"/>
                            <!-- 文件上传按钮 -->
                            <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像
                            </el-button>
                            <image-cropper
                                    v-show="imagecropperShow"
                                    :width="300"
                                    :height="300"
                                    :key="imagecropperKey"
                                    :url="'uploadFile'"
                                    field="file"
                                    @close="closeImage"
                                    @crop-upload-success="cropSuccess"
                            />
                        </el-form-item>
                    </el-col>

                    <el-col :span="12">
                        <el-form-item label="账号"  prop="userName">
                            <el-input v-model="user.userName"  placeholder="请输入用户名" :disabled="true" clearable :style="{width: '100%'}">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="名称" prop="nickname">
                            <el-input v-model="user.nickname" placeholder="请输入姓名" clearable :style="{width: '100%'}">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="password">
                            <el-input v-model="user.password" placeholder="请输入密码" clearable :style="{width: '100%'}">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="15">
                        <el-form-item >
                            <el-button type="primary" :loading="loading" @click="submitForm">保存</el-button>
                        </el-form-item>
                    </el-col>
                </el-form>
            </el-row>
        </div>
    </div>
</template>

<script>
    import token from "../../../../public/js/token";
    import PanThumb from '../../../components/PanThumb/index'
    import ImageCropper from '../../../components/ImageCropper'
    import {update,detail} from "../../../api/user";

    export default {
        name: "userInfo",
        components:{PanThumb,ImageCropper},
        data(){
            return{
                user: {
                    userId: '',
                    userName:'',
                    nickname: '',
                    password:'',
                    avatar: '',
                },
                requestUser:{
                    id: '',
                    userName:'',
                    nickname: '',
                    avatar: '',
                },
                department:[],
                headtitle:[],
                loading: false,
                rules: {
                    nickname: [{required: true, message: '请输入昵称', trigger: 'blur'}],
                    email: [{required: true, message: '请输入邮箱', trigger: 'blur'}],
                },
                imagecropperShow: false, // 是否显示上传组件
                imagecropperKey: 0, // 上传组件id
                image: '',
            }
        },
        created(){
            this.detail()
        },
        methods:{
            detail(){
                detail(token.getUser().userId).then(data=>{
                    console.log(data)
                    this.user=data.data
                    if (this.user.avatar==''&&this.user.avatar==null){
                        this.image='https://wpimg.wallstcn.com/577965b9-bb9e-4e02-9f0c-095b41417191'
                    }else {
                        this.image=this.user.avatar
                    }
                })
            },
            submitForm(){
                this.$refs['user'].validate(valid=>{
                    if (valid){
                        update(this.user).then(()=>{
                            this.$message.success('修改信息成功')
                            this.detail()
                        })
                    }
                })
            },
            // 上传成功后的回调函数
            cropSuccess(data) {
                //使用该属性，保存上传之前的头像
                this.imagecropperShow = false
                this.user.avatar=this.image = data;
                // 上传成功后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
                this.imagecropperKey = this.imagecropperKey + 1
            },
            // 关闭上传组件
            closeImage() {
                this.imagecropperShow = false
                // 上传失败后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
                this.imagecropperKey = this.imagecropperKey + 1
            }
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
