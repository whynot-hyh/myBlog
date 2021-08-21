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
            <h3 class="title">系统登录</h3>
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
            <div></div>

            <el-form-item style="width:100%;">
                <div style="float:right;">
                    <el-button
                            type="primary"
                            class="el-icon-mobile-phone"
                            @click="handleSubmit"
                            :loading="loading"
                    >登录
                    </el-button>
                    <el-button class="el-icon-refresh" @click="resetForm">注册</el-button>
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

    export default {
        data() {
            return {
                isShow: false,
                dialogVisible: false,
                imgCode: undefined,
                //表单用户登入数据
                loading: false,
                user: {
                    userName: "zhaoguoshun",
                    password: "985211"
                },
                checked: true,

                //验证规则
                loginRules: {
                    username: [
                        {required: true, message: "请输入用户名", trigger: "blur"},
                        {min: 3, max: 12, message: "长度在 3 到 12 个字符", trigger: "blur"}
                    ],
                    password: [
                        {required: true, message: "请输入用户密码", trigger: "blur"},
                        {min: 6, max: 15, message: "长度在 6 到 15 个字符", trigger: "blur"}
                    ]
                },
            };
        },
        components: {
            Vcode
        },

        methods: {
            //登入提交
            handleSubmit: function () {
                this.$refs.user.validate(valid => {
                    if (!valid) {
                        return;
                    } else {
                        this.isShow = true;
                    }
                });
            },
            //重置表单
            resetForm () {
                this.$router.push('/register')
            },
            //验证成功
            async success() {
                this.loading = true;
                this.$store.dispatch('login',this.user).then(()=>{
                    this.$message({
                        title: "登入成功",
                        message: "欢迎你进入系统",
                        type: "success"
                    });
                    this.$router.push("/home");
                }).catch(()=>{
                    this.close()
                    this.$message.error('账号或者密码错误')
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


