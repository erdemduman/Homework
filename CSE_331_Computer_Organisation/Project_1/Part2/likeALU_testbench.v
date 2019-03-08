`define DELAY 20
module likeALU_testbench();

reg [4:0]inp_A, inp_B;
reg [1:0] select;
wire [4:0]out;

likeALU alu_tb (out, inp_A, inp_B, select);

initial begin
select = 2'b00; inp_A = 5'b11000; inp_B = 5'b11111;
#`DELAY;
select = 2'b01; inp_A = 5'b10101; inp_B = 5'b00011;
#`DELAY;
select = 2'b10; inp_A = 5'b11111; inp_B = 5'b11001;
#`DELAY;
select = 2'b11; inp_A = 5'b00000; inp_B = 5'b00101;
end

initial
begin
$monitor("time=%2d, select =%2b, inp_A=%5b, inp_B=%5b, out=%5b ", $time, select, inp_A, inp_B, out);
end

endmodule