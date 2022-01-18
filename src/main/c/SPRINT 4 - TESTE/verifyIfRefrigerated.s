.section .data

.equ STRUCT_SIZE,20	#All structure off sets

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
.equ ENERGY_CONSUMPTION_OFFSET, 16
.equ THERMAL_RESISTANCE_OFFSET, 18

.section .text
	.global verifyIfRefrigerated

verifyIfRefrigerated:		# %rdi: Container_Array / %rsi: Container_Position 
	cmpb $0, %sil
	je first_position		#in case the container position is the first in the array
	
	movl $STRUCT_SIZE, %eax	#prepare the off set to get the correct position
	mull %esi
		
	addq %rax, %rdi			#now we have the correct container position
	
	jmp verify_integrity
	
first_position:
	#since the container position is the first in the array, we don't have to increment any off set
	
verify_integrity:

	movb IS_REFRIGERATED_OFFSET(%rsi), %cl

	cmpb $0, %cl
	je not_refrigerated	#if the value of the variable inside the struct "isRefrigerated" is 0 then the container is not refrigerated
	
	movb $1, %al
	
	jmp end
	
not_refrigerated:
	movb $0, %al
	
end:
	ret
