# feign-multiple-pojos
a demo supports mutiple pojos and file arrs with FeignClient's default Contract by rewrting form-encoder.

First,you should use feignContract instead of SpringMVCContract.
So, you should use @RequestLine to write your url and @Param mark your params in FeignClient.

the most important config under the directory com.neo.config, you can see FeignConfiguration.java especially.

I make 3 dirs just like D:\testFile, D:\testFile\producer, D:\testFile\temp For test.you could make same dirs manually for testing upload File.

使用feign自带Contract，通过重写编码器支持多个pojo与多文件传输。

文件上传demo,用了三个文件夹 D:\testFile, D:\testFile\producer, D:\testFile\temp写死了三个文件夹用于测试文件上传相关，手动在电脑中建立一下。Mac自己改一下代码中配置的路径。

最重要的配置都在config目录下,尤其是FeignConfiguration。

Feign默认是不支持多实体传输的。
这里需要注意<br>

一是feign默认是采用
SpringMvcContract注解翻译器。我们需要在一个FeignConfiguration文件中声明feign.Contract.Default(),使用feign自带的注解。
这样例子中的编码器才会生效。
具体的调用示例见代码
详细教程参加我的博客 https://blog.csdn.net/qq_34523427/article/details/88863800
