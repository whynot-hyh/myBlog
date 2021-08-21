<template>
    <div id="login">

        <el-form
                :model="user"
                :rules="loginRules"
                status-icon
                ref="user"
                label-position="left"
                label-width="0px"
                class="demo-ruleForm login-page"
        >
            <h3 class="title">系统注册</h3>
            <el-form-item prop="userName">
                <el-input
                        type="text"
                        @keyup.enter.native="handleSubmit"
                        v-model="user.userName"
                        auto-complete="off"
                        placeholder="用户名"
                        prefix-icon="iconfont el-icon-user"
                ></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input
                        @keyup.enter.native="handleSubmit"
                        type="password"
                        v-model="user.password"
                        auto-complete="off"
                        placeholder="密码"
                        prefix-icon="el-icon-suitcase-1"
                ></el-input>
            </el-form-item>

            <el-form-item prop="nickname">
                <el-input
                        type="text"
                        @keyup.enter.native="handleSubmit"
                        v-model="user.nickname"
                        auto-complete="off"
                        placeholder="用户昵称"
                ></el-input>
            </el-form-item>
            <div></div>

            <el-form-item style="width:100%;">
                <div style="float:right;">
                    <el-button
                            type="primary"
                            class="el-icon-mobile-phone"
                            @click="resetForm"
                            :loading="loading"
                    >注册
                    </el-button>
                    <el-button class="el-icon-refresh" @click="handleSubmit">去登陆</el-button>
                </div>
            </el-form-item>
        </el-form>

        <!-- 验证码 -->
        <Vcode
                :show="isShow"
                @success="success"
                @close="close"
                :canvasWidth="500"
                :canvasHeight="350"
        />
    </div>
</template>

<script>
    import Vcode from "vue-puzzle-vcode";

    import {register} from "../api/user";

    export default {
        data() {
            return {
                isShow: false,
                dialogVisible: false,
                imgCode: undefined,
                //表单用户登入数据
                loading: false,
                user: {
                    userName: "",
                    password: "",
                    nickname:""
                },
                checked: true,

                //验证规则
                loginRules: {
                    userName: [
                        {required: true, message: "请输入用户名", trigger: "blur"},
                    ],
                    password: [
                        {required: true, message: "请输入用户密码", trigger: "blur"},
                        {min: 6, max: 15, message: "长度在 6 到 15 个字符", trigger: "blur"}
                    ],
                    nickname: [
                        {required: true, message: "请输入用户昵称", trigger: "blur"},
                    ]
                },
            };
        },
        components: {
            Vcode
        },

        methods: {
            handleSubmit: function () {
             this.$router.push('/login')
            },
            //重置表单
            resetForm: function () {
                this.$refs.user.validate(valid => {
                    if (!valid) {
                        return;
                    } else {
                        this.isShow = true;
                    }
                });
            },
            //验证成功
            async success() {
                this.loading = true;
                register(this.user).then(()=>{
                    this.$message.success('注册成功,快去登录吧')
                    this.$router.push('/login')
                })
                this.loading = false;
            },

            close() {
                this.isShow = false;
            }
        },
    };
</script>

<style scoped>
    .login-container {
        width: 100%;
        height: 100%;

    }

    #login {
        position: relative;
    }


    .login-page {
        position: relative;
        -webkit-border-radius: 5px;
        border-radius: 5px;
        margin: 190px auto;
        width: 370px;
        padding: 40px 35px 15px;
        background: #fff;
        border: 1px solid #eaeaea;
    }

    label.el-checkbox.rememberme {
        margin: 0px 0px 15px;
        text-align: left;
    }

</style>


