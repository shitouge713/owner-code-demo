# Java Agent探针
实现一个简单性能工具，通过探针统计Java程序所有方法的执行时间 
1、构建 Maven 项目工程，添加 MANIFEST.MF
2、在 MANIFEST.MF文件中定义Premain-Class属性，指定一个实现类。类中实现了Premain方法，这就是Java Agent 在类加载启动入口
Premain-Class包含Premain方法的类
Can-Redefine-Classes为true时表示能够重新定义Class
Can-Retransform-Classes为true时表示能够重新转换Class，实现字节码替换
3、构建Premain方法
4、编写类转换器 PreMainTransformerDemo
5、pom文件添加assembly插件配置
6、通过右侧plugins assembly:single打包（注意：和package打包不同，package打的包不能使用）
7、写一个Java测试程序启动类如：AgentDemo，验证探针是否生效
8、启动参数VM Options中配置：-javaagent:E:\owner-code\owner-code-demo\target\owner-code-demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar
  包为实际存储的路径
9、debug或run启动即可
