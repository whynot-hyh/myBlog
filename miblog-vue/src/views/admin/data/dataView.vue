<template>
    <div>
            <!-- 用户登入报表 -->
            <div id="loginReport" style="width: 1000px;height:450px;"></div>
    </div>
</template>

<script>
    import * as echarts from 'echarts';
    import axiosIstance from "../../../utils/request";
    export default {
        name: "dataView",
        data(){
            return{
                dataView:[]
            }
        },
        created() {
            axiosIstance.get('/article/getDataView').then(data=>{
                this.dataView=data.data
                this.draw();
            })
        },
        methods: {
            /**
             * 绘制登入报表
             */
            async draw() {
                console.log('aaa')
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('loginReport'));

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '博客统计'
                    },
                    tooltip: {},
                    legend: {
                        data:['统计']
                    },
                    xAxis: {
                        data: ["文章数","浏览量","置顶文章","评论数","相册数"]
                    },
                    yAxis: {},
                    series: [{
                        name: '数量',
                        type: 'bar',
                        data: this.dataView
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        },

    }
</script>

<style scoped>

</style>
