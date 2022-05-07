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
>     "status": 状态码,
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
>     "status": 200,
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
>     "status": 200,
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

> **注4：**
>
> 分页查询：
>
> ```json
> {
>     "page": 页码（从1开始）,
>     "pageSize": 页面大小,
>     "total": 总计多少页,
>     "row": list有多少条数据,
>     "hasPreviousPage": 是否有上一页,
>     "hasNextPage": 是否有下一页,
>     "list": [
>         // 数据内容
>     ]
> }
> ```
>
> 

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



### 【post】修改密码：`/user/password`（用户）

输入：

```json
{
    "oldPassword": "原密码",
    "newPassword": "新密码"
}
```

输出：

> 根据状态码来确定是否修改成功

```json

```



### 【get】管理员查询用户：`/administrator/user/{id}`（管理员）

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



### 【get】管理员分页条件查询用户：`/administrator/users`（管理员）

输入：

```java
page        页码
pageSize    页面大小
name        用户名
valid       是否没有被删除
gender      性别
phone       电话
nickname    昵称
beginTime   创建时间下限
endTime     创建时间上限
minWalk     行走经验下限
maxWalk     行走经验上限
minRead     阅读经验下限
maxRead     阅读经验上限
minSport    运动经验下限
maxSport    运动经验上限
minArt      艺术经验下限
maxArt      艺术经验上限
minPractice 实践经验下限
maxPractice 实践经验上限
```

输出：

```json
{
    "page": 1,
    "pageSize": 3,
    "total": 2,
    "row": 3,
    "hasPreviousPage": false,
    "hasNextPage": true,
    "list": [
        {
            "id": 1,
            "name": "bear",
            "nickname": "",
            "avatar": "",
            "gender": "UNKNOWN",
            "walk": 0,
            "read": 0,
            "sport": 0,
            "art": 0,
            "practice": 0,
            "valid": 1,
            "gmtCreate": "2022-05-06T13:56:16.000"
        },
        {
            "id": 2,
            "name": "bear2",
            "nickname": "",
            "avatar": "",
            "gender": "UNKNOWN",
            "walk": 0,
            "read": 0,
            "sport": 0,
            "art": 0,
            "practice": 0,
            "valid": 1,
            "gmtCreate": "2022-05-06T18:54:51.000"
        },
        {
            "id": 3,
            "name": "bear3",
            "nickname": "",
            "avatar": "",
            "gender": "UNKNOWN",
            "walk": 0,
            "read": 0,
            "sport": 0,
            "art": 0,
            "practice": 0,
            "valid": 1,
            "gmtCreate": "2022-05-07T16:41:13.000"
        }
    ]
}
```



## 管理员：`/administrator`

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



### 【put】创建管理员：`/administrator`（管理员）

输入：

```json
{
    "name": "用户名",
    "password": "密码"
}
```

输出：

> 根据状态码来确定是否创建成功

```json

```



### 【post】修改密码：`/administrator/password`（管理员）

输入：

```json
{
    "oldPassword": "原密码",
    "newPassword": "新密码"
}
```

输出：

> 根据状态码来确定是否修改成功

```json

```



### 【post】修改管理员用户名：`/administrator/name`（管理员）

输入：

```json
{
    "name": "用户名"
}
```

输出：

> 根据状态码来确定是否修改成功

```json

```



### 【delete】删除管理员：`/administrator/{id}`（管理员）

输入：

```json
```

输出：

> 根据状态码来确定是否删除成功

```json
```



### 【get】获取管理员`/administrator/{id}`（管理员）

输入：

```json

```

输出：

```json
{
    "id": 4,
    "name": "admin4",
    "valid": 0,
    "gmtCreate": "2022-05-06T23:42:03",
    "create": {
        "id": 1,
        "name": "admin"
    },
    "gmtModified": "2022-05-07T13:49:21",
    "modified": {
        "id": 1,
        "name": "admin"
    }
}
```



### 【get】分页条件查询管理员`/administrators`（管理员）

输入：

```json
{
    "page":  "页面",
    "pageSize": "页面大小",
    "name": "用户名",
    "valid": "是否没有被删除",
    "beginTime": "创建时间下限",
    "endTime": "创建时间上限"
}
```

输出：

```json
{
    "page": 1,
    "pageSize": 2,
    "total": 2,
    "row": 2,
    "hasPreviousPage": true,
    "hasNextPage": false,
    "list": [
        {
            "id": 1,
            "name": "admin",
            "valid": 1,
            "gmtCreate": "2022-05-06T23:27:39",
            "create": {
                "id": 0,
                "name": "system"
            },
            "gmtModified": "2022-05-06T23:27:39",
            "modified": {
                "id": 0,
                "name": "system"
            }
        },
        {
            "id": 2,
            "name": "admin2",
            "valid": 1,
            "gmtCreate": "2022-05-06T23:39:59",
            "create": {
                "id": 0,
                "name": "system"
            },
            "gmtModified": "2022-05-07T13:46:34",
            "modified": {
                "id": 2,
                "name": "admin2"
            }
        }
    ]
}
```



## 邀请码：`/invitation_code`

### 【put】创建邀请码：`/invitation_code`（管理员）

输入：

```json
{
    "code": "邀请码",
    "validTime": "2022-05-09T13:56:16.000" // 根据情况修改时间
}
```

输出：

> 根据状态码来确定是否创建成功

```json

```

