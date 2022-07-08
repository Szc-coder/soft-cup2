<template>
    <div>
        <div id="map" class="map-style"></div>
        <img :src="imgUrl">  <!-- 临时展示图能否访问 -->
    </div>
</template>

<script>
    import Projection from "ol/proj/Projection";
    import {Map, View} from "ol";
    import {getCenter} from "ol/extent";
    import ImageLayer from "ol/layer/Image";
    import Static from "ol/source/ImageStatic";

    export default {
        name: "TargetExtract",
        data() {
            return {
                map: null,
                imgUrl: '',
                ViewCommon: null,
                extent: [0, 0, 1024, 1024],
                projection: new Projection({
                    code: 'xkcd-image',
                    units: 'pixels',
                    extent: this.extent,
                })
            }
        },
        mounted() {
            const that = this

            that.$bus.$on('targetExtract', (data) => {
                console.log("@@@@@", data)
                this.imgUrl = 'http://localhost:8080/api' + data
                console.log('#####', this.imgUrl)
            })
            
            this.ViewCommon = new View({
                projection: that.projection,
                center: getCenter(that.extent),
                zoom: 2,
                maxZoom: 8,
            })

            console.log(that.imgUrl)

            that.Map = new Map({
                target: 'map',
                layers: [
                    new ImageLayer({
                        source: new Static({
                            url: that.imgUrl,
                            projection: that.projection,
                            imageExtent: that.extent
                        })
                    })
                ],
                view: that.ViewCommon
            })
        }
    }
</script>

<style scoped>

</style>