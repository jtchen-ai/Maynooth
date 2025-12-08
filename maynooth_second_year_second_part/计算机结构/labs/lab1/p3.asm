.MODEL medium
.STACK
.DATA
.CODE
.STARTUP

    mov ah, 02h       ; 打印 'S' 表示开始
    mov dl, 'S'
    int 21h

    mov bx, 6500     ; 外循环次数
back2:
    mov cx, 6400    ; 内循环次数
back1:
    nop               ; 空操作，占用时间
    loop back1        ; 内循环控制

    dec bx            ; 外循环控制
    jnz back2

    mov ah, 02h       ; 打印 'F' 表示结束
    mov dl, 'F'
    int 21h

.EXIT
END