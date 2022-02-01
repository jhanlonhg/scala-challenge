package org.hanlonjohn23

object ArgParser {
  case class Arguments(addends: Seq[Int] = List.empty)

  val parser = new scopt.OptionParser[Arguments]("java -jar q01-add.jar") {
    opt[Seq[Int]]('a', "addends")
      .required()
      .valueName("Comma-separated list of numbers to be added")
      .action((value, arguments) => arguments.copy(addends = value))
  }
}
