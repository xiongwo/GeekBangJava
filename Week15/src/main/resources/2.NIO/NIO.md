NIO

关于 NIO，可能在实际开发中直接使用的机会比较少，间接使用的机会可能多一点，因为 Netty、Spring WebFlux、Spring Gateway 都是基于 NIO 开发的。但也是有必要去了解一下其背后的机制，因为排错定制都有可能会用上。

而在 Java 中说的 NIO，其实是包含 I / O 模型和线程模型，I / O 模型主要受制于操作系统，Java NIO 本质上也是调用操作系统的接口，而线程模型，在 Java 中可以优化，用得比较多的应该是主从 Reactor 模型，用线程池 Acceptor 去接收客户端的连接请求，Reactor 建立连接并注册，然后依赖 Selector 循环监听，最后将事件交给对应的 Handler。