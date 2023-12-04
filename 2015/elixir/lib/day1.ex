defmodule Day1 do
  def part1(data) do
    data_list = to_charlist(data)
    count_stories(data_list, 0)
  end

  defp count_stories([char | rest], count) when char == ?( do
    count_stories(rest, count + 1)
  end

  defp count_stories([char | rest], count) when char == ?) do
    count_stories(rest, count - 1)
  end

  defp count_stories([], count) do
    count
  end
end
