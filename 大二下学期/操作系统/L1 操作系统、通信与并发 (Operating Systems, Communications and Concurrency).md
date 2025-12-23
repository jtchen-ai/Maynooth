# 操作系统、通信与并发 (Operating Systems, Communications and Concurrency)



- **操作系统概述 (Operating System Overview)**

  - **定义 (Definition)**: 操作系统（OS）是一个庞大而复杂的软件系统，它管理计算机的所有硬件资源，并使其易于使用

  - 核心功能 (Core Functions)

    :

    - 提供一个抽象层，便于开发可移植的应用程序
    - 为应用程序提供通用服务以使用底层硬件资源（如处理资源、内存空间、磁盘存储、I/O设备和网络通信功能）
    - 将机器启动到可用状态

  - 在系统中的位置

    : 位于硬件和应用程序之间，用户通过应用程序与操作系统交互

    - `硬件 (Hardware) <-> 操作系统 (Operating System) <-> 应用程序 (Application) <-> 用户 (User)`

  - 作为编程平台 (Programming Platform)

    :

    - 应用程序通过API和系统调用与操作系统交互
    - 应用程序是为特定操作系统编写的
    - OS API的存在减少了应用程序在硬件上运行所需的开发工作

  - 并发环境 (Concurrent Environment)

    :

    - ##操作系统是一个并发环境，必须同时处理大量任务和事件
    - 需要协调这些任务，以最高效和最佳地利用可用资源

  - 用户交互接口 (User Interface)

    :

    - 通常通过图形用户界面（GUI）或基于文本的命令行界面（CLI）与操作系统进行交互

- **操作系统内核 (The Kernel)**

  - **定义**: 内核是指与管理CPU、内存、基本进程间通信和底层硬件设备相关的核心服务集

  - **运行模式**: 内核在处理器的特权模式下执行，在该模式下可以使用额外的指令和权限

  - **通用性**: 内核在各种操作系统发行版中是通用的

  - 结构

    :

    - **硬件层**: CPU, 内存, 设备
    - **内核层**: 进程管理, 内存管理, 设备管理
    - **用户空间**: 应用程序, 库

  - 操作系统发行版 (OS Distribution)

    :

    - 除了内核之外，发行版还包含文件系统、数据库引擎、网络套件、图形和媒体功能、Web服务器、用户界面GUI、安全和认证元素以及各种外部I/O设备的驱动程序等附加组件
    - 发行的软件包可以针对不同目标环境（如桌面、服务器、移动设备）进行定制

- **操作系统类型与实例 (Types and Examples of OS)**

  - 桌面/笔记本电脑 (Desktops/Laptops)
    - 占设备类型的约40% (截至2020年1月)
    - **Microsoft Windows**: 占据了88%的桌面/笔记本电脑市场份额
    - **Apple (Mac) OS**: 占据了9.4%的市场份额
    - **Linux/Free-BSD**: 许多服务器环境使用基于Unix的操作系统，约占2%的市场份额
  - 移动设备 (Mobile Devices)
    - 占设备类型的超过59% (截至2020年1月)
    - 移动设备及其他复杂设备（如无线接入点、机顶盒）也运行操作系统
    - **Android**: 基于Linux，为触摸屏移动设备设计。超过25亿设备使用，占据约70%的移动市场份额 (截至2020年1月)
    - **Apple iOS**: 占据29%的移动市场份额
    - **Microsoft Windows Phone**: 市场份额小于0.04%
  - 云操作系统 (Cloud Operating System)
    - **定义**: 数据和/或应用程序远程托管在数据中心，而不是在用户自己的计算机上
    - **模式**: 用户“租用”这些系统的软件、存储或计算资源，并使用Web浏览器或Web API进行访问
    - **特点**: 高可靠、高可用、成本效益高、可扩展、高性能
    - **市场领导者**: Amazon, Microsoft, Google, Salesforce, Adobe等

- **操作系统核心任务总结 (Summary of OS Tasks)**

  - 在竞争的用户进程之间分配和管理资源
  - 最大化资源利用率，以提高整体系统吞吐量
  - 提供用户界面和应用程序接口
  - 协调并发活动和设备，处理硬件的输入输出，确保正确的同步和通信
  - 作为资源守护者，保护计算机系统资源免遭恶意或意外滥用
  - 核算用户进程的资源使用情况，强制执行配额或限制
  - 电源和热量管理

- **课程内容概览 (Course Overview/Syllabus)**

  - 进程管理 (Process Management)
    - 进程与线程 (Processes and Threads)
    - 调度理论与进程调度算法 (Scheduling Theory & Process Scheduling Algorithms)
    - 进程同步 (Process Synchronisation)
    - 经典协调问题 (Classical Coordination Problems)
    - 死锁 (Deadlocks)
    - 进程间通信 (Interprocess Communication): 消息传递, 流通信, 远程过程调用 (Message Passing, Stream Communication, Remote Procedure Call)
  - 内存管理与虚拟内存 (Memory Management and Virtual Memory)
    - 空间分配、分页体系结构、请求分页和替换 (Space allocation, paged architectures, demand paging and replacement)
  - 文件系统 (File Systems)
    - 磁盘操作、空间分配、分层目录结构 (Disk operation, space allocation, hierarchical directory structures)
  - 保护与安全 (Protection & Security)
    - 指定和实施身份验证、授权和执行 (Specifying and implementing authentication, authorisation and enforcement)
    - 维护系统中信息的完整性 (Maintaining the integrity of information in the system)