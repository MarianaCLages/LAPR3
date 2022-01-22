.section .data

.equ STRUCT_SIZE,24	#All structure off sets

.equ XPOS_OFFSET,0
.equ YPOS_OFFSET,1
.equ ZPOS_OFFSET,2
.equ WIDTH_OFFSET,3
.equ LENGHT_OFFSET,4
.equ HEIGHT_OFFSET,5
.equ IS_REFRIGERATED_OFFSET,6
.equ REFRIGERATION_TEMPERATURE_OFFSET, 7
.equ PAYLOAD_OFFSET,8
.equ TARE_OFFSET,10
.equ GROSS_OFFSET,12
.equ ID_OFFSET,14
.equ THERMAL_RESISTANCE_OFFSET, 16
.equ ENERGY_CONSUMPTION_OFFSET, 20

.section .text
	.global verifyIfRefrigerated

verifyIfRefrigerated:		# %rdi: Container_Array / %rsi: Container_Position 
	cmpb $0, %sil
	je first_position		#In case the container position is the first in the array we can jump straight to the element
	
	movl $STRUCT_SIZE, %eax	#Prepare the off set to get the correct position

sum_loop:
	cmpl $0,%esi			#Loop to prepare the correct container that we are dealing with, according to the container position
	je continue
	addl %eax,%eax
	
	decl %esi
	jmp sum_loop
	
continue:
	addq %rax, %rdi		#Now we have the correct container position
	
	jmp verify_integrity
	
first_position:
	#Since the container position is the first in the array, we do not have to increment any off set
	
verify_integrity:

	movl IS_REFRIGERATED_OFFSET(%rdi), %ecx
	
	cmpb $0, %cl
	je not_refrigerated		#If the value of the variable inside the struct isRefrigerated is 0 then the container is not refrigerated
	
	movb $1, %al
	
	jmp end
	
not_refrigerated:
	movb $0, %al			#In case the container is not refrigerated we just return 1
	
end:
	ret
