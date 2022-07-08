<template>
    <div class="header" :style="headerBackgroundColor">
        <nav class="nav-bar">
            <div class="logo">
                <img src="../assets/logo.png" width="50" height="50">
                <div class="logo-line"></div>
                <h1>EarthSense</h1>
            </div>
            <div class="nav-links">
                <router-link to="/home" class="nav-link">主页</router-link>
                <router-link to="/apply" class="nav-link">体验EarthSense</router-link>
            </div>
        </nav>
    </div>
</template>

<script>
    export default {
        name: "Header",
        data() {
            return {
                headerBackgroundColor: {
                    backgroundColor: 'black'
                }
            }
        },
        mounted() {
            addEventListener('scroll', this.handleScroll)
        },
        methods: {
            handleScroll() {
                let scrollTop = pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
                if (scrollTop) {
                    this.headerBackgroundColor.backgroundColor = `transparent`
                } else if (scrollTop === 0) {
                    this.headerBackgroundColor.backgroundColor = 'transparent'
                }
            }
        }
    }
</script>

<style scoped>
    .header{
        top: 0;
        left: 0;
        width: 100%;
        height: 50px;
        position: fixed;
        z-index: 999999;
    }
    .header .nav-bar{
        display: flex;
        position: absolute;
        width: 100%;
        /*左右对齐*/
        justify-content: space-between;
        color: whitesmoke;
        padding:0;
    }
    .nav-links{
        display: flex;
        align-items: center;
    }
    .logo{
        display: flex;
        align-items: center;
    }
    .logo-line{
        height: 40px;
        width: 2px;
        background: #9caa95;
        margin: 0 10px;
    }
    .nav-link{
        margin: 0 30px;
        padding: 5px 10px;
        color: whitesmoke;
        font-weight: bold;
    }
    .nav-link:hover{
        color: #3bd7ff;
    }
</style>