`define DELAY 20
module mux_4x1_testbench();

reg s1, s2, inA, inB, pre_inA, pre_inB;
wire bigO;

mux_4x1 mux_tb (bigO, s1, s2, inA, inB, pre_inA, pre_inB);

initial begin
s1 = 1'b0; s2 = 1'b0; inA = 1'b1; inB = 1'b1;
#`DELAY;
s1 = 1'b0; s2 = 1'b1; inA = 1'b1; inB = 1'b1;
#`DELAY;
s1 = 1'b1; s2 = 1'b0; inA = 1'b1; inB = 1'b1;
#`DELAY;
s1 = 1'b1; s2 = 1'b1; inA = 1'b1; inB = 1'b1;
end

initial
begin
$monitor("time=%2d, s1=%1b, s2=%1b, inA=%1b, inB=%1b, out=%1b ", $time, s1, s2, inA, inB, out);
end

endmodule
