# 01 Overview: 计算机网络 (Computer Network)

## 1. 定义与基础
- **定义**: 一组通过通信链路连接在一起的节点 (Nodes)
- **节点类型**: 计算机, 路由器, 集线器, 服务器等
- **核心组件**:
    - **Server (服务器)**: 提供资源、数据、服务
    - **Client (客户端)**: 访问服务的计算机
    - **Hub (集线器)**: 连接拆分设备
    - **Router (路由器)**: 接收和转发数据包
    - **Data Cable (数据线)**: 传输媒介
    - **Peer (对等节点)**: 既是发送者也是接收者

## 2. 数据通信系统 (Data Communication System)
- **组成部分**:
    - **Protocol (协议)**: 传输规则 (如四次握手)
    - **Sender (发送方)**
    - **Receiver (接收方)**
    - **Medium (传输介质)**: 电缆、无线电波
- **协议流程示例**:
    1. 客户端发送连接请求
    2. 服务器返回连接回复
    3. 客户端请求网页名称
    4. 服务器返回网页文件

## 3. 传输模式 (Transmission Modes)
- **Simplex (单工模式)**
    - 单向传输 (Unidirectional)
    - 例: 键盘 -> 电脑
- **Half-Duplex (半双工模式)**
    - 双向但不同时
    - 例: 对讲机
- **Full-Duplex (全双工模式)**
    - 双向且同时
    - 实现方式: 两条独立路径 或 信号频分
    - 例: 电话

## 4. 数据通信方式 (Data Communication Ways)
- **Unicast (单播)**: 1对1 (例: 打电话)
- **Broadcast (广播)**: 1对所有 (例: 广播电台)
- **Multicast (多播)**: 1对特定组 (例: 付费电视频道)

## 5. 计算机网络结构
- **Network Edge (网络边缘)**
    - **End systems (终端系统/主机)**: 客户端 + 服务器
    - **Access networks (接入网络)**: 连接 ISP 的网络 (DSL, Cable)
    - **Links (链接)**: 物理连接
- **Network Core (网络核心)**: 互联的路由器网络

## 6. 通信介质 (Communication Media)
- **Guided (有线/引导)**
    - 双绞线 (Twisted pair)
    - 同轴电缆 (Coaxial cable)
    - 光纤 (Fiber optics)
- **Unguided (无线/非引导)**
    - 无线电波 (Radio wave): 3kHz - 1GHz
    - 微波 (Microwave): 1GHz - 300GHz
    - 红外线 (Infrared): 300Hz - 400Hz

## 7. 交换技术 (Switching)
- **Circuit Switching (电路交换)**
    - 特点: 建立专用路径, 非存储转发
    - **三个阶段**:
        1. 电路建立 (前两次握手)
        2. 数据传输 (后两次握手)
        3. 电路断开
    - **复用技术**:
        - **FDM (频分复用)**: 按频率划分信道
        - **TDM (时分复用)**: 按时间片划分信道
- **Packet Switching (分组交换)**
    - 特点: 消息分解为数据包 (Packets)
    - **机制**: 存储和转发 (Store-and-forward)
    - **功能**:
        - 路由 (Routing): 确定路径
        - 转发 (Forwarding): 输入口移至输出口
    - **计算**: 单跳延迟 = L (包大小) / R (速率)

## 8. 延迟与丢包 (Delay & Loss)
- **四种延迟类型**:
    1. **Processing (处理延迟)**: 检查头部, 决定去向
    2. **Queuing (排队延迟)**: 在缓冲区等待
    3. **Transmission (传输延迟)**: $L/R$ (推向链路的时间)
    4. **Propagation (传播延迟)**: $d/s$ (物理距离/光速)
- **总延迟公式**: $d_{total} = d_{proc} + d_{queue} + d_{trans} + d_{prop}$
- **Packet Loss (丢包)**: 缓冲区(Buffer)满时丢弃数据包

## 9. 吞吐量 (Throughput)
- **定义**: 发送方和接收方之间传输位的速率 (bits/sec)
- **限制因素**: 瓶颈链路 (Bottleneck link)
- **公式**: $Throughput = min(R_s, R_c, R/N)$
- **计算题示例**:
    - 12000 frames/min, 1 frame = 10000 bits
    - 结果 = 2 Mbps

## 10. 网络类型 (按区域)
- **LAN (局域网)**: 小区域, 办公室/家庭
- **PAN (个域网)**: 个人设备 (蓝牙/USB)
- **MAN (城域网)**: 城市范围, 互连LAN
- **WAN (广域网)**: 跨州/跨国 (Internet)

## 11. 网络拓扑 (Network Topology)
- **Bus (总线拓扑)**
    - 结构: 公共总线
    - 优: 省线, 成本低
    - 缺: 总线断则全网崩
- **Star (星形拓扑)**
    - 结构: 集中式集线器
    - 优: 单点故障不影响全局, 易扩展
    - 缺: 中心坏则全网崩, 布线成本高
- **Ring (环形拓扑)**
    - 结构: 闭环连接
    - 优: 单向传输无冲突
    - 缺: 任意故障影响全网
- **Tree (树形/混合拓扑)**
    - 结构: 星形+总线
    - 优: 易管理, 适合大型网络
    - 缺: 主干故障影响分支