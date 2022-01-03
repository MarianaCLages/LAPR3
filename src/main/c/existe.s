.section .data
.global posicao_z
.global posicao_y
.global posicao_x
.global tamanho_y
.global tamanho_x
.global tamanho_z
.global matrix

.section .text
.global existe
existe:
//a verdadeira posição do elemento que queremos encontrar irá ser dada por (posicao_x*tamanho_y*tamanho_z +posicao_y*tamanho_z+posicao_z), uma vez que todos os elementos se econtram seguidos em memória

	movq matrix(%rip), %r12	#coloca o endereço da matriz no registo r12, vai ser usado sempre ccomo endereço base
	
	#Faz as operaçoes aritmeticas necessarias para obter a posição na memoria do elemento necessario 
	movl posicao_y(%rip), %eax 
	mull tamanho_z(%rip)
	movl %eax, %ecx
	
	movl posicao_x(%rip), %eax
	mull tamanho_y(%rip)
	mull tamanho_z(%rip)
	
	addl posicao_z(%rip), %eax
	addl %ecx, %eax
	
    movl (%r12,%rax,4), %edx #obtem o valor associado á posição
			
	cmpl $0, %edx #compara o valor obtido com zero, o valor ser 0 significa que a posição está vazia
	
	je vazio
	movb $1, %al #Se a posição não estiver vazia, então o valor a retornar é 1
	jmp fim
	
vazio:
	movb $0, %al #Se a posição estiver vazia, então o valor a retornar é 0
	
fim:
ret
