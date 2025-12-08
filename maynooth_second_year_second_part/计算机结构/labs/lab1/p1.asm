.MODEL medium
.STACK
.DATA
.CODE
.STARTUP

nextc:
    mov ah, 8
    int 21h
    mov dl, al

    cmp dl, 'q'
    je quit

    cmp dl, '0'
    jb nextc        ; 无符号小于 '0'

    cmp dl, '9'
    ja nextc        ; 无符号大于 '9'

    mov ah, 2
    int 21h

    jmp nextc

quit:
    .EXIT
END