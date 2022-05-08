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



### 【post】管理员重置用户密码：`/user/{id}/password`（管理员）

输入：

```json
{
    "password": "密码"
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



### 【delete】删除用户`/user/{id}`（管理员）

输入：

```json

```

输出：

> 根据状态码来确定是否删除成功

```json

```



### 【post】修改用户数据`/user/{id}`（管理员）

输入：

```json
{
    "nickname": "昵称",
    "avatar": "头像",
    "birthday": "2022-05-09T13:56:16.000",
    "gender": 1,
    "phone": "电话",
    "honorPoint": 1, // 荣誉值
    "selfControlPoint": 2, // 自律值
    "contributionPoint": 3, // 贡献值
    "walk": 1, // 行走的经验值
    "read": 2, // 阅读的经验值
    "sport": 3, // 体育的经验值
    "art": 4, // 艺术的经验值
    "practice": 5 // 实践的经验值
}
```

输出：

> 根据状态码来确定是否删除成功

```json

```





### 【post】修改自己的数据`/user/self`（用户）

输入：

```json
{
    "nickname": "昵称",
    "birthday": "2022-05-09T13:56:16.000",
    "avatar": "头像地址",
    "gender": 1,
    "phone": "手机号"
}
```

输出：

> 根据状态码来确定是否修改成功

```json

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



## 贡献：`/contribution`

### 【put】添加贡献：`/contribution`（管理员）

输入：

```json
{
    "userId": 用户id,
    "point": 点数,
    "comment": "备注"
}
```

输出：

```json
```



### 【post】修改贡献的备注：`/contribution/{id}`（管理员）

输入：

```json
{
    "comment": "备注"
}
```

输出：

> 根据返回的状态码，来决定修改的结果

```json
```



### 【get】根据id获取贡献：`/contribution/{id}`（管理员）

输入：

```json

```

输出：

```json
{
    "id": 贡献id,
    "administratorId": 管理员id,
    "userId": 用户id,
    "point": 贡献点数,
    "comment": "贡献备注",
    "gmtCreate": "创建人",
    "create": {
        "id": 创建id,
        "name": "创建人"
    }
}
```



### 【get】根据条件分页查询贡献：`/contributions`（管理员）

输入：

```json
page                页码
pageSize            页面大小
userId              用户id
contributionAdminId 贡献管理员id
comment             贡献评论
minPoint            最小积分
maxPoint            最大积分
beginTime           开始时间
endTime             结束时间
```

输出：

```json
{
    "page": 1,
    "pageSize": 10,
    "total": 1,
    "row": 2,
    "hasPreviousPage": false,
    "hasNextPage": false,
    "list": [
        {
            "id": 1,
            "administratorId": 1,
            "userId": 1,
            "point": 100,
            "comment": "备注2",
            "gmtCreate": "2022-05-07T19:26:45",
            "create": {
                "id": 1,
                "name": "admin"
            }
        },
        {
            "id": 2,
            "administratorId": 1,
            "userId": 1,
            "point": 99,
            "comment": "这是贡献2",
            "gmtCreate": "2022-05-07T19:43:53",
            "create": {
                "id": 1,
                "name": "admin"
            }
        }
    ]
}
```



### 【post】确认贡献：`/contribution/{id}/check`（管理员）

> 请注意，只有确认贡献只有才会发放贡献值，发放后无法撤回！

输入：

```json

```

输出：

```json

```



## 打卡：`/check`

### 【put】创建打卡：`/check`（用户）

输入：

```json
{
    "type": 类别,
    "url": "图片链接/base64",
    "comment": "用户的说明"
}
```

输出：

> 根据状态码确定是否创建成功

```json

```



### 【get】查询自己的打卡：`/check/{id}/self`（用户）

输入：

```json
```

输出：

```json
{
    "id": 2,
    "userId": 1,
    "administratorId": null,
    "type": "WALK",
    "url": "测试url",
    "point": 0,
    "exp": 0,
    "comment": null,
    "gmtCreate": "2022-05-08T11:56:27",
    "create": {
        "id": 1,
        "name": "bear"
    }
}
```



### 【get】查询打卡：`/check/{id}`（管理员）

输入：

```json

```

输出：

```json
{
    "id": 2,
    "userId": 1,
    "administratorId": null,
    "type": "WALK",
    "url": "测试url",
    "point": 0,
    "exp": 0,
    "comment": null,
    "gmtCreate": "2022-05-08T11:56:27",
    "create": {
        "id": 1,
        "name": "bear"
    }
}
```



### 【get】用户条件分页查询自己的打卡：`/checks/self`（用户）

输入：

```json
page         页码
pageSize     页大小
adminId      管理员id
type         打卡类型
userComment  用户备注
adminComment 管理员备注
minPoint     最小积分
maxPoint     最大积分
minExp       最小经验
maxExp       最大经验
beginTime    开始时间
endTime      结束时间
```

输出：

```json
{
    "page": 1,
    "pageSize": 10,
    "total": 1,
    "row": 2,
    "hasPreviousPage": false,
    "hasNextPage": false,
    "list": [
        {
            "id": 1,
            "userId": 1,
            "administratorId": null,
            "type": "WALK",
            "url": "测试url",
            "point": 0,
            "exp": 0,
            "adminComment": null,
            "userComment": null,
            "gmtCreate": "2022-05-08T11:48:21",
            "create": {
                "id": 1,
                "name": "bear"
            }
        },
        {
            "id": 2,
            "userId": 1,
            "administratorId": null,
            "type": "WALK",
            "url": "测试url",
            "point": 0,
            "exp": 0,
            "adminComment": null,
            "userComment": "这是一个备注",
            "gmtCreate": "2022-05-08T11:56:27",
            "create": {
                "id": 1,
                "name": "bear"
            }
        }
    ]
}
```



### 【get】管理员条件分页查询打卡：`/checks`（管理员）

输入：

> 输入多了一个userId的限制项

```json
page         页码
pageSize     页大小
adminId      管理员id
type         打卡类型
userComment  用户备注
adminComment 管理员备注
minPoint     最小积分
maxPoint     最大积分
minExp       最小经验
maxExp       最大经验
beginTime    开始时间
endTime      结束时间
userId       用户id
```

输出：

```json
{
    "page": 1,
    "pageSize": 10,
    "total": 1,
    "row": 2,
    "hasPreviousPage": false,
    "hasNextPage": false,
    "list": [
        {
            "id": 1,
            "userId": 1,
            "administratorId": null,
            "type": "WALK",
            "url": "测试url",
            "point": 0,
            "exp": 0,
            "adminComment": null,
            "userComment": null,
            "gmtCreate": "2022-05-08T11:48:21",
            "create": {
                "id": 1,
                "name": "bear"
            }
        },
        {
            "id": 2,
            "userId": 1,
            "administratorId": null,
            "type": "WALK",
            "url": "测试url",
            "point": 0,
            "exp": 0,
            "adminComment": null,
            "userComment": "这是一个备注",
            "gmtCreate": "2022-05-08T11:56:27",
            "create": {
                "id": 1,
                "name": "bear"
            }
        }
    ]
}
```

