import Vue from 'vue'
import VueRouter from 'vue-router'
import token from "../../public/js/token";

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/login.vue')
    }
  },
  {
    path: '/search',
    name: 'search',
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/front/search.vue')
    },
    meta:{
      requireLogin: true
    }
  },
  {
    path: '/detail/:id',
    name: 'detail',
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/front/Detail.vue')
    },
    meta:{
      requireLogin: true
    }
  },
  {
    path: '/',
    name: 'login',
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/front/index.vue')
    },
    meta:{
      requireLogin: true
    }
  },
  {
    path: '/register',
    name: 'register',
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/register.vue')
    },
    meta:{
      requireLogin: true
    }
  },
  {
    path: '/home',
    name: 'home',
    component: () => import(/* webpackChunkName: "about" */ '../views/admin/main.vue'),
    redirect: '/system/welcome',
    children: [
      {
        path: '/system/welcome',
        name: 'Welcome',
        component: () => import( '../views/admin/home.vue'),
      },
      {
        path: '/article/insert',
        name: 'ArticleInsert',
        component: () => import( '../views/admin/article/insert.vue'),
      },
      {
        path: '/article/myself',
        name: 'articleMyself',
        component: () => import( '../views/admin/article/index.vue'),
      },
      {
        path: '/article/edit',
        name: 'articleEdit',
        component: () => import( '../views/admin/article/edit.vue'),
      },
      {
        path: '/photo/myself',
        name: '/photoMyself',
        component: () => import( '../views/admin/file/file.vue'),
      },
      {
        path: '/comment/myself',
        name: '/commentMyself',
        component: () => import( '../views/admin/comment/myself.vue'),
      },
      {
        path: '/comment/commentMy',
        name: '//commentCommentMy',
        component: () => import( '../views/admin/comment/receiver.vue'),
      },
      {
        path: '/admin/type',
        name: 'type',
        component: () => import( '../views/admin/type/index.vue'),
      },

      {
        path: '/admin/article',
        name: 'type',
        component: () => import( '../views/admin/article/admin.vue'),
      },

      {
        path: '/admin/comment',
        name: 'adminComment',
        component: () => import( '../views/admin/comment/list.vue'),
      },


      {
        path: '/admin/photo',
        name: 'photoList',
        component: () => import( '../views/admin/file/list.vue'),
      },

      {
        path: '/admin/user',
        name: 'userList',
        component: () => import( '../views/admin/user/list.vue'),
      },
      {
        path: '/userInfo',
        name: 'userInfo',
        component: () => import( '../views/admin/user/info.vue'),
      },
      {
        path: '/admin/data',
        name: 'data',
        component: () => import( '../views/admin/data/dataView.vue'),
      },
    ]
  }
]

const router = new VueRouter({
  routes
})

//路由导航守卫
router.beforeEach((to, from, next) => {

  if (token.get()!==null){
    next()
    console.log(to.matched.some(r=>r.meta.requireLogin))
  }else if (to.path === '/login'){
    next()
  }else  if (to.matched.some(r=>r.meta.requireLogin)) {
    next()
  } else {
    next('/login')
  }


})

export default router
