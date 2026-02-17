# Fast-Server
## 📖 项目简介

fast-server 是一个轻量化的 Spring Boot Web 项目脚手架，旨在帮助开发者快速搭建企业级 B/S 架构应用。项目集成了 Web 开发中最基础、最通用的功能模块，让你无需重复造轮子，专注于业务逻辑的实现。

## ✨ 核心特性

🚀 前沿技术栈：基于 Spring Boot 4 + Spring Security 6 + MySQL 8 + Redis 7

🔐 用户认证：完整的 JWT 认证体系，支持登录、登出、注册

📸 图片验证码：基于Hutool集成图形验证码功能

📚 系统词典：统一的数据字典管理，支持动态配置

🛡️ 权限控制：基于 RBAC 的细粒度权限管理

📦 开箱即用：提供最常用的功能模块，快速启动新项目

🔧 代码规范：按照低耦合高内聚的代码规范，保证项目代码可扩展性、复用性高

## 📁 项目结构
```
src
├── main
│   ├── java
│   │   └── com
│   │       └── fast
│   │           └── server
│   │               ├── annotation
│   │               │   ├── dict
│   │               │   │   └── Dictionary.java 
│   │               │   └── Util.java
│   │               │
│   │               ├── cache
│   │               │   └── CaptchaCache.java
│   │               │
│   │               ├── config
│   │               │   ├── DictConfig.java
│   │               │   ├── RedisConfig.java
│   │               │   └── SecurityConfig.java
│   │               │
│   │               ├── controller
│   │               │   ├── base
│   │               │   │   └── BaseController.java
│   │               │   └── AuthenticationController.java
│   │               │
│   │               ├── dto
│   │               │   ├── AjaxResult.java 统一接口返回格式
│   │               │   ├── AuthenticationRequest.java 用户认证请求体
│   │               │   ├── Captcha.java 验证码
│   │               │   ├── CaptchaRequest.java 验证码请求体
│   │               │   ├── CaptchaResponse.java 验证码生成请求结果
│   │               │   ├── Expires.java 有效期
│   │               │   ├── SystemDict.java 系统词典Bean
│   │               │   ├── SystemUser.java 系统用户
│   │               │   ├── Token.java Token
│   │               │   └── UserInfoResponse.java 用户信息请求结果
│   │               │
│   │               ├── entity 数据库实体
│   │               │   ├── Dict.java
│   │               │   ├── Role.java
│   │               │   └── User.java
│   │               │
│   │               ├── exception
│   │               │   ├── BaseException.java
│   │               │   ├── CaptchaIncorrectException.java
│   │               │   ├── CharacterInvalidException.java
│   │               │   ├── PasswordIncorrectException.java
│   │               │   ├── UsernameExistException.java
│   │               │   └── UsernameNotExistException.java
│   │               │
│   │               ├── filter
│   │               │   └── JwtValidateFilter.java
│   │               │
│   │               ├── handler
│   │               │   └── GlobalExceptionHandler.java 全局异常控制器
│   │               │
│   │               ├── mapper
│   │               │   ├── DictMapper.java
│   │               │   ├── RoleMapper.java
│   │               │   └── UserMapper.java
│   │               │
│   │               ├── service
│   │               │   ├── impl
│   │               │   │   ├── DataSourceDictionaryService.java 数据库词典加载的实现
│   │               │   │   ├── LineCaptchaService.java 线段干扰验证码的实现
│   │               │   │   ├── SystemUserDetailsService.java 用户名密码验证服务
│   │               │   │   └── UsernamePasswordAuthenticationService.java 用户名密码认证的实现
│   │               │   ├── AuthenticationService.java 用户认证服务
│   │               │   ├── CaptchaService.java 验证码服务
│   │               │   └── DictionaryService.java 词典服务
│   │               │
│   │               ├── util
│   │               │   ├── impl
│   │               │   │   └── SystemUserJwtUtil.java JWT令牌解析系统用户的实现 
│   │               │   ├── AuthenticationUtil.java 线程用户信息获取工具类
│   │               │   └── JwtUtil.java JWT令牌工具类
│   │               │
│   │               └── FsServerApplication.java
│   │
│   └── resources
│       ├── mapper
│       │   └── UserMapper.xml
│       ├── static
│       ├── templates
│       ├── application-dev.yaml
│       └── application.yaml
│
└── test
└── java
└── com
└── fast
└── server
└── FsServerApplicationTests.java
```

## 🚀 快速开始
环境要求

JDK 17+

MySQL 8.0+

Redis 7.0+

Maven 3.8+