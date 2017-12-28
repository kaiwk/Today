
### Today

+ todo
+ 天气查询
+ 新闻

![Today](image/flux0.png)

1. fluxbase包下的类只和EventBus有耦合, 所以是可复用的, 可以根据自己的情况做出改进
2. 这个项目的核心库是EventBus, 除此之外所有的库都是非必需的
3. Dispatcher可以用EventBus/Otto实现, 如果使用了RxJava, 可以实现RxBus替代

### Reference

+ [Flux介绍](https://github.com/Kermit95/Today/wiki)
+ [lgvalle](https://github.com/lgvalle/android-flux-todo-app)
+ [AndroidFlux一览](http://androidflux.github.io/docs/overview.html#content)
+ [ntop](http://www.jianshu.com/p/896ce1a8e4ed)
