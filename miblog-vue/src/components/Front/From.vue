<template>
    <div class="guoshun-form">
        <el-row :gutter="15">
            <el-form ref="comment" :model="comment" :rules="rules" size="medium" label-width="100px">
                <el-col :span="24">
                    <el-form-item prop="description">
                        <el-input type="textarea" :rows="8" v-model="comment.description" height=130px></el-input>
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
    import token from "../../../public/js/token";
    import axiosIstance from "../../utils/request";

    export default {
        name: "Form",
        data() {
            return{
                comment: {
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
            this.comment.articleId=this.$route.params.id
            this.comment.userId=token.getUser().userId
            axiosIstance.get('article/detail?id='+this.$route.params.id).then(data=>{
                if (data.data.user.userId===token.getUser().userId){
                    this.comment.receiver=''
                }else {
                    this.comment.receiver=data.data.user.userId
                }
            })
        },
        methods: {
            submitForm(){
                this.$refs['comment'].validate((valid) => {
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
                        if (valid) {
                            axiosIstance.post('comment/insert',this.comment).then(data=>{
                                console.log(data)
                                this.$message.success('评论成功')
                                this.$refs['comment'].resetFields()
                                this.$emit("after")
                            })
                        } else {
                            console.log('error submit!!');
                            return false;
                        }
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
