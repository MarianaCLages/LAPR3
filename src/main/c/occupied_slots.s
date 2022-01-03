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
    cmpq %r9, %rax #verifica se estamos a analisar a ultima posição da matrix
    je f_end

    movl (%r12,%r9,1), %edx #obtem o valor que queremos obter atravez de um acesso á memoria

    cmpl $0, %edx  #compara o valor obtido com zero, o valor ser 0 significa que a posição está vazia
    je free_Slot

occupied_Slot: #adiciona 1 ao contador de ocupados e adiciona 4 ao registo que indica a posição no vetor passando assim para a proxima posição, continua o ciclo
    incl %r11d
    addq $4, %r9
    jmp verifySlots

free_Slot: #adiciona 1 ao contador de não ocupados e adiciona 4 ao registo que indica a posição no vetor passando assim para a proxima posição, continua o ciclo
    incl %r10d
    addq $4, %r9
    jmp verifySlots

f_end: # coloca o valor do contador de não ocupados nos 4 bytes menos significativos de rax, aplica um shift á esquerda de 32 bits fazendo o falor que estava nos 4 byts menos significativos no 4 bytes mais signifivativos
    movl %r10d, %eax
    salq $32 , %rax
    movq %r11, %rdx #coloca o valor do contador de ocupados em rdx, aplica depois uma mascara logica de or em que troca todos o bits a 0 pelo favor de rdx, fazendo com que o valor do contador fique nos 4 bytes menos significativos
    orq %rdx, %rax
    ret
