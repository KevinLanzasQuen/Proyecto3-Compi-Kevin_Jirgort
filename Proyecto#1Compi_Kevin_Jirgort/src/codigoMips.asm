.data
.text
.globl main
print:
sub $sp, $sp, 4
sw $ra, 0($sp)
li $v0,1
syscall
addi $sp, $sp, 0
lw $t0, 0($sp)
move $ra, $t0
jr $ra

main:
sub $sp, $sp, 4
sw $ra, 0($sp)
li $t0, 12
sub $sp, $sp, 4 
sw $t0, 0($sp) 

li $t0, 2
sub $sp, $sp, 4 
sw $t0, 0($sp) 

li $t0, 5
li $t1, 2
mult $t0, $t1
mflo $t0
li $t1, 7
sub $sp, $sp, 4 
sw $t0, 0($sp) 

div $t1, 
mflo $t0
li $t1, 8
sub $sp, $sp, 4 
sw $t0, 0($sp) 

mult $t1, 
mflo $t0
sub $sp, $sp, 4 
sw $t0, 0($sp) 

li $t0, 2
add $t1, , $t0
sub $sp, $sp, 4 
sw $t1, 0($sp) 

sub $sp, $sp, 4 
sw $v0, 0($sp) 

li $t0, 5
li $t1, 5
div $t0, $t1
mflo $t0
sub $sp, $sp, 4 
sw $t0, 0($sp) 

sub $sp, $sp, 4 
sw $v0, 0($sp) 

li $t0, 55
sub $sp, $sp, 4 
sw $t0, 0($sp) 

lw $t0, 36($sp)
lw $t1, 0($sp)
add $t2, $t0, $t1
lw $t0, 32($sp)
sub $sp, $sp, 4 
sw $t2, 0($sp) 

add $t1, $t0, 
sub $sp, $sp, 4 
sw $t1, 0($sp) 

li $t0, 2
li $t1, 10
sub $sp, $sp, 4 
sw $t1, 0($sp) 

sub $sp, $sp, 4 
sw $v0, 0($sp) 

lw $t1, 28($sp)
sub $sp, $sp, 4 
sw $t1, 0($sp) 

lw $t1, 24($sp)
lw $t2, 56($sp)
lw $t3, 20($sp)
add $t4, $t2, $t3
sub $sp, $sp, 4 
sw $t4, 0($sp) 

sub $sp, $sp, 4 
sw $v0, 0($sp) 

lw $t2, 64($sp)
lw $t3, 28($sp)
sub $sp, $sp, 4 
sw $t3, 0($sp) 

sub $sp, $sp, 4 
sw $v0, 0($sp) 

lw $t3, 72($sp)
lw $t4, 36($sp)
mult $t3, $t4
mflo $t3
sub $sp, $sp, 4 
sw $t3, 0($sp) 

sw , 72($sp) 
lw $t3, 76($sp)
lw $t4, 40($sp)
div $t3, $t4
mflo $t3
sub $sp, $sp, 4 
sw $t3, 0($sp) 

sub $sp, $sp, 4 
sw $v0, 0($sp) 

lw $t3, 48($sp)
lw $t4, 88($sp)
lw $t5, 84($sp)
lw $t6, 48($sp)
sw $t6, 40($sp) 
lw $t6, 84($sp)
lw $t7, 48($sp)
mult $t6, $t7
mflo $t6
sw $t6, 40($sp) 
lw $t6, 84($sp)
lw $t7, 48($sp)
div $t6, $t7
mflo $t6
sw $t6, 40($sp) 
li $t6, 15.7
li $t7, 15.7
div $t6, $t7
mflo $t6
li $t7, 5.1
sub $sp, $sp, 4 
sw $t6, 0($sp) 

mult $t7, 
mflo $t6
sub $sp, $sp, 4 
sw $t6, 0($sp) 

li $t6, 15.5
li $t7, 15.5
add , $t6, $t7
sub $sp, $sp, 4 
sw $v0, 0($sp) 

li $t6, 17.6
li $t7, 15.6
mult $t6, $t7
mflo $t6
sub $sp, $sp, 4 
sw $t6, 0($sp) 

li $t6, 20.2
li $t7, 15.5
sub $sp, $sp, 4 
sw $t7, 0($sp) 

lw $t7, 108($sp)
lw , 8($sp)
li , 5
li , 10.5
add , , 
sub $sp, $sp, 4 
sw $v0, 0($sp) 

li , 9.5
li , 7
add , , 
sub $sp, $sp, 4 
sw $v0, 0($sp) 

li , 2
li , 15.5
div , 
mflo 
sub $sp, $sp, 4 
sw $v0, 0($sp) 

li , 10.6
li , 5
div , 
mflo 
sw , 0($sp) 
li , 2
li , 10.5
mult , 
mflo 
sub $sp, $sp, 4 
sw $v0, 0($sp) 

li , 2.7
li , 5
mult , 
mflo 
sub $sp, $sp, 4 
sw $v0, 0($sp) 

li , 9
li , 9.9
sub $sp, $sp, 4 
sw $v0, 0($sp) 

li , 9.9
li , 10
sub $sp, $sp, 4 
sw $v0, 0($sp) 

lw , 36($sp)
lw , 136($sp)
add , , 
sub $sp, $sp, 4 
sw $v0, 0($sp) 

lw , 40($sp)
lw , 140($sp)
sub $sp, $sp, 4 
sw $v0, 0($sp) 

lw , 44($sp)
lw , 144($sp)
mult , 
mflo 
sub $sp, $sp, 4 
sw $v0, 0($sp) 

lw , 48($sp)
lw , 148($sp)
div , 
mflo 
sub $sp, $sp, 4 
sw $v0, 0($sp) 

lw , 20($sp)
lw , 40($sp)
add , , 
sw , 4($sp) 
lw , 20($sp)
lw , 40($sp)
sw , 4($sp) 
lw , 20($sp)
lw , 40($sp)
mult , 
mflo 
sw , 4($sp) 
lw , 20($sp)
lw , 40($sp)
div , 
mflo 
sw , 4($sp) 
lw , 152($sp)
sub $sp, $sp, 4 
sw $v0, 0($sp) 

sub $sp, $sp, 4 
sw $v0, 0($sp) 

end:
li $v0,10
syscall