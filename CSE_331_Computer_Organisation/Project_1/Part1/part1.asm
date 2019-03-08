.data

	decimal1:		.word 0
	space_oddity:		.asciiz " "
	float1:			.word 0
	operator:		.space 3
	add_op:			.byte '+'
	sub_op:			.byte '-'
	mul_op:			.byte '*'
	decimal2:		.word 0
	float2: 		.word 0
	newline:		.byte '\n'
	dot:			.byte '.'
	digit_of_first:		.word 0
	digit_of_second:	.word 0
	final_decimal:		.word 0
	final_float:		.word 0

.text

main:
	
	#beginning of getting inputs.
	li $v0, 5
	syscall
	sw $v0, decimal1
	
	li $v0, 5
	syscall
	sw $v0, float1
	
	li $v0, 12
	syscall
	sb $v0, operator
	
	li $v0, 12
	syscall
	
	li $v0, 5
	syscall
	sw $v0, decimal2
	
	li $v0, 5
	syscall
	sw $v0, float2
	#ending of getting inputs.
	
	#operator to t0 register
	lb $t0, operator
	
	#compare the operators
	lb $t1, add_op
	beq $t0, $t1, add_if
	
	lb $t1, sub_op
	beq $t0, $t1, sub_if
	
	lb $t1, mul_op
	beq $t0, $t1, mul_if
	
	#if the operator does not exist, finish the program.
	li $v0, 10
	syscall

#the operator is +
add_if:
	
	lw $s0, float1
	li $t2, 10
	li $t3, 0
	
	jal findDigit
	
	sw $t3, digit_of_first
	
	lw $s0, float2
	li $t3, 0
	
	jal findDigit
	
	sw $t3, digit_of_second
	
	lw $s1, digit_of_first
	lw $s2, digit_of_second
	
	jal equalize_digits
	
	li $t3, 0
	add $s0, $t6, $t7
	move $s5, $s0
	
	jal findDigit
	
	beq $t3, $s2, add_print_normal
	bne $t3, $s2, carry_process
	
	li $v0, 10
	syscall
	
sub_if:

	lw $s0, float1
	li $t2, 10
	li $t3, 0
	
	jal findDigit
	
	sw $t3, digit_of_first
	
	lw $s0, float2
	li $t3, 0
	
	jal findDigit
	
	sw $t3, digit_of_second
	
	lw $s1, digit_of_first
	lw $s2, digit_of_second
	
	jal equalize_digits
	
	li $t3, 0
	lw $t9, decimal1
	
	jal merge
	
	add $s5, $t9, $t6
	
	li $t3, 0
	lw $t9, decimal2
	
	jal merge
	
	add $s6, $t9, $t7
	
	sub $t0, $s5, $s6
	
	li $t9, 1
	li $s7, 0
	
	move $t3, $s1
	
	jal get_power
	
	div $t0, $t9
	mflo $t1
	mfhi $t8
	
	li $v0, 1
	move $a0, $t1
	syscall
	
	li $v0, 11
	lb $a0, dot
	syscall
	
	move $s0, $t8
	li $t2, 10
	li $t3, 0
	
	jal findDigit
	
	beq $t3, $s1, normal_sub
	
	li $s6, 0
	li $s7, 0
	
	sub $t9, $s1, $t3
	
	bne $t3, $s1, other_sub
	
	li $v0, 10
	syscall
	
mul_if:

	lw $s0, float1
	li $t2, 10
	li $t3, 0
	
	jal findDigit
	
	sw $t3, digit_of_first
	
	lw $s0, float2
	li $t3, 0
	
	jal findDigit
	
	sw $t3, digit_of_second
	
	lw $s1, digit_of_first
	lw $s2, digit_of_second
	
	jal equalize_digits

	li $t3, 0
	lw $t9, decimal1
	
	jal merge
	
	add $s5, $t9, $t6
	
	li $t3, 0
	lw $t9, decimal2
	
	jal merge
	
	add $s6, $t9, $t7
	
	mul $t0, $s5, $s6
	
	li $t9, 1
	li $s7, 0
	
	add $t3, $s1, $s2
	
	jal get_power
	
	div $t0, $t9
	mflo $t1
	mfhi $t2
	
	li $v0, 1
	move $a0, $t1
	syscall
	
	li $v0, 11
	lb $a0, dot
	syscall
	
	li $v0, 1
	move $a0, $t2
	syscall

	li $v0, 10
	syscall
	

normal_sub:

	blt $t8, $zero, normal_neg_sub
	
	li $v0, 1
	move $a0, $t8
	syscall
	
	li $v0, 10
	syscall
	
normal_neg_sub:

	sub $t8, $zero, $t8
	
	li $v0, 1
	move $a0, $t8
	syscall
	
	li $v0, 10
	syscall
		
other_sub:

	li $v0, 1
	move $a0, $s6
	syscall
	
	addi $s7, $s7, 1
	
	bne $s7, $t9, other_sub
	
	blt $t8, $zero, normal_neg_sub
	
	li $v0, 1
	move $a0, $t8
	syscall

	li $v0, 10
	syscall
	
merge:
	
	mul $t9, $t9, $t2
	addi $t3, $t3, 1
	
	bne $t3, $s1, merge
	
	jr $ra
	
	
findDigit:
	div $s0, $t2
	mflo $t4
	move $s0, $t4
	addi $t3, $t3, 1
	
	bne $zero, $t4, findDigit
	
	jr $ra

equalize_digits:

	lw $t6, float1
	lw $t7, float2
	blt $s1, $s2, equ1
	bgt $s1, $s2, equ2
	
	jr $ra
	
equ1:
	mul $t6, $t6, $t2
	addi $s1, $s1, 1
	bne $s1, $s2, equ1
	
	sw $t6, float1
	
	jr $ra

equ2:
	mul $t7, $t7, $t2
	addi $s2, $s2, 1
	bne $s1, $s2, equ2
	
	sw $t7, float2
	
	jr $ra


	
add_print_normal:
	
	lw $t1, decimal1
	lw $t2, decimal2
	lw $t5, float1
	lw $t6, float2
	
	add $t7, $t1, $t2
	add $t8, $t5, $t6
	
	li $v0, 1
	move $a0, $t7
	syscall
	
	li $v0, 11
	lb $a0, dot
	syscall
	
	li $v0, 1
	move $a0, $t8
	syscall
	
	li $v0, 10
	syscall
	

carry_process:
	
	li $s7, 1
	li $t9, 1
	
	jal get_power
	
	div $s5, $t9
	mfhi $s5
	
	move $s0, $s5
	li $t3, 0
	
	jal findDigit
	
	sub $t9, $s2, $t3
	
	li $s6, 0
	li $s7, 0
	
	lw $t0, decimal1
	lw $t1, decimal2
	add $t2, $t0, $t1
	addi $t2, $t2, 1
	
	li $v0, 1
	move $a0, $t2
	syscall
	
	li $v0, 11
	lb $a0, dot
	syscall
	
	beq $t9, $zero, add_print_carry_1
	bne $t9, $zero, add_print_carry_2
	
	li $v0, 10
	syscall
	
	
	
get_power:
		
	mul $t9, $t9, $t2
	addi $s7, $s7, 1
	
	bne $s7, $t3, get_power
	
	jr $ra
	
add_print_carry_1:
	
	li $v0, 1
	move $a0, $s5
	syscall
	
	li $v0, 10
	syscall
	
add_print_carry_2:
	
	li $v0, 1
	move $a0, $s6
	syscall
	
	addi $s7, $s7, 1
	
	bne $s7, $t9, add_print_carry_2 
	
	li $v0, 1
	move $a0, $s5
	syscall
	
	li $v0, 10
	syscall
