# 基于[·Tomcat安全·官方文档](http://tomcat.apache.org/tomcat-7.0-doc/realm-howto.html) 的实践#

# Realm #
类似Unix的groups 根据指定的role访问资源
## 6个标准插件 ##
### 1. JDBCRealm + BASIC认证###
[JDBCRealm + BASIC认证](1.md)

### 2. DataSourceRealm + FORM认证###
[DataSourceRealm + FORM认证](2.md)

### 3. UserDatabaseRealm + DIGEST认证 ###
[UserDatabaseRealm + DIGEST认证](3.md)

### 4. JAASRealm + FORM认证 ###
[JAASRealm + FORM认证](4.md)

### 5. MemoryRealm ###
 - Accesses authentication information stored in an in-memory object collection, which is initialized from an XML document (conf/tomcat-users.xml).
 - 通过读取XML格式内存集合对象，获取认证信息

这是一个过时的Realm，被UserDatabaseRealm取代

### 6. JNDIRealm ###
 - Accesses authentication information stored in an LDAP based directory server, accessed via a JNDI provider.
 - 通过JNDI访问LDAP服务器，获取认证信息

## 自定义Realm ##
1. Implement org.apache.catalina.Realm 编写实现org.apache.catalina.Realm接口的类
1. Place your compiled realm in $CATALINA_HOME/lib
将编译过的类放到$CATALINA_HOME/lib目录
1. Declare your realm as described in the "Configuring a Realm" section below
定义该类的realm描述--参考Configuring a Realm一节
1. Declare your realm to the MBeans Descriptor
定义MBeans描述
## Configuring a Realm ##
通常在配置文件conf/server.xml中定义如下：
> <Realm className="实现org.apache.catalina.Realm接口的类全名"
       ... 该实现的其它属性 .../>

Realm项可以内置于任何一个*容器项*中，这将影响该realm的作用域(scope)：

- 内置于*Engine项* 该Realm将作用于所有hosts中的web应用，除非在*Host项*或者*Context项*中被覆盖
- 内置于*Host项* 该Realm将作用于所有virtual host中的web应用，除非在*Context项*或者*Context项*中被覆盖
- 内置于*Context项* 该Realm将只作用于该web应用

## 摘要密码 ##
摘要算法：

java.security.MessageDigest class (SHA, MD2, or MD5)

获取摘要密码：

- org.apache.catalina.realm.RealmBase.Digest()	
- CATALINA_HOME/bin/digest.[bat|sh] -a {algorithm} {cleartext-password}