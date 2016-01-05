1.服务器端创建自签证书

	keytool -genkey -dname "CN=mars64, OU=Rest, O=JaxRs2, L=HaiDian, ST=Beijing, C=China" -alias server -keyalg RSA -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restServer.keystore -keypass restful -storepass restful -validity 60

CN使用测试主机的hostname，其他选项没有要求。

2.服务器端导出自签证书

	keytool -export -alias server -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restServer.keystore -storepass restful -rfc -file D:\-aries\github\jax-rs2-guide\sample\6\security-rest\certificate\restServer.cer

3.客户端创建自签证书

	keytool -genkey -dname "CN=mars64, OU=Rest, O=JaxRs2, L=HaiDian, ST=Beijing, C=China" -alias admin -keyalg RSA -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restAdminClient.keystore -keypass restful -storepass restful -validity 60
	
	keytool -genkey -dname "CN=mars64, OU=Rest, O=JaxRs2, L=HuangGu, ST=Shenyang, C=China" -alias user -keyalg RSA -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restUserClient.keystore -keypass restful -storepass restful -validity 60

4.客户端导入服务器证书

	keytool -importcert -noprompt -trustcacerts -alias server -file D:\-aries\github\jax-rs2-guide\sample\6\security-rest\certificate\restServer.cer -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restAdminClient.keystore -storepass restful -keypass restful
	keytool -importcert -noprompt -trustcacerts -alias server -file D:\-aries\github\jax-rs2-guide\sample\6\security-rest\certificate\restServer.cer -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restUserClient.keystore -storepass restful -keypass restful
	
	keytool -list -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restUserClient.keystore -storepass restful

5.客户端导出自签证书

	keytool -export -alias admin -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restAdminClient.keystore -storepass restful -rfc -file D:\-aries\github\jax-rs2-guide\sample\6\security-rest\certificate\restAdminClient.cer
	
	keytool -export -alias user -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restUserClient.keystore -storepass restful -rfc -file D:\-aries\github\jax-rs2-guide\sample\6\security-rest\certificate\restUserClient.cer

6.服务器端导入客户端证书

	keytool -importcert -noprompt -trustcacerts -alias admin -file D:\-aries\github\jax-rs2-guide\sample\6\security-rest\certificate\restAdminClient.cer -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restServer.keystore -storepass restful -keypass restful
	
	keytool -importcert -noprompt -trustcacerts -alias user -file D:\-aries\github\jax-rs2-guide\sample\6\security-rest\certificate\restUserClient.cer -keystore D:\-aries\github\jax-rs2-guide\sample\6\security-rest\keystore\restServer.keystore -storepass restful -keypass restful

7.服务器端权限配置

	<user username="CN=mars64, OU=Rest, O=JaxRs2, L=HaiDian, ST=Beijing, C=China" password="null" roles="admin"/>
	<user username="CN=mars64, OU=Rest, O=JaxRs2, L=HuangGu, ST=Shenyang, C=China" password="null" roles="user"/>