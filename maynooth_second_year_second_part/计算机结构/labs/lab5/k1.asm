;/* k1.asm Detects keypress from the keyboard      */
;/* If the key presses is 'h' or'H'                 */
;/* the routine prints 'Hello' on the screen       */
; By  C.Markham
; Revised  March 2016

.MODEL    small
.STACK
.DATA      ; DS will may change when TSR operates
.CODE
.STARTUP
          push    es              ; Save existing value of ES.
          mov     ax, 0000h       ; Set ES (extra segment) to page 0
          mov     es, ax          ; Note page 0 is vector address table
          mov     bx, 024h        ; Vector address for keyboard INT 9, IRQ1
          mov     ax, es:[bx]     ; ax=[0000:0020], CS to jump to at end of our new ISR
          mov     vtabip, ax      ; Store the CS, 1st two bytes of table
          inc     bx
          inc     bx
          mov     ax, es:[bx]     ; ax=[0000:0020], IP to jump to
          mov     vtabcs, ax      ; Store the IP, 2nd two bytes
          pop     es              ; Restore the value of es

          push    es              ; Change vector address to ISR routine
          mov     ax, 0000h       ; Vector address table segment
          mov     es, ax
          mov     bx, 024h
          mov     ax, OFFSET isr  ; Set IP of vector table to new ISR
          mov     es:[bx], ax
          inc     bx
          inc     bx
          mov     ax, cs          ; The ISR is in the same segment as the program.
          mov     es:[bx], ax     ; New CS in vector table is the code segment of
          pop     es              ; this program.

          mov     ah, 031h        ; TSR Entry code ah=31h,al=0h
          mov     al, 00h
          mov     dx, 1024        ; DX=no of bytes to remain resident
          int     021h            ; Terminate and stay resident.

          jmp     quit            ; Historic code, never reached

isr:      push    ax              ; Start of code you wish to call ever 50mS (18ms PC).
          push    bx              ; Interrupts don’t store general registers, you must.
          push    cx
          push    dx
          push    di
          push    si
          push    bp
          push    es
          push    ds

          mov     dx, 60h         ; Read the last scan code
          in      al, dx
          and     al, 127         ; Mask MSB 1=Key Down, 0=Key Up remove if just up or down
          cmp     al, 23h         ; Was a h/H pressed on the XT keyboard
          jnz     ovr1

; Change to
; <space> scan
; code rather
; than ‘h’.
; Remove the
; ‘and al,127’
          mov     ax, 0b800h      ; Base address of screen memory
          mov     es, ax
          mov     bx, 140         ; Screen position (1,70)

          mov     al, 'H'         ; Print 'Hello' on the screen
          mov     es:[bx], al

          inc     bx
          inc     bx
          mov     al, 'e'
          mov     es:[bx], al

          inc     bx
          inc     bx
          mov     al, 'l'
          mov     es:[bx], al

          inc     bx
          inc     bx
          mov     al, 'l'
          mov     es:[bx], al

          inc     bx
          inc     bx
          mov     al, 'o'
          mov     es:[bx], al

          jmp     ovr2

ovr1:     mov     ax, 0b800h      ; If not an H overwrite top right with ' '
          mov     es, ax
          mov     bx, 140         ; Screen position (1,70)
          mov     cx, 5
again:    mov     al, ' '
          mov     es:[bx], al
          inc     bx
          inc     bx
          loop    again

ovr2:     mov     dx, 020h        ; Non Specific end of interrupt command
          mov     al, 020h        ; to sent 8259 #1 controller.
          out     dx, al
          pop     ds              ; Restore all registers previously save on
          pop     es              ; the stack.
          pop     bp
          pop     si
          pop     di
          pop     dx
          pop     cx
          pop     bx
          pop     ax

          db      0EAh            ; jump far  (instruction created on the fly...)
vtabip    dw      0               ; IP offset
vtabcs    dw      0               ; CS segment

quit:     .EXIT                   ; Historic code, never reached

END