<template>
    <div class="home-index">
        <Header @child-even="parentEven" @child-evenTitle="parentEvenTitle"></Header>
        <!--top end-->
        <article>
            <div class="lbox">
             <Swiper></Swiper>
                <div class="clearblank"></div>
                <Blogs :search="newData"></Blogs>
            </div>
            <Right></Right>
        </article>
<!--   <Footer></Footer>-->
    </div>
</template>

<script>
    import '@/assets/css/base.css'
    import '@/assets/css/m.css'
    import Header from "../../components/Front/Header";
    import Swiper from "../../components/Front/Swiper";
    import Type from "../../components/Front/Type";
    import Blogs from "../../components/Front/Blogs";
    import Right from "../../components/Front/Right";
    import Footer from "../../components/Front/Footer";
    import {all} from "../../api/type";
    export default {
        name: "index",
        components: {Footer, Blogs,Right, Type, Swiper, Header},
        data(){
            return{
                type:[],
                typeId:'',
                newData:'',
                title:''
            }
        },
        methods:{
            parentEven(data) {
                this.newData = data;
            },
            parentEvenTitle(data){
                this.search=data
            }
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
