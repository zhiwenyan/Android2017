1.效果分析
1.1 写死效果，每个页面都写一个 （自定义View，代码封装）  
1.2写活，每个页面都可以用同一个  
1.3 布局 = LinearLayout = LinearLayout(放 Tab 点击的View) + 阴影（View 透明度变化 ) + 菜单的内容(FrameLayout)      

步骤：
1. 布局实例化好（组合控件）  
2. 引入Adapter设计模式去适配Tab的样式是不一样的，不同页面不同的项目中是不一样的，所以我们要用Adapter设计模式去适配 View (ListView)  
3. 执行动画  
4. 测试  
5. Adapter设计模式介绍做类的适配，参考 ListView的Adapter设计模式  ， 参考 BannerView 的Adapter方式，流失布局的 Adapter 方式 （自定义的11）  
6. 观察者设计模式介绍  
