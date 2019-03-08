module mips_core(instruction, result);

input [31:0] instruction;
output reg [31:0] result;
wire [31:0] rsCon, rtCon;
reg reg_write_signal = 1'b0;

mips_registers mr_call(rsCon, rtCon, result, instruction[25:21], instruction[20:16], instruction[15:11], reg_write_signal, 1'b0);

always @(*) begin

        if(instruction[5:0] == 6'b100000)
				begin
					
					result = $signed (rsCon) + $signed (rtCon);
				
				end
			
			else if (instruction[5:0] == 6'b100010)
				begin 
				
					result = rsCon - rtCon;
				
				end
			
			else if (instruction[5:0] == 6'b100100)
				begin
				
					result = rsCon & rtCon;
				
				end
			
			else if (instruction[5:0] == 6'b100101)
				begin
				
					result = rsCon | rtCon;
				
				end
			
			else if (instruction[5:0] == 6'b101011)
				begin
				
				if(rsCon < rtCon)
					begin
					
						result = 32'b00000000000000000000000000000001;
					
					end
				
				else
					begin
					
						result = 32'b00000000000000000000000000000000;
					
					end
				
				end
				
			else if (instruction[5:0] == 6'b100001)
				begin
				
					result = rsCon + rtCon;
				
				end
				
			else if(instruction[5:0] == 6'b000000)
				begin
				
					result = rtCon << instruction[10:6];
				
				end
		
			else if(instruction[5:0] == 6'b000010)
				begin
				
					result = rtCon >> instruction[10:6];
				
				end
				
			
			else if(instruction[5:0] == 6'b000011)
				begin
				
					result = $signed (rtCon) >>> instruction[10:6];
				
				end
			
		reg_write_signal = 1'b1;
		  
end


endmodule