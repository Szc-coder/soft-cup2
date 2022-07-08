<template>
    <div style="width: 100%; margin-top: 50px">
        <Navigator style="float: left; width: 15%"></Navigator>
        <router-view></router-view> <!-- 展示具体的功能页面 -->
    </div>
</template>

<script>
    import BuildChange from "@/components/BuildChange";
    import TargetExtract from "@/components/TargetExtract";
    import TargetMonitor from "@/components/TargetMonitor";
    import FeatureClassify from "@/components/FeatureClassify";
    import Navigator from "@/pages/Navigator";

    export default {
        name: "Apply",
        components: { BuildChange, TargetExtract, TargetMonitor, FeatureClassify, Navigator },
        methods: {

        }
    }
</script>

<style scoped>

</style>