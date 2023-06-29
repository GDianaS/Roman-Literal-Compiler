;LLVM version 3.8.0 (http://llvm.org/)
;program teste
declare i32 @printf(i8*, ...) nounwind
@str_print_int = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@str_print_double = private unnamed_addr constant [7 x i8] c"%.2lf\0A\00", align 1
define i32 @main() nounwind {
%a = alloca i32
store i32 0, i32* %a
%b = alloca i32
store i32 0, i32* %b
%c = alloca i32
store i32 0, i32* %c
%d = alloca i32
store i32 0, i32* %d
%1 = sitofp i32 0rIVxx to double
store i32 %1, i32* %d
%2 = sitofp i32 0rii to double
store i32 %2, i32* %a
%3 = sitofp i32 0rxxx to double
store i32 %3, i32* %b
ret i32 0
}
