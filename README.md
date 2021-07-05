# 短信转邮件

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)

### 介绍
软件运行时拦截短信内容，将短信内容转发至设定的邮箱内，只支持发送到单个邮箱。
邮件发送使用的android-mail资源。

### 使用说明

![页面](https://images.gitee.com/uploads/images/2021/0705/101725_5e624fb5_1021361.jpeg "8364fa18f37f749c5eebeeda53d09d9.jpg")


- smtp地址：填写你的邮件运营商smtp服务器地址；
- smtp SSL 端口：填写你的邮件运营商smtp SSL对应的端口号；
- 账号：填写你的发送邮件账号；
- 授权码：填写你的邮件账号对应的授权码；
- 接收人邮件地址：目前只支持填写一位接收人；
- 邮件消息标题：填写收到邮件时的邮件标题内容，便于邮件自定义归档；


### 注意事项

- 软件需要读取短信权限，请保证开启；
- 需要设置允许软件后台运行、自启、加入白名单等最大程度保证软件存活，不同手机设置方法不同；
- 部分手机需要在“ **短信-设置-验证码安全保护** ”关闭此项，以确保可以拦截验证码类短信；


License
-------

    Copyright 2021 mjsoftking

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.






