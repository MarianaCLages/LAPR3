.section .data
.global matrix
.global tamanho_x
.global tamanho_y
.global tamanho_z

.section .text
.global occupiedSlots

occupiedSlots:
    movq matrix(%rip), %r12 
    //Move the pointer of the matrix to a register
    movl tamanho_x(%rip), %eax
    imull tamanho_y(%rip)
    imull tamanho_z(%rip) 
    salq $2 , %rax

    //After this multiplications we know the size of the matrix
    movq $0, %r9 
    //This register will serve to verify in which position of the matrix we are

    movl $0, %r11d 
    //Count of occupied slots
    movl $0, %r10d 
    //Count of free slots

verifySlots:
    cmpq %r9, %rax
    je f_end

    movl (%r12,%r9,1), %edx

    cmpl $0, %edx
    je free_Slot

occupied_Slot:
    incl %r11d
    addq $4, %r9
    jmp verifySlots

free_Slot:
    incl %r10d
    addq $4, %r9
    jmp verifySlots

f_end:
    movl %r10d, %eax
    salq $32 , %rax
    movq %r11, %rdx
    orq %rdx, %rax
    ret
