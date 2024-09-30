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

import org.openqa.selenium.WebElement
import uk.gov.hmrc.configuration.TestEnvironment
import org.openqa.selenium.By

object CookieSettingsPage extends BasePage {
  val url: String = TestEnvironment.url("tracking-consent-frontend") + "/cookie-settings"

  val useMeasurementCookies: String          = "Use cookies that measure my website use"
  def clickUseMeasurementCookies(): Unit     = click(labelByPartialText(useMeasurementCookies))
  def useMeasurementCookiesInput: WebElement = findBy(inputByLabelPartialText(useMeasurementCookies))

  val doNotUseMeasurementCookies: String          = "Do not use cookies that measure my website use"
  def clickDoNotUseMeasurementCookies(): Unit     = click(labelByPartialText(doNotUseMeasurementCookies))
  def doNotUseMeasurementCookiesInput: WebElement = findBy(inputByLabelPartialText(doNotUseMeasurementCookies))

  val useSettingsCookies: String          = "Use cookies that remember my settings on services"
  def clickUseSettingsCookies(): Unit     = click(labelByPartialText(useSettingsCookies))
  def useSettingsCookiesInput: WebElement = findBy(inputByLabelPartialText(useSettingsCookies))

  val doNotUseSettingsCookies: String          = "Do not use cookies that remember my settings on services"
  def clickDoNotUseSettingsCookies(): Unit     = click(labelByPartialText(doNotUseSettingsCookies))
  def doNotUseSettingsCookiesInput: WebElement = findBy(inputByLabelPartialText(doNotUseSettingsCookies))

  def clickSubmitButton(): Unit      = click(buttonByPartialText("Save changes"))
  def clickWelshSubmitButton(): Unit = click(buttonByPartialText("Cadw newidiadau"))

  def switchLanguageToWelsh(): Unit = click(By.partialLinkText("Cymraeg"))
}
