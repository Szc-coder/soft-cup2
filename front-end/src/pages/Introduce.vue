<template>
    <div class="introduce-content">
        <div class="concrete-content">
            <h2>一键式遥感影像智能解译 <br> 全方位解锁空间地理信息</h2>
            <div class="line"></div>
            <h1>SenseEarth</h1>
            <router-link to="/apply" class="nav-link">体验SenseEarth</router-link>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Introduce"
    }
</script>

<style scoped>
    .introduce-content {
        width: 100%;
        height: 100vh;
        background: url("../assets/background.jpg") center center no-repeat;
        background-size: cover;
        margin-top: 0;
        margin-bottom: 0;
        color: whitesmoke;
        text-align: center;
        position: relative;
        overflow-x: hidden;
    }
    .concrete-content{
        position: absolute;
        left: 50%;
        transform: translate(-50%, -40%);
        bottom: 0;
    }
    .introduce-content h2 {
        /* vmin是相对于页面大小的4% */
        font-size: 5vmin;
    }
    .introduce-content h1 {
        color: #3ad7ff;
        font-size: 8vmin;
        margin-top: 30px;
        margin-bottom: 30px;
    }
    .nav-link{
        margin: 0 30px;
        background: #3ad7ff;
        padding: 10px 15px;
        border-radius: 10px;
        color: black;
    }
    .nav-link:hover{
        color: whitesmoke;
        background-color: #2da8cb;
    }
</style>