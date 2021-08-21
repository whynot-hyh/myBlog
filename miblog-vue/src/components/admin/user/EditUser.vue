<template>

    <div>
        <el-row :gutter="15">
            <el-form ref="user" :model="user"  size="medium" label-width="100px">
                    <el-form-item label="权限">
                        <el-select v-model="user.isadmin" placeholder="请选择状态" clearable :style="{width: '100%'}">
                            <el-option v-for="item in this.utils.status"
                                       :key="item.value"
                                       :label="item.label"
                                       :value="item.value"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                <el-col :span="15">
                    <el-form-item>
                        <el-button @click="close">取消</el-button>
                        <el-button type="primary" :loading="loading" @click="submitForm">保存</el-button>
                    </el-form-item>
                </el-col>
            </el-form>
        </el-row>
    </div>
</template>
<script>
    import {update} from "../../../api/user";

    export default {
        name: "EditUser",
        props:['data'],
        data(){
            return{
                user:{
                    id:'',
                    isAdmin:''
                },
                loading: false
            }
        },
        mounted() {
            this.user =JSON.parse(JSON.stringify(this.data))
            console.log(this.user)
        },
        methods:{
            submitForm(){
                this.loading=true
                this.$refs['user'].validate(valid=>{
                    if (valid) {
                        console.log('user')
                        console.log(this.user)
                        update(this.user).then(()=>{
                            this.loading=false
                            this.close()
                            this.$emit("after")
                            this.$message.success('权限设置成功')
                        })
                    }
                })
            },
            close(){
                this.$emit("hideDialog")
            }
        }
    }
</script>

<style scoped>

</style>
