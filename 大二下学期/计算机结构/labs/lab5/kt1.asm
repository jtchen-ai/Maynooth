; kt1.asm
; 功能:
; - 按下空格键，屏幕计数器加1。
; - 按下回车键，屏幕计数器清零。
; - 作为TSR程序驻留内存。

.MODEL small
.STACK 100h

.CODE
    jmp     program_entry   ; 跳转到程序主入口，跳过数据区

; --- 常驻内存的数据区 ---
counter_digits      DB '0','0','0','0'     ; 4位计数器的ASCII码存储
orig_int9_ip        DW 0                   ; 保存原始INT 9中断服务程序的IP
orig_int9_cs        DW 0                   ; 保存原始INT 9中断服务程序的CS
screen_pos_counter  DW (0 * 80 + 70) * 2   ; 计数器在屏幕上的显示位置 (第0行, 第70列)
                                           ; 字节偏移量 = (行号 * 80 + 列号) * 2
counter_attribute   DB 07h                 ; 计数器显示属性 (07h = 黑底白字)

program_entry:
    ; .STARTUP 宏通常用于 .EXE 文件，它会设置堆栈和DS寄存器。
    ; 这里我们手动确保DS指向代码段，以便访问我们定义在代码段中的数据。
    push    cs
    pop     ds

    ; 1. 保存原始的INT 9 (键盘) 中断向量
    push    es
    mov     ax, 0000h
    mov     es, ax          ; ES = 0000h 指向中断向量表
    mov     bx, 024h        ; INT 9 的向量偏移地址 (9 * 4 = 36 = 024h)

    mov     ax, es:[bx]     ; 读取原始IP
    mov     [orig_int9_ip], ax
    inc     bx
    inc     bx
    mov     ax, es:[bx]     ; 读取原始CS
    mov     [orig_int9_cs], ax
    pop     es

    ; 2. 安装新的INT 9中断向量，使其指向我们的isr_keyboard
    push    es
    mov     ax, 0000h
    mov     es, ax          ; ES = 0000h
    cli                     ; 禁止中断，以防在修改向量时发生中断
    mov     bx, 024h
    mov     dx, OFFSET isr_keyboard ; 我们新的ISR的偏移地址
    mov     es:[bx], dx     ; 设置新的IP
    inc     bx
    inc     bx
    mov     ax, cs          ; 新的ISR位于当前代码段 (CS)
    mov     es:[bx], ax     ; 设置新的CS
    sti                     ; 重新允许中断
    pop     es

    ; 3. 初始化时显示一次计数器 (初始值为 "0000")
    call    display_counter_proc

    ; 4. 设置程序驻留内存 (TSR)
    ; DX = 需要驻留内存的大小 (以段为单位，1段 = 16字节)
    ; resident_part_end_marker 标签标记了驻留部分的末尾
    mov     dx, OFFSET resident_part_end_marker
    shr     dx, 4           ; 将字节偏移量转换为段数 (除以16)
    inc     dx              ; 额外增加一个段，确保所有代码都被包含，并向上取整

    mov     ah, 31h         ; DOS TSR 功能号
    mov     al, 00h         ; 返回码 (00h = 成功)
    int     21h             ; 调用DOS中断

    ; 成功设置为TSR后，程序不应执行到这里
    jmp     exit_program_non_resident ; 只是一个备用跳转

;-------------------------------------------------------------------------------
; 新的键盘中断服务程序 (ISR for INT 9)
;-------------------------------------------------------------------------------
isr_keyboard PROC FAR
    ; 保存所有可能被修改的寄存器
    push    ax
    push    bx
    push    cx
    push    dx
    push    si
    push    di
    push    ds
    push    es
    push    bp

    ; 设置DS = CS，以便访问代码段中的全局数据
    push    cs
    pop     ds

    in      al, 60h         ; 从键盘控制器端口60h读取扫描码

    ; 检查是否是我们关心的按键的按下码 (Make Code)
    ; 空格键 (Space) 的按下扫描码是 39h
    ; 回车键 (Enter) 的按下扫描码是 1Ch
    ; PDF文档提示移除 `k1.asm` 中的 `and al, 127`，这意味着我们直接使用原始扫描码。
    ; 按下码的最高位通常是0。

    cmp     al, 39h         ; 是空格键吗?
    je      isr_handle_space_key

    cmp     al, 1Ch         ; 是回车键吗?
    je      isr_handle_enter_key

    jmp     isr_proceed_to_chain ; 如果不是我们处理的键，则直接跳到中断链部分

isr_handle_space_key:
    call    increment_counter_proc ; 调用计数器加1子程序
    call    display_counter_proc   ; 调用显示计数器子程序
    jmp     isr_proceed_to_chain

isr_handle_enter_key:
    call    reset_counter_proc     ; 调用计数器清零子程序
    call    display_counter_proc   ; 调用显示计数器子程序
    ; 顺势执行到 isr_proceed_to_chain

isr_proceed_to_chain:
    ; 向8259 PIC (可编程中断控制器) 发送中断结束命令 (EOI)
    ; 这是因为我们的ISR已经响应了中断源 (键盘)
    mov     al, 20h
    out     20h, al         ; 发送EOI到主PIC (处理IRQ0-7)

    ; 恢复所有保存的寄存器
    pop     bp
    pop     es
    pop     ds
    pop     di
    pop     si
    pop     dx
    pop     cx
    pop     ax
    
    ; 跳转到原始的INT 9中断服务程序 (中断链)
    ; 使用 PUSH CS / PUSH IP / RETF 的技巧来实现远跳转
    ; 这会使CPU执行原始ISR，原始ISR最终会执行IRET，
    ; IRET会从堆栈中弹出由硬件压入的原始标志、CS和IP。
    push    cs:[orig_int9_cs] ; 将原始ISR的CS压栈
    push    cs:[orig_int9_ip] ; 将原始ISR的IP压栈
    retf                      ; 远返回：弹出IP，然后弹出CS，跳转执行
isr_keyboard ENDP

;-------------------------------------------------------------------------------
; 子程序：计数器加1 (处理4位ASCII数字字符串)
;-------------------------------------------------------------------------------
increment_counter_proc PROC NEAR
    push    ax
    push    si
    push    cx
    ; DS 已经由ISR设置为CS

    ; 从最低位开始处理 (counter_digits[3])
    mov     si, OFFSET counter_digits
    add     si, 3           ; 指向 '0000' 中的最后一个 '0' (最低有效位)
    mov     cx, 4           ; 总共4位数字

increment_digit_loop:
    mov     al, [si]        ; 获取当前位数字字符
    inc     al              ; 加1
    mov     [si], al        ; 保存回内存
    cmp     al, ':'         ; 比较是否大于 '9' (即变成了':' )
    jne     increment_finished_no_carry ; 如果不等于':' (即0-9)，则处理完毕，无进位

    ; 当前位产生进位 (从'9'变成':')
    mov     byte ptr [si], '0' ; 将当前位重置为'0'
    dec     si              ; 指向更高一位 (左边的数字)
    loop    increment_digit_loop ; 如果还有更高位 (cx != 0)，则继续处理进位

increment_finished_no_carry:
    ; 如果某一位增加后没有产生进位，或者所有位都处理完毕 (可能从9999变为0000)
    pop     cx
    pop     si
    pop     ax
    ret
increment_counter_proc ENDP

;-------------------------------------------------------------------------------
; 子程序：重置计数器为 "0000"
;-------------------------------------------------------------------------------
reset_counter_proc PROC NEAR
    push    ax
    push    si
    push    cx
    ; DS 已经由ISR设置为CS

    mov     si, OFFSET counter_digits
    mov     cx, 4
    mov     al, '0'
reset_char_loop:
    mov     [si], al
    inc     si
    loop    reset_char_loop
    
    pop     cx
    pop     si
    pop     ax
    ret
reset_counter_proc ENDP

;-------------------------------------------------------------------------------
; 子程序：在屏幕上显示4位计数器
;-------------------------------------------------------------------------------
display_counter_proc PROC NEAR
    push    ax
    push    bx
    push    cx
    push    dx
    push    si
    push    es
    ; DS 已经由ISR设置为CS

    mov     ax, 0B800h
    mov     es, ax          ; ES 指向文本模式下的屏幕内存基址
    mov     bx, cs:[screen_pos_counter] ; BX = 计数器在屏幕上的起始字节偏移

    mov     si, OFFSET counter_digits ; SI 指向我们存储计数器的ASCII字符串 (在CS中)
    mov     cx, 4           ; 显示4个数字
    mov     ah, cs:[counter_attribute] ; AH = 显示属性 (例如：黑底白字)

display_char_loop:
    mov     al, cs:[si]     ; 从我们的字符串中获取一个数字字符
    mov     es:[bx], al     ; 将字符写入屏幕内存
    inc     bx              ; 指向属性字节位置
    mov     es:[bx], ah     ; 将属性写入屏幕内存
    inc     bx              ; 指向下一个字符位置
    inc     si              ; 指向我们字符串中的下一个字符
    loop    display_char_loop
    
    pop     es
    pop     si
    pop     dx
    pop     cx
    pop     bx
    pop     ax
    ret
display_counter_proc ENDP

;-------------------------------------------------------------------------------
; 标记常驻内存部分结束的位置，用于计算TSR所需内存大小
resident_part_end_marker LABEL BYTE
;-------------------------------------------------------------------------------

exit_program_non_resident:
    ; 这部分代码在TSR成功安装后不会执行
    mov     ah, 4Ch         ; DOS 功能号：终止程序
    mov     al, 0           ; 返回码
    int     21h

END program_entry ; 指定程序入口点 (如果使用 .STARTUP，则默认为 START)
                  ; 如果没有 .STARTUP，则 END 后的标签是入口点