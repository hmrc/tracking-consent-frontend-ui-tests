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

import uk.gov.hmrc.driver.BrowserDriver
import org.openqa.selenium.{By, Cookie, JavascriptExecutor, WebElement}
import uk.gov.hmrc.selenium.component.PageObject
import uk.gov.hmrc.selenium.webdriver.Driver
import org.openqa.selenium.By.tagName

import java.util.logging.Level.SEVERE
import scala.jdk.CollectionConverters._

trait BasePage extends PageObject with BrowserDriver {
  val url: String

  def goTo(): Unit = get(url)

  def userConsentCookie: Cookie = driver().manage().getCookieNamed("userConsent")

  def windowLoadedGtmEvent: AnyRef = findDataLayerEvent("gtm.load")

  def optimizelyOptOutEvent: AnyRef = findOptimizelyOptOutEvent(true)

  def optimizelyOptInEvent: AnyRef = findOptimizelyOptOutEvent(false)

  def measurementAllowedGtmEvent: AnyRef = findDataLayerEvent("trackingConsentMeasurementAccepted")

  def settingsAllowedGtmEvent: AnyRef = findDataLayerEvent("trackingConsentSettingsAccepted")

  def h1Element: WebElement = findBy(By.cssSelector("h1"))

  def h2Text(): String = Driver.instance.findElement(tagName("h2")).getText

  def h3Element: WebElement = findBy(By.cssSelector("h3"))

  def confirmationMessageBanner: WebElement = findBy(By.cssSelector("h2.govuk-notification-banner__title"))

  def consoleErrors: Seq[String] = {
    val logs = driver().manage().logs().get("browser").asScala
    logs
      .filter(_.getLevel == SEVERE)
      .map(_.getMessage)
      .toSeq
  }

  def renderedHtml: String =
    driver()
      .asInstanceOf[JavascriptExecutor]
      .executeScript("return document.getElementsByTagName('html')[0].outerHTML")
      .toString
}
