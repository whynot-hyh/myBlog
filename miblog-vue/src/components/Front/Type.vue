<template>

</template>

<script>
    import {all} from "../../api/type";
    export default {
        name: "Type",
        data(){
            return{
                type:[]
            }
        },
        methods:{
        },
        created() {
            all().then(data=>{
                console.log(data)
                this.type=data.data
            })
        }
    }
</script>

<style scoped>

</style>
