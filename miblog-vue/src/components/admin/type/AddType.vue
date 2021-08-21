<template>
        <el-form :model="type" :rules="rules" ref="type" label-width="100px" class="demo-ruleForm">
            <el-form-item label="类型名称" prop="typeName" style="width: 60%">
                <el-input v-model="type.typeName"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm()">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
</template>

<script>
    import {insert} from "../../../api/type";

    export default {
        name: "AddType",
        data(){
            return{
                type:{
                    typeName:''
                },
                rules: {
                    typeName: [
                        { required: true, message: '请输入类型名称', trigger: 'blur' },
                    ],
                }
            }
        },
        methods:{
            submitForm(){
                this.$refs['type'].validate((valid) => {

                    if (valid) {
                        insert(this.type).then(()=>{
                            this.$message.success('添加成功')
                            this.$emit("after")
                            this.$emit("hideDialog")
                        })
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

</style>
