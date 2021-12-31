.section .data
.global posicao_z
.global posicao_y
.global posicao_x
.global tamanho_y
.global tamanho_x
.global tamanho_z
.global matrix
.global rederecoTest

.section .text
.global existe
existe:
//a verdadeira posição do elemento que queremos encontrar irá ser dada por (posicao_x*tamanho_y*tamanho_z +posicao_y*tamanho_z+posicao_z), uma vez que todos os elementos se econtram seguidos em memória

	movq matrix(%rip), %r12	
	
	movl posicao_y(%rip), %eax
	mull tamanho_z(%rip)
	movl %eax, %ecx
	
	movl posicao_x(%rip), %eax
	mull tamanho_y(%rip)
	mull tamanho_z(%rip)
	
	addl posicao_z(%rip), %eax
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
