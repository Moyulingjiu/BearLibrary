# API详细文档

[TOC]

> **注1：**
>
> 对于所有的api都表示data内部的内容，而省略掉统一格式的其他部分。
>
> 返回格式统一为：
>
> ```json
> {
>     "msg": "消息",
>     "code": 状态码,
>     "data": {
>         // 数据载体
>     },
>     "timestamp": "时间戳"
> }
> ```
>
> 例如登陆：
>
> ```json
> {
>     "msg": "成功",
>     "code": 200,
>     "data": "token",
>     "timestamp": "2022-05-06 22:10:37.703"
> }
> ```
>
> 那么输出只会写
>
> ```json
> token
> ```
>
> 再比如查询自己的数据：
>
> ```json
> {
>     "msg": "成功",
>     "code": 200,
>     "data": {
>         "id": 1,
>         "name": "bear",
>         "invitationCodeId": 1,
>         "nickname": "",
>         "avatar": "",
>         "birthday": "",
>         "gender": "UNKNOWN",
>         "phone": "",
>         "honorPoint": 0,
>         "selfControlPoint": 0,
>         "contributionPoint": 0,
>         "walk": 0,
>         "read": 0,
>         "sport": 0,
>         "art": 0,
>         "practice": 0,
>         "gmtCreate": "2022-05-06 13:56:16.000"
>     },
>     "timestamp": "2022-05-06 22:18:17.332"
> }
> ```
>
> 输出只会写：
>
> ```json
> {
>     "id": 1,
>     "name": "bear",
>     "invitationCodeId": 1,
>     "nickname": "",
>     "avatar": "",
>     "birthday": "",
>     "gender": "UNKNOWN",
>     "phone": "",
>     "honorPoint": 0,
>     "selfControlPoint": 0,
>     "contributionPoint": 0,
>     "walk": 0,
>     "read": 0,
>     "sport": 0,
>     "art": 0,
>     "practice": 0,
>     "gmtCreate": "2022-05-06 13:56:16.000"
> }
> ```
>
> 

> **注2：**
>
> 所有的请求地址为：**前缀+请求地址**。
>
> 例如注册为：`/user/register`。而不是直接的`/register`。

> **注3：**
>
> 标注`（管理员）`表示需要管理员token
>
> 标注`（用户）`表示需要用户token
>
> 无标注表示不需要token

## 实体介绍

### 用户类（user）

| 字段 | 介绍 |
| ---- | ---- |
|      |      |
|      |      |
|      |      |



### 管理员（administrator）



## 用户：`/user`

### 【post】注册：`/register`

> 对于直接使用账号密码的用户，使用该API进行注册。微信登陆/注册都是一起使用另一个api

输入：

```json
{
    "code": "邀请码",
    "name": "用户名",
    "password": "密码"
}
```

输出：

```json
token
```



### 【post】登陆：`/login`

输入：

```json
{
    "name": "用户名",
    "password": "密码"
}
```

输出：

```json
token
```



### 【post】微信登陆：`/wxlogin`

输入：

```json
{
    // 暂时不详
}
```

输出：

```json
token
```



### 【get】获取自己的信息：`/user/self`（用户）

输入：

```json
```

输出：

```json
{
    "id": 1,
    "name": "bear",
    "invitationCodeId": 1,
    "nickname": "",
    "avatar": "",
    "birthday": "",
    "gender": "UNKNOWN",
    "phone": "",
    "honorPoint": 0,
    "selfControlPoint": 0,
    "contributionPoint": 0,
    "walk": 0,
    "read": 0,
    "sport": 0,
    "art": 0,
    "practice": 0,
    "gmtCreate": "2022-05-06 13:56:16.000"
}
```



### 【get】获取其他人信息：`/user/{id}`（用户）

输入：

```json

```

输出：

```json
{
    "id": 1,
    "name": "bear1",
    "nickname": "",
    "avatar": "",
    "birthday": "",
    "gender": "UNKNOWN",
    "phone": "",
    "walk": 0,
    "read": 0,
    "sport": 0,
    "art": 0,
    "practice": 0
}
```



