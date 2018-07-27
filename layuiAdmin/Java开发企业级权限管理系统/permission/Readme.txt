项目启动注意事项：

1、数据库配置：/resources/settings.properties
2、redis配置：/resources/redis.properties
3、项目登录页：/signin.jsp
4、登录使用用户名和密码：
username: admin@qq.com
password: 12345678

其他：
1、如果暂时不想使用redis，如何移除
1) applicationContext.xml里移除 <import resource="redis.xml" />
2) 修改RedisPool.java 类取消被spring管理
3）修改SysCacheService.java 类移除RedisPool.java的使用

2、如果想在正式环境使用，需要注意哪些
1）如果是集群部署，需要配置session共享，保证登录一次就可以，体验好
可以参考一下：http://blog.csdn.net/pingnanlee/article/details/68065535
2）确认一下项目里超级管理员的权限的规则
代码位置：SysCoreService.java类里的isSuperAdmin()
3）新增管理员的密码处理
SysUserService.java里的save() 方法里需要移除 password = "12345678";
同时，MailUtil里的发信参数要补全，并在SysUserService.java里的save()里 sysUserMapper.insertSelective(user) 之前调用
这是默认给的逻辑，可以根据项目实际情况调整


