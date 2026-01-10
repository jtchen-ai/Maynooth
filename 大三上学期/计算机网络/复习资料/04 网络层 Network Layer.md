# 04 网络层 Network Layer

路由算法决定端到端的路径通过网络（控制平面），转发表决定在此路由器上的本地转发（数据平面）<br>
## **网络层的两个功能：**

### 转发：

- 将数据包从路由器的输入端移动到合适的输出端  (数据平面 Data plane)

### 路由：

- 确定数据包从源地址到目的地址所经过的路径      (控制平面 Control plane)

## 网络层的两个平面：

### 数据平面：

- 确定如何将到达路由器输入端口的数据报 (datagram) 转发到路由器输
出端口，执行转发功能 (forwarding function)。

### 控制平面：

- 决定数据报如何沿着从源主机到目的主机的端到端路径在路由器之间进
行路由 (route)，

- 两种方法：

	- 软件定义网络 (Software-Defined Networking, SDN)： 在（远程）服务器中实现

		- 对应 逻辑上集中的控制平面 Logically centralized control plane：

			- 一个独立的（通常是远程的）控制器与路由器中的本地控制代理（CAs）交互以计算转发表

	- 传统路由算法 (traditional routing algorithms)： 在路由器中实现。

		- 对应 每路由器控制平面Per-router control plane：

			- 每个路由器在控制平面中相互交互以计算转发表

## 路由算法：寻找最低成本路径的算法

### Dijkstra 算法

之前算法与数据结构的笔记点这 https://mrblackpineapple.my.canvasite.cn/data-structures-and-algorithms2/#l3

- 初始化开始节点与初始距离

- 寻找促就最短距离的节点，更新最短距离D(v) = min{D(v), D(w) + c(w,v)}

- 重复步骤2，直到把所有节点都找完

### 路由矢量算法 Distance vector algorithm

- 初始化开始节点与初始距离

- 利用公式 Dx (y) ← minv {c(x,v) + Dv (y)} 更新最短距离

	- 其中，Dx (y)表示x到y的最短距离，c(x,v) 表示x，v的路由距离

- 不断更新，直到不变为止（太慢了，可通过  解决）

  毒性反转 https://baike.baidu.com/item/%E6%AF%92%E6%80%A7%E5%8F%8D%E8%BD%AC/6315268
  
## 路由器架构

### 控制平面（control plane）

- 组成： 路由处理器

- 操作： 执行路由、管理等功能

- 实现： 主要通过软件实现

- 时间尺度： 在毫秒时间范围内操作

### 转发数据平面（forwarding data plane）

- 组成：

	- 路由器输入端口、

	- 路由器输出端口、

	- 高速交换结构（high-seed switching fabric）。

- 操作： 执行转发功能

- 实现： 主要通过硬件实现

- 时间尺度： 在纳秒时间范围内操作

  215<br>
## 输入端口功能

### 线路终端（line termination）：

- 对应物理层，进行位级别的接收（bit-level reception）

### 链路层协议

- 对应链路层，接收一个链路层帧（a link layer frame）

### 查找、转发与排队（lookup, forwarding, queuing）：

- 行分散式交换 decentralized switching ,

- 使用报头字段值 header field  values  查找输出端口，并根据查找结果进行转发

## **转发的两种方式**

### 基于目的地的转发（destination-based forwarding）：

- 传统方式，仅基于目的IP 地址进行转发。

### 通用转发（generalized forwarding）：

- 更灵活的方式，基于任意一组报头字段值进行转发

- 使用最长前缀匹配 longest prefix matching ：在查找给定目标地址的转发**
**表条目时，使用与目标地址匹配的最长地址前缀。

## 交换网络 Switching fabrics：

### 当交换慢于输入端口总和时，

- 输入队列可能发生排队，导致排队延迟和丢包

### 首件阻塞 Head-of-the-Line（HOL）：

- 队列前端的排队数据包阻止队列中的其他数据包前进，如图：最底下的绿包得等红包过了才能走

### 三种类型

- 通过内存切换 memory：

	- 传统计算机，交换由 CPU 直接控制，数据包被复制到系统内存，速度受内存带宽限制

- 通过总线切换 bus:

	- 从输入端口内存通过共享总线到输出端口内存的数据报,切换速度受总线带宽（容量）限制

- 通过互联网络交换 interconnection network:

	- 每条垂直总线与每条水平总线在交叉点相交，可以并行转发多个数据包。

## 优先级调度——谁能获得最佳性能（详见操作系统）

### 当交换快于输出线路速度时进行缓冲，可能导致排队（延迟）和丢包

### **调度机制 Scheduling mechanisms**

- FIFO（先进先出）调度：

	- 按队列到达顺序发送

	- 丢弃策略：尾丢弃/优先级/随机

- **优先级调度：**

	- 首先发送最高优先级队列中的包

- 轮转调度 Round Robin（RR）：**

- 加权公平排队Weighted Fair Queuin（WFQ）：*

	- *根据权重值，将从每个队列中选择进程数量

## 输出端口功能

### 线路终端（line termination）：

- **对应物理层，进行**位级别**的接收(bit-level reception）

### 链路层协议

- 对应链路层，接收一个链路层帧（a link layer frame）

### 查找、转发与排队（lookup, forwarding, queuing）：

- 执行分散式交换 decentralized switching , 使用报头字段值 header field   values查找输出端口，并根据查找结果进行转发

## **转发的两种方式**

### 基于目的地的转发（destination-based forwarding）：

- 传统方式，仅基于目的
IP 地址进行转发。

### 通用转发（generalized forwarding）：

- 更灵活的方式，基于任意一组报头字段
值进行转发

- 使用最长前缀匹配 longest prefix matching ：在查找给定目标地址的转发**
**表条目时，使用与目标地址匹配的最长地址前缀。

## **IP**

### 223.1.3.0/24 子网掩码:/24(8*3) 用于将一个 IP 地址分成两部分

- 子网部分（223.1.1）高位部分

- 主机部分（.0）低位部分

### 属于同一子网的主机可以彼此物理连接，无需经过路由器

### IPv4：

- 使用 32 位地址，超过 40 亿个地址。

### IPv6：

- 使用 128 位地址，超过 340 亿亿（36 个零）个地址

### **IP 分片，重组 IP fragmentation, reassembly**

- 链路层帧能携带的最大数据量称为最大传输单元maximum transmission unit
（MTU）。当一个大型的 IP 数据报被分割成小尺寸的数据报时，称为分片。这些分
片只在最终目的地被“重新组装”。

### **互联网控制消息协议Internet Control Message Protocol (ICMP)**

### 网络层的主要组成部分之一，被主机和路由器用来相互通信网络层信息，最典型的用
途是错误报告 error reporting。

### ICMP 消息具有特定的类型。它包含:

- 一个代码字段

- 一个描述

