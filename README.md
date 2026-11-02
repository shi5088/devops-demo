# DevOps 演示项目 (DevOps Demo)

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.8+-orange.svg)
![GitHub Actions](https://img.shields.io/badge/CI-GitHub%20Actions-blueviolet.svg)

## 📖 项目简介
本项目是一个基于 Spring Boot 的任务管理演示系统，作为《DevOps 实践与项目验收交付》课程的实践项目。项目集成了 GitHub Actions 持续集成流水线，实现了代码提交即触发自动编译、单元测试与打包的标准化流程。

## 🏗️ 架构简图
```mermaid
graph TD
    Client[客户端/浏览器] -->|HTTP 请求| Controller[Controller 层]
    Controller -->|调用| Mapper[MyBatis Mapper 层]
    Mapper -->|SQL| DB[(MySQL 数据库)]
    Controller -->|返回 HTML/JSON| Client