<template>
    <div>
        <h2>这是地物分类</h2>
        <el-upload></el-upload>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        name: "FeatureClassify",
        data() {
            return {
                listData: {
                    'name': '',
                    'provinceName': '',
                    'cityName': '',
                    'admin': ''
                }
            }
        },
        mounted() {
            // axios.get('/api/warehouse/showWarehouse', {
            //     params: {
            //         name: 'warehouseA'
            //     }
            // })
            //     .then((response) => {
            //     this.listData = response.data
            // })
            //     .catch((error) => {
            //         console.log(error)
            //     })

            // /comics/online_communities.png
        }
    }
</script>

<style scoped>

</style>