<template>
    <div class="show-func">
        <div class="title">
            <h1>人工智能解译场景</h1>
            <div class="line"></div>
            <p class="function-note">基于深度学习技术解译遥感图像，多方面提升影像的自动化分析、应用能力。</p>
            <div class="title-line"></div>
        </div>

        <div class="func-example">
            <ul class="func-list">
                <li>
                    <div @click="onBuildChange" class="func-logo">
                        <img src="../assets/func-logo1.png" width="35" height="35">
                        <p>建筑变化</p>
                    </div>
                </li>
                <li>
                    <div @click="onTargetExtract" class="func-logo">
                        <img src="../assets/func-logo2.png" width="35" height="35">
                        <p>目标提取</p>
                    </div>
                </li>
                <li>
                    <div @click="onTargetMonitor" class="func-logo">
                        <img src="../assets/func-logo3.png" width="35" height="35">
                        <p>目标检测</p>
                    </div>
                </li>
                <li>
                    <div @click="onFeatureClassify" class="func-logo">
                        <img src="../assets/func-logo4.png" width="35" height="35">
                        <p>地物分类</p>
                    </div>
                </li>
            </ul>
        </div>

        <div v-bind:class="examples">
            <div v-show="value === 1" class="func-link">
                <router-link to="/apply/change">建筑变化解译场景</router-link>
                <router-link to="/apply/change" class="function-btn">查看解译效果</router-link>
            </div>
            <div v-show="value === 2" class="func-link">
                <router-link to="/apply/extract">目标提取解译场景</router-link>
                <router-link to="/apply/extract" class="function-btn">查看解译效果</router-link>
            </div>
            <div v-show="value === 3" class="func-link">
                <router-link to="/apply/monitor">目标检测解译场景</router-link>
                <router-link to="/apply/monitor" class="function-btn">查看解译效果</router-link>
            </div>
            <div v-show="value === 4" class="func-link">
                <router-link to="/apply/classify">地物分类解译场景</router-link>
                <router-link to="/apply/classify" class="function-btn">查看解译效果</router-link>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "FunctionShow",
        data() {
            return {
                value: 0,
                examples: 'exchange-example'
            }
        },
        methods: {
            onBuildChange() {
                this.value = 1
                this.examples = "exchange-example"
            },
            onTargetExtract() {
                this.value = 2
                this.examples = "extract-example"
            },
            onTargetMonitor() {
                this.value = 3
                this.examples = "monitor-example"
            },
            onFeatureClassify() {
                this.value = 4
                this.examples = "classify-example"
            }
        }
    }
</script>

<style scoped>
    .show-func{
        width: 100%;
        height: 100vh;
        color: whitesmoke;
        background: #040913;
        padding:4% 0 5% 0;
        /*padding-top: 60px;*/
        position: relative;
    }
    .title{
        text-align: center;
        font-size: 3vmin;
        color: #f8f6f8;
    }
    div.line{
        margin-top: 10px;
        margin-bottom: 10px;
    }
    .title-line{
        height: 1px;
        width: 100%;
        margin-top: 25px;
        background-color: #765a5a;
    }
    p.function-note{
        font-size: 2vmin;
        color: #d0cdd0;
    }
    .func-example{
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100vw;
    }
    .func-list{
        display: flex;
        align-items: center;
    }
    .func-list li{
        margin: 30px 30px;
    }
    .func-logo{
        text-align: center;
        color: #aca5ac;
        font-size: 2.5vmin;
    }
    .func-logo:hover{
        color: white;
        cursor: pointer;
    }
    .exchange-example{
        /*父类：相对定位*/
        position: relative;
        margin: auto;
        width: 60vw;
        height: 50vh;
        color: whitesmoke;
        background: url("../assets/building-change.png") no-repeat center center;
        background-size: cover;
        text-align: center;
        border-radius: 10px;
    }
    .extract-example{
        position: relative;
        margin: auto;
        width: 60vw;
        height: 50vh;
        color: whitesmoke;
        background: url("../assets/test_1.png") no-repeat center center;
        background-size: cover;
        text-align: center;
        border-radius: 10px;
    }
    .monitor-example{
        position: relative;
        margin: auto;
        width: 60vw;
        height: 50vh;
        color: whitesmoke;
        background: url("../assets/building-change.png") no-repeat center center;
        background-size: cover;
        text-align: center;
        border-radius: 10px;
    }
    .classify-example{
        position: relative;
        margin: auto;
        width: 60vw;
        height: 50vh;
        color: whitesmoke;
        background: url("../assets/test_1.png") no-repeat center center;
        background-size: cover;
        text-align: center;
        border-radius: 10px;
    }
    .func-link{
        /*子类：绝对定位*/
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        display: inline-block;
        height: auto;
        align-items: center;
    }
    .func-link a{
        font-size: 25px;
        display: block;
        color: white;
        margin:10px 20px;
    }
    .func-link a.function-btn{
        color: black;
        font-size: 2.5vmin;
        padding: 10px 20px;
        border: 1px white solid;
        border-radius: 10px;
    }
    .func-link a.function-btn:hover{
        background-color: darkturquoise;
        border: none;
        cursor: pointer;
    }
</style>