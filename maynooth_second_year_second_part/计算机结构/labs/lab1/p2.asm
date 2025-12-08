.MODEL small
.STACK 100h
.DATA
buffer db 100 dup('$')      ; 缓冲区
msg    db 'Input: $'
newline db 13, 10, '$'

.CODE
main:
    mov ax, @data
    mov ds, ax

    ; 显示提示
    mov ah, 09h
    mov dx, offset msg
    int 21h

    ; 读取一行字符串
    mov ah, 0Ah
    lea dx, buffer
    int 21h

    ; 换行
    mov ah, 09h
    mov dx, offset newline
    int 21h

    ; 输出转换后的字符串
    lea si, buffer+2         ; 第一个字符偏移（跳过长度信息）
next_char:
    mov al, [si]
    cmp al, 13               ; 回车结束
    je done

    cmp al, 'a'
    jb print
    cmp al, 'z'
    ja print
    sub al, 32               ; 转大写

print:
    mov dl, al
    mov ah, 02h
    int 21h

    inc si
    jmp next_char

done:
    mov ah, 4Ch
    int 21h

END main