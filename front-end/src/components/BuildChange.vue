<template>
    <div class="mapBody" style="float: right; width: 85%">
        <div id="beforeMap" class="map1"></div>
        <div id="afterMap" class="map2"></div>
    </div>
</template>

<script>
    import 'ol/ol.css';
    import {Map, View} from 'ol';
    import ImageLayer from 'ol/layer/Image';
    import Projection from 'ol/proj/Projection';
    import Static from 'ol/source/ImageStatic';
    import {getCenter} from 'ol/extent';

    export default {
        name: "BuildChange",
        data() {
            return {
                Map1: null,
                Map2: null,
                ViewCommon: null,
                img1Url: '',
                img2Url: '',
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

            that.$bus.$on('buildChange', (data) => {
                console.log("@@@@@", data)
                this.img1Url = 'http://localhost:8080/api' + data[0]
                this.img2Url = 'http://localhost:8080/api' + data[1]
                console.log('1111@@@@', this.img1Url)
                console.log('2222@@@@', this.img2Url)
            })

            this.ViewCommon = new View({
                projection: that.projection,
                center: getCenter(that.extent),
                zoom: 2,
                maxZoom: 8,
            })

            that.Map1 = new Map({
                target: 'beforeMap',
                layers: [
                    new ImageLayer({
                        source: new Static({
                            url: that.img1Url,
                            projection: that.projection,
                            imageExtent: that.extent
                        })
                    })
                ],
                view: that.ViewCommon
            })

            that.Map2 = new Map({
                target: 'afterMap',
                layers: [
                    new ImageLayer({
                        source: new Static({
                            url: that.img2Url,
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
    .mapBody {
        width: 60%;
        height: 400px;
        background-color: green;
    }
    .map1 {
        width: 49%;
        height: 400px;
        float: left;
    }
    .map2 {
        width: 49%;
        height: 400px;
        float: right;
    }
</style>