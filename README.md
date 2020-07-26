# ChaForward Plugin  
将Minecraft中的聊天信息转发至HTTP服务器进行对接  
## 使用方法
下载最新的Releases版本，扔进Plugins文件夹，使用plugman加载或重启服务器  
## 配置文件
config.yml  
 - enable
 - url
 - server
# 指令
 - chatforward help  获取帮助
 - chatforward reload  重载配置
 - chatforward on  开启转发
 - chatforward off  关闭转发
 - chatforward url  获取当前url
 - chatforward url \<url\>  设置url
 - chatforward server  获取当前服务器名称
 - chatforward server \<ServerName\>  设置服务器名称
## 权限节点
 - ChatForward.Admin  
   - 指令需要该权限  
   - 拥有该权限节点的玩家在POST数据中Perm将会传递ADMIN  
   - 无该权限节点玩家在POST数据中Perm将会传递USUAL  
   - OP默认拥有此权限  
## Web地址
在config.yml中修改url配置  
```
默认: http://127.0.0.1:8081/post  
```
## POST数据格式
text数据会经过URL编码
```$xslt
name=xxx&perm=xxx&text=xxx&server=xxx  
```
## 服务器名称
在config.yml中修改server配置  
```
默认: XXX  
```
## 开源协议
MIT License