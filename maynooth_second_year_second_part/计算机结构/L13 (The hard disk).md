好的，我们来一起提炼这份PPT "CS253_L13ab.pdf" 中的知识点，并进行分类和图片讲解，尽量引用PPT内容。

------

## 模块一：硬盘驱动器 (The hard disk)

### 1.1 硬盘基本结构 (PPT L13, S1)

- 主要组件：
  - "Platter" (盘片)：存储数据的圆形盘片。
  - "Head" (磁头)：用于在盘片上读写数据。
  - "Motors" (马达)：驱动盘片旋转和移动磁头臂的马达。
  - "Case (No dust)" (外壳 - 无尘)：硬盘被密封在外壳中以防止灰尘进入。
- 图片解读 (PPT L13, S1)：
  - 展示了一个打开的硬盘驱动器的内部结构示意图，清晰地标示了盘片、磁头、马达和用于防止灰尘的密封外壳。

### 1.2 磁盘布局 (Disk Layout - PPT L13, S2)

- 关键概念：
  - "Cylinder" (柱面)：所有盘片上相同半径的磁道的集合。
  - "Shaft" (主轴)：盘片围绕其旋转的中心轴。
  - "Track" (磁道)：盘片上一个同心圆，用于存储数据。
  - "Sector" (扇区)：磁道被划分成的更小的弧形区域，是数据读写的最小单位。
- **数据读取方式：** "Read data from a sector, given the cylinder, head and track number." (通过给定的柱面号、磁头号和磁道号（实际应为扇区号）来读取一个扇区的数据)。
- **容量计算示例：** "512 Byte block x Cylinders 12495 x 16 Heads x 63 sectors = 6GByte" (一个示例硬盘的容量计算方式)。
- 图片解读 (Disk Layout - PPT L13, S2)：
  - 左上图展示了多个盘片堆叠，形成了柱面、磁道和扇区的概念。
  - 右下图展示了单个盘片上的磁道和扇区划分。H1到H6可能代表不同的磁头（对应不同的盘面）。

### 1.3 磁读/写头原理 (Magnetic Read/Write Head - PPT L13, S2)

- 基本原理：

   基于电磁感应和磁性材料的特性。

  - **写入：** 电流通过磁头线圈产生磁场，磁化盘片表面的磁性材料，形成代表0或1的磁化方向。

  - 读取：

     磁头经过盘片上已磁化的区域时，变化的磁通量在磁头线圈中感应出电压。

    - "Faraday’s Law" (法拉第电磁感应定律):  `V = -N (dΦ/dt)`，其中 `Φ = BA`。简化为 `V = -K (dB/dt)`。感应电压与磁通量变化率成正比。

- 信号处理：

  - "Amplifier" (放大器)：放大感应到的微弱电压信号。
  - "Threshold Comparator" (阈值比较器)：将放大后的模拟信号与阈值比较，转换为数字信号 (0或1)。
  - "Data Out" (数据输出)。

- 图片解读 (Magnetic Read/Write Head - PPT L13, S2)：

  - 上图展示了磁头 (Head/Iron Core) 在磁盘表面/磁带 (Disk Surface/Tape) 上移动时，磁介质上不同的磁极化方向 (N S N S ...)。
  - 下图展示了读取过程的简化框图：磁头线圈 (Coil) 感应电压，经过放大器放大，再通过阈值比较器输出数字数据。

### 1.4 磁头与盘片间隙 (Heads - PPT L13, S3)

- **软盘 (Floppy disks)：** "On floppy disks the heads touch the surface of the disk" (软盘的磁头接触盘片表面)。
- **硬盘 (Hard disks)：** "on hard disks they fly over the surface." (硬盘的磁头在盘片表面飞行)。
- **飞行高度与风险：** "The heads are so close that even smoke particles could cause them to ‘crash’ into the disk surface. Hard disks are sealed into their case." (硬盘磁头飞行高度极低，即使是烟雾颗粒也可能导致磁头“撞击”盘面，造成损坏，因此硬盘被密封在外壳中)。
- 图片解读 (Heads - PPT L13, S3)：
  - 展示了磁头在磁盘盘片上方飞行的示意图，中间有极小的 "Air Gap" (空气间隙)。

### 1.5 数据编码方式

#### 1.5.1 FM 数据编码 (FM Data Coding - PPT L13, S3)

- FM (Frequency Modulation / Single density / F2F - Frequency to Frequency)：
  - "Data bit 1 makes signal change level" (数据位1使信号电平翻转)。
  - "Data bit 0 signal does not change" (数据位0信号电平不翻转)。
  - "signal changes level after each time slot." (在每个时间槽（位单元）结束时，信号电平都会翻转，作为时钟信号)。
- **特点：** "Note that there are 1 or 2 edges per time slot." (每个时间槽有1到2个边沿)。
- **时钟恢复问题：** "How do you create the clock if you only have the signal?" (如何仅从信号中恢复时钟？FM编码自身就包含了时钟信息)。
- 图片解读 (FM Data Coding - PPT L13, S3)：
  - 顶部是数据序列 "1 1 1 0 1 0 0 0 1 0 0 1 0 Data"。
  - 中间是对应的FM编码波形，可以看到数据位1导致数据区域内电平翻转，每个位单元结束时都有一个时钟翻转。
  - 底部是对应的时钟信号 "Clck"。

#### 1.5.2 锁相环 (Phase locked loops - PLL - PPT L13, S4)

- **作用：** "A clock (square wave oscillator) is synchronised with the rotation of the disk. Using the clock and data signal it is possible to know from where you are reading the data. The device used to synchronise the clock is known as a phase locked loop or PLL." (PLL用于使内部时钟与从磁盘读取的数据信号同步)。
- **工作原理：** "A PLL adjusts its output frequency so that difference between the input frequencies is zero." (PLL调整其输出频率，使得输入频率（来自数据信号）与反馈频率（来自PLL自身输出）之间的相位差为零)。
- **特性：** "Note PLL can freewheel past missing pulses and stay locked to the data signal." (PLL可以“自由滑行”经过丢失的脉冲并保持与数据信号的锁定)。
- 图片解读 (Phase locked loops - PPT L13, S4)：
  - 展示了一个PLL的简化框图。数据信号 (Data Signal, F1) 作为输入，经过PLL处理后输出同步的时钟信号 (Clock, F2)。反馈路径通常涉及分频器 ("/2 or /1")。

#### 1.5.3 MFM 数据编码 (MFM Data Coding - PPT L13, S4)

- MFM (Modified Frequency Modulation / Double density)：
  - "Create a transition for all the data bits of value 1" (为所有数据位1创建一个翻转)。
  - "Transition at start if current data bit is 0 and previous data bit is 0." (如果当前数据位为0且前一个数据位也为0，则在当前位单元开始时创建一个翻转)。
- **目标：** "How do we reduce the number of transitions an store the same amount of data?" (如何减少翻转次数并存储相同量的数据？)。
- **效果：** "Roughly half the number of transitions, sequence packed closer together on disk, double density." (翻转次数大约减半，数据序列在磁盘上可以更紧密地排列，实现双倍密度)。
- 图片解读 (MFM Data Coding - PPT L13, S4)：
  - 顶部是数据序列 "1 1 1 0 1 0 0 0 1 0 0 1 0 Data"。
  - 中间是对应的MFM编码波形，根据上述规则产生翻转。
  - 底部是对应的时钟信号 "Clck"。

#### 1.5.4 RLL 数据编码 (RLL Coding - PPT L13, S5)

- **RLL (Run Length Limited)：** 一种更高级的编码方式，限制连续0的个数（游程长度）。

- 示例 (RLL 2,7 Code)：

   将特定的数据位组合映射为更长的码字，目标是控制翻转之间的最小和最大0的个数。

  - 例如：数据 "10" 编码为 "1000" (RLL码字中没有直接的0到1的翻转，而是通过码字序列的组合来表示原始数据，并控制翻转密度)。PPT中的表格展示了部分数据位到RLL码字的映射。

- 效果：

  - "Only 5 Transitions" (对于示例数据，RLL编码产生的翻转次数更少)。
  - "40% better than MFM on average" (平均比MFM好40%)。

- 图片解读 (RLL Coding - PPT L13, S5)：

  - 顶部是数据序列。
  - 中间表格展示了数据位如何映射到RLL 2,7码字。
  - 底部是对应的RLL编码波形和时钟。

- **技术进展：** "IBM announced a GMR head which can read data at 35.3 Gbits/in2" (GMR巨磁阻磁头技术可以实现极高的数据密度)。

- **硬盘特性：** "Access time 6ms, ... 10,000RPM!. Disks run on air bearings with brushless motors (stepper)." (现代硬盘的典型访问时间、转速、空气轴承和无刷马达/步进马达)。

### 1.6 硬盘性能参数 (Some performance parameters - PPT L13, S6 & S7)

- **Access Time (访问时间)：** "Time between a request for data and receiving the data, typically 20mS." (从请求数据到接收到数据之间的时间，典型值20毫秒)。
- **Seek Time (寻道时间)：** "Time taken for heads to move to a specific location, typically 12mS." (磁头移动到特定磁道所需的时间，典型值12毫秒)。
- **Latency Time (等待时间/旋转延迟)：** "Time taken for PLL etc to start reading data from the track, 8mS." (更准确地说是盘片旋转到所需扇区到达磁头下方所需的时间，典型值8毫秒，取决于转速)。
- **Transfer Rate (传输速率)：** "Rate at which data can be read from the disk at a sustained rate. 10Mbits/Sec (HD), 1Mbit/sec (Floppy)" (磁盘持续读取数据的速率)。

### 1.7 磁道/扇区格式 (Track/Sector formats - PPT L13, S6 & S7)

- **扇区内容：** "The sectors contain ID, Location information, Saved Data, and checksum information." (扇区包含标识信息、位置信息、存储的数据和校验和信息)。
- **校验和 (Checksum)：** "The checksum is used to see if data was successfully written to the disk. If an error occurs the sector is marked as ‘bad’ and the data is written to a different sector." (校验和用于检测数据写入是否成功，如果出错则将扇区标记为坏道，数据写入其他扇区)。
- **格式化操作：** "This is put on the hard-disk when you format it." (这些格式信息是在格式化硬盘时写入的)。
- 图片解读 (Track/Sector formats - PPT L13, S6 & S7)：
  - 展示了一个磁道的典型布局，包含多个扇区。
  - 每个磁道开始有 "Preamble" (前同步码), "Index Add." (索引地址标记)。
  - 扇区之间有 "Gap" (间隙)。
  - 每个扇区内部可能包含 "Id Add." (扇区ID地址), "Gap", "Data" (数据区), "Checksum/ECC" (校验和/错误校验码)。
  - PPT中给出了一个包含26或27个扇区的磁道示例，以及每个扇区包含33个字节数据的示例（这只是示意性的，实际扇区大小通常是512字节或更大）。

### 1.8 磁盘驱动器控制器 (Disc drive controller - PPT L13, S8)

- **作用：** "Synchronising the mechanical movement of the head and platters, decoding the data and providing an interface to the PC bus is done through custom IC known as disk controllers." (磁盘控制器负责同步磁头和盘片的机械运动、解码数据并提供与PC总线的接口)。
- 示例 (8272 软盘控制器)：
  - **主要功能块：** DMA Controller, Registers, Serial Interface Controller, Drive Interface, Buffers, Data Separator, Write Precompensate。
  - **接口信号：** Write data, Read data, Ready, Track 0/Faulty, Single/double, Write protection, Drive select 1/0, Load head, Head select, Reset, D0-7 (数据总线), INT (中断), DRQ/DACK (DMA请求/应答), RD/WR (读/写), A0 (地址线), CS (片选)。
- 图片解读 (Disc drive controller - PPT L13, S8)：
  - 展示了磁盘控制器（以8272软盘控制器为例）的内部功能框图及其与外部的接口信号。它是一个复杂的集成电路，用于处理所有与磁盘驱动器相关的低层操作。

### 1.9 错误检测与纠正 (Error detecting and correcting - PPT L13, S8 & S9)

- 基本思想 (电话号码示例)：
  - 如果记录的电话号码中有一位数字模糊不清，可以通过额外的信息来识别或纠正。
  - **重复：** "We could just repeat the number twice when we write it down." (简单重复可以检测错误，但效率低)。
  - **校验和：** "Try adding up all the digits and send the total also ... So we can add the digits we have ... then take away from the checksum ... gives the missing number." (通过发送数字总和作为校验和，可以检测单个错误并可能纠正单个丢失的数字)。
- 多维奇偶校验码 (Multidimensional parity-check code - MDPC - PPT L13, S9)：
  - "MDPC was used to encode 16 data bits into 25 transmission bits." (一个例子，将16位数据编码为25位传输数据，增加了冗余用于检错纠错)。
  - **原理：** 数据排列成矩阵，计算每行和每列的奇偶校验位。如果接收到的数据中某个位出错，会导致其所在行和列的奇偶校验都失败，从而定位错误位。
  - 图片解读 (MDPC - PPT L13, S9)：
    - 展示了一个5x5的矩阵，其中A到E列，F到J行。数据位填充在矩阵中，每行末尾有一个水平奇偶校验位，每列末尾有一个垂直奇偶校验位。
    - "Note: The bit at BH is in error as the parity on the horizontal and vertical columns is different to that expected." (示例中指出BH位置的位是错误的，因为它导致B列和H行的奇偶校验都出错)。

## 模块二：磁盘文件系统与格式化

### 2.1 磁盘格式化与引导记录 (Disk Formatting - PPT L13, S9)

- 软盘/引导盘结构：

   "The Floppy Disk (or any boot disk) contains data starting at Track 0, Sector 0 organised as follows." (磁盘数据从0磁道0扇区开始组织)。

  - "Boot record - variable size" (引导记录 - 大小可变)。
  - "File allocation table - known as the FAT" (文件分配表)。
  - "Copy of the file allocation table" (文件分配表的副本)。
  - "Root directory" (根目录)。
  - "Data area" (数据区)。

- **引导过程：** "Boot-DOS(disk operating system)-lifting the compute up by its bootlaces." (引导操作系统，常说的“自举”)。

### 2.2 文件分配表 (FAT) 与簇 (FAT/Clusters - PPT L13, S10)

- **FAT作用：** "The File Allocation Table stores the location of (Track, Sector) of the start of each cluster stored on the disk." (FAT存储磁盘上每个簇的起始位置（磁道、扇区）信息，更准确地说是簇之间的链接关系)。
- 簇 (Clusters)：
  - "Sectors are grouped to form clusters (32,768/512=64sectors/cluster)." (扇区被组合成簇，例如每簇64个扇区，每个扇区512字节，则簇大小为32KB)。
- FAT16：
  - "Win 3.1, Win95 950/950a uses FAT 16"。
  - "FAT 16 allows 2^16=65,535 clusters to be contained on one drive." (FAT16可以管理65535个簇)。
  - "Total storage 65,535*32,768 = 2,147,488,648 Bytes" (如果簇大小为32KB，则FAT16管理的最大分区大小约为2GB)。
  - "Sets a 2 Gbyte limit on drive size." (因此FAT16有2GB的分区大小限制)。
- 解决大硬盘问题 (Q: How do you use a bigger disk?)：
  - "Disk Partitioning" (磁盘分区): "A hard disk that exceeds 2Gbyte can split into two halves and treated as two separate disks by the OS ... Thus a 6Gbyte disk drive using FAT 16 would require three logical drives (C:, D:, E:) to access the disk." (超过2GB的硬盘可以被分成分区，每个分区被操作系统视为独立的逻辑驱动器)。
- 空间浪费问题 (Q: how much disk space is required to store a 1K file on 2Gbyte disk using FAT 16?)：
  - "At least 1 cluster is required to store the file ... Thus 31744 bytes are wasted and 1024 stored (3% used)." (即使文件很小，也至少占用一个簇的空间，导致空间浪费，称为内部碎片)。

### 2.3 FAT32 (PPT L13, S11)

- **改进：** "FAT 32 Solves this problem to some extent." (FAT32在一定程度上解决了FAT16的空间浪费和分区大小限制问题)。
- **簇数量：** "FAT 32 can reference 2^32 = 4,294,967,296 clusters" (FAT32理论上可以引用非常多的簇，但实际实现中通常使用28位，即2^28个簇)。
- **簇大小：** "Each cluster need only be one sector in size for the 2Gbyte disk" (对于大硬盘，FAT32可以使用更小的簇大小，减少空间浪费)。
- 10GB硬盘簇大小计算 (Q: What is the cluster size of a 10Gbyte disk using FAT 32?)：
  - 硬盘制造商的10GB通常指10,000,000,000字节。
  - 理论计算: "10,737,418,240/4,294,967,296 = 2… bytes" (这里计算的是每个簇平均字节数，如果簇数量达到上限)。实际上簇大小是2的幂次方个扇区。对于10GB硬盘，FAT32通常会选择如4KB或8KB的簇大小。 "one cluster per sector." (对于非常大的分区和非常多的簇，簇大小可以等于扇区大小，但通常会更大以减少FAT表的大小)。

### 2.4 根目录结构 (The Root Directory - PPT L13, S11)

- **目录项：** "The root directory contains 32bytes of data for each file stored on the disk." (每个文件或目录在目录中占用一个32字节的目录项)。
- 目录项字段 (Byte Number)：
  - `0-7`: "Filename" (文件名)
  - `8-10`: "Extension" (扩展名)
  - `11`: "File Attribute, 1 Read, 2 Hidden, 4 System file, …." (文件属性：只读、隐藏、系统文件等)
  - `12-21`: "Reserved" (保留)
  - `22-23`: "Time file was created" (文件创建时间)
  - `24-25`: "Date the file was created" (文件创建日期)
  - `26-27`: "Starting cluster number" (文件起始簇号)
  - `28-31`: "File size in bytes." (文件大小，以字节为单位)

### 2.5 文件与簇的链接 (Clusters to Files - PPT L13, S12)

- 查找过程：
  1. "The root directory identifies the first cluster in the FAT for a specific file." (根目录项中包含文件数据的第一个簇号)。
  2. "The first cluster entry in the FAT will contain the cluster number for the second cluster. The process continues until enough clusters are linked to contain the file size." (FAT表中，对应于文件第一个簇的条目内容是文件第二个簇的簇号，以此类推，形成一个簇链，直到文件结束标记)。
- **链表结构：** "The root directory and FAT form a linked list that refers to a set of clusters that contain the file data" (根目录和FAT共同构成一个指向文件数据簇集合的链表结构)。
- 图片解读 (Summary - PPT L13, S12)：
  - 展示了根目录项 (包含文件名、大小、起始簇号如3)、FAT表 (Cluster 3 指向 Cluster 8，Cluster 8 指向 Cluster 9，...) 以及磁盘上实际存储数据的扇区/簇的对应关系。形象地说明了文件如何在磁盘上通过目录和FAT表组织起来。

## 模块三：磁盘接口与阵列

### 3.1 IDE/EIDE 与 SCSI 接口 (EIDE/SCSI - PPT L13, S13)

- **IDE (Integrated Disk Electronics)：**
- EIDE (Enhanced IDE / ATA-2)：
  - "Classic IDE supported two hard disks of 528 megabyte or less." (经典IDE支持两个小于等于528MB的硬盘)。
  - "EIDE allows four devices, including a mixture of disks, tapes, and CD-ROM, and the hard disks can be larger." (EIDE允许连接四个设备，包括硬盘、磁带、CD-ROM，且支持更大容量的硬盘)。
- SCSI (Small Computer Systems Interface / "scuzzy")：
  - "50 wire cable" (50针电缆)。
  - "SCSI is used by Macintosh computers, RISC workstations, minicomputers, and even some mainframes and is available on PC’s." (广泛用于Mac、工作站、小型机等，PC也可用)。
  - "Allows many devices to be chained together." (允许菊花链式连接多个设备)。
  - "Microprocessor does not need to operate if hard disk is being backed up on a tape streamer." (在硬盘备份到磁带机等操作时，可以不占用CPU太多资源，因SCSI设备间可直接通信)。
  - "SCSI Cables need terminating!" (SCSI电缆需要终端电阻)。

### 3.2 RAID (Random Array of Inexpensive Disks - PPT L13, S13 & S14)

- **目的：** "Allows faster disk access, improves reliability and removes down time for service." (提供更快的磁盘访问、提高可靠性、减少因服务造成的停机时间)。
- 基本原理：
  - "Data is striped across a number of disks." (数据被条带化地分布在多个磁盘上)。
  - "Extra parity information is stored on the disk." (额外的奇偶校验信息存储在磁盘上用于冗余)。
- 优点：
  - "Since data is read/written to the disk in parallel data rates can be higher." (由于数据并行读写，数据传输率更高)。
  - "Disk can be removed and replaced without loss of data. The missing disk is rebuilt from the parity information stored on the other disks. This is known as a hot-swap." (支持热插拔，损坏的磁盘可以被替换而数据不丢失，数据可以从其他磁盘上的奇偶校验信息重建)。
- RAID 级别 (PPT L13, S14)：
  - **Level 0 (Striping)：** "Striping without parity across four disks, gives increased speed but no improvement in data integrity." (数据条带化分布在多个磁盘上，提高速度，但没有冗余，不提高可靠性)。
  - **Level 1 (Mirroring)：** "Disk mirroring, no striping, maintains a copy of one disk on another disk." (磁盘镜像，数据完全复制到另一个磁盘，提高可靠性，但成本高)。
  - **Level 3 (Striping with dedicated parity disk)：** "Striping, with an extra dedicated parity disk. Only 20% of the disk is used to store redundant information." (数据条带化，使用一个专门的磁盘存储奇偶校验信息。PPT中20%的冗余是指例如4个数据盘+1个校验盘的情况)。
  - **Level 5 (Striping with distributed parity)：** "Data and parity are striped across 5 disks." (数据和奇偶校验信息都条带化地分布在所有磁盘上，提供较好的性能和冗余)。
  - **Level 10 (Striped mirrors / Mirrored stripes)：** "Striped data across two disks, disks are mirrored." (先做镜像，再做条带化，或者先做条带化，再做镜像，结合了RAID 0和RAID 1的特点)。
- 图片解读 (RAID - PPT L13, S14)：
  - 展示了一个RAID控制卡连接多个同步运行的磁盘的示意图，数据在这些磁盘间进行读写。

## 模块四：文件删除与CD-ROM

### 4.1 文件删除机制 (File Delete on FAT 32/FAT16 - PPT L13, S15)

- **删除操作：** "When you delete a file from a disk the first character of the name of the file is changed to a ‘?’ character." (删除文件时，文件名第一个字符被替换为特殊标记，如'?'或E5h)。

- **簇状态：** "The clusters used by the file can then be used by other files for storage." (文件占用的簇在FAT表中被标记为可用，可以被其他文件覆盖)。

- 数据残留与恢复：

   "It should be noted that most deleted files stay intact for quite a while and are easily undeleted." (被删除文件的数据通常在磁盘上保留一段时间，直到被新数据覆盖，因此可以被恢复)。

  - "To undelete files type LOCK C:, a:undelete, UNLOCK C:. Undelete.exe is readily available as part of DOS 5.0." (DOS时代恢复删除文件的方法)。
  - "An empty Trash-can can be restored using this approach." (回收站清空后仍可能恢复)。

- **安全隐患：** "Deleted files can pose a threat to security since deleted files may not have the same protection." (已删除文件可能带来安全风险)。

- **永久删除：** "Use erase to permanently remove disk data." (使用专门的擦除工具可以更彻底地移除数据)。

### 4.2 CD-ROM 与 DVD (PPT L13, S15 & S16)

- 容量对比：
  - "DVD Digital Versatile (formerly video) Disc single layer 4.7 GB of capacity"
  - "CDs 680 MB capacity"
  - "or 17.0 GB capacity of the double sided, dual layer DVD."
- **生产方式：** "CDs can be mass produced by mastering/pressing or created individually using CD-R, CD-RW technologies." (CD可以通过母盘压制批量生产，也可以通过CD-R/CD-RW技术单独创建)。
- CD-ROM 结构与读取 (PPT L13, S16)：
  - "20,000 tracks on a CD organised as a single spiral." (CD上有约20000条磁道，组织成单一螺旋形)。
  - "Constant linear velocity scanning 200-500RPM." (采用恒定线速度扫描，光盘转速在200-500RPM之间变化)。
  - **读取原理：** 激光束照射到光盘表面，从凹坑 (Pit) 和平面 (Land) 反射的光强度不同，通过光电检测器转换成电信号。
  - 图片解读 (CD-ROMS - PPT L13, S16)：
    - 左图展示了CD的螺旋磁道。
    - 右图展示了CD播放器中激光读取头的简化原理：激光器发出激光，经过分光镜 (Beam splitter) 照射到光盘的凹坑 (Pit) 或平面上，反射光再经过分光镜到达光电检测器，输出信号。

## 模块五：硬件保护与操作系统支持 (Lecture 13b - 运行时应用程序的硬件保护)

### 5.1 计算机系统操作概述 (Computer System Operation - PPT L13b, S17)

- **典型组件：** CPU, Disk Controller, Printer Controller, Tape Controller, Memory Controller, Memory, Disks, Printer。
- 启动过程：
  1. "Turn on a computer"
  2. "Bootstrap: Initialises registers." (引导程序：初始化寄存器)。
  3. "Loads Kernel into memory (core of the OS)." (将操作系统内核加载到内存)。
  4. "OS waits for events to occur, notified via an interrupt." (操作系统等待事件发生，通过中断通知)。
  5. "Software or Hardware interrupt occur."
  6. "Each interrupt has a service routine." (每个中断都有一个服务例程)。
- 图片解读 (A Modern Computer System - PPT L13b, S17)：
  - 展示了一个现代计算机系统的简化框图，CPU通过内存控制器与内存相连，通过各种设备控制器与磁盘、打印机等外设相连。

### 5.2 中断向量表与处理流程 (Interrupts Vector table - PPT L13b, S18)

- 中断处理步骤：
  1. "Interrupt occurs (hardware or software), return address put on stack." (中断发生，返回地址压栈)。
  2. "Look up address of service routine." (在中断向量表中查找服务例程的地址)。
  3. "Start ISR, push registers on stack." (开始执行中断服务例程ISR，将需要保护的寄存器压栈)。
  4. "End ISR, pop registers off stack, pop return address and return." (ISR结束，弹出寄存器，弹出返回地址并返回)。
- 中断禁用与优先级：
  - "Interrupts are disabled during the processing of an existing interrupt." (在处理一个中断期间，通常会禁止新的同级或低级中断，以避免嵌套问题)。
  - "The 8259 allows an interrupt to be interrupted based on priority." (8259中断控制器允许基于优先级的中断嵌套)。
  - "The aim is to avoid having lost interrupts." (目标是避免丢失中断)。
- 图片解读 (Interrupts Vector table - PPT L13b, S18)：
  - 左侧是内存字节示意图，显示了中断向量表。
  - 右侧流程图描述了中断发生时，程序从正常流程跳转到中断服务例程，再返回的过程。

### 5.3 中断概述 (Interrupts - PPT L13b, S18下半部分 & S19)

- **控制权交接：** "When an interrupt occurs control is handed back to a service routine contained in the operating system." (中断发生时，控制权交给操作系统中的服务例程)。
- **轮询：** "Sometimes more than one piece of hardware can cause an interrupt so the service routine has to poll devices using I/O to see which needs servicing." (有时多个硬件可能共享中断，服务例程需要轮询设备以确定中断源)。
- **I/O与中断时序：** "I/O request causes hardware to start obtaining data, the rising edge of the I/O causes interrupt to occur indicating that the hardware needs servicing. Service routines starts shortly afterwards to obtain data acquired by the hardware." (I/O请求启动硬件获取数据，I/O完成的边沿触发中断，服务例程随后获取数据)。
- 图片解读 (Application, ISR, CPU, I/O Device Timing - PPT L13b, S18)：
  - 展示了应用程序、ISR、CPU活动和I/O设备状态随时间变化的示意图，描述了I/O请求、数据传输和中断完成的过程。
- 8086中断来源 (Interrupts, Traps and Exceptions - PPT L13b, S19)：
  - **Hardware (硬件)：** "A signal to NMI (Non maskable interrupt line). A signal to the INTR line (via one of two 8259PICs)."
  - **Software (软件)：** "A software interrupt such as int 021h used to print."
  - **Errors/faults (错误/故障)：** "Exception interrupts, divide by zero, parity error, single step etc"
- **中断检测时机：** "8086 checks to see if an interrupt has occurred at the end of each instruction"

### 5.4 I/O 结构与方法 (I/O Structure - PPT L13b, S19 & S20)

- **设备控制器：** "Most device controllers contain a set of special purpose registers a buffer and some I/O." (多数设备控制器包含专用寄存器、缓冲区和I/O接口)。

- UART示例 (8253/8250/16550)：

   通过寄存器配置波特率、奇偶校验等，有缓冲区存储收发字符。

  - **图片解读 (UART Interface - PPT L13b, S19)：** 展示了微处理器(uP)通过数据、地址、读写总线与UART芯片（如8250或16550）连接，UART再通过MAX232电平转换芯片与外部串行接口（RX, TX等）连接的框图。

- 同步I/O (I/O Methods Synchronous - PPT L13b, S20)：

  - "After I/O starts the CPU controls the hardware, on completion of the I/O task control is returned to the requesting process." (I/O开始后CPU等待硬件完成，完成后控制权返回请求进程)。
  - "Note: If the CPU is only wasting time during the I/O cycle then this is an inefficient process." (如果CPU在I/O期间只是等待，则效率低下)。
  - **图片解读：** 用户进程发起请求，内核态的设备驱动程序启动硬件，CPU等待数据传输完成，然后返回用户进程。

- 异步I/O (I/O Methods Asynchronous - PPT L13b, S20)：

  - "After I/O starts control is handed straight back to the requesting process. An interrupt then signals the end of data transfer from the hardware." (I/O开始后控制权立即返回请求进程，硬件完成数据传输后通过中断通知)。
  - "Note: If the requesting process can’t proceed until the completion interrupt has occurred then an instruction wait is available ... This is inefficient because the CPU can only process one hardware device at a time." (如果请求进程必须等待I/O完成，可以使用等待指令，但这仍然限制了CPU并发处理能力)。
  - **图片解读：** 用户进程发起请求，内核态的设备驱动程序启动硬件，控制权立即返回用户进程。硬件完成后通过中断处理程序通知。

- 更优的异步I/O (I/O Methods Asynchronous - Best of all - PPT L13b, S21)：

  - "Start the I/O and continue processing user programme or OS code." (启动I/O后继续处理用户程序或操作系统代码)。
  - "A system_call to the OS starts the I/O device and/or waits for the data." (通过系统调用启动I/O或等待数据)。
  - "A device status-table is used by the OS to check the status of the hardware. Each device may have a queue of requests." (操作系统使用设备状态表跟踪硬件状态，每个设备可能有请求队列)。
  - "If the Hardware is busy and the OS is not the OS just waits otherwise it processes other interrupts." (如果硬件忙而操作系统空闲则等待，否则处理其他中断)。
  - "I/O Device interrupts the OS, OS checks device status_table, modifies the table and allows the data to be returned by the original system call." (I/O设备中断操作系统，操作系统检查状态表，修改表并允许数据通过原始系统调用返回)。

### 5.5 硬件保护机制 (Hardware Protection - PPT L13b, S21-S24)

- **目标：** "A properly designed OS must ensure that an incorrect program can not interfere with another program." (设计良好的操作系统必须确保一个错误的程序不会干扰其他程序)。
- **错误处理：** "If the hardware detects an error it will send an interrupt (it traps the error) and the OS will report the error, terminate the program and store a record of the memory associated with the program when it failed (dump)." (硬件检测到错误会发中断，操作系统报告错误、终止程序并可能生成内存转储)。

#### 5.5.1 双重模式操作 (Dual Mode Operation - PPT L13b, S22)

- 模式：

   "The CPU allows two modes of operation. A single bit in a registers sets which mode the CPU is running in."

  - **User Mode (用户模式)：** "Code run on behalf of the User."
  - **Monitor Mode (监控模式/内核模式/特权模式)：** "Code run on behalf of the Operating System."

- 切换：

  - "In user mode you can not change to monitor mode." (用户模式不能直接切换到监控模式)。
  - "The OS switches to monitor mode before handing control to user programme." (操作系统在将控制权交给用户程序前会切换回用户模式，这里原文可能是笔误，应为“操作系统在响应中断或系统调用时切换到监控模式，完成操作后切换回用户模式再把控制权交还用户程序”)。

- **特权指令：** "Privileged Instructions that are capable of harming other programs are available in monitor mode only." (可能损害其他程序的特权指令只能在监控模式下执行)。

- 图片解读 (Dual Mode Operation - PPT L13b, S22)：

  - 展示了监控模式和用户模式之间的切换。从用户模式发生中断或故障 (Interrupt/fault) 会切换到监控模式。从监控模式通过 "set user mode" 指令返回用户模式。

#### 5.5.2 I/O 保护 (I/O Protection - PPT L13b, S23)

- **机制：** "All I/O instructions are privileged instructions." (所有I/O指令都是特权指令)。
- **用户访问：** "It is only through the OS that the USER can interact with the hardware!" (用户程序只能通过操作系统来与硬件交互)。
- 图片解读 (I/O Protection - PPT L13b, S23)：
  - 与双重模式图类似，强调了在用户模式下直接访问硬件X是被禁止的 ("Not Allowed, Forbidden")。

#### 5.5.3 内存保护 (Memory Protection - PPT L13b, S23 & S24)

- **保护对象：** "Must provide memory protection for Interrupt Vector Table. Why? Service routines are part of the OS and run in monitor mode. By re-vectoring entries in the table (as we did!!) you can jump back to a user program in monitor mode." (必须保护中断向量表，因为修改它可以让用户程序以监控模式运行，造成安全问题)。
- 解决方案：
  - **基址寄存器 (Base register)：** "The base register is loaded with the lowest address in memory that is safe." (加载程序可访问内存的最低安全地址)。
  - **界限寄存器 (Limit register)：** "The limit register is loaded with the highest address in memory that is safe." (加载程序可访问内存的最高安全地址或范围大小)。
  - "Memory outside this range is protected." (超出此范围的内存受保护)。
  - "As the OS switches between each process it changes the registers to limit the processes address space." (操作系统切换进程时，会更新这些寄存器以限制当前进程的地址空间)。
- 图片解读 (Memory Protection - PPT L13b, S24)：
  - 引用了 Silberschatz & Galvin 教材的图，展示了CPU产生的地址如何与基址寄存器和界限寄存器比较，以判断访问是否合法。如果地址小于基址或大于等于基址+界限，则产生陷阱 (trap) 给操作系统。
- **错误信息：** "Regularly causes the ‘blue screen’ error message “General Protection Fault” in Windows." (内存保护错误常导致Windows蓝屏，提示“常规保护错误”)。

#### 5.5.4 CPU 保护 (CPU Protection - PPT L13b, S24)

- **问题：** "If a program goes badly wrong how does the OS get a chance to regain control?" (如果程序出错进入死循环，操作系统如何重新获得控制权？)。
- 定时器中断 (Timer interrupt)：
  - "The timer interrupt fires at regular intervals (18.2 times a second on the PC)." (定时器以固定间隔产生中断)。
  - **上下文切换 (Context-switch)：** "The timer can be used to cause a context-switch between processes. This stops one process and switches to another, multitasking. Each process is allowed a period of time called a time-slice." (定时器中断可用于实现进程间的上下文切换，实现多任务，每个进程获得一个时间片)。
  - **监控程序运行时间：** "The timer can be used to see if the user program is running for too long." (定时器可用于检测用户程序是否运行时间过长)。
- **实时时钟：** "Most systems have a separate ‘real clock’ to monitor time and date." (多数系统有独立的实时时钟用于计时和日期)。

### 5.6 系统调用 (System call - PPT L13b, S25)

- **问题：** "Given the I/O instructions are privileged, how does the user program perform I/O?" (既然I/O指令是特权指令，用户程序如何执行I/O？)。
- **系统调用定义：** "System call – the method used by a process to request action by the operating system." (进程请求操作系统执行操作的方法)。
- 实现方式：
  - "Usually takes the form of a trap to a specific location in the interrupt vector." (通常以陷入（一种特殊的软中断）到中断向量表中特定位置的形式实现)。
  - "Control passes through the interrupt vector to a service routine in the OS, and the mode bit is set to monitor mode." (控制权通过中断向量传递给操作系统中的服务例程，并且CPU模式位被设置为监控模式)。
  - "The monitor verifies that the parameters are correct and legal, executes the request, and returns control to the instruction following the system call." (监控程序（操作系统内核）验证参数的正确性和合法性，执行请求，并将控制权返回给系统调用指令之后的指令)。
- 图片解读 (System Call Flow - PPT L13b, S25)：
  - 用户程序执行系统调用 (System Call) -> 触发中断，切换到监控模式 (Interrupt to Monitor mode) -> 操作系统监控程序 (OS Monitor) 执行 -> 检查参数合法性 (Check all is OK) -> 执行硬件I/O (Hardware I/O) -> 返回用户模式并返回到用户程序 (Back to User)。

------

# 提纲

## 考试复习提纲：硬盘与操作系统基础

### 模块一：硬盘驱动器 (The hard disk)

1. 硬盘基本结构 (1.1)
   - 主要组件：盘片 (Platter)、磁头 (Head)、马达 (Motors)、无尘外壳 (Case)。
2. 磁盘布局 (1.2)
   - 关键概念：柱面 (Cylinder)、主轴 (Shaft)、磁道 (Track)、扇区 (Sector)。
   - 数据读取：通过柱面号、磁头号、扇区号定位。
   - 容量计算：扇区大小 x 柱面数 x 磁头数 x 每磁道扇区数。
3. 磁读/写头原理 (1.3)
   - 写入：电流产生磁场，磁化盘片。
   - 读取：磁头感应盘片磁场变化产生电压 (法拉第电磁感应定律 `V = -K (dB/dt)`)。
   - 信号处理：放大器 (Amplifier)、阈值比较器 (Threshold Comparator)。
4. 磁头与盘片间隙 (1.4)
   - 软盘：磁头接触盘面。
   - 硬盘：磁头在盘面飞行，间隙极小，需无尘环境。
5. 数据编码方式 (1.5)
   - FM (Frequency Modulation / 单密度) (1.5.1)
     - 原理：数据1使电平翻转，数据0不翻转；每时间槽结束时电平翻转 (时钟)。
     - 特点：每时间槽1-2个边沿。
   - 锁相环 (PLL - Phase Locked Loop) (1.5.2)
     - 作用：从数据信号中恢复并同步时钟。
     - 原理：调整输出频率使输入与反馈频率相位差为零。
     - 特性：可“自由滑行”经过丢失脉冲。
   - MFM (Modified Frequency Modulation / 双密度) (1.5.3)
     - 原理：数据1翻转；当前位0且前一位0时，在当前位单元开始时翻转。
     - 效果：减少翻转，提高密度。
   - RLL (Run Length Limited) (1.5.4)
     - 原理：限制连续0的个数，将数据位组合映射为特定码字。
     - 效果：进一步减少翻转，密度更高 (如RLL 2,7比MFM好40%)。
     - 技术：GMR (巨磁阻磁头) 提高数据密度。
6. 硬盘性能参数 (1.6)
   - 访问时间 (Access Time)：请求到接收数据的总时间 (约20ms)。
   - 寻道时间 (Seek Time)：磁头移动到目标磁道的时间 (约12ms)。
   - 等待时间/旋转延迟 (Latency Time)：盘片旋转到目标扇区的时间 (约8ms)。
   - 传输速率 (Transfer Rate)：持续读写数据的速率。
7. 磁道/扇区格式 (1.7)
   - 扇区内容：ID、位置信息、数据、校验和 (Checksum)。
   - 校验和作用：检测写入错误，标记坏道。
   - 格式化：写入磁道和扇区的结构信息 (前同步码, 索引地址, 间隙, 扇区ID等)。
8. 磁盘驱动器控制器 (1.8)
   - 作用：同步机械运动、解码数据、提供PC总线接口。
   - 示例：8272软盘控制器 (功能块：DMA, 寄存器, 串行接口, 驱动接口等)。
9. 错误检测与纠正 (1.9)
   - 基本方法：重复、校验和。
   - 多维奇偶校验码 (MDPC)：通过行和列的奇偶校验位定位并纠正单个错误位。

### 模块二：磁盘文件系统与格式化

1. 磁盘格式化与引导记录 (2.1)
   - 引导盘结构 (从Track 0, Sector 0开始)：引导记录 (Boot record)、文件分配表 (FAT)、FAT副本、根目录 (Root directory)、数据区 (Data area)。
   - 引导过程 (Booting)：加载操作系统内核。
2. 文件分配表 (FAT) 与簇 (Clusters) (2.2)
   - FAT作用：存储文件占用的簇之间的链接关系。
   - 簇 (Cluster)：一组连续的扇区，是文件分配的最小单位。
   - FAT16：最多管理2^16个簇，有2GB分区限制，大文件或小文件多时易产生空间浪费 (内部碎片)。
   - 磁盘分区 (Disk Partitioning)：解决FAT16对大硬盘的限制。
3. FAT32 (2.3)
   - 改进：支持更多簇 (理论2^32，实际常用2^28)，对大硬盘可使用更小簇，减少浪费。
4. 根目录结构 (2.4)
   - 目录项：每个文件/目录占32字节。
   - 主要字段：文件名、扩展名、文件属性、创建时间/日期、起始簇号、文件大小。
5. 文件与簇的链接 (2.5)
   - 查找过程：根目录项 -> 起始簇号 -> FAT表查找下一簇号，形成簇链。
   - 结构：根目录和FAT构成指向文件数据簇的链表。

### 模块三：磁盘接口与阵列

1. IDE/EIDE 与 SCSI 接口 (3.1)
   - IDE (Integrated Disk Electronics)：经典IDE支持2个<=528MB硬盘。
   - EIDE (Enhanced IDE / ATA-2)：支持4个设备 (硬盘, 磁带, CD-ROM)，更大容量。
   - SCSI (Small Computer Systems Interface)：50针电缆，可菊花链连接多设备，设备间可直接通信，电缆需终端电阻。
2. RAID (Random Array of Inexpensive Disks) (3.2)
   - 目的：提高速度、可靠性，减少停机时间。
   - 原理：数据条带化 (Striping) 分布在多磁盘，存储额外奇偶校验信息。
   - 优点：并行读写提高速率，热插拔 (Hot-swap) 可在线更换故障盘并通过奇偶校验重建数据。
   - RAID级别：
     - Level 0 (Striping)：仅条带化，高速，无冗余。
     - Level 1 (Mirroring)：磁盘镜像，高冗余，成本高。
     - Level 3 (Striping with dedicated parity disk)：条带化+专用奇偶校验盘。
     - Level 5 (Striping with distributed parity)：数据和奇偶校验信息都条带化分布在所有磁盘。
     - Level 10 (Striped mirrors / Mirrored stripes)：RAID 1+0 或 RAID 0+1。

### 模块四：文件删除与CD-ROM

1. 文件删除机制 (FAT32/FAT16) (4.1)
   - 操作：文件名首字符改为特殊标记 (如'?'或E5h)，FAT中簇标记为可用。
   - 数据残留：实际数据仍在盘上，直到被覆盖，可被恢复。
   - 安全隐患：已删除文件可能被非法恢复。
   - 永久删除：需用专门工具擦除。
2. CD-ROM 与 DVD (4.2)
   - 容量：CD (约680MB)，DVD (单层4.7GB，双层双面17GB)。
   - 生产：母盘压制 (批量) 或 CD-R/CD-RW (单独创建)。
   - CD-ROM结构与读取：单一螺旋磁道 (约2万条)，恒定线速度 (CLV) 扫描，激光读取凹坑 (Pit) 和平面 (Land) 的反射差异。

### 模块五：硬件保护与操作系统支持

1. 计算机系统操作概述 (5.1)
   - 启动过程：加电 -> 引导程序 (Bootstrap) 初始化 -> 加载内核 (Kernel) -> OS等待事件 (中断)。
2. 中断向量表与处理流程 (5.2)
   - 处理步骤：中断发生 -> 保存返回地址 -> 查中断向量表 -> 执行ISR (保存/恢复寄存器) -> 返回。
   - 中断禁用与优先级：处理中断时常禁用同级/低级中断，8259支持优先级嵌套。
3. 中断概述 (5.3)
   - 控制权：中断将控制权交给OS服务例程。
   - 轮询 (Polling)：当中断源不明确时，OS轮询设备。
   - 8086中断来源：硬件 (NMI, INTR)、软件 (INT指令)、错误/故障 (异常)。
   - 检测时机：每条指令执行完毕后。
4. I/O 结构与方法 (5.4)
   - 设备控制器：含寄存器、缓冲区。UART (8250/16550) 是示例。
   - 同步I/O：CPU启动I/O后等待其完成，效率低。
   - 异步I/O：CPU启动I/O后立即返回，I/O完成后通过中断通知。
   - 更优异步I/O：系统调用启动I/O，OS使用设备状态表管理，CPU可处理其他任务。
5. 硬件保护机制 (5.5)
   - 目标：防止错误程序干扰其他程序或OS。
   - 双重模式操作 (Dual Mode Operation) (5.5.1)
     - 模式：用户模式 (User Mode)、监控模式 (Monitor Mode / 内核模式 / 特权模式)。
     - 切换：用户模式不能直接切到监控模式；中断/系统调用导致切换到监控模式，返回时切换回用户模式。
     - 特权指令 (Privileged Instructions)：只能在监控模式执行。
   - I/O 保护 (5.5.2)
     - 机制：I/O指令是特权指令，用户程序需通过OS访问硬件。
   - 内存保护 (5.5.3)
     - 目的：保护中断向量表、OS及其他程序内存空间。
     - 机制：基址寄存器 (Base register) 和界限寄存器 (Limit register) 定义进程合法内存范围。OS切换进程时更新这些寄存器。非法访问产生陷阱 (trap)。
     - 错误："General Protection Fault" (常规保护错误)。
   - CPU 保护 (5.5.4)
     - 问题：防止程序死循环导致OS失控。
     - 机制：定时器中断 (Timer interrupt) 定期发生。
     - 作用：实现上下文切换 (Context-switch)、多任务 (Multitasking)、时间片 (Time-slice)，监控程序运行时间。
6. 系统调用 (System call) (5.6)
   - 定义：用户进程请求操作系统服务的机制。
   - 实现：通常通过陷阱 (trap，一种软中断) 实现，控制权转交OS服务例程，CPU切换到监控模式。OS验证参数、执行请求、返回结果给用户进程，并切换回用户模式。