defmodule Day1Test do
  use ExUnit.Case, async: true
  import Day1

  test "Part 1 with test data" do
    assert part1("()()") == 0
    assert part1("(())") == 0
    assert part1("(((") == 3
    assert part1("(()(()(") == 3
    assert part1("())") == -1
    assert part1("))(") == -1
    assert part1(")())())") == -3
    assert part1(")))") == -3
  end

  test "Part 1 with real data" do
    {:ok, data} = Helpers.loadFile("day1")
    result = part1(data)
    IO.puts(result)

    assert true
  end
end
