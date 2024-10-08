/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.pages

import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.{By, JavascriptExecutor, WebDriver, WebElement}
import uk.gov.hmrc.selenium.component.PageObject
import uk.gov.hmrc.selenium.webdriver.Driver
import org.openqa.selenium.By.tagName

import java.util.logging.Level.SEVERE
import scala.jdk.CollectionConverters._

trait BasePage extends PageObject with LazyLogging {
  val url: String

  def driver(): WebDriver = Driver.instance

  def goTo(): Unit = get(url)

  def deleteAllCookies(): Unit = driver().manage().deleteAllCookies()

  def refreshPage(): Unit = driver().navigate().refresh()

  def getUserConsentCookie(): String = driver().manage().getCookieNamed("userConsent").getValue

  def windowLoadedGtmEvent(): AnyRef = findDataLayerEvent("gtm.load")

  def optimizelyOptOutEvent(): AnyRef = findOptimizelyOptOutEvent(true)

  def optimizelyOptInEvent(): AnyRef = findOptimizelyOptOutEvent(false)

  def measurementAllowedGtmEvent(): AnyRef = findDataLayerEvent("trackingConsentMeasurementAccepted")

  def settingsAllowedGtmEvent(): AnyRef = findDataLayerEvent("trackingConsentSettingsAccepted")

  def getH1Text(): String = findBy(By.cssSelector("h1")).getText

  def getH2Text(): String = findBy(tagName("h2")).getText

  def getH3Text(): String = findBy(By.cssSelector("h3")).getText

  def getConfirmationMessageBanner(): String = findBy(By.cssSelector("h2.govuk-notification-banner__title")).getText

  def consoleErrors(): Seq[String] = {
    val logs = driver().manage().logs().get("browser").asScala
    logs
      .filter(_.getLevel == SEVERE)
      .map(_.getMessage)
      .toSeq
  }

  def renderedHtml(): String =
    driver()
      .asInstanceOf[JavascriptExecutor]
      .executeScript("return document.getElementsByTagName('html')[0].outerHTML")
      .toString

  def findBy(by: By): WebElement = driver().findElement(by)

  def labelByPartialText(partialText: String): By =
    By.xpath(s"""//label[contains(text(),'$partialText')]""")

  def inputByLabelPartialText(partialText: String): By =
    By.xpath(s"""//input[@id=(//label[contains(text(), '$partialText')]/@for)]""")

  def buttonByPartialText(partialText: String): By =
    By.xpath(s"""//button[contains(text(),'$partialText')]""")

  def findOptimizelyOptOutEvent(isOptOut: java.lang.Boolean): AnyRef =
    driver()
      .asInstanceOf[JavascriptExecutor]
      .executeScript(
        "return window.optimizely.find(({type, isOptOut}) => type === 'optOut' && isOptOut === arguments[0])",
        isOptOut
      )

  def findDataLayerEvent(event: String): AnyRef =
    driver()
      .asInstanceOf[JavascriptExecutor]
      .executeScript("return window.dataLayer.find(({event}) => event === arguments[0])", event)
}
