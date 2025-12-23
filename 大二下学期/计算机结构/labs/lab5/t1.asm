.MODEL    small
.STACK
.DATA
  
.CODE

.STARTUP

          push    es
          mov     ax, 0000h
          mov     es, ax
          mov     bx, 024h        ; INT 9 vector offset

          
          mov     ax, es:[bx]     ; Old IP
          
          mov     cs:[OFFSET vtabip_label], ax 

          inc     bx
          inc     bx
          mov     ax, es:[bx]     ; Old CS
          mov     cs:[OFFSET vtabcs_label], ax ; 
          pop     es


          mov     ah, 031h
          mov     al, 00h
          mov     dx, OFFSET EndResidentPart ; 
          sub     dx, OFFSET isr             ;
          shr     dx, 4
          inc     dx
          int     021h

          jmp     quit            ;

isr:
          ;
          push    cs
          pop     ds              ;

          

          db      0EAh            ; 
vtabcs_label dw   0               ; 

quit:
EndResidentPart LABEL BYTE ; 
          .EXIT

END