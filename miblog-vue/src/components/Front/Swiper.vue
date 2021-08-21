<template>
    <div class="banbox " style="width: 1000px">
        <swiper class="swiper"  style="height: 300px;" >
            <swiper-slide v-for="(item,index) in sliders" :key="index">
                <router-link :to="item.url" target="_blank">
                    <img :src="item.img" width="100%" height="100%" alt=""></router-link>
            </swiper-slide>
        </swiper>
    </div>
</template>

<script>
    import { Swiper, SwiperSlide } from 'vue-awesome-swiper'

    import 'swiper/swiper-bundle.css'
    export default {
        name: "FSwiper",
        components:{
            Swiper,SwiperSlide
        },
        data(){
            return{
                sliders:[
                    {
                        "img": "https://linxuan.fun/images/bg/chengsan-cj.png",
                        url:"www.baidu.com "
                    },
                    {
                        "img": "https://r.photo.store.qq.com/psc?%2FV53KcXfb1umonn4HbITu3rINxs43TczD%2F45NBuzDIW489QBoVep5mcQaBtLM2yTpYe999VZqnRjrcd90GMu1NvFVRUxh.ZERBAbPSIeInGGY0C9uI2uTdJATSv*Nt3RFEYI4RVJUCXqo%21%2Fr=",
                        url:"www.baidu.com "
                    },
                    {

                        "img": "https://r.photo.store.qq.com/psc?%2FV53KcXfb1umonn4HbITu3rINxs43TczD%2F45NBuzDIW489QBoVep5mcedMeLHGVj5EQ3pZboWf9ZUT1VbRGevOCgENGxhi4UfCZmagBgzctF7EuOFHUysHbFq2ePjbWIGvH5niXuYkow8%21%2Fr=",
                        url:"www.baidu.com "
                    },


                ],

            }
        }
    }
</script>

<style scoped>

</style>
