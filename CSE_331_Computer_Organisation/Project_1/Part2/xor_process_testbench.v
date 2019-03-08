`define DELAY 20
module xor_process_testbench();

reg inA, inB;
wire out;

xor_process two_inp_xor_process(out, inA, inB);

initial begin
inA = 1'b0; inB = 1'b0;
#`DELAY;
inA = 1'b0; inB = 1'b1;
#`DELAY;
inA = 1'b1; inB = 1'b0;
#`DELAY;
inA = 1'b1; inB = 1'b1;
end

initial
begin
$monitor("time=%2d, inA=%1b, inB=%1b, out=%1b", $time, inA, inB, out);
end

endmodule