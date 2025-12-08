.286
.model medium
.8087
.stack 100h

.DATA
    SX      dd 3.0           ; 
    SY      dd 4.0           ; 
    cntrl   dw 03FFh         ; 
    stat    dw 0             ; 
    INTG    dw 0             ; 
    CRLF    db 0Dh,0Ah,'$'   ;

.CODE
.STARTUP
    FINIT
    FLDCW   cntrl

    ;—— HY = sqrt(SX² + SY²) ——————————————————
    FLD     SX
    FMUL    ST,ST(0)         ; ST0 = SX*SX
    FLD     SY
    FMUL    ST,ST(0)         ; ST0 = SY*SY
    FADD    ST,ST(1)         ; ST0 = SX²+SY²
    FSQRT                    ; ST0 = √(SX²+SY²)
    FSTSW   stat
    mov     ax,stat
    and     al,0BFh
    jnz     DONE
    FSTP    HY               ;

   —
    mov     bx,OFFSET HY
    add     bx,2
    mov     ax,[bx]
    mov     bx,ax
    mov     cx,16
_P_HIGH:
    rol     bx,1
    jc      _H1
    mov     dl,'0'
    jmp     _H2
_H1:
    mov     dl,'1'
_H2:
    mov     ah,02h
    int     21h
    loop    _P_HIGH

    mov     bx,OFFSET HY
    mov     ax,[bx]
    mov     bx,ax
    mov     cx,16
_P_LOW:
    rol     bx,1
    jc      _L1
    mov     dl,'0'
    jmp     _L2
_L1:
    mov     dl,'1'
_L2:
    mov     ah,02h
    int     21h
    loop    _P_LOW


    mov     dx,OFFSET CRLF
    mov     ah,09h
    int     21h
————————————
    fld     HY
    fistp   word ptr INTG    ; INTG ← 5

    mov     ax,INTG
    xor     dx,dx
    mov     bx,10
    div     bx               
    cmp     ax,0
    je      _PRINT_REM
    add     al,'0'
    mov     dl,al
    mov     ah,02h
    int     21h
_PRINT_REM:
    add     dl,'0'
    mov     ah,02h
    int     21h

 
    mov     dl,'.'
    mov     ah,02h
    int     21h
    mov     dl,'0'
    mov     ah,02h
    int     21h

DONE:
    .EXIT
END