module likeALU(out, inp_A, inp_B, select);

input [4:0]inp_A, inp_B;
input [1:0] select;
output [4:0]out;
wire [4:0]c_out;

mux_4x1 first(out[0], c_out[0], select[1], select[0], inp_A[0], inp_B[0], 1'b0);
mux_4x1 second(out[1], c_out[1], select[1], select[0], inp_A[1], inp_B[1], c_out[0]);
mux_4x1 third(out[2], c_out[2], select[1], select[0], inp_A[2], inp_B[2], c_out[1]);
mux_4x1 fourth(out[3], c_out[3], select[1], select[0], inp_A[3], inp_B[3], c_out[2]);
mux_4x1 fifth(out[4], c_out[4], select[1], select[0], inp_A[4], inp_B[4],c_out[3]);

endmodule

