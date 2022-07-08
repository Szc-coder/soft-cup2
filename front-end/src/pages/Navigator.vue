<template>
    <!-- 左侧工具栏，用于展示四种功能进行选择和图片上传 -->
    <div class="content">
        <!--选择功能区-->
        <div class="func-content">
            <p class="func-title"><i class="iconfont icon-liebiao"></i><span>解译算法</span></p>
            <div class="choose-func">
                <div class="row1">
                    <a @click="onBuildChanges" class="box"><i class="iconfont icon-cangku"></i><span class="tip-text">变化检测</span></a>
                    <a @click="onTargetExtract" class="box"><i class="iconfont icon-zhuti_tiaosepan"></i><span class="tip-text">目标提取</span></a>
                </div>
                <div class="row2">
                    <a @click="onTargetMonitor" class="box"><i class="iconfont icon-qianbao"></i><span class="tip-text">目标检测</span></a>
                    <a @click="onFeatureClassify" class="box"><i class="iconfont icon-tupian"></i><span class="tip-text">地物分类</span></a>
                </div>
            </div>
        </div>

        <!--点击按钮上传图片-->
        <div>
            <el-upload class="upload-demo"
                       action=""
                       :auto-upload="false"
                       :multiple="true"
                       :file-list="fileList"
                       list-type="picture"
                       :headers="{'Content-Type': 'multipart/form-data'}"
                       :on-change="handleChange"
                       :on-remove="handleRemove"
                       :limit="limit">
                <el-button slot="trigger" type="primary">选取图片</el-button>
                <el-button type="primary" @click="uploadForm">点击上传</el-button>
                <div slot="tip">{{message}}</div>
            </el-upload>
        </div>
    </div>
</template>

<script>

    import axios from "axios";

    export default {
        name: "Navigator",
        data() {
            return {
                fileList: [],
                limit: 2,
                functionType: 1,
                imgUrl: '/api/Inf/ChangeDetection/uploadAndRun/files',
                message: '上传2张png格式图片',
            }
        },
        methods: {
            // 建筑变化路由
            onBuildChanges() {
                this.$router.push({
                    path: '/apply/change'
                })
                this.limit = 2
                this.functionType = 1
                this.imgUrl = '/api/Inf/ChangeDetection/uploadAndRun/files'
                this.message = '上传2张png格式图片'
            },
            // 目标提取路由
            onTargetExtract() {
                this.$router.push({
                    path: '/apply/extract'
                })
                this.limit = 1
                this.functionType = 2
                this.imgUrl = '/api/Inf/TargetExtraction/uploadAndRun/files'
                this.message = '上传1张png格式图片'
            },
            // 目标监测路由
            onTargetMonitor() {
                this.$router.push({
                    path: '/apply/monitor'
                })
                this.limit = 1
                this.functionType = 3
                this.imgUrl = '/api//Inf/ObjectDetection/uploadAndRun/files'
                this.message = '上传1张png格式图片'
            },
            // 特征分类路由
            onFeatureClassify() {
                this.$router.push({
                    path: '/apply/classify'
                })
                this.limit = 1
                this.functionType = 4
                this.imgUrl = '/api/Inf/ClassificationOfFeatures/uploadAndRun/files'
                this.message = '上传1张png格式图片'
            },
            // 选择文件时，在fileList里修改
            handleChange(file, fileList) {
                this.fileList = fileList
            },
            // 删除图片是，在fileList里修改
            handleRemove(file, fileList) {
                this.fileList = fileList
            },
            // 批量上传
            uploadForm() {
                if (this.fileList.length < this.limit) {
                    this.$message.warning('请选择文件')
                    return
                }
                console.log(this.fileList)
                const formData = new FormData()
                this.fileList.forEach(file => {
                    formData.append('files', file.raw)
                })
                axios.post(this.imgUrl, formData)
                     .then(response => {
                         console.log('图片上传成功')
                         this.$message.success('图片上传成功')
                         response.data
                         if (this.functionType === 1) {
                             this.$bus.$emit('buildChange', response.data)
                         } else if (this.functionType === 2) {
                             this.$bus.$emit('targetExtract', response.data)
                         } else if (this.functionType === 3) {
                             this.$bus.$emit('targetMonitor', response.data)
                         } else if (this.functionType === 4) {
                             this.$bus.$emit('featureClassify', response.data)
                         }
                     })
                     .catch(error => {
                         console.log('图片上传失败')
                         this.$message.success('图片上传失败')
                     })
            }
        }
    }
</script>

<style scoped>
    .content{
        position: fixed;
        left: 0;
        bottom: 0;
        top: 50px;
        width: 15%;
        display: flex;
        flex-direction: column;
        justify-content: space-evenly;
        background-color: #060b15;
        z-index: 9999;
        color: whitesmoke;
    }
    span{
        margin-left: 20px;
    }
    .func-content{
        flex: 1;
        border: 1px white solid;
        border-radius: 10px;
        padding: 10px 10px;
        margin: 10px 10px 6px 10px;
        position: relative;
    }
    .upload-image{
        flex:2;
        border: 1px solid white;
        border-radius: 3px;
        margin: 2px 10px 5px 10px;
        color: whitesmoke;
        text-align: center;
        vertical-align: center;
        font-size: 2vmin;
    }
    .choose-func{
        position: absolute;
        display: flex;
        justify-content: center;
        margin-left: 0;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
    }
    .func-title{
        font-size: 2.5vmin;
    }
    .box{
        margin: 10px 10px;
    }
    .box i{
        font-size: 6vmin;
        border:1px white solid;
        border-radius: 100%;
    }
    .box i:hover{
        cursor: pointer;
        background-color: #8a90a0;
    }
    /*修改样式，当鼠标移动到logo上时，可以提示当前功能*/
    .box{
        position: relative;
        display: inline-block;
    }
    .tip-text{
        visibility: hidden;
        background-color: black;
        width: 5vw;
        color: whitesmoke;
        font-size: 2vmin;
        text-align: center;
        border-radius: 6px;
        padding: 5px 0;

        position: absolute;
        z-index: 1;
        top: 80%;
        left: 90%;
        margin-left: -50%;

        opacity: 0;
        transition: opacity 1s;
    }
    .box:hover .tip-text{
        visibility: visible;
        opacity: 1;
    }
</style>