defmodule HelpersTest do
  use ExUnit.Case, async: true

  test "loads the files contents" do
    {:ok, contents} = Helpers.loadFile("test")

    assert contents == "Test"
  end
end
