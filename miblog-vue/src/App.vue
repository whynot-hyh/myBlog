<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<style lang="less">

    html,body{
        margin: 0px 0px 0px 0px;
    }

  #app{width: 100%;height: 100%!important;
      margin: 0px;padding: 0px;
      position: absolute;
      background: #ffffff;
      top: 0px;
      left: 0;
      right: 0;
      background: url("https://linxuan.fun/images/bg/教室近1.png") center center /
    cover no-repeat;
      background-color: #49b1f5;
      background-attachment: fixed;
      color: #fff !important;
      animation: header-effect 1s !important;
  }
</style>
