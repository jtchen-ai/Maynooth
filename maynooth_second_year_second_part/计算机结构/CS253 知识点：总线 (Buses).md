# CS253 知识点：总线 (Buses)

## 1. 总线基础 (Bus Fundamentals)

- 定义 (Definition)

  - 总线是一组共享的通信线路，用于连接计算机的不同组件，并规定了使用它的标准。

- 通信类型 (Types of Communication)

  - 系统内通信 (Intrasystem communication)

    :

    - 连接计算机内部的各种组件，距离小于一米。
    - 通过称为总线的大量并行数据线实现。

  - 系统间通信 (Intersystem communication)

    :

    - 计算机与远程硬件之间的连接。
    - 通常使用串行链路实现。
    - 选择串行是为了成本和可靠性。

- 互连结构 (Interconnection Structure)

  - **专用总线结构 (Dedicated bus structures)**: 仅连接两个设备。
  - **共享总线结构 (Shared bus structures)**: 在不同时间使用相同的数据路径。

## 2. 典型计算机总线结构 (Typical Computer Bus Structure)

- 地址总线 (Address Bus)

  :

  - 当计算机希望读 / 写内存或 IO 设备时，地址总线指定要访问的位置。
  - 仅为输出。

- 数据总线 (Data Bus)

  :

  - 一种双向总线，用于在 CPU 和外部设备之间传输数据。

- 控制总线 (Control Bus)

  :

  - 一组输入 / 输出线，用于在读 / 写操作期间控制事件的顺序。

## 3. 总线操作与挑战 (Bus Operation & Challenges)

- 总线竞争 (Contention)

  - 问题

    : 当两个输出连接在一起时，就会产生称为竞争的结果。

    - 尤其是在双向总线（如数据总线）上。
    - 最好的情况是产生不明确的数据，最坏的情况是可能损坏总线或内存。

  - **CMOS 中的竞争**: 如果两个单元的输出 (*D*0*X* 和 *D*0*Y*) 不相等，大电流会流过晶体管，可能导致故障。

- 竞争的解决方案 (Solutions to Contention)

  - 电流吸收逻辑 (Current sinking logic)

    :

    - 输出被有效地 “或” 在一起。
    - 如果发生竞争，电阻器会限制电流，因此不会造成损坏。
    - **问题**: 速度非常慢（没有主动上拉）且浪费功率。

  - 三态设备 (Tri-State devices)

    :

    - 使用第二条控制线将单元与总线断开。
    - 断开状态具有高输入阻抗，可防止竞争。

- 总线时序 (Bus Timing)

  - 在任何时刻，只有一个设备可以发送数据，所有其他设备必须侦听或忽略数据。

  - 发送控制信息的设备称为主设备 (master)，其他单元称为从设备 (slave)。

  - 同步总线 (Synchronous buses)

    :

    - 包含一个时钟信号，允许每个项目在主从双方预先知道的时间槽内传输。
    - 可以实现非常快的数据速率，但系统会变得和总线上最慢的设备一样慢。

  - 异步总线 (Asynchronous bus)

    :

    - 不含时钟信号，每个要传输的项目都伴随一组控制信号来对传输进行排序。
    - 这有时会涉及握手 (handshaking)。

## 4. 总线仲裁 (Bus Arbitration)

- **目的**: 避免总线上的冲突或竞争。

- 硬件仲裁方法

  :

  - 菊花链 (Daisy Chaining)

    :

    - 当 `Request` 信号为高电平时，表示有设备希望使用总线。
    - 如果 `Busy` 信号为低，控制器向特定设备发送 `Grant` 信号。
    - 该设备将 `Busy` 信号设为高电平，并将数据放到总线上。

  - 轮询 (Polling)

    :

    - `Grant` 线被轮询计数线取代。
    - 收到请求后，控制单元循环通过轮询线，寻找活动的单元。
    - `Busy` 信号变高，数据线被连接。
    - 优先级由轮询顺序设定。

  - 独立请求 (Independent Requesting)

    :

    - 每个外设单元都有独立的总线请求（Request）和授予（Grant）线。
    - 优先级由总线控制单元控制。
    - **缺点**: 复杂性高，需要为每个单元设置单独的请求和授予线。

## 5. PC 总线 (PC Buses)

- ISA (Industry Standard Architecture - 工业标准结构)

  :

  - 1984 年发布，由于使用它的外设数量庞大，至今仍很流行。
  - 最初是运行在 4.77MHz 的 8 位总线。
  - 后来扩展到 16 位，8MHz。
  - 16 位外设使用完整的 ISA 连接器，8 位外设只使用前半部分。

- PCI (Peripheral Component Interconnect - 外设组件互连总线)

  :

  - 1993 年发布，是现在最流行的 I/O 总线。
  - 宽度为 32 位，运行在 33MHz。
  - PCI 控制器管理总线仲裁和控制。
  - PCI 标准的应用范围超出了 PC 领域。
  - PCI 已被用于实现即插即用 (Plug and Play)。