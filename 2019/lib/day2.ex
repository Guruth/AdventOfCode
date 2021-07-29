defmodule Day2 do
	
	def execute() do
		data = [1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,10,19,1,19,5,23,1,6,23,27,1,27,5,31,2,31,10,35,2,35,6,39,1,39,5,43,2,43,9,47,1,47,6,51,1,13,51,55,2,9,55,59,1,59,13,63,1,6,63,67,2,67,10,71,1,9,71,75,2,75,6,79,1,79,5,83,1,83,5,87,2,9,87,91,2,9,91,95,1,95,10,99,1,9,99,103,2,103,6,107,2,9,107,111,1,111,5,115,2,6,115,119,1,5,119,123,1,123,2,127,1,127,9,0,99,2,0,14,0]
		corruptData = [3,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,10,19,1,19,5,23,1,6,23,27,1,27,5,31,2,31,10,35,2,35,6,39,1,39,5,43,2,43,9,47,1,47,6,51,1,13,51,55,2,9,55,59,1,59,13,63,1,6,63,67,2,67,10,71,1,9,71,75,2,75,6,79,1,79,5,83,1,83,5,87,2,9,87,91,2,9,91,95,1,95,10,99,1,9,99,103,2,103,6,107,2,9,107,111,1,111,5,115,2,6,115,119,1,5,119,123,1,123,2,127,1,127,9,0,99,2,0,14,0]

		executeProgram(data)
	end

	defp executeProgram(data) do
		executeProgram({:OK, data}, 0)
	end

	defp probeInputs(data, i, j) when j < 99 do
		
		probeInputs(data, i , j + 1)
	end

	defp probeInputs(data, i , j) when j == 99 do
		
		probeInputs(data , i+1, 0)
	end

	defp probeInputs(data,i ,j) when i == 99 && j == 99 do 

		executeProgram(data)
	end
	
	defp executeProgram({:OK, data}, pos) do
		instructionResult = executeInstruction(data, Enum.slice(data, pos * 4,  4))
		executeProgram(instructionResult, pos+1)
	end
	
	defp executeProgram({:END, [result | _]}, _) do
		IO.puts("Program finished")
		IO.puts("#{result}")
	end
	
	defp executeProgram({:ERR, [result | _]}, _) do
		IO.puts("Program error")
		IO.puts("#{result}")
	end
	
	defp executeInstruction(data, [opcode, valPos1, valPos2, retPos]) when opcode == 1 do
		val1 = data |> Enum.at(valPos1)
		val2 = data |> Enum.at(valPos2)
		{:OK, List.update_at(data, retPos, fn(_) -> val1 + val2 end)}
	end
	
	defp executeInstruction(data, [opcode, valPos1, valPos2, retPos]) when opcode == 2 do
		val1 = data |> Enum.at(valPos1)
		val2 = data |> Enum.at(valPos2)
		{:OK, List.update_at(data, retPos, fn(_) -> val1 * val2 end)}
	end
	
	defp executeInstruction(data, [opcode | _]) when opcode == 99 do
		{:END, data}
	end
	
	defp executeInstruction(data, [_ | _]) do
		{:ERR, data}
	end
	
	defp executeInstruction(data, []) do
		{:END, data}
	end
	
end