module mux_4x1(bigO, c_out, s1, s2, inA, inB, c_in);

input s1, s2, inA, inB, c_in;
output bigO, c_out;
wire and_wire, or_wire, xor_wire, add_wire, o1, o2, o3, o4;

and_process run_and(and_wire, inA, inB);
full_adder run_add (add_wire, c_out, inA, inB, c_in);
or_process run_or(or_wire, inA, inB);
xor_process run_xor(xor_wire, inA, inB);

and output_for_first(o1, !s1, !s2, and_wire);
and output_for_second(o2, !s1, s2, add_wire);
and output_for_third(o3, s1, !s2, or_wire);
and output_for_fourth(o4, s1, s2, xor_wire);
or last_output(bigO, o1, o2, o3, o4);

endmodule