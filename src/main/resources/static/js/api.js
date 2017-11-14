//添加一个请求拦截器
axios.interceptors.request.use(function(config){
    // 在请求发送之前做一些事
    NProgress.start()
    return config;
},function(error){
    // 当出现请求错误是做一些事
    NProgress.remove();
    return Promise.reject(error);
});

//添加一个返回拦截器
axios.interceptors.response.use(function(response){
    // 对返回的数据进行一些处理
    NProgress.done()
    return response;
},function(error){
    // 对返回的错误进行一些处理
    NProgress.remove();
    return Promise.reject(error);
});