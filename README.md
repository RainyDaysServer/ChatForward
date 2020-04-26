# ChaForward Plugin  
将Minecraft中的聊天信息转发至HTTP服务器进行对接  
## 使用方法
下载最新的Releases版本，扔进Plugins文件夹，使用plugman加载或重启服务器  
## 配置文件
config.yml  
 - enable
 - url
# 指令
 - chatforward help
 - chatforward reload
 - chatforward on
 - chatforward off
 - chatforward url
 - chatforward url \<url\>
## 权限节点
 - ChatForward.Admin  
   - 指令需要该权限  
   - 拥有该权限节点的玩家在POST数据中Perm将会传递ADMIN  
   - 无该权限节点玩家在POST数据中Perm将会传递USUAL  
   - OP默认拥有此权限  
## Web地址
在config.yml中修改url配置  
默认: http://127.0.0.1:8081/post  
```
## POST数据格式
text数据会经过URL编码
```$xslt
name=xxx&perm=xxx&text=xxx
```
## 开源协议
MIT License