import VueRouter from 'vue-router';

import Home from "@/pages/Home";
import Apply from "@/pages/Apply";

import BuildChange from "@/components/BuildChange";
import TargetExtract from "@/components/TargetExtract";
import TargetMonitor from "@/components/TargetMonitor";
import FeatureClassify from "@/components/FeatureClassify";

export default new VueRouter({
    routes: [
        {
            path: '/',
            name: '',
            component: Home
        },
        {
            path: '/home',
            name: '主页',
            component: Home
        },
        {
            path: '/apply',
            name: '应用',
            component: Apply,
            children: [
                {
                    path: '',
                    name: '',
                    component: BuildChange
                },
                {
                    path: 'change',
                    name: '建筑变化',
                    component: BuildChange
                },
                {
                    path: 'extract',
                    component: TargetExtract
                },
                {
                    path: 'monitor',
                    component: TargetMonitor
                },
                {
                    path: 'classify',
                    component: FeatureClassify
                }
            ]
        },
    ]
})