<template>
    <el-container class="home-container">
        <!--    导航-->
        <el-header>
            <div>
        <span >
          <h3 style="margin-left:20px; color: #2d8cf0;">MIBlog</h3>
        </span>

            </div>
            <el-dropdown>
                <div class="block">
                    <el-avatar  :size="50" v-if="userInfo.avatar!==null && userInfo.avatar!==''"  :src="userInfo.avatar" style="cursor: pointer;"></el-avatar>
                    <el-avatar  :size="50" v-else  src="" style="cursor: pointer;"></el-avatar>
                </div>
                <el-dropdown-menu slot="dropdown" trigger="click">
                    <el-dropdown-item>
                        <span type="danger"  @click="toWelcome"><span class="el-icon-house"></span> &nbsp;前台</span>
                    </el-dropdown-item>
                    <el-dropdown-item>
                        <span type="danger" @click="getContact"><span class="el-icon-user"></span> &nbsp;个人信息</span>
                    </el-dropdown-item>
                    <el-dropdown-item>
                        <span type="danger" @click="logout"><span class="el-icon-switch-button"></span> &nbsp;退出登入</span>
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-header>
        <!--主体-->
        <el-container style="height: 500px;">
            <!--菜单-->
            <el-aside :width="isOpen===true?'64px':'200px'">
                <div class="toggle-btn" @click="toggleMenu">|||</div>
                <el-menu
                        class="el-menu-vertical-demo"
                        :collapse="isOpen"
                        :router="true"
                        :default-active="activePath"
                        background-color="#001529"
                        :collapse-transition="false"
                        text-color="rgba(255,255,255,0.7)"
                        unique-opened
                >
                    <MenuTree  :menuList="this.menuList"></MenuTree>
                </el-menu>
            </el-aside>
            <!--右边主体-->
            <el-main  v-loading="loading">
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import MenuTree from "../../components/MenuTree"; //引进菜单模板

    import token from '../../../public/js/token'
    import {menus} from "../../api/menus";
    import {detail} from "../../api/user";

    export default {
        data() {
            return {
                loading: true,
                activePath: "", //激活的路径
                isOpen: false,
                menuList: {},
                userInfo: {},
                requestUser:{
                    id:''
                }
            };
        },
        components: {
            MenuTree
        },

        methods: {
            /**
             *
             * 退出登入
             */
            async logout() {
                const res = await this.$confirm("此操作将退出系统, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消退出登入"
                    });
                });
                if (res === "confirm") {
                    // LocalStorage.clearAll();
                    token.clear()
                    await this.$router.push("/login");
                }
            },
            /**
             * 去系统首页
             */
            toWelcome(){
                this.$router.push("/");
            },
            /**
             加载菜单数据
             */
            async getMenuList() {
                menus(token.getUser().isadmin).then(data=>{
                    console.log(data)
                    this.menuList=data.data
                }).catch(error=>{
                    this.$message.error(error)
                })
            },
            /**
             获取用户信息
             */
            async getUserInfo() {
                detail(token.getUser().userId).then(data=>{
                    this.userInfo = data.data
                })
            },
            /**
             * 菜单伸缩
             */
            toggleMenu() {
                this.isOpen = !this.isOpen;
            },
            /**
             * 点击交流
             */
            getContact(){
                this.$router.push("/userInfo");
            }
        },
        mounted() {
        },
        created() {
            this.getUserInfo();
            this.getMenuList();
            this.activePath = window.sessionStorage.getItem("activePath");
            setTimeout(() => {
                this.loading = false;
            }, 500);
        }
    };
</script>

<style>
    /* 为对应的路由跳转时设置动画效果 */

    .el-header {
        background-color: #001529;
        display: flex;
        justify-content: space-between;
        align-items: center;
        color: #fff;
        font-size: 19px;

        padding-left: 0px;
    }
    .el-aside {
        background-color:#001529
    }
    .el-main {
        background-color: #eaedf1;
    }
    .home-container {
        width: 100%;
        height: 100% !important;
    }
    .toggle-btn {
        background-color: #2d8cf0 !important;
        font-size: 10px;
        line-height: 24px;
        color: #fff;
        text-align: center;
        letter-spacing: 0.2em;
        cursor: pointer;
    }

</style>
