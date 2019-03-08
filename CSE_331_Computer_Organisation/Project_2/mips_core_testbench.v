module mips_testbench ();

reg [31:0] instruction_set;
wire [31:0] result;

mips_core mc_tb(instruction_set, result);

initial begin
instruction_set = 32'b00000011110111110000000001100000;
#10; 	
instruction_set = 32'b00000011110111110000000001100010;
#10; 
instruction_set = 32'b00000011110111110000000001100100; 
#10;
instruction_set = 32'b00000011110111110000000001100101; 
#10;
instruction_set = 32'b00000011110111110000000001101011;
#10; 
instruction_set = 32'b00000011110111110000000001100001; 
#10;
instruction_set = 32'b00000011110111110000000001000000; 
#10;
instruction_set = 32'b00000011110111110000000001000010; 
#10;
instruction_set = 32'b00000011110111110000000001000011; 
#10;

end


initial
begin
$monitor("time=%2d, function code=%6b result=%32b", $time, instruction_set[5:0],result);
end



endmodule