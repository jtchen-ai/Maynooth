.MODEL small
 .STACK
 .DATA
    ; You can define your initials here if you don't want to hardcode them in the code
    ; INITIAL1 DB 'S'
    ; INITIAL2 DB 'J'
 .CODE
 .STARTUP
    mov ax,0b800h     ; Set the video memory segment address to ax
    mov es,ax         ; Assign the value of ax to the es register, es now points to the video memory segment

    ; Calculate the offset address for the target position (row 10, column 12)
    ; Row number y = 10, Column number x = 12
    ; Offset = (y * 160) + (x * 2) = (10 * 160) + (12 * 2) = 1600 + 24 = 1624 (decimal) = 658h
    mov bx, 1624      ; Store the calculated offset address in bx

    ; --- First Initial ---
    ; Character: Your first initial (e.g., 'S')
    ; Color: Yellow on black background (attribute 0Eh)
    mov al, 'C'       ; Put your first initial into the al register (please replace 'S')
    mov es:[bx], al   ; Write the character in al to the current video memory location (es:bx)

    inc bx            ; bx points to the attribute byte location of the first character
    mov al, 0Eh       ; Set attribute to yellow on black (yellow foreground E, black background 0)
    mov es:[bx], al   ; Write the attribute byte to video memory

    inc bx            ; bx points to the ASCII code location of the second character

    ; --- Second Initial ---
    ; Character: Your second initial (e.g., 'J')
    ; Color: Bright blue on yellow background (attribute 69h)
    mov al, 'J'       ; Put your second initial into the al register (please replace 'J')
    mov es:[bx], al   ; Write the character in al to the current video memory location (es:bx)

    inc bx            ; bx points to the attribute byte location of the second character
    mov al, 69h       ; Set attribute to bright blue on yellow (bright blue foreground 9, yellow background 6)
    mov es:[bx], al   ; Write the attribute byte to video memory

quit:
    .EXIT             ; End of program
END