.MODEL small
.STACK 100h

.DATA
    ; No data section for TSR

.CODE
    ORG 100h        ; COM file format

;-------------------------------------------------------------------------------
; Data definitions (stored in code segment for TSR)
;-------------------------------------------------------------------------------
CounterValue  db '0','0','0','0'    ; Counter ASCII characters
OldKeyIP      dw ?                  ; Save old keyboard ISR IP
OldKeyCS      dw ?                  ; Save old keyboard ISR CS

MyProg PROC FAR
    ; Initialize
    push cs
    pop ds              ; DS = CS for COM program

    ; --- Save old keyboard interrupt vector (INT 9) ---
    push es
    xor ax, ax
    mov es, ax          ; ES = 0000h
    mov bx, 9 * 4       ; INT 9 vector address

    mov ax, es:[bx]
    mov cs:[OldKeyIP], ax
    mov ax, es:[bx+2]
    mov cs:[OldKeyCS], ax
    pop es

    ; --- Set new keyboard interrupt vector ---
    cli                 ; Disable interrupts
    push es
    xor ax, ax
    mov es, ax
    mov bx, 9 * 4

    mov ax, OFFSET KeyISR
    mov es:[bx], ax
    mov ax, cs
    mov es:[bx+2], ax
    pop es
    sti                 ; Enable interrupts

    ; --- Initialize counter display ---
    call DisplayCounter

    ; --- TSR exit, stay resident ---
    mov dx, OFFSET EndResidentPart
    add dx, 15          ; Round up to paragraph boundary
    shr dx, 4           ; Convert to paragraphs
    mov ax, 3100h       ; TSR function, return code 0
    int 21h

MyProg ENDP

;-------------------------------------------------------------------------------
; Keyboard Interrupt Service Routine (ISR)
;-------------------------------------------------------------------------------
KeyISR PROC FAR
    push ax
    push bx
    push cx
    push dx
    push si
    push di
    push ds
    push es
    push bp

    ; Set DS to code segment
    push cs
    pop ds

    ; Read keyboard scan code
    in al, 60h

    ; Check if Enter key (scan code 1Ch)
    cmp al, 1Ch
    je IsEnterKey

    ; Check if Space key (scan code 39h)
    cmp al, 39h
    je IsSpaceKey

    ; Not our key, chain to original handler
    jmp ChainToOriginal

IsSpaceKey:
    ; Increment counter
    mov cx, 4
    mov si, OFFSET CounterValue + 3    ; Start from least significant digit
IncrementLoop:
    mov al, [si]
    inc al
    cmp al, '9' + 1
    jne StoreDigit
    mov al, '0'                        ; Carry, set current digit to 0
    mov [si], al
    dec si
    loop IncrementLoop
    jmp UpdateDone

StoreDigit:
    mov [si], al
UpdateDone:
    call DisplayCounter
    jmp SendEOI

IsEnterKey:
    ; Reset counter to 0
    mov byte ptr [CounterValue],   '0'
    mov byte ptr [CounterValue+1], '0'
    mov byte ptr [CounterValue+2], '0'
    mov byte ptr [CounterValue+3], '0'
    call DisplayCounter

SendEOI:
    ; Send EOI to 8259A interrupt controller
    mov al, 20h
    out 20h, al

    ; Restore registers and return
    pop bp
    pop es
    pop ds
    pop di
    pop si
    pop dx
    pop cx
    pop bx
    pop ax
    iret                                ; Interrupt return

ChainToOriginal:
    ; Restore registers
    pop bp
    pop es
    pop ds
    pop di
    pop si
    pop dx
    pop cx
    pop bx
    pop ax
    ; Jump to original keyboard handler
    jmp dword ptr cs:[OldKeyIP]

KeyISR ENDP

;-------------------------------------------------------------------------------
; Display counter subroutine
;-------------------------------------------------------------------------------
DisplayCounter PROC NEAR
    push ax
    push bx
    push cx
    push dx
    push si
    push ds
    push es

    ; Set DS to code segment
    push cs
    pop ds

    ; Set ES to video memory
    mov ax, 0B800h
    mov es, ax

    ; Screen position (row 0, column 0)
    mov bx, 0                           ; (0 * 80 + 0) * 2

    ; Display 4 digits
    mov cx, 4
    mov si, OFFSET CounterValue
DisplayLoop:
    mov al, [si]                        ; Get character
    mov es:[bx], al                     ; Write character
    inc bx
    mov byte ptr es:[bx], 07h           ; White on black attribute
    inc bx
    inc si
    loop DisplayLoop

    pop es
    pop ds
    pop si
    pop dx
    pop cx
    pop bx
    pop ax
    ret
DisplayCounter ENDP

EndResidentPart LABEL BYTE              ; TSR end marker

END MyProg