import sbt.*

object Dependencies {

  private val playVersion      = "play-30"
  private val bootstrapVersion = "9.4.0"

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc" %% "ui-test-runner"               % "0.37.0"         % Test,
    "uk.gov.hmrc" %% s"bootstrap-test-$playVersion" % bootstrapVersion % Test
  )

}
