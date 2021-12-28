.section .data
.global posicao_z
.global posicao_y
.global posicao_x
.global tamanho_y
.global tamanho_x
.global matrix

.section .text
.global existe
existe:
//a verdaeira posição do elemento que queremos encontrar irá ser dada por (posicao_z*tamanho_y +posicao_y*tamanho_x+posicao_x), uma vez que todos os elementos se econtram seguidos em memória

	movl posicao_y(%rip), %eax
	mull tamanho_x(%rip)
	movl %eax, %ecx
	
	movl posicao_z(%rip), %eax
	mull tamanho_y(%rip)
	mull tamanho_x(%rip)
	
	addl posicao_x(%rip), %eax
	addl %ecx, %eax
	
	movl %eax, %edx
	
	cmpl (matrix(%rip),%edx,4), 0
	je vazio
	movb %1, %al
	jmp fim
	
vazio:
	movb $0, %al
	
fim:
ret
