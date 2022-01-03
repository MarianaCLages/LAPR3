.section .data
    .global tamanho_y
    .global tamanho_x
    .global tamanho_z
    .global matrix
x:
    .int # %rdi
y: 
    .int # %rsi
z: 
    .int # rdx

.section .text
.global existe2
existe:
//a verdadeira posição do elemento que queremos encontrar irá ser dada por (%rdi*tamanho_y*tamanho_z +%rsi*tamanho_z+%rdx), uma vez que todos os elementos se econtram seguidos em memória

	movq matrix(%rip), %r12	
	
	movl %rsi(%rip), %eax
	mull tamanho_z(%rip)
	movl %eax, %ecx
	
	movl %rdi(%rip), %eax
	mull tamanho_y(%rip)
	mull tamanho_z(%rip)
	
	addl %rdx(%rip), %eax
	addl %ecx, %eax
	
    movl (%r12,%rax,4), %edx
			
	cmpl $0, %edx
	
	je vazio
	movb $1, %al
	jmp fim
	
vazio:
	movb $0, %al
	
fim:
ret