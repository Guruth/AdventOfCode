defmodule Helpers do


  def loadFile(name)do
    File.read("data/" <> name)
  end

end