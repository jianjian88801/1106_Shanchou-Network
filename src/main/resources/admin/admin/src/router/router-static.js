import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import canyuzhongchou from '@/views/modules/canyuzhongchou/list'
    import dictionary from '@/views/modules/dictionary/list'
    import shangpin from '@/views/modules/shangpin/list'
    import shangpinCollection from '@/views/modules/shangpinCollection/list'
    import shangpinLiuyan from '@/views/modules/shangpinLiuyan/list'
    import yonghu from '@/views/modules/yonghu/list'
    import zhongchou from '@/views/modules/zhongchou/list'
    import zhongchouCollection from '@/views/modules/zhongchouCollection/list'
    import zhongchouLiuyan from '@/views/modules/zhongchouLiuyan/list'
    import config from '@/views/modules/config/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShangpin from '@/views/modules/dictionaryShangpin/list'
    import dictionaryShangpinCollection from '@/views/modules/dictionaryShangpinCollection/list'
    import dictionaryShangxia from '@/views/modules/dictionaryShangxia/list'
    import dictionaryZhongchou from '@/views/modules/dictionaryZhongchou/list'
    import dictionaryZhongchouCollection from '@/views/modules/dictionaryZhongchouCollection/list'
    import dictionaryZhongchouKuaidi from '@/views/modules/dictionaryZhongchouKuaidi/list'
    import dictionaryZhongchouZhuangtai from '@/views/modules/dictionaryZhongchouZhuangtai/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShangpin',
        name: '商品类型',
        component: dictionaryShangpin
    }
    ,{
        path: '/dictionaryShangpinCollection',
        name: '收藏表类型',
        component: dictionaryShangpinCollection
    }
    ,{
        path: '/dictionaryShangxia',
        name: '上下架',
        component: dictionaryShangxia
    }
    ,{
        path: '/dictionaryZhongchou',
        name: '众筹类型',
        component: dictionaryZhongchou
    }
    ,{
        path: '/dictionaryZhongchouCollection',
        name: '收藏表类型',
        component: dictionaryZhongchouCollection
    }
    ,{
        path: '/dictionaryZhongchouKuaidi',
        name: '快递状态',
        component: dictionaryZhongchouKuaidi
    }
    ,{
        path: '/dictionaryZhongchouZhuangtai',
        name: '众筹状态',
        component: dictionaryZhongchouZhuangtai
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/canyuzhongchou',
        name: '参与的众筹',
        component: canyuzhongchou
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/shangpin',
        name: '商品',
        component: shangpin
      }
    ,{
        path: '/shangpinCollection',
        name: '商品收藏',
        component: shangpinCollection
      }
    ,{
        path: '/shangpinLiuyan',
        name: '商品留言',
        component: shangpinLiuyan
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }
    ,{
        path: '/zhongchou',
        name: '众筹',
        component: zhongchou
      }
    ,{
        path: '/zhongchouCollection',
        name: '众筹收藏',
        component: zhongchouCollection
      }
    ,{
        path: '/zhongchouLiuyan',
        name: '众筹留言',
        component: zhongchouLiuyan
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
