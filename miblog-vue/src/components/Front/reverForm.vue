<template>
    <div class="guoshun-form">
        <h2 style="margin-left:100px">回复{{comment.name}}</h2>
        <el-row :gutter="15">
            <el-form ref="requestComment" :model="requestComment" :rules="rules" size="medium" label-width="100px">

                <el-col :span="24">
                    <el-form-item prop="description">
                        <el-input type="textarea" :rows="8"  v-model="requestComment.description" :clearable="true" height=130px></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="15">
                    <el-form-item>
                        <el-button type="primary" style="width:379px; height: 43px;margin-bottom:20px;font-size: 17px" :loading="loading" @click="submitForm">立即评论</el-button>
                    </el-form-item>
                </el-col>
            </el-form>
        </el-row>
    </div>
</template>

<script>
    import {insert} from "@/api/comment";
    import token from "../../../public/js/token";

    export default {
        name: "Form",
        props:['data'],
        data() {
            return{
                comment: {
                    id:'',
                    pid: 0,
                    userId: '',
                    articleId: '',
                    content:''
                },
                requestComment: {
                    pid: 0,
                    articleId: '',
                    userId:'',
                    description:'',
                    receiver:''
                },
                loading: false,
                rules: {
                    name: [{required: true, message: '请输入昵称', trigger: 'blur'}],
                    description: [{required: true, message: '请输入评论内容', trigger: 'blur'}],
                    email: [ { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                        { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }],
                }
            }
        },

        created(){
            this.comment =JSON.parse(JSON.stringify(this.data))
            console.log('receiver')
            console.log(this.comment)
            if (this.comment.userId===token.getUser().userId){
                this.requestComment.receiver=''
            }else {
                this.requestComment.receiver=this.comment.userId
            }
        },
        methods: {
            submitForm(){
                this.$refs['requestComment'].validate((valid) => {
                    if (valid) {
                        if (token.get()===null){
                            this.$confirm('此操作需要进行登录操作, 请登录后再来评论吧?', '提示', {
                                confirmButtonText: '确定',
                                cancelButtonText: '取消',
                                type: 'warning'
                            }).then(() => {
                                this.$router.push('/login')
                            }).catch(() => {
                                this.$message.info('已取消操作')
                            });
                        }else {
                            this.requestComment.pid=this.comment.id
                            this.requestComment.articleId=this.comment.articleId
                            this.requestComment.userId=token.getUser().userId
                            insert(this.requestComment).then(data=>{
                                console.log(data)
                                this.$message.success('评论成功')
                                this.$refs['requestComment'].resetFields()
                                this.$emit("after")
                                this.$emit("hideDialog")
                            }).catch(error=>{
                                this.$message.error(error)
                            })
                        }
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>

    .guoshun-form{
        margin-left: -80px;
        margin-top: 20px;
    }

</style>
