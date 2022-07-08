<template>
    <div class="home">
        <Introduce/>
        <FunctionShow/>
    </div>
</template>

<script>
    import Introduce from "@/pages/Introduce";
    import FunctionShow from "@/pages/FunctionShow";

    export default {
        name: "Home",
        components: { Introduce, FunctionShow }
    }
</script>

<style scoped>
    .home {
        width: 100%;
        height: 100%;
        overflow-x: hidden;
    }
</style>