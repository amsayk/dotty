package test

import scala.reflect.io._
import dotty.tools.dotc.util._
import dotty.tools.dotc.core._
import dotty.tools.dotc.parsing._
import Tokens._, Parsers._
import UntypedTrees.untpd._
import org.junit.Test

class ParserTest extends DottyTest {

  def parse(name: String): Tree = parse(new PlainFile(name))

  var parsed = 0

  def parse(file: PlainFile): Tree = {
    //println("***** parsing " + file)
    val source = new SourceFile(file)
    val parser = new Parser(source)
    val tree = parser.parse()
    parsed += 1
    tree
  }

  def parseDir(path: String): Unit = parseDir(Directory(path))

  def parseDir(dir: Directory): Unit = {
    for (f <- dir.files)
      if (f.name.endsWith(".scala")) parse(new PlainFile(f))
    for (d <- dir.dirs)
      parseDir(d.path)
  }
/*
  @Test
  def parseList() = {
    println(System.getProperty("user.dir"))
    parse("src/dotty/tools/dotc/core/Symbols.scala")
    parse("src/dotty/tools/dotc/core/Types.scala")
  }

  @Test
  def parseDotty() = {
    parseDir("src")
  }*/

  @Test
  def parseScala() = {
    parseDir("/Users/odersky/workspace/scala/src")
  }

}