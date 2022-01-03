.section .data 
    .global matrix
    .global tamanho_y
    .global tamanho_x
    .global tamanho_z


.section .text
    .global existeLoop
    
    # save registers
    pushl %eax # nº de posiçoes ocupadas
    pushq %rdi # x
    pushq %rsi # y
    pushq %rdx # z
    pushl %r9 # total ids encontrados

    movq $0, %rdi
    movq $0, %rsi
    movq $0, %rdx

existeLoop:
    call existeComParametros

    cmpb %al, $1
    jne aumentar_y

adicionar_total:
    incl %r9
    incq %rdx

    cmpq tamanho_z(%rip), %rdx
    jne existeLoop  

aumentar_y:
    movq $0, %rdx
    incq %rsi
    
    cmpq tamanho_y(%rip), %rsi
    jne existeLoop

aumentar_x:
    movq $0, %rsi
    incq %rdi
    
    cmpq tamanho_y(%rip), %rdi
    jne existeLoop

end:
movl %r9, %eax
popl %r9
popl %rdx
popl %rsi
popl %rdi

ret
    

