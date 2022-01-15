.section .data

.equ STRUC_SIZE,32
.equ PAYLOAD_OFFSET,0
.equ TARE_OFFSET,4
.equ GROSS_OFFSET,8
.equ IDENTIFICATION_OFFSET, 12
.equ ISO_CODE_OFFSET,20
.equ IS_REFRIGERATED_OFFSET,31

.section .text
.global verifyStruc

verifyStruc:
	movl PAYLOAD_OFFSET(%rdi), %eax
	
end:
	ret
